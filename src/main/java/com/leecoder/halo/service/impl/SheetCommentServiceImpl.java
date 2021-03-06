package com.leecoder.halo.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.leecoder.halo.exception.BadRequestException;
import com.leecoder.halo.exception.NotFoundException;
import com.leecoder.halo.model.dto.post.BasePostMinimalDTO;
import com.leecoder.halo.model.entity.Sheet;
import com.leecoder.halo.model.entity.SheetComment;
import com.leecoder.halo.model.vo.SheetCommentWithSheetVO;
import com.leecoder.halo.repository.SheetCommentRepository;
import com.leecoder.halo.repository.SheetRepository;
import com.leecoder.halo.service.OptionService;
import com.leecoder.halo.service.SheetCommentService;
import com.leecoder.halo.service.UserService;
import com.leecoder.halo.utils.ServiceUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sheet comment service implementation.
 *
 * @author johnniang
 * @date 19-4-24
 */
@Service
public class SheetCommentServiceImpl extends BaseCommentServiceImpl<SheetComment> implements SheetCommentService {

    private final SheetCommentRepository sheetCommentRepository;

    private final SheetRepository sheetRepository;

    public SheetCommentServiceImpl(SheetCommentRepository sheetCommentRepository,
                                   OptionService optionService,
                                   UserService userService,
                                   ApplicationEventPublisher eventPublisher,
                                   SheetRepository sheetRepository) {
        super(sheetCommentRepository, optionService, userService, eventPublisher);
        this.sheetCommentRepository = sheetCommentRepository;
        this.sheetRepository = sheetRepository;
    }

    @Override
    public void validateTarget(Integer sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId)
                .orElseThrow(() -> new NotFoundException("该页面不存在或已删除").setErrorData(sheetId));

        if (sheet.getDisallowComment()) {
            throw new BadRequestException("该页面已被禁止评论").setErrorData(sheetId);
        }
    }

    @Override
    public List<SheetCommentWithSheetVO> convertToWithPostVo(List<SheetComment> sheetComments) {
        if (CollectionUtils.isEmpty(sheetComments)) {
            return Collections.emptyList();
        }

        Set<Integer> sheetIds = ServiceUtils.fetchProperty(sheetComments, SheetComment::getPostId);

        Map<Integer, Sheet> sheetMap = ServiceUtils.convertToMap(sheetRepository.findAllById(sheetIds), Sheet::getId);

        return sheetComments.stream()
                .filter(comment -> sheetMap.containsKey(comment.getPostId()))
                .map(comment -> {
                    SheetCommentWithSheetVO sheetCmtWithPostVO = new SheetCommentWithSheetVO().convertFrom(comment);
                    sheetCmtWithPostVO.setSheet(new BasePostMinimalDTO().convertFrom(sheetMap.get(comment.getPostId())));
                    return sheetCmtWithPostVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<SheetCommentWithSheetVO> convertToWithPostVo(Page<SheetComment> sheetCommentPage) {
        Assert.notNull(sheetCommentPage, "Sheet comment page must not be null");

        return new PageImpl<>(convertToWithPostVo(sheetCommentPage.getContent()), sheetCommentPage.getPageable(), sheetCommentPage.getTotalElements());

    }
}
