package com.leecoder.halo.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leecoder.halo.model.dto.UserDTO;
import com.leecoder.halo.service.UserService;

/**
 * Portal user controller.
 *
 * @author johnniang
 * @date 4/3/19
 */
@RestController("ApiContentUserController")
@RequestMapping("/api/content/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile")
    @ApiOperation("Gets blogger profile")
    public UserDTO getProfile() {
        return userService.getCurrentUser().map(user -> (UserDTO) new UserDTO().convertFrom(user)).get();
    }
}
