package com.cd.nearby.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "offertable")
public class OfferTable {

	// private variables corresponding to each column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	 @NotNull
	 @Column(name = "mdn")
	 private String mdn;
	@NotNull
	@Column(name = "expire_time")
	private Timestamp expiry;

	@NotNull
	@Column(name = "discription")
	private String Discription;

	@NotNull
	@Column(name = "title")
	private String Title;

//	@ManyToOne
//	@JoinColumn(name = "mdn")
//	private Merchant merchant;

	public OfferTable() {
	}

    public OfferTable(long id, String mdn, Timestamp expiry, String Discription, String Title) {
        this.id = id;
        this.mdn = mdn;
        this.expiry = expiry;
        this.Discription = Discription;
        this.Title = Title;
    }

	public long getOffer_id() {
		return id;
	}

	public void setOffer_id(long offer_id) {
		this.id = offer_id;
	}

	public Timestamp getExpiry() {
		return expiry;
	}

	public void setExpiry(Timestamp expiry) {
		this.expiry = expiry;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String Discription) {
		this.Discription = Discription;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

        
//	public Merchant getMerchant() {
//		return merchant;
//	}
//
//	public void setMerchant(Merchant merchant) {
//		this.merchant = merchant;
//	}

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }
}
