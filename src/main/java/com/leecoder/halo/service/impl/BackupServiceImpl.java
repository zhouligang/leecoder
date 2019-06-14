package com.leecoder.halo.service.impl;

import cn.hutool.core.io.IoUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.leecoder.halo.model.dto.post.BasePostDetailDTO;
import com.leecoder.halo.service.BackupService;
import com.leecoder.halo.service.PostService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Backup service implementation.
 *
 * @author johnniang
 * @date 19-4-26
 */
@Service
public class BackupServiceImpl implements BackupService {

    private final PostService postService;

    public BackupServiceImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public BasePostDetailDTO importMarkdowns(MultipartFile file) throws IOException {

        // Read markdown content.
        String markdown = IoUtil.read(file.getInputStream(), StandardCharsets.UTF_8);

        // TODO sheet import

        return postService.importMarkdown(markdown, file.getOriginalFilename());
    }
}
