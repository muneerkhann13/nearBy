/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cd.nearby.cache.JedisUpload;
import com.cd.nearby.cache.RedisStorage;
import com.cd.nearby.common.ExceptionHandling;
import com.cd.nearby.dao.MerchantDao;
import com.cd.nearby.dao.OfferTableDao;
import com.cd.nearby.json.request.Request;
import com.cd.nearby.json.response.Offer;
import com.cd.nearby.json.response.Response;
import com.cd.nearby.model.Merchant;
import com.cd.nearby.model.OfferTable;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class OfferController extends ExceptionHandling {

    @Autowired
    OfferTableDao offerDao;

    @Autowired
    MerchantDao merchantDao;

    @Autowired
    RedisStorage redisStorage;

    @Autowired
    JedisUpload jedisUpload;

    @Autowired
    Offer offer;

    @RequestMapping(value = {"/offer"}, method = RequestMethod.POST, produces = "application/json")
    public Response getOffer(@RequestBody Request offerRequest) {

//		Response response = new Response();
//
//		String merchantMdn = offerRequest.getMerchantId();
//
//		List<OfferTable> offers = redisStorage.fetchAlloffers();
//		Merchant merchant = merchantDao.getAllMerchant(merchantMdn);
//
//		response.setCode(0);
//		response.setDesc("SUCCESS");
//		response.setRating(merchant.getRating());
//
//		ArrayList<com.cd.nearby.json.response.Offer> responseOffer = new ArrayList<com.cd.nearby.json.response.Offer>();
//		for (OfferTable ofx : offers) {
//			if(ofx.getMdn().equalsIgnoreCase(merchant.getMdn())){
//			com.cd.nearby.json.response.Offer of = new com.cd.nearby.json.response.Offer();
//			of.setDesc(ofx.getDiscription());
//			of.setExpiry(ofx.getExpiry());
//			of.setId(ofx.getOffer_id());
//			of.setTitle(ofx.getTitle());
//
//			responseOffer.add(of);
//
//		}
//                }
//        int randomNum = 0 + (int) (Math.random() * 5);
//        response.setUserCount(randomNum+15);
//		response.setOffers(responseOffer);
//		return response;
	
        Response response = new Response();

        String merchantMdn = offerRequest.getMerchantId();

        ArrayList<Offer> offerList = new ArrayList<Offer>();

        Merchant merchant = merchantDao.getAllMerchant(merchantMdn);

        List<OfferTable> offers = offerDao.getAllOffer(merchantMdn);

        System.out.println(merchant.getDob());
        for (OfferTable of : offers) {
            Offer offer = new Offer();
            offer.setId(of.getOffer_id());
            offer.setDesc(of.getDiscription());
            offer.setExpiry(of.getExpiry());
            offer.setTitle(of.getTitle());

            offerList.add(offer);
        }
        int randomNum = 0 + (int) (Math.random() * 5);
        response.setUserCount(randomNum+15);
        response.setOffers(offerList);

        return response;

    }

    @RequestMapping(value = {"/update-offer"}, method = RequestMethod.POST, produces = "application/json")
    public Response updateOffer(@RequestBody Request offerRequest) {

        OfferTable offer = new OfferTable();
        offer.setOffer_id(offerRequest.getOffer().getOfferId());
        offer.setDiscription(offerRequest.getOffer().getDesc());
        offer.setExpiry(new Timestamp(Long.parseLong(offerRequest.getOffer().getExpire())));
        offer.setTitle(offerRequest.getOffer().getTitle());

        Response response = new Response();

        String merchantMdn = offerRequest.getMerchantId();

        offerDao.updateOffer(offer);

        List<OfferTable> offers = offerDao.getAllOffer(merchantMdn);

        ArrayList<com.cd.nearby.json.response.Offer> responseOffer = new ArrayList<com.cd.nearby.json.response.Offer>();
        for (OfferTable offer1 : offers) {
            com.cd.nearby.json.response.Offer of = new com.cd.nearby.json.response.Offer();

            of.setDesc(offer1.getDiscription());
            of.setExpiry(offer1.getExpiry());
            of.setId(offer1.getOffer_id());
            of.setTitle(offer1.getTitle());
            responseOffer.add(of);
        }
        response.setOffers(responseOffer);
        //jedisUpload.uploadOffers();
        response.setDesc("SUCCESS");
        return response;
    }

    @RequestMapping(value = {"/add-offer"}, method = RequestMethod.POST, produces = "application/json")
    public Response addOffer(@RequestBody Request offerRequest) {

        Response response = new Response();

        String merchantMdn = offerRequest.getMerchantId();

        OfferTable offer = new OfferTable();

        offer.setOffer_id(offerRequest.getOffer().getOfferId());
        offer.setDiscription(offerRequest.getOffer().getDesc());
        offer.setExpiry(new Timestamp(Long.parseLong(offerRequest.getOffer().getExpire())));
        offer.setTitle(offerRequest.getOffer().getTitle());
        offer.setMdn(merchantMdn);

        offerDao.save(offer);

        List<OfferTable> offers = offerDao.getAllOffer(merchantMdn);

        ArrayList<com.cd.nearby.json.response.Offer> responseOffer = new ArrayList<com.cd.nearby.json.response.Offer>();
        for (OfferTable offer1 : offers) {
            com.cd.nearby.json.response.Offer of = new com.cd.nearby.json.response.Offer();

            of.setDesc(offer1.getDiscription());
            of.setExpiry(offer1.getExpiry());
            of.setId(offer1.getOffer_id());
            of.setTitle(offer1.getTitle());

            responseOffer.add(of);

        }
    //    jedisUpload.uploadOffers();
        response.setOffers(responseOffer);

        response.setDesc("SUCCESS");
        return response;
    }
    //

    @RequestMapping(value = {"/delete-offer"}, method = RequestMethod.POST, produces = "application/json")
    public Response deleteOffer(@RequestBody Request offerRequest) {

        Response response = new Response();

        String merchantMdn = offerRequest.getMerchantId();
        OfferTable offer = new OfferTable();

        offer.setOffer_id(offerRequest.getOffer().getOfferId());

        offerDao.deleteOffer(offer);

        List<OfferTable> offers = offerDao.getAllOffer(merchantMdn);

        ArrayList<com.cd.nearby.json.response.Offer> responseOffer = new ArrayList<com.cd.nearby.json.response.Offer>();
        for (OfferTable offer1 : offers) {
            com.cd.nearby.json.response.Offer of = new com.cd.nearby.json.response.Offer();

            of.setDesc(offer1.getDiscription());
            of.setExpiry(offer1.getExpiry());
            of.setId(offer1.getOffer_id());
            of.setTitle(offer1.getTitle());

            responseOffer.add(of);

        }
      //  jedisUpload.uploadOffers();
        response.setOffers(responseOffer);


        response.setDesc("SUCCESS");
        return response;
    }

}
