/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cd.nearby.model.OfferTable;
import java.sql.Timestamp;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Shiv
 */
@Repository
@Transactional
public class OfferTableDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public List getAllOffer() {
        return getSession().createCriteria(OfferTable.class).list();
    }

    public List<OfferTable> getAllOffer(String merchantMdn) {

        Criteria cr = getSession().createCriteria(OfferTable.class);
        cr.add(Restrictions.eq("mdn", merchantMdn));
        List<OfferTable> offerList = null;
        try {
            //userObject = (User) cr.list().get(0);
            offerList = cr.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerList;
    }

    public void save(OfferTable offer) {
        getSession().save(offer);
    }

    public void updateOffer(OfferTable offer) {
//        getSession().update(offer);

        Query q = getSession().createQuery("update OfferTable set discription= :discription,title= :title,expiry= :expiry where id= :id");
        q.setString("discription", offer.getDiscription());
        q.setString("title", offer.getTitle());
        q.setTimestamp("expiry", offer.getExpiry());
        q.setLong("id", offer.getOffer_id());
        q.executeUpdate();

    }

    public void deleteOffer(OfferTable offer) {
        getSession().delete(offer);

    }
}
