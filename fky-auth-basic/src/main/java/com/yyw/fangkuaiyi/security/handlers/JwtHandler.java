package com.yyw.fangkuaiyi.security.handlers;

import com.nimbusds.jose.JWSObject;
import com.yyw.fangkuaiyi.codec.request.Jwt;
import com.yyw.fangkuaiyi.codec.response.StandardResponse;
import com.yyw.fangkuaiyi.codec.response.StandardResult;
import com.yyw.fangkuaiyi.security.jwt.JWTAuthenticationToken;
import com.yyw.fangkuaiyi.security.mgt.FkyHandler;
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

import static com.yyw.fangkuaiyi.security.mgt.DefaultHandler.roles;
import static org.jboss.resteasy.util.HttpHeaderNames.AUTHORIZATION;

/**
 * Created by lins on 16-2-23.
 */
public class JwtHandler extends SimpleChannelInboundHandler<Jwt> implements FkyHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Jwt jwt) throws Exception {
        StandardResult result = null;
        NettyHttpRequest request = (NettyHttpRequest) jwt.getRequest();
        if (request.getHttpMethod().equals("OPTIONS")){channelHandlerContext.fireChannelRead(request);
            return;}
        if (isLoggedAttempt(request)) {
            try {
                AuthenticationToken token = createToken(request);

                if (token!=null){
                    Subject e = SecurityUtils.getSubject();
                    e.login(token);

                    channelHandlerContext.fireChannelRead(this.newInstance(roles.name(),request));
                    return;
                }
            } catch (AuthenticationException var5) {
                result = new StandardResult(401,"Restore JWTToken Failure! CAUSE："+var5.getMessage());
            }
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
            //Invalid serialized plain/JWS/JWE object: Missing part delimiters
            throw new AuthenticationException(ex);
        }

    }

}
