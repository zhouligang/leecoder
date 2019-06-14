package com.leecoder.halo.service;

import org.springframework.data.domain.Page;
import com.leecoder.halo.model.dto.LogDTO;
import com.leecoder.halo.model.entity.Log;
import com.leecoder.halo.service.base.CrudService;

/**
 * Log service.
 *
 * @author johnniang
 */
public interface LogService extends CrudService<Log, Long> {

    /**
     * Lists latest logs.
     *
     * @param top top number must not be less than 0
     * @return a page of latest logs
     */
    Page<LogDTO> pageLatest(int top);
}
