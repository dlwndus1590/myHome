package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 주문 상세 내역 도메인 클래스
 * @author 김보성
 */
public class OrdersDetail implements Serializable {
	private String oDATE;
	private int oNO;
	private int dNO;
	private String memberId;
	private String pName;
	private String pImg;
	private int dCount;
	private int oDeliveryFee;
	
	/**
	 * 기본 생성자
	 */
	public OrdersDetail() {}

	/** 주문 상세 내역 생성자 메서드
	 * @param oDATE 구매 날짜
	 * @param oNO 구매 번호
	 * @param dNO 구매 상세 번호
	 * @param memberId 구매자 아이디
	 * @param pName 구매 상품 이름
	 * @param pImg 구매 상품 사진
	 * @param dCount 구매 상품 수량
	 * @param oDeliveryFee 배송비
	 */
	public OrdersDetail(String oDATE, int oNO, int dNO, String memberId, String pName, String pImg, int dCount,
			int oDeliveryFee) {
		super();
		this.oDATE = oDATE;
		this.oNO = oNO;
		this.dNO = dNO;
		this.memberId = memberId;
		this.pName = pName;
		this.pImg = pImg;
		this.dCount = dCount;
		this.oDeliveryFee = oDeliveryFee;
	}

	
	/**
	 * @return the oDATE
	 */
	public String getoDATE() {
		return oDATE;
	}

	/**
	 * @param oDATE the oDATE to set
	 */
	public void setoDATE(String oDATE) {
		this.oDATE = oDATE;
	}

	/**
	 * @return the oNO
	 */
	public int getoNO() {
		return oNO;
	}

	/**
	 * @param oNO the oNO to set
	 */
	public void setoNO(int oNO) {
		this.oNO = oNO;
	}

	/**
	 * @return the dNO
	 */
	public int getdNO() {
		return dNO;
	}

	/**
	 * @param dNO the dNO to set
	 */
	public void setdNO(int dNO) {
		this.dNO = dNO;
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
	public int getoDeliveryFee() {
		return oDeliveryFee;
	}

	/**
	 * @param oDeliveryFee the oDeliveryFee to set
	 */
	public void setoDeliveryFee(int oDeliveryFee) {
		this.oDeliveryFee = oDeliveryFee;
	}
}
