package com.mtd.security.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Wooce Yang on 2016/1/6.
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    //��Ⱥ�п��ܻᵼ�³�����֤���5�ε�������ΪAtomicIntegerֻ�ܱ�֤���ڵ㲢��
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(EhCacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 4) {
            System.out.println("username: " + username + " tried to login more than 4 times in period");
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 4 times in period"
            ); }
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}