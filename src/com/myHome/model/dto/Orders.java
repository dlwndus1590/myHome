package com.myHome.model.dto;

import java.io.Serializable;

public class Orders implements Serializable {
	private int oNo;
	private String memberId;
	private int oMethodId;
	private int dNo;
	private int oTotalPrice;
	private String oDate;
	private int oDeliveryFee;
	
	public Orders() {}
	
	public Orders(int oNo, String memberId, int oMethodId, int dNo, int oTotalPrice, String oDate, int oDeliveryFee) {
		this.oNo = oNo;
		this.memberId = memberId;
		this.oMethodId = oMethodId;
		this.dNo = dNo;
		this.oTotalPrice = oTotalPrice;
		this.oDate = oDate;
		this.oDeliveryFee = oDeliveryFee;
	}

	/**
	 * @return the oNo
	 */
	public int getoNo() {
		return oNo;
	}
	/**
	 * @param oNo the oNo to set
	 */
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the oMethodId
	 */
	public int getoMethodId() {
		return oMethodId;
	}
	/**
	 * @param oMethodId the oMethodId to set
	 */
	public void setoMethodId(int oMethodId) {
		this.oMethodId = oMethodId;
	}
	/**
	 * @return the dNo
	 */
	public int getdNo() {
		return dNo;
	}
	/**
	 * @param dNo the dNo to set
	 */
	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	/**
	 * @return the oTotalPrice
	 */
	public int getoTotalPrice() {
		return oTotalPrice;
	}
	/**
	 * @param oTotalPrice the oTotalPrice to set
	 */
	public void setoTotalPrice(int oTotalPrice) {
		this.oTotalPrice = oTotalPrice;
	}
	/**
	 * @return the oDate
	 */
	public String getoDate() {
		return oDate;
	}
	/**
	 * @param oDate the oDate to set
	 */
	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	/**
	 * @return the oDeliveryFee
	 */
	public int getoDeliveryFee() {
		return oDeliveryFee;
	}
	/**
	 * @param oDeliveryFee the oDeliveryFee to set
	 */
	public void setoDeliveryFee(int oDeliveryFee) {
		this.oDeliveryFee = oDeliveryFee;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orders [oNo=");
		builder.append(oNo);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append(", oMethodId=");
		builder.append(oMethodId);
		builder.append(", dNo=");
		builder.append(dNo);
		builder.append(", oTotalPrice=");
		builder.append(oTotalPrice);
		builder.append(", oDate=");
		builder.append(oDate);
		builder.append(", oDeliveryFee=");
		builder.append(oDeliveryFee);
		builder.append("]");
		return builder.toString();
	}
	
}
