package com.leecoder.halo.security.handler;

import com.leecoder.halo.exception.HaloException;
import com.leecoder.halo.exception.NotInstallException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Content authentication failure handler.
 *
 * @author johnniang
 * @date 19-5-6
 */
public class ContentAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onFailure(HttpServletRequest request, HttpServletResponse response, HaloException exception) throws IOException, ServletException {
        if (exception instanceof NotInstallException) {
            response.sendRedirect(request.getContextPath() + "/install");
            return;
        }

        // Forward to error
        request.getRequestDispatcher(request.getContextPath() + "/error").forward(request, response);
    }
}