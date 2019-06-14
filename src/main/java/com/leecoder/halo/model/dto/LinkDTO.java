package com.leecoder.halo.model.dto;

import lombok.Data;
import com.leecoder.halo.model.dto.base.OutputConverter;
import com.leecoder.halo.model.entity.Link;

/**
 * Link output dto.
 *
 * @author ryanwang
 * @date : 2019/3/21
 */
@Data
public class LinkDTO implements OutputConverter<LinkDTO, Link> {

    private Integer id;

    private String name;

    private String url;

    private String logo;

    private String description;

    private String team;
}
