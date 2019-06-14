package com.leecoder.halo.security.context;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.leecoder.halo.security.authentication.Authentication;

/**
 * Security context implementation.
 *
 * @author johnniang
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SecurityContextImpl implements SecurityContext {

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

}
