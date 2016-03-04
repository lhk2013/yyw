package com.yyw.fangkuaiyi.security.mgt;

import com.yyw.fangkuaiyi.codec.request.HttpRequest;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lins on 16-3-3.
 */
public interface FkyHandler {
    Map<String, HttpRequest> FKY_HANDLERS = DefaultHandler.createInstanceMap();

    default Object newInstance(String key, NettyHttpRequest request){
        HttpRequest fky = null;
        try {
            fky = (HttpRequest) (FKY_HANDLERS.get(key)).clone();
            fky.setRequest(request);
            return fky;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }finally {
            return fky;
        }
    }


    /**
     * 测试 代码
     */
    Map<String,Env> testEnv = new HashMap<String,Env>(){{
            put("/index/test",new Env("/index/test","anon",null,null,0));
            put("/auth/login",new Env("/auth/login","login",null,null,1));
            put("/auth/tet",new Env("/auth/tet","jwt","system",null,3));
        }};

}
