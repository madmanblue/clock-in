package com.madman.future.month.march.fourth.day1.distributedLocks;

import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisLock {

	
	
	public static boolean setnx(String key, String val) {
		return  false;
		
	}
	
	public static void main(String[] args) {
		RedisLock.setnx("sjf", "666");
	}
}

class RedisPool{
	private static JedisPool pool;
	
	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(20);
		config.setMinIdle(20);
		config.setBlockWhenExhausted(true);
		pool = new JedisPool(config, "192.168.120.115", 6379, 2000);
	}
	
	static {
		initPool();
	}
	
	public static Jedis getJedis() {
		return pool.getResource();
	}
	
	public static void returnResource(Jedis jedis) {
	
	}
}
