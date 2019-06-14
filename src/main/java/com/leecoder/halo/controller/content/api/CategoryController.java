package com.leecoder.halo.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.model.dto.CategoryDTO;
import com.leecoder.halo.model.dto.post.BasePostSimpleDTO;
import com.leecoder.halo.model.entity.Category;
import com.leecoder.halo.model.entity.Post;
import com.leecoder.halo.service.CategoryService;
import com.leecoder.halo.service.PostCategoryService;
import com.leecoder.halo.service.PostService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Category portal controller.
 *
 * @author ryanwang
 * @date 6/9/19
 */
@RestController("ApiContentCategoryController")
@RequestMapping("/api/content/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final PostCategoryService postCategoryService;

    private final PostService postService;

    public CategoryController(CategoryService categoryService,
                              PostCategoryService postCategoryService,
                              PostService postService) {
        this.categoryService = categoryService;
        this.postCategoryService = postCategoryService;
        this.postService = postService;
    }

    @GetMapping
    @ApiOperation("Lists categories")
    public List<? extends CategoryDTO> listCategories(@SortDefault(sort = "updateTime", direction = DESC) Sort sort,
                                                      @RequestParam(name = "more", required = false, defaultValue = "false") Boolean more) {
        if (more) {
            return postCategoryService.listCategoryWithPostCountDto(sort);
        }
        return categoryService.convertTo(categoryService.listAll(sort));
    }

    @GetMapping("{slugName}/posts")
    @ApiOperation("Lists posts by category slug name")
    public Page<BasePostSimpleDTO> listPostsBy(@PathVariable("slugName") String slugName,
                                               @PageableDefault(sort = "updateTime", direction = DESC) Pageable pageable) {
        // Get category by slug name
        Category category = categoryService.getBySlugName(slugName);

        Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), pageable);
        return postService.convertToSimple(postPage);
    }
}
