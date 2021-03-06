package com.leecoder.halo.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.leecoder.halo.model.dto.base.OutputConverter;
import com.leecoder.halo.model.entity.Category;

import java.util.Date;

/**
 * Category output dto.
 *
 * @author johnniang
 * @date 3/19/19
 */
@Data
@ToString
@EqualsAndHashCode
public class CategoryDTO implements OutputConverter<CategoryDTO, Category> {

    private Integer id;

    private String name;

    private String slugName;

    private String description;

    private Integer parentId;

    private Date createTime;
}
