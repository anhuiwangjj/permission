package com.stone.manage.admin.util;

import java.util.UUID;

/**
 * 密码工具类
 * @author wjj
 * @date 2020/6/2
 */
public class PasswordUtils {

    /**
     * 密码匹配
     * @param salt 盐
     * @param rawPass 明文
     * @param encPass 密文
     */
    public static boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncoder(salt).matches(encPass,rawPass);
    }

    /**
     * 明文密码加密
     * @param rawPass
     * @param salt
     * @return
     */
    public static String encode(String rawPass, String salt) {
        return new PasswordEncoder(salt).encode(rawPass);
    }

    /**
     * 获取加密盐
     * @return
     */
    public static String getSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}
