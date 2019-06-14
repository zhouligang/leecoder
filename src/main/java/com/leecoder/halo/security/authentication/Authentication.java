package com.leecoder.halo.security.authentication;

import org.springframework.lang.NonNull;
import com.leecoder.halo.security.support.UserDetail;

/**
 * Authentication.
 *
 * @author johnniang
 */
public interface Authentication {

    /**
     * Get user detail.
     *
     * @return user detail
     */
    @NonNull
    UserDetail getDetail();
}
