package com.yyw.fangkuaiyi.security.handlers;

import com.nimbusds.jose.JWSObject;
import com.yyw.fangkuaiyi.codec.request.JwtHttpRequest;
import com.yyw.fangkuaiyi.codec.response.StandardResponse;
import com.yyw.fangkuaiyi.codec.response.StandardResult;
import com.yyw.fangkuaiyi.security.jwt.JWTAuthenticationToken;
import com.yyw.fangkuaiyi.util.JsonMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;

import static org.jboss.resteasy.util.HttpHeaderNames.AUTHORIZATION;

/**
 * Created by lins on 16-2-23.
 */
public class JwtHandler extends SimpleChannelInboundHandler<JwtHttpRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JwtHttpRequest jwt) throws Exception {
        StandardResult result = null;
        NettyHttpRequest request = (NettyHttpRequest) jwt.getRequest();
        if (isLoggedAttempt(request)) {
            AuthenticationToken token = createToken(request);

            if (token!=null){
                try {
                    Subject e = SecurityUtils.getSubject();
                    e.login(token);

                    channelHandlerContext.fireChannelRead(request);
                    return;
                } catch (AuthenticationException var5) {
                    var5.printStackTrace();
                }

            }
            result = new StandardResult(401,"Restore JWTToken Failure!");
        }else {
            result = new StandardResult(401, "HttpHeader.AUTHORIZATION is null!");
        }

        LOGGER.error(result.toString());
        StandardResponse.write(channelHandlerContext,result);
        return;
    }


    protected AuthenticationToken createToken(NettyHttpRequest request) throws IOException {
        String jwtToken = getAuthzHeader(request);
        if (jwtToken != null) {
            return createToken(jwtToken);
        }
        return null;
    }

    protected boolean isLoggedAttempt(NettyHttpRequest request) {
        String authzHeader = getAuthzHeader(request);
        return authzHeader != null;
    }

    protected String getAuthzHeader(NettyHttpRequest request) {
        return request.getHttpHeaders().getHeaderString(AUTHORIZATION);
    }

    public JWTAuthenticationToken createToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String decrypted = jwsObject.getPayload().toString();

            JSONObject jsonObj = new JSONObject(decrypted);
            Object o = jsonObj.get("sub");

            return new JWTAuthenticationToken(o, token);
        } catch (ParseException |JSONException ex) {
            throw new AuthenticationException(ex);
        }

    }

}
