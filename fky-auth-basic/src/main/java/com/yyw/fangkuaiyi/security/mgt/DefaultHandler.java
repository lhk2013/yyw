package com.yyw.fangkuaiyi.security.mgt;

import com.yyw.fangkuaiyi.codec.request.*;
import org.apache.shiro.util.ClassUtils;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lins on 16-3-3.
 */
public enum DefaultHandler {

    anon(Anon.class),
    login(Authc.class),
    jwt(Jwt.class),
    stateless(Stateless.class),
    logout(Logout.class),
    perms(Permissions.class),
    roles(Roles.class);

    private final Class<? extends HttpRequest> handlerClass;

    private DefaultHandler(Class<? extends HttpRequest> handlerClass) {
        this.handlerClass = handlerClass;
    }

    public HttpRequest newInstance(NettyHttpRequest request) {
        return (HttpRequest) ClassUtils.newInstance(this.handlerClass,request);
    }
    public HttpRequest newInstance() {
        return (HttpRequest) ClassUtils.newInstance(this.handlerClass);
    }

    public Class<? extends HttpRequest> getHandlerClass() {
        return this.handlerClass;
    }

    public static Map<String, HttpRequest> createInstanceMap() {
        LinkedHashMap handlers = new LinkedHashMap(values().length);
        DefaultHandler[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            DefaultHandler defaultHandler = arr$[i$];
            HttpRequest handler = defaultHandler.newInstance();

            handlers.put(defaultHandler.name(), handler);
        }

        return handlers;
    }
}
