package com.mascix;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
public class CacheHelper {

    private static final String CACHE_NAME = "cache";
    private CacheManager cacheManager;
    private Cache<Object, Object> cache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(1000))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(1, TimeUnit.DAYS))).build();
        cache = cacheManager.createCache(CACHE_NAME, cacheConfiguration);
    }

    public Cache<Integer, Integer> getSquareNumberCacheFromCacheManager() {
        return cacheManager.getCache(CACHE_NAME, Integer.class, Integer.class);
    }

    public Object get(Object k) {
        return cache.get(k);
    }

    public void put(Object k, Object v) {
        cache.put(k, v);
    }
}