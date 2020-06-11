package com.stone.manage.admin.util;

import com.stone.manage.admin.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Security工具类
 * @author wjj
 * @date 2020/6/4
 */
public class SecurityUtils {

    /**
     * 登录认证
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager){
        JwtAuthenticationToken token = new JwtAuthenticationToken(username,password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功，将认证信息存储到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌进行验证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request){
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUserName(){
        String username = null;
        Authentication authentication = getAuthentication();
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (Objects.nonNull(principal) && principal instanceof UserDetails){
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     * @param authentication
     * @return
     */
    public static String getUserName(Authentication authentication){
        String username = null;
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (Objects.nonNull(principal) && principal instanceof UserDetails){
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = null;
        if (Objects.nonNull(SecurityContextHolder.getContext())){
            authentication = SecurityContextHolder.getContext().getAuthentication();
        }
        return authentication;
    }
}
