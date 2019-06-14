package com.leecoder.halo.model.dto.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.leecoder.halo.model.dto.base.OutputConverter;
import com.leecoder.halo.model.entity.BasePost;
import com.leecoder.halo.model.enums.PostStatus;
import com.leecoder.halo.model.enums.PostType;

import java.util.Date;

/**
 * Base post minimal output dto.
 *
 * @author johnniang
 */
@Data
@ToString
@EqualsAndHashCode
public class BasePostMinimalDTO implements OutputConverter<BasePostMinimalDTO, BasePost> {

    private Integer id;

    private String title;

    private PostStatus status;

    private String url;

    private PostType type;

    private Date updateTime;

    private Date createTime;

    private Date editTime;
}
