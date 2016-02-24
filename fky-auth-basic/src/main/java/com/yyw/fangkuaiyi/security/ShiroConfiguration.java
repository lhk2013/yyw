package com.yyw.fangkuaiyi.security;

import com.google.common.collect.Lists;
import com.yyw.fangkuaiyi.security.jwt.realm.JWTRealm;
import com.yyw.fangkuaiyi.security.shiro.realm.StandardRealm;
import com.yyw.fangkuaiyi.security.utils.constants.Securitys;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lins on 16-1-11.
 */
@Configuration
public class ShiroConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    private static Map<String, Filter> filtersMap = new LinkedHashMap<>();


    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm realm(EhCacheManager cacheManager) {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Securitys.HASH_ALGORITHM);
        credentialsMatcher.setHashIterations(Securitys.HASH_INTERATIONS);

        StandardRealm securityRealm = new StandardRealm();
        securityRealm.setCredentialsMatcher(credentialsMatcher);
        securityRealm.setCacheManager(cacheManager);
        return securityRealm;
    }

    @Bean(name = "jwtrealm")
    public AuthorizingRealm jwtrealm(EhCacheManager cacheManager) {
        SimpleCredentialsMatcher tokenMatcher = new SimpleCredentialsMatcher();

        JWTRealm jwtRealm = new JWTRealm();
        jwtRealm.setCredentialsMatcher(tokenMatcher);
        return jwtRealm;
    }

    @Bean(name = "sessionManager")
    public DefaultSessionManager defaultSessionManager() {
        DefaultSessionManager dsm = new DefaultSessionManager();
        dsm.setSessionValidationSchedulerEnabled(false);
        return dsm;
    }

    @Bean(name = "subjectFactory")
    public StatelessDefaultSubjectFactory subjectFactory() {
        StatelessDefaultSubjectFactory sdsf = new StatelessDefaultSubjectFactory();
        return sdsf;
    }

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(EhCacheManager cacheManager, AuthorizingRealm realm, AuthorizingRealm jwtrealm, DefaultSessionManager defaultSessionManager, StatelessDefaultSubjectFactory subjectFactory) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        Collection realms = Lists.newArrayList();
        realms.add(jwtrealm);
        realms.add(realm);

        dwsm.setRealms(realms);
        dwsm.setSessionManager(defaultSessionManager);
//        dwsm.setSubjectFactory(subjectFactory);
        dwsm.setCacheManager(cacheManager);
        return dwsm;
    }

}
