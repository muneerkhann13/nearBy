/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.json.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author Asce
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {

	@JsonProperty("offer_id")
	long id;
	@JsonProperty("desc")
	String desc;
	@JsonProperty("title")
	String title;
	@JsonProperty("expiry")
	Timestamp expiry;

	public Offer() {
	}

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@JsonIgnore
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public Timestamp getExpiry() {
		return expiry;
	}

	public void setExpiry(Timestamp expiry) {
		this.expiry = expiry;
	}

	/**
	 * @param id
	 * @param desc
	 * @param title
	 * @param expiry
	 */
	public Offer(long id, String desc, String title, Timestamp expiry) {
		super();
		this.id = id;
		this.desc = desc;
		this.title = title;
		this.expiry = expiry;
	}

}
