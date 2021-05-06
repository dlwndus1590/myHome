package com.myHome.model.dto;

import java.io.Serializable;

public class Orders implements Serializable {
	private int oNo;
	private String memberId;
	private int oMethodId;
	private int oTotalPrice;
	private String oDate;
	private int oDeliveryFee;
	private int usedMileage;
	private int accumulateMileage;
	
	public Orders() {}
	
	public Orders(int oNo, String memberId, int oMethodId, int oTotalPrice, String oDate, int oDeliveryFee, int usedMileage, int accumulateMileage) {
		this.oNo = oNo;
		this.memberId = memberId;
		this.oMethodId = oMethodId;
		this.oTotalPrice = oTotalPrice;
		this.oDate = oDate;
		this.oDeliveryFee = oDeliveryFee;
		this.usedMileage = usedMileage;
		this.accumulateMileage = accumulateMileage;
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

	/**
	 * @return the usedMileage
	 */
	public int getUsedMileage() {
		return usedMileage;
	}

	/**
	 * @param usedMileage the usedMileage to set
	 */
	public void setUsedMileage(int usedMileage) {
		this.usedMileage = usedMileage;
	}

	/**
	 * @return the accumulateMileage
	 */
	public int getAccumulateMileage() {
		return accumulateMileage;
	}

	/**
	 * @param accumulateMileage the accumulateMileage to set
	 */
	public void setAccumulateMileage(int accumulateMileage) {
		this.accumulateMileage = accumulateMileage;
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
