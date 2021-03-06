package com.dct.swocean.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 家族文化字派
 * 
 * @author Administrator
 *
 */
public class SysZipaiInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zipaIid;
	private String zipaiOrder;
	private String surname;
	private String region;
	private String ancestorsName;
	private String creator;
	private Timestamp createTime;
	private int status;
	private int like;
	private int comment;
	private int relay;
	private int collection;

	public String getZipaIid() {
		return zipaIid;
	}

	public void setZipaIid(String zipaIid) {
		this.zipaIid = zipaIid;
	}

	public String getZipaiOrder() {
		return zipaiOrder;
	}

	public void setZipaiOrder(String zipaiOrder) {
		this.zipaiOrder = zipaiOrder;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAncestorsName() {
		return ancestorsName;
	}

	public void setAncestorsName(String ancestorsName) {
		this.ancestorsName = ancestorsName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getRelay() {
		return relay;
	}

	public void setRelay(int relay) {
		this.relay = relay;
	}

	public int getCollection() {
		return collection;
	}

	public void setCollection(int collection) {
		this.collection = collection;
	}

}
