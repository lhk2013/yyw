package com.yyw.fangkuaiyi.security.mgt;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by lins on 16-3-3.
 */
public class Env {

    public Env(String uri,
            String basics,
            String roles,
            String permissions,
            int weight){
        this.uri = uri;
        this.basics = basics;
        this.roles =roles;
        this.perms = permissions;
        this.weight = weight;
    }

    private String uri;
    private String basics;
    private String roles;
    private String perms;
    private int weight;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBasics() {
        return basics;
    }

    public void setBasics(String basics) {
        this.basics = basics;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
