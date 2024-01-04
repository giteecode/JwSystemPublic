package com.zxw.System.config;

import com.zxw.security.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class shiroConfig {


    // 配置自定义Realm
    @Bean
    public CustomRealm customRealm() {
//        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(credentialsMatcher()); //配置使用哈希密码匹配
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    // 配置url过滤器
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // DefaultFilter枚举类中
        chainDefinition.addPathDefinition("/kaptcha/create", "anon");
        chainDefinition.addPathDefinition("/logout", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/api/**", "anon");
//        chainDefinition.addPathDefinition("/**", "anon");
        // 静态资源访问设置
        chainDefinition.addPathDefinition("/web/js/**", "anon");
        chainDefinition.addPathDefinition("/web/css/**", "anon");
        chainDefinition.addPathDefinition("/web/images/**", "anon");
        chainDefinition.addPathDefinition("/web/font/**", "anon");
        chainDefinition.addPathDefinition("/web/iview/**", "anon");
        chainDefinition.addPathDefinition("/web/layui/**", "anon");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    // 设置用于匹配密码的CredentialsMatcher
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);  // 散列算法，这里使用更安全的sha256算法
        credentialsMatcher.setStoredCredentialsHexEncoded(false);  // 数据库存储的密码字段使用HEX还是BASE64方式加密
        credentialsMatcher.setHashIterations(1024);  // 散列迭代次数
        return credentialsMatcher;
    }

    // 配置security并设置userReaml，避免xxxx required a bean named 'authorizer' that could not be found.的报错
    @Bean
    public SessionsSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }
}