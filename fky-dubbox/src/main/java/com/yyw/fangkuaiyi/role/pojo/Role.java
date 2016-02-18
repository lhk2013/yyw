package com.yyw.fangkuaiyi.role.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by lins on 16-2-18.
 */
public class Role {

    private Long id;
    private String roleAliasName;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleAliasName() {
        return roleAliasName;
    }

    public void setRoleAliasName(String roleAliasName) {
        this.roleAliasName = roleAliasName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
