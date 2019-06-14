package com.leecoder.halo.model.dto;

import lombok.Data;
import com.leecoder.halo.model.enums.Mode;

/**
 * Theme controller.
 *
 * @author ryanwang
 * @date : 2019/5/4
 */
@Data
public class EnvironmentDTO {

    private String database;

    private long startTime;

    private String version;

    private Mode mode;
}
