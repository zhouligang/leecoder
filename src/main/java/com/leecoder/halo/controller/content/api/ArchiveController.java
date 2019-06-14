package com.leecoder.halo.controller.content.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leecoder.halo.model.vo.ArchiveMonthVO;
import com.leecoder.halo.model.vo.ArchiveYearVO;
import com.leecoder.halo.service.PostService;

import java.util.List;

/**
 * Archive portal controller.
 *
 * @author johnniang
 * @date 4/2/19
 */
@RestController("ApiContentArchiveController")
@RequestMapping("/api/content/archives")
public class ArchiveController {

    private final PostService postService;

    public ArchiveController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("years")
    public List<ArchiveYearVO> listYearArchives() {
        return postService.listYearArchives();
    }

    @GetMapping("months")
    public List<ArchiveMonthVO> listMonthArchives() {
        return postService.listMonthArchives();
    }
}
