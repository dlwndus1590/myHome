package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 상품 도메인 클래스
 * @author 이주연
 */
public class Product implements Serializable {
	/** 상품 번호 */
	private int pNo;
	/** 상품명 */
	private String pName;
	/** 가격 */
	private int pPrice;
	/** 사진 */
	private String pImg;
	/** 상품설명 */
	private String pDescribe;
	/** 배송비 */
	private int deliveryFee;
	/** 회사/점포명 */
	private String companyName;
	/** 카테고리 번호 */
	private int categoryId;
	/** 평점 */
	private float pScore;
	/** 판매량 */
	private int pSales;
	/** 재고 */
	private int pCount;
	
	/** 기본생성자 */
	public Product() {}

	/**
	 * 생성자
	 * @param pNo
	 * @param pName
	 * @param pPrice
	 * @param pImg
	 * @param pDescribe
	 * @param deliveryFee
	 * @param companyName
	 * @param categoryId
	 * @param pScore
	 * @param pSales
	 * @param pCount
	 */
	public Product(int pNo, String pName, int pPrice, String pImg, String pDescribe, int deliveryFee,
			String companyName, int categoryId, float pScore, int pSales, int pCount) {
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pImg = pImg;
		this.pDescribe = pDescribe;
		this.deliveryFee = deliveryFee;
		this.companyName = companyName;
		this.categoryId = categoryId;
		this.pScore = pScore;
		this.pSales = pSales;
		this.pCount = pCount;
	}

	/** getter/setter */
	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public String getpDescribe() {
		return pDescribe;
	}

	public void setpDescribe(String pDescribe) {
		this.pDescribe = pDescribe;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public float getpScore() {
		return pScore;
	}

	public void setpScore(float pScore) {
		this.pScore = pScore;
	}

	public int getpSales() {
		return pSales;
	}

	public void setpSales(int pSales) {
		this.pSales = pSales;
	}

	public int getpCount() {
		return pCount;
	}

	public void setpCount(int pCount) {
		this.pCount = pCount;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pName=" + pName + ", pPrice=" + pPrice + ", pImg=" + pImg + ", pDescribe="
				+ pDescribe + ", deliveryFee=" + deliveryFee + ", companyName=" + companyName + ", categoryId="
				+ categoryId + ", pScore=" + pScore + ", pSales=" + pSales + ", pCount=" + pCount + "]";
	}
}
