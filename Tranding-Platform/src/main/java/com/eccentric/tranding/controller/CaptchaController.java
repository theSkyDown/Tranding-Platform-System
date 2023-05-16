package com.eccentric.tranding.controller;


import com.eccentric.tranding.pojo.common.Ret;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码
 * @author eccentric
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    /**
     * 返回图形验证码
     * @param request
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void getCaptchaImg(HttpServletRequest request, HttpServletResponse res) throws IOException {
        CaptchaUtil.out(request,res);
    }



}
