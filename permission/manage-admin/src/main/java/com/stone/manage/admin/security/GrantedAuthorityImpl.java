package com.stone.manage.admin.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 * @author wjj
 * @date 2020/6/5
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
