package com.elend.gate.admin.service.impl;

import org.mybatis.caches.memcached.MemcachedCache;
import org.springframework.stereotype.Service;

import com.elend.gate.admin.constant.CacheKeyConstant;
import com.elend.gate.admin.service.RecMemcacheService;


@Service
public class RecMemcacheServiceImpl implements RecMemcacheService {

	@Override
	public void firstRequest(String userName) {
		clearCache(userName);
	}

	@Override
	public void logOut(String userName) {
		clearCache(userName);
	}
	
	@Override
	public void welcome(String userName) {
		clearCache(userName);
	}
	/**
	 * 用户退出与第一次访问的时候，清理缓存
	 * @param user_id
	 */
	private void clearCache(String userName){
		MemcachedCache cache = new MemcachedCache(CacheKeyConstant.MEMCACHED_PRIVILEGE_ID);
		{
			String key = CacheKeyConstant.PRIVILEGE_RESOURCE_KEY_PREFIX + userName;
			cache.removeObject(key);
		}
		{
			String key = CacheKeyConstant.PRIVILEGE_MENU_KEY_PREFIX + userName;
			cache.removeObject(key);			
		}
		{
			String key = CacheKeyConstant.PRIVILEGE_USER_INFO_KEY_PREFIX + userName;
			cache.removeObject(key);			
		}		
	}

}
