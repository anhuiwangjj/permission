package com.stone.manage.admin.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Objects;

/**
 * 密码加密
 * @author wjj
 * @date 2020/6/2
 */
public class PasswordEncoder {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private final static String MD5 = "MD5";
    private final static String SHA = "SHA";

    private Object salt;

    private String algorithm;

    public PasswordEncoder(Object salt){
        this(salt,MD5);
    }

    public PasswordEncoder(Object salt,String algorithm){
        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * 密码加密
     * @param rawPass
     * @return
     */
    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 密码匹配验证
     * @param encPass 密文
     * @param rawPass 明文
     * @return
     */
    public boolean matches(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);
        return Objects.equals(pass1,pass2);
    }

    /**
     * 密码加盐
     * @param password
     * @return
     */
    private String mergePasswordAndSalt(String password) {
        if (StringUtils.isBlank(password)){
            password = "";
        }
        if (Objects.isNull(salt) || Objects.equals(salt,"")){
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 将字节数组转为16进制字符串
     * @param b
     * @return
     */
    private String byteArrayToHexString(byte[] b) {
        StringBuilder strb = new StringBuilder();
        for (int i=0;i< b.length; i++){
            strb.append(byteToHexString(b[i]));
        }
        return strb.toString();
    }

    /**
     * 将字节转为16进制
     * @param b
     * @return
     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n <0) {
            n = 256 +b;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
