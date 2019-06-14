package com.leecoder.halo.model.vo;

import lombok.Data;
import lombok.ToString;
import com.leecoder.halo.model.dto.LinkDTO;

import java.util.List;

/**
 * Link team vo.
 *
 * @author ryanwang
 * @date : 2019/3/22
 */
@Data
@ToString
public class LinkTeamVO {

    private String team;

    private List<LinkDTO> links;
}
