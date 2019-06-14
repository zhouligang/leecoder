package com.leecoder.halo.model.enums.converter;

import com.leecoder.halo.model.enums.PostType;

import javax.persistence.Converter;

/**
 * PostType converter.
 *
 * @author johnniang
 * @date 3/27/19
 */
@Converter(autoApply = true)
public class PostTypeConverter extends AbstractConverter<PostType, Integer> {

    public PostTypeConverter() {
        super(PostType.class);
    }
}
