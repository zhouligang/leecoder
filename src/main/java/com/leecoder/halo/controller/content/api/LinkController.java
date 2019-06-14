package com.leecoder.halo.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leecoder.halo.model.dto.LinkDTO;
import com.leecoder.halo.model.vo.LinkTeamVO;
import com.leecoder.halo.service.LinkService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Portal link controller.
 *
 * @author johnniang
 * @author ryanwang
 * @date 4/3/19
 */
@RestController("ApiContentLinkController")
@RequestMapping("/api/content/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    @ApiOperation("List all links")
    public List<LinkDTO> listLinks(@SortDefault(sort = "createTime", direction = DESC) Sort sort) {
        return linkService.listDtos(sort);
    }

    @GetMapping("team_view")
    @ApiOperation("List all links with team view")
    public List<LinkTeamVO> listTeamVos(Sort sort) {
        return linkService.listTeamVos(sort);
    }
}
