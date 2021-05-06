package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 주문내역 도메인 클래스
 * @author 김보성
 */
public class OrderList implements Serializable {
	private int oNo;
	private String memberId;
	private String oDate;
	private String pName;
	private String pImg;
	private int pPrice;
	private double pScore;
	
	/**
	 * 기본 생성자 메서드
	 */
	public OrderList() {
	}

	/** 주문 내역 리스트 생성자 메서드
	 * @param oNO 주문 번호
	 * @param memberId 구매자 아이디
	 * @param oDate 주문 날짜
	 * @param pName 상품명
	 * @param pImg 상품 사진
	 * @param pPrice 상품 가격
	 * @param pScore 상품 별점
	 */
	public OrderList(int oNo, String memberId, String oDate, String pName, String pImg, int pPrice, double pScore) {
		super();
		this.oNo = oNo;
		this.memberId = memberId;
		this.oDate = oDate;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.pScore = pScore;
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
	public void setoNO(int oNo) {
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
	public int getpPrice() {
		return pPrice;
	}

	/**
	 * @param pPrice the pPrice to set
	 */
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	/**
	 * @return the pScore
	 */
	public double getpScore() {
		return pScore;
	}

	/**
	 * @param pScore the pScore to set
	 */
	public void setpScore(double pScore) {
		this.pScore = pScore;
	}

	@Override
	public String toString() {
		return "OrderList [oNo=" + oNo + ", memberId=" + memberId + ", oDate=" + oDate + ", pName=" + pName + ", pImg="
				+ pImg + ", pPrice=" + pPrice + ", pScore=" + pScore + "]";
	}
}
