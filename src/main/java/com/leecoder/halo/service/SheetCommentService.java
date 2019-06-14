package com.leecoder.halo.service;

import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.leecoder.halo.model.entity.SheetComment;
import com.leecoder.halo.model.vo.SheetCommentWithSheetVO;
import com.leecoder.halo.service.base.BaseCommentService;

import java.util.List;

/**
 * Sheet comment service interface.
 *
 * @author johnniang
 * @date 19-4-24
 */
public interface SheetCommentService extends BaseCommentService<SheetComment> {

    @NonNull
    List<SheetCommentWithSheetVO> convertToWithPostVo(@Nullable List<SheetComment> sheetComments);

    @NonNull
    Page<SheetCommentWithSheetVO> convertToWithPostVo(@NonNull Page<SheetComment> sheetCommentPage);
}
