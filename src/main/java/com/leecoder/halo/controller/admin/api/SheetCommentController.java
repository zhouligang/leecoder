package com.leecoder.halo.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.model.dto.BaseCommentDTO;
import com.leecoder.halo.model.entity.SheetComment;
import com.leecoder.halo.model.enums.CommentStatus;
import com.leecoder.halo.model.params.CommentQuery;
import com.leecoder.halo.model.params.SheetCommentParam;
import com.leecoder.halo.model.vo.SheetCommentWithSheetVO;
import com.leecoder.halo.service.SheetCommentService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Sheet comment controller.
 *
 * @author johnniang
 * @date 19-4-25
 */
@RestController
@RequestMapping("/api/admin/sheets/comments")
public class SheetCommentController {

    private final SheetCommentService sheetCommentService;

    public SheetCommentController(SheetCommentService sheetCommentService) {
        this.sheetCommentService = sheetCommentService;
    }

    @GetMapping
    public Page<SheetCommentWithSheetVO> pageBy(@PageableDefault(sort = "updateTime", direction = DESC) Pageable pageable,
                                                CommentQuery commentQuery) {
        Page<SheetComment> sheetCommentPage = sheetCommentService.pageBy(commentQuery, pageable);
        return sheetCommentService.convertToWithPostVo(sheetCommentPage);
    }

    @GetMapping("latest")
    public List<SheetCommentWithSheetVO> listLatest(@RequestParam(name = "top", defaultValue = "10") int top,
                                                    @RequestParam(name = "status", required = false) CommentStatus status) {
        Page<SheetComment> sheetCommentPage = sheetCommentService.pageLatest(top, status);
        return sheetCommentService.convertToWithPostVo(sheetCommentPage.getContent());
    }

    @PostMapping
    @ApiOperation("Creates a comment (new or reply)")
    public BaseCommentDTO createBy(@RequestBody SheetCommentParam commentParam) {
        SheetComment createdComment = sheetCommentService.createBy(commentParam);
        return sheetCommentService.convertTo(createdComment);
    }

    @PutMapping("{commentId:\\d+}/status/{status}")
    @ApiOperation("Updates comment status")
    public BaseCommentDTO updateStatusBy(@PathVariable("commentId") Long commentId,
                                         @PathVariable("status") CommentStatus status) {
        // Update comment status
        SheetComment updatedSheetComment = sheetCommentService.updateStatus(commentId, status);
        return sheetCommentService.convertTo(updatedSheetComment);
    }

    @DeleteMapping("{commentId:\\d+}")
    @ApiOperation("Deletes comment permanently and recursively")
    public BaseCommentDTO deleteBy(@PathVariable("commentId") Long commentId) {
        SheetComment deletedSheetComment = sheetCommentService.removeById(commentId);
        return sheetCommentService.convertTo(deletedSheetComment);
    }
}
