package com.leecoder.halo.model.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import com.leecoder.halo.model.dto.base.InputConverter;
import com.leecoder.halo.model.entity.Category;
import com.leecoder.halo.utils.HaloUtils;
import com.leecoder.halo.utils.SlugUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Category param.
 *
 * @author johnniang
 * @date 3/21/19
 */
@Data
public class CategoryParam implements InputConverter<Category> {

    /**
     * Category name.
     */
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 50, message = "分类名称的字符长度不能超过 {max}")
    private String name;

    /**
     * Category slug name.
     */
    @Size(max = 50, message = "分类别名的字符长度不能超过 {max}")
    private String slugName;

    /**
     * Category description.
     */
    @Size(max = 100, message = "分类描述的字符长度不能超过 {max}")
    private String description;

    /**
     * Parent category.
     */
    private Integer parentId = 0;

    @Override
    public Category convertTo() {
        // Handle default value
        if (StringUtils.isBlank(slugName)) {
            slugName = SlugUtils.slugify(name);

            if (StringUtils.isBlank(slugName)) {
                slugName = HaloUtils.initializeUrlIfBlank(slugName);
            }
        }

        return InputConverter.super.convertTo();
    }
}
