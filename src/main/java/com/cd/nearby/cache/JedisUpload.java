/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cd.nearby.dao.MerchantDao;
import com.cd.nearby.dao.OfferTableDao;
import com.cd.nearby.model.OfferTable;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 *
 * @author Shiv
 */
@Component
public class JedisUpload {

	@Value("#{ @environment['redis.offerTable'] ?: 'US' }")
	private String offerTable;

	// @Value("#{ @environment['redis.baseCategory'] ?: 'US' }")
	// private String baseCategory;
	//
	// @Value("#{ @environment['redis.derivedCategory'] ?: 'US' }")
	// private String derivedCategory;

	@Autowired
	private JedisFactory jedisFactory;

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private OfferTableDao offerTableDao;

	public void uploadAll() {

		uploadOffers();
	}

	public void uploadOffers() throws NullPointerException {
		// read product table from database
		List<OfferTable> product = offerTableDao.getAllOffer();

		// get a resource from pool of redis cache
		Jedis jedis = jedisFactory.getCacheResource().getResource();
		Pipeline pipeline = jedis.pipelined();
		pipeline.multi();

		for (OfferTable entry : product) {
			String productKey = jedisFactory.generateKey(offerTable, String.format("%d", entry.getOffer_id()));
			Map<String, String> auxProduct = new HashMap<String, String>();
			auxProduct.put("id", String.format("%d", entry.getOffer_id()));
			auxProduct.put("mdn",  entry.getMdn());
			auxProduct.put("discription", entry.getDiscription());
			auxProduct.put("title", entry.getTitle());
			auxProduct.put("expiry_time", entry.getExpiry().toString());

			// update product cache
			pipeline.hmset(productKey, auxProduct);
			pipeline.expire(productKey, 21600);

		}

		redis.clients.jedis.Response<List<Object>> response = pipeline.exec();

		pipeline.sync();
		jedis.close();
	}

	//
	// public void uploadCategoryDetails() throws NullPointerException {
	// // read category table form database
	// List<CategoryDetails> categoryList = (List<CategoryDetails>)
	// categoryDetailsDao.findAll();
	//
	// // get a resource from pool of redis cache
	// Jedis jedis = jedisFactory.getCacheResource().getResource();
	//
	// String baseCategoryKey = jedisFactory.generateKey(baseCategory);
	//
	// for (CategoryDetails entry : categoryList) {
	// if (entry.isIsBase()) {
	// jedis.sadd(baseCategoryKey,
	// jedisFactory.generateKey(derivedCategory, String.format("%d",
	// entry.getCategoryId())));
	// jedis.expire(baseCategoryKey, 21600);
	// } else {
	// String derivedCategoryKey = jedisFactory.generateKey(derivedCategory,
	// String.format("%d", entry.getBaseId()));
	// jedis.sadd(derivedCategoryKey,
	// jedisFactory.generateKey(derivedCategory, String.format("%d",
	// entry.getCategoryId())));
	// jedis.expire(derivedCategoryKey, 21600);
	// }
	// }
	// jedis.close();
	// }
	//
	// public void uploadDenominationDetails() {
	// // read category table form database
	// List<DenominationDetails> denomList = (List<DenominationDetails>)
	// denominationDetailsDao.findAll();
	//
	// // get a resource from pool of redis cache
	// Jedis jedis = jedisFactory.getCacheResource().getResource();
	// Pipeline pipeline = jedis.pipelined();
	// pipeline.multi();
	//
	// for (DenominationDetails elem : denomList) {
	// String key = jedisFactory.generateKey(elem.getProductId(),
	// String.format("%d", elem.getDenominationType()));
	//
	// Map<String, String> denomData = new HashMap<String, String>();
	// denomData.put("product_id", elem.getProductId());
	// denomData.put("denomination_type", String.format("%d",
	// elem.getDenominationType()));
	// denomData.put("denomination", String.format("%d",
	// elem.getDenomination()));
	// denomData.put("max_value", String.format("%d", elem.getMaxValue()));
	// denomData.put("min_value", String.format("%d", elem.getMinValue()));
	//
	// // upload denom details
	// pipeline.hmset(key, denomData);
	// pipeline.expire(key, 21600);
	// }
	// redis.clients.jedis.Response<List<Object>> response = pipeline.exec();
	//
	// pipeline.sync();
	// jedis.close();
	// }
}
