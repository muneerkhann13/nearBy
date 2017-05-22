/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.dao;

import com.cd.nearby.model.Merchant;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asce
 */
@Repository
@Transactional
public class MerchantDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public List getAllMerchant() {

        Criteria cr = getSession().createCriteria(Merchant.class);
        cr.add(Restrictions.eq("is_merchant", true));
        return cr.list();
    }

    public Merchant getAllMerchant(String merchantMdn) {
        Criteria cr = getSession().createCriteria(Merchant.class);
        cr.add(Restrictions.eq("mdn", merchantMdn)).add(Restrictions.eq("is_merchant", true));
//        cr.add(Restrictions.eq("is_merchant", true));
        Merchant merchant = null;
        try {
            //userObject = (User) cr.list().get(0);
            merchant = (Merchant) cr.list().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return merchant;
    }

    public void updateMerchant(Merchant merchant) {
        getSession().merge(merchant);

    }
}
