package com.leecoder.halo.controller.content;

import cn.hutool.core.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.leecoder.halo.model.entity.Category;
import com.leecoder.halo.model.entity.Post;
import com.leecoder.halo.model.vo.PostListVO;
import com.leecoder.halo.service.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author ryanwang
 * @date : 2019/3/20
 */
@Controller
@RequestMapping(value = "/categories")
public class ContentCategoryController {

    private final CategoryService categoryService;

    private final ThemeService themeService;

    private final PostCategoryService postCategoryService;

    private final PostService postService;

    private final OptionService optionService;

    public ContentCategoryController(CategoryService categoryService,
                                     ThemeService themeService,
                                     PostCategoryService postCategoryService,
                                     PostService postService, OptionService optionService) {
        this.categoryService = categoryService;
        this.themeService = themeService;
        this.postCategoryService = postCategoryService;
        this.postService = postService;
        this.optionService = optionService;
    }

    /**
     * Render category list page
     *
     * @return template path: themes/{theme}/categories.ftl
     */
    @GetMapping
    public String categories(Model model) {
        model.addAttribute("is_categories", true);
        return themeService.render("categories");
    }

    /**
     * Render post list page by category
     *
     * @param model    model
     * @param slugName slugName
     * @return template path: themes/{theme}/category.ftl
     */
    @GetMapping(value = "{slugName}")
    public String categories(Model model,
                             @PathVariable("slugName") String slugName) {
        return this.categories(model, slugName, 1, Sort.by(DESC, "createTime"));
    }

    /**
     * Render post list page by category
     *
     * @param model    model
     * @param slugName slugName
     * @param page     current page number
     * @return template path: themes/{theme}/category.ftl
     */
    @GetMapping("{slugName}/page/{page}")
    public String categories(Model model,
                             @PathVariable("slugName") String slugName,
                             @PathVariable("page") Integer page,
                             @SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        // Get category by slug name
        final Category category = categoryService.getBySlugName(slugName);

        final Pageable pageable = PageRequest.of(page - 1, optionService.getPostPageSize(), sort);
        Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), pageable);
        Page<PostListVO> posts = postService.convertToListVo(postPage);
        final int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);

        model.addAttribute("is_category", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        model.addAttribute("category", category);
        return themeService.render("category");
    }
}
