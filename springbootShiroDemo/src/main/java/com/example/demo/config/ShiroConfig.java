package com.example.demo.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Client;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.session.SessionStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.shiro.PmsRealm;

import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;

import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
@Configuration
public class ShiroConfig {
	@Value("${sso.cas.client.callbackUrl}")
	private String callbackUrl;
	@Value("${sso.cas.server.loginUrl}")
	private String loginUrl;
	@Value("${sso.cas.server.prefixUrl}/")
	private String prefixUrl;
	@Value("${sso.cas.client.successUrl}")
	private String defaultUrl;
	
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/callback", "callbackFilter");
        filterChainDefinitionMap.put("/index", "casSecurityFilter");
       
        filterChainDefinitionMap.put("/** ", "casSecurityFilter");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("casSecurityFilter", casSecurityFilter());
        filters.put("callbackFilter", callbackFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;

    }

    private Filter callbackFilter() {
    	CallbackFilter callbackFilter=new CallbackFilter();
    	callbackFilter.setConfig(authcConfig());
    	callbackFilter.setDefaultUrl(defaultUrl);
    	callbackFilter.setMultiProfile(true);
    	return callbackFilter;
	}

    private Filter casSecurityFilter() {
    	SecurityFilter casSecurityFilter=new SecurityFilter();
    	casSecurityFilter.setClients("CasClient");
    	casSecurityFilter.setConfig(authcConfig());
		return casSecurityFilter;
	}

    @Bean
    public Config authcConfig() {
    	Config config=new Config(casClient());
    	config.setSessionStore(new ShiroSessionStore());
		return config;
	}


    @Bean
    public CasClient casClient() {
    	CasClient casClient=new CasClient(casConfig());
    	casClient.setCallbackUrl(callbackUrl);
		return casClient;
	}

    @Bean
    public CasConfiguration casConfig() {
		CasConfiguration casConfig=new CasConfiguration();
		casConfig.setLoginUrl(loginUrl);
		casConfig.setPrefixUrl(prefixUrl);
		return casConfig;
	}

	@Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
//        securityManager.setCacheManager(shiroEhcacheManager());
        securityManager.setSubjectFactory(new Pac4jSubjectFactory());
        return securityManager;
    }

//	@Bean
//    public CacheManager shiroEhcacheManager() {
//    	EhCacheManager shiroEhcacheManager=new EhCacheManager();
//    	shiroEhcacheManager.setCacheManager(manager);
//    	return null;
//	}

	@Bean
    public Realm myRealm() {
		PmsRealm realm=new PmsRealm();
		realm.setCredentialsMatcher(new SimpleCredentialsMatcher());
		return realm;
	}

}
