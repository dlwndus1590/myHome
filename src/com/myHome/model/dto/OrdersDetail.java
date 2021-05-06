package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 주문 상세 내역 도메인 클래스
 * @author 김보성
 */
public class OrdersDetail implements Serializable {
	private int dNo;
	private String memberId;
	private String pName;
	private String pImg;
	private String pPrice;
	private int dCount;
	private String oDeliveryFee;
	private String usedMileage;
	private String accumulateMileage;
	private String oTotalPrice;
	private String totalAmount;
	
	/**
	 * 기본 생성자
	 */
	public OrdersDetail() {}

	/** 주문 상세 내역 생성자 메서드
	 * @param dNO 구매 상세 번호
	 * @param memberId 구매자 아이디
	 * @param pName 구매 상품 이름
	 * @param pImg 구매 상품 사진
	 * @param pPrice 구매 상품 가격
	 * @param dCount 구매 상품 수량
	 * @param oDeliveryFee 배송비
	 * @param usedMileage 사용 마일리지
	 * @param accumulateMileage 적립 마일리지
	 * @param oTotalPrice 총 구매 합계
	 */
	public OrdersDetail(int dNo, String memberId, String pName, String pImg, String pPrice, int dCount,
			String oDeliveryFee, String usedMileage, String accumulateMileage, String oTotalPrice, String totalAmount) {
		super();
		this.dNo = dNo;
		this.memberId = memberId;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.dCount = dCount;
		this.oDeliveryFee = oDeliveryFee;
		this.usedMileage = usedMileage;
		this.accumulateMileage = accumulateMileage;
		this.oTotalPrice = oTotalPrice;
		this.totalAmount = totalAmount;
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
	public void setdNO(int dNo) {
		this.dNo = dNo;
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
	 * @return the pName
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName the pName to set
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}

	/**
	 * @return the pImg
	 */
	public String getpImg() {
		return pImg;
	}

	/**
	 * @param pImg the pImg to set
	 */
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	
	/**
	 * @return the pPrice
	 */
	public String getpPrice() {
		return pPrice;
	}

	/**
	 * @param pPrice the pPrice to set
	 */
	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}

	/**
	 * @return the dCount
	 */
	public int getdCount() {
		return dCount;
	}

	/**
	 * @param dCount the dCount to set
	 */
	public void setdCount(int dCount) {
		this.dCount = dCount;
	}

	/**
	 * @return the oDeliveryFee
	 */
	public String getoDeliveryFee() {
		return oDeliveryFee;
	}

	/**
	 * @param oDeliveryFee the oDeliveryFee to set
	 */
	public void setoDeliveryFee(String oDeliveryFee) {
		this.oDeliveryFee = oDeliveryFee;
	}

	/**
	 * @return the usedMileage
	 */
	public String getUsedMileage() {
		return usedMileage;
	}

	/**
	 * @param usedMileage the usedMileage to set
	 */
	public void setUsedMileage(String usedMileage) {
		this.usedMileage = usedMileage;
	}

	/**
	 * @return the accumulateMileage
	 */
	public String getAccumulateMileage() {
		return accumulateMileage;
	}

	/**
	 * @param accumulateMileage the accumulateMileage to set
	 */
	public void setAccumulateMileage(String accumulateMileage) {
		this.accumulateMileage = accumulateMileage;
	}

	/**
	 * @return the oTotalPrice
	 */
	public String getoTotalPrice() {
		return oTotalPrice;
	}

	/**
	 * @param oTotalPrice the oTotalPrice to set
	 */
	public void setoTotalPrice(String oTotalPrice) {
		this.oTotalPrice = oTotalPrice;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @param dNo the dNo to set
	 */
	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	
}
