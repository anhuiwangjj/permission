package com.stone.manage.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.stone.manage.admin.model.dto.LoginRequestDto;
import com.stone.manage.admin.model.dto.SysUserResponseDto;
import com.stone.manage.admin.security.JwtAuthenticationToken;
import com.stone.manage.admin.service.SysUserService;
import com.stone.manage.admin.util.PasswordUtils;
import com.stone.manage.admin.util.SecurityUtils;
import com.stone.manage.core.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static com.stone.manage.core.util.ResultUtil.handleSuccess;

/**
 * 登录controller
 * @author wjj
 * @date 2020/6/4
 */
@Api(value = "登录操作接口",tags = {"登录操作接口"})
@RestController
public class SysLoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "获取验证码",notes = "获取验证码")
    @GetMapping(value = "captcha",produces = MediaType.IMAGE_JPEG_VALUE)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Cache-Control","no-store, no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //保存验证码至session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @ApiOperation(value = "登录接口",notes = "登录接口")
    @PostMapping("login")
    public ResultDto<JwtAuthenticationToken> login(@RequestBody LoginRequestDto login,HttpServletRequest request){
        String username = login.getUsername();
        String password = login.getPassword();
        String captcha = login.getCaptcha();
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Assert.notNull(kaptcha,"验证码已失效");
        Assert.isTrue(Objects.equals(kaptcha,captcha),"验证码不正确");
        SysUserResponseDto sysUser = sysUserService.findByUserName(username);
        Assert.notNull(sysUser,"账号不存在");
        Assert.isTrue(PasswordUtils.matches(sysUser.getSalt(), password, sysUser.getPassword()),"密码不正确");
        Assert.isTrue(!Objects.equals(sysUser.getStatus(),"0"),"账号已被锁定，请联系管理员");
        JwtAuthenticationToken token = SecurityUtils.login(request,username,password,authenticationManager);
        return handleSuccess(token);
    }
}
