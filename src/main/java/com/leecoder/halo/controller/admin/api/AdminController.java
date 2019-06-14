package com.leecoder.halo.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.cache.lock.CacheLock;
import com.leecoder.halo.model.dto.EnvironmentDTO;
import com.leecoder.halo.model.dto.StatisticDTO;
import com.leecoder.halo.model.params.LoginParam;
import com.leecoder.halo.security.token.AuthToken;
import com.leecoder.halo.service.AdminService;

import javax.validation.Valid;

/**
 * Admin controller.
 *
 * @author johnniang
 * @date 3/19/19
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Get some statistics about the count of posts, the count of comments, etc.
     *
     * @return counts
     */
    @GetMapping("counts")
    @ApiOperation("Gets count info")
    public StatisticDTO getCount() {
        return adminService.getCount();
    }

    @GetMapping("environments")
    @ApiOperation("Gets environments info")
    public EnvironmentDTO getEnvironments() {
        return adminService.getEnvironments();
    }

    @PostMapping("login")
    @ApiOperation("Login")
    @CacheLock(autoDelete = false)
    public AuthToken auth(@RequestBody @Valid LoginParam loginParam) {
        return adminService.authenticate(loginParam);
    }

    @PostMapping("logout")
    @ApiOperation("Logs out (Clear session)")
    @CacheLock(autoDelete = false)
    public void logout() {
        adminService.clearToken();
    }

    @PostMapping("refresh/{refreshToken}")
    @ApiOperation("Refreshes token")
    @CacheLock(autoDelete = false)
    public AuthToken refresh(@PathVariable("refreshToken") String refreshToken) {
        return adminService.refreshToken(refreshToken);
    }

    @PutMapping("halo-admin")
    @ApiOperation("Updates halo-admin manually")
    public void updateAdmin() {
        adminService.updateAdminAssets();
    }

}
