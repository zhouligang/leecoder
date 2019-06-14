package com.leecoder.halo;

import io.swagger.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

@SpringCloudApplication
@EnableAsync
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class HaloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaloApplication.class, args);
    }

    @GetMapping("/v1/test")
    @ApiOperation(value = "测试文章", tags = {"文章管理"})
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数错误"),
            @ApiResponse(code = 404, message = "接口路径不正确")
    })
    @ResponseBody
    public String test() {
        return "success";
    }

}
