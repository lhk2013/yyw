package com.yyw.fangkuaiyi.account.pojo;

import com.google.common.collect.Lists;
import com.yyw.fangkuaiyi.role.pojo.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lins on 16-2-18.
 */
public class Account implements Serializable {
    private Long id;
    private String aliasName;
    private String loginName;
    private String hashPassword;
    private String salt;
    private String email;
    private boolean islock;
    private List<Role> roles = Lists.newArrayList(); // 有序的关联对象集合

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean islock() {
        return islock;
    }

    public void setIslock(boolean islock) {
        this.islock = islock;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
