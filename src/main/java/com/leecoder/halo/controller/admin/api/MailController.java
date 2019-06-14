package com.leecoder.halo.controller.admin.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leecoder.halo.model.params.MailParam;
import com.leecoder.halo.model.support.BaseResponse;
import com.leecoder.halo.service.MailService;

import javax.validation.Valid;

/**
 * Mail controller.
 *
 * @author johnniang
 * @date 19-5-7
 */
@RestController
@RequestMapping("/api/admin/mails")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("test")
    public BaseResponse testMail(@Valid @RequestBody MailParam mailParam) {
        mailService.sendMail(mailParam.getTo(), mailParam.getSubject(), mailParam.getContent());
        return BaseResponse.ok("发送成功");
    }
}
