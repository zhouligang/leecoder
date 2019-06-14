package com.leecoder.halo.service;

import org.springframework.lang.NonNull;
import com.leecoder.halo.model.dto.EnvironmentDTO;
import com.leecoder.halo.model.dto.StatisticDTO;
import com.leecoder.halo.model.params.LoginParam;
import com.leecoder.halo.security.token.AuthToken;

/**
 * Admin service.
 *
 * @author johnniang
 * @author ryanwang
 * @date 19-4-29
 */
public interface AdminService {

    /**
     * Expired seconds.
     */
    int ACCESS_TOKEN_EXPIRED_SECONDS = 24 * 3600;

    int REFRESH_TOKEN_EXPIRED_DAYS = 30;

    String ACCESS_TOKEN_CACHE_PREFIX = "halo.admin.access_token.";

    String REFRESH_TOKEN_CACHE_PREFIX = "halo.admin.refresh_token.";

    /**
     * Authenticates.
     *
     * @param loginParam login param must not be null
     * @return authentication token
     */
    @NonNull
    AuthToken authenticate(@NonNull LoginParam loginParam);

    /**
     * Clears authentication.
     */
    void clearToken();

    /**
     * Get system counts.
     *
     * @return count dto
     */
    @NonNull
    StatisticDTO getCount();

    /**
     * Get system environments
     *
     * @return environments
     */
    @NonNull
    EnvironmentDTO getEnvironments();

    /**
     * Refreshes token.
     *
     * @param refreshToken refresh token must not be blank
     * @return authentication token
     */
    @NonNull
    AuthToken refreshToken(@NonNull String refreshToken);

    /**
     * Updates halo admin assets.
     */
    void updateAdminAssets();
}
