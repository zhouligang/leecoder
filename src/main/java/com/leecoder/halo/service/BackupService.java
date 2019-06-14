package com.leecoder.halo.service;

import org.springframework.web.multipart.MultipartFile;
import com.leecoder.halo.model.dto.post.BasePostDetailDTO;

import java.io.IOException;

/**
 * Backup service interface.
 *
 * @author johnniang
 * @date 19-4-26
 */
public interface BackupService {

    /**
     * Backup posts and sheets
     *
     * @param file file
     * @return post info
     */
    BasePostDetailDTO importMarkdowns(MultipartFile file) throws IOException;
}
