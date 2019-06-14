package com.leecoder.halo.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.cache.lock.CacheLock;
import com.leecoder.halo.model.dto.BaseCommentDTO;
import com.leecoder.halo.model.dto.post.BasePostDetailDTO;
import com.leecoder.halo.model.dto.post.BasePostSimpleDTO;
import com.leecoder.halo.model.entity.Post;
import com.leecoder.halo.model.entity.PostComment;
import com.leecoder.halo.model.enums.CommentStatus;
import com.leecoder.halo.model.enums.PostStatus;
import com.leecoder.halo.model.params.PostCommentParam;
import com.leecoder.halo.model.vo.BaseCommentVO;
import com.leecoder.halo.model.vo.BaseCommentWithParentVO;
import com.leecoder.halo.model.vo.CommentWithHasChildrenVO;
import com.leecoder.halo.service.OptionService;
import com.leecoder.halo.service.PostCommentService;
import com.leecoder.halo.service.PostService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Portal post controller.
 *
 * @author johnniang
 * @date 4/2/19
 */
@RestController("ApiContentPostController")
@RequestMapping("/api/content/posts")
public class PostController {

    private final PostService postService;

    private final PostCommentService postCommentService;

    private final OptionService optionService;

    public PostController(PostService postService,
                          PostCommentService postCommentService,
                          OptionService optionService) {
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.optionService = optionService;
    }

    @GetMapping
    @ApiOperation("Lists posts")
    public Page<BasePostSimpleDTO> pageBy(@PageableDefault(sort = "updateTime", direction = DESC) Pageable pageable) {
        Page<Post> postPage = postService.pageBy(PostStatus.PUBLISHED, pageable);
        return postService.convertToSimple(postPage);
    }

    @PostMapping(value = "search")
    @ApiOperation("Lists posts by keyword")
    public Page<BasePostSimpleDTO> pageBy(@RequestParam(value = "keyword") String keyword,
                                          @PageableDefault(sort = "createTime", direction = DESC) Pageable pageable) {
        Page<Post> postPage = postService.pageBy(keyword, pageable);
        return postService.convertToSimple(postPage);
    }

    @GetMapping("{postId:\\d+}")
    @ApiOperation("Gets a post")
    public BasePostDetailDTO getBy(@PathVariable("postId") Integer postId,
                                   @RequestParam(value = "formatDisabled", required = false, defaultValue = "true") Boolean formatDisabled,
                                   @RequestParam(value = "sourceDisabled", required = false, defaultValue = "false") Boolean sourceDisabled) {
        BasePostDetailDTO detailDTO = postService.convertToDetail(postService.getById(postId));

        if (formatDisabled) {
            // Clear the format content
            detailDTO.setFormatContent(null);
        }

        if (sourceDisabled) {
            // Clear the original content
            detailDTO.setOriginalContent(null);
        }

        return detailDTO;
    }

    @GetMapping("{postId:\\d+}/comments/top_view")
    public Page<CommentWithHasChildrenVO> listTopComments(@PathVariable("postId") Integer postId,
                                                          @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                          @SortDefault(sort = "createTime", direction = DESC) Sort sort) {

        Page<CommentWithHasChildrenVO> result = postCommentService.pageTopCommentsBy(postId, CommentStatus.PUBLISHED, PageRequest.of(page, optionService.getCommentPageSize(), sort));

        return postCommentService.filterIpAddress(result);
    }


    @GetMapping("{postId:\\d+}/comments/{commentParentId:\\d+}/children")
    public List<BaseCommentDTO> listChildrenBy(@PathVariable("postId") Integer postId,
                                               @PathVariable("commentParentId") Long commentParentId,
                                               @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        // Find all children comments
        List<PostComment> postComments = postCommentService.listChildrenBy(postId, commentParentId, CommentStatus.PUBLISHED, sort);
        // Convert to base comment dto

        List<BaseCommentDTO> result = postCommentService.convertTo(postComments);

        return postCommentService.filterIpAddress(result);
    }

    @GetMapping("{postId:\\d+}/comments/tree_view")
    @ApiOperation("Lists comments with tree view")
    public Page<BaseCommentVO> listCommentsTree(@PathVariable("postId") Integer postId,
                                                @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Page<BaseCommentVO> result = postCommentService.pageVosBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
        return postCommentService.filterIpAddress(result);
    }

    @GetMapping("{postId:\\d+}/comments/list_view")
    @ApiOperation("Lists comment with list view")
    public Page<BaseCommentWithParentVO> listComments(@PathVariable("postId") Integer postId,
                                                      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                      @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        Page<BaseCommentWithParentVO> result = postCommentService.pageWithParentVoBy(postId, PageRequest.of(page, optionService.getCommentPageSize(), sort));
        return postCommentService.filterIpAddress(result);
    }

    @PostMapping("comments")
    @ApiOperation("Comments a post")
    @CacheLock(autoDelete = false, traceRequest = true)
    public BaseCommentDTO comment(@RequestBody PostCommentParam postCommentParam) {
        return postCommentService.convertTo(postCommentService.createBy(postCommentParam));
    }

    @PostMapping("{postId:\\d+}/likes")
    @ApiOperation("Likes a post")
    public void like(@PathVariable("postId") Integer postId) {
        postService.increaseLike(postId);
    }
}
