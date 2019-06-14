package com.leecoder.halo.model.dto.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.leecoder.halo.model.enums.PostCreateFrom;
import com.leecoder.halo.model.enums.PostType;

/**
 * Base page simple output dto.
 *
 * @author johnniang
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasePostSimpleDTO extends BasePostMinimalDTO {

    private PostType type;

    private String summary;

    private String thumbnail;

    private Long visits = 0L;

    private Boolean disallowComment;

    private String template;

    private Integer topPriority = 0;

    private PostCreateFrom createFrom;

    private Long likes = 0L;
}
