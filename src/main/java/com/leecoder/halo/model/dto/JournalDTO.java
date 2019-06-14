package com.leecoder.halo.model.dto;

import lombok.Data;
import com.leecoder.halo.model.dto.base.OutputConverter;
import com.leecoder.halo.model.entity.Journal;

import java.util.Date;

/**
 * Journal dto.
 *
 * @author johnniang
 * @date 19-4-24
 */
@Data
public class JournalDTO implements OutputConverter<JournalDTO, Journal> {

    private Integer id;

    private String content;

    private Long likes;

    private Date createTime;
}
