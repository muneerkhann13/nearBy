/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.cache;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cd.nearby.json.response.Offer;
import com.cd.nearby.model.OfferTable;

import redis.clients.jedis.Jedis;

/**
 *
 * @author Shiv
 */
@Component
public class RedisStorage {

	@Value("#{ @environment['redis.offerTable'] ?: 'US' }")
	private String offerTable;

	@Value("#{ @environment['redis.baseCategory'] ?: 'US' }")
	private String baseCategory;

	@Value("#{ @environment['redis.derivedCategory'] ?: 'US' }")
	private String derivedCategory;

	@Autowired
	private JedisFactory jedisFactory;

	@Autowired
	private JedisUpload jedisUpload;

	@Autowired
	private Offer offer;

	/**
	 *
	 * @param imageString
	 * @return
	 * @throws NullPointerException
	 * @author prince
	 */

	public List<OfferTable> fetchAlloffers() {

		// create new list of Tag type
		List<OfferTable> tags = new ArrayList<OfferTable>();

		// get a resource from pool of redis cache
		Jedis jedis = jedisFactory.getCacheResource().getResource();

		// if no products in cache the upload from database
		if (jedis.keys(jedisFactory.generateKey(offerTable, "*")).isEmpty()) {
			jedisUpload.uploadAll();
		}

		// fetch all keys of all product caches
		Set<String> offerTables = jedis.keys(jedisFactory.generateKey(offerTable, "*"));

		// fetch all tags related data from cache for all products
		for (String elem : offerTables) {
			List<String> auxTags = jedis.hmget(elem, "id", "mdn", "discription", "title", "expiry_time");
			boolean ok = tags.add(new OfferTable(Long.parseLong(auxTags.get(0)),auxTags.get(1),Timestamp.valueOf(auxTags.get(4)),
                                auxTags.get(2), auxTags.get(3) ) );
		}
		jedis.close();
		return tags;
	}

}
