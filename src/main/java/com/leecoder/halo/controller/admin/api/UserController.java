package com.leecoder.halo.controller.admin.api;

import org.springframework.web.bind.annotation.*;
import com.leecoder.halo.model.dto.UserDTO;
import com.leecoder.halo.model.entity.User;
import com.leecoder.halo.model.params.PasswordParam;
import com.leecoder.halo.model.params.UserParam;
import com.leecoder.halo.model.support.BaseResponse;
import com.leecoder.halo.model.support.UpdateCheck;
import com.leecoder.halo.service.UserService;
import com.leecoder.halo.utils.ValidationUtils;

import javax.validation.Valid;

/**
 * User controller.
 *
 * @author johnniang
 * @date 3/19/19
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profiles")
    public UserDTO getProfile(User user) {
        return new UserDTO().convertFrom(user);
    }

    @PutMapping("profiles")
    public UserDTO updateProfile(@RequestBody UserParam userParam, User user) {
        // Validate the user param
        ValidationUtils.validate(userParam, UpdateCheck.class);

        // Update properties
        userParam.update(user);

        // Update user and convert to dto
        return new UserDTO().convertFrom(userService.update(user));
    }

    @PutMapping("profiles/password")
    public BaseResponse updatePassword(@RequestBody @Valid PasswordParam passwordParam, User user) {
        userService.updatePassword(passwordParam.getOldPassword(), passwordParam.getNewPassword(), user.getId());
        return BaseResponse.ok("密码修改成功");
    }
}
