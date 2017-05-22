package com.cd.nearby.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 *
 * @author Shiv
 */
@Component
public class JedisFactory {

	final private String cacheServerAddress;
	final private int cacheServerPort;
	final private JedisPool jedisPool;

	@Value("#{ @environment['redis.dbName'] ?: 'US' }")
	private String redisDbName;

	@Autowired
	public JedisFactory(@Value("#{ @environment['redis.port'] ?: 6379 }") int cacheServerPort,
			@Value("#{ @environment['redis.address'] ?: 'localhost' }") String cacheServerAddress) {
		this.cacheServerPort = cacheServerPort;
		this.cacheServerAddress = cacheServerAddress;
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		jedisPool = new JedisPool(poolConfig, cacheServerAddress, cacheServerPort, Protocol.DEFAULT_TIMEOUT, null);
	}

	public JedisPool getCacheResource() {
		return jedisPool;
	}

	public String generateKey(String... namespace) {
		String key = redisDbName + ":";
		for (String s : namespace) {
			key = key + s + ":";
		}
		return key.substring(0, key.length() - 1);
	}

	public String splitKey(String key) {
		String[] splittedKey = key.split(":");
		return splittedKey[splittedKey.length - 1];
	}
}
