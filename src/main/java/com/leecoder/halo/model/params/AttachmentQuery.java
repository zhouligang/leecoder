package com.leecoder.halo.model.params;

import lombok.Data;
import com.leecoder.halo.model.enums.AttachmentType;

/**
 * Attachment query params.
 *
 * @author ryanwang
 * @date : 2019/04/18
 */
@Data
public class AttachmentQuery {

    /**
     * Keyword.
     */
    private String keyword;

    private String mediaType;

    private AttachmentType attachmentType;
}
