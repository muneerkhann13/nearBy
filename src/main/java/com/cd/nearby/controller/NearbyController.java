/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.controller;

import com.cd.nearby.common.ExceptionHandling;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cd.nearby.dao.MerchantDao;
import com.cd.nearby.dao.OfferTableDao;
import com.cd.nearby.json.request.Request;
import com.cd.nearby.json.response.Merchants;
import com.cd.nearby.json.response.Response;
import com.cd.nearby.model.Merchant;

@RestController
public class NearbyController extends ExceptionHandling{

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	OfferTableDao offerDao;

	@RequestMapping(value = { "/merchant" }, method = RequestMethod.POST, produces = "application/json")
	public Response getMerchant(@RequestBody Request request) {

		Response response = new Response();
		List<Merchant> merchants = merchantDao.getAllMerchant();
		ArrayList<Merchants> merchantsResponse = new ArrayList<Merchants>();

		response.setCode(0);
		response.setDesc("SUCCESS");
		System.out.println(merchants.size());
		for (Merchant merchant : merchants) {

			Merchants m = new Merchants();

			m.setCategory(merchant.getCategory());
			m.setRating(merchant.getRating());
			m.setMerchantMdn(merchant.getMdn());
			m.setAddress(merchant.getAddress());
			m.setShopName(merchant.getShop_name());
			m.setLat(merchant.getLat());
			m.setLng(merchant.getLng());

			merchantsResponse.add(m);
		}
		response.setMerchants(merchantsResponse);
		// System.out.println(merchants.get(0).getFirst_name() +
		// merchants.get(0).getOffer().size());
		return response;
	}
}
