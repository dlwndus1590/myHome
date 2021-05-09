package com.myHome.model.dto;

/**
 * 결제 페이지 도메인
 * @author 최인묵
 *
 */
public class OrdersPage {
	/** 회원 정보 */
	private String name;
	private String email;
	private String mobile;
	private int mileage;
	private int zipCode;
	private String address1;
	private String address2;
	/** 상품 정보 */
	private int pNo;
	private String pName;
	private int pPrice;
	private int cCount;
	private String pImg;
	private String pDescribe;
	/** 결제 정보 */
	private int stock;
	private int deliveryFee;
	private int totalPrice;
	
	/** 기본 생성자 */
	public OrdersPage() {
	}

	/**
	 * 장바구니 페이지 생성자
	 * @param pNo
	 * @param pName
	 * @param pPrice
	 * @param cCount
	 * @param pImg
	 * @param deliveryFee
	 * @param totalPrice
	 * @param stock
	 */
	public OrdersPage(int pNo, String pName, int pPrice, int cCount, String pImg, int deliveryFee, int totalPrice, int stock) {
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.cCount = cCount;
		this.pImg = pImg;
		this.deliveryFee = deliveryFee;
		this.totalPrice = totalPrice;
		this.stock = stock;
	}

	/**
	 * 결제 페이지 생성자
	 * @param name
	 * @param email
	 * @param mobile
	 * @param mileage
	 * @param zipCode
	 * @param address1
	 * @param address2
	 * @param pNo
	 * @param pName
	 * @param pPrice
	 * @param cCount
	 * @param pImg
	 * @param pDescribe
	 * @param stock
	 * @param deliveryFee
	 * @param totalPrice
	 */
	public OrdersPage(String name, String email, String mobile, int mileage, int zipCode, String address1,
			String address2, int pNo, String pName, int pPrice, int cCount, String pImg, String pDescribe, int stock,
			int deliveryFee, int totalPrice) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.mileage = mileage;
		this.zipCode = zipCode;
		this.address1 = address1;
		this.address2 = address2;
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.cCount = cCount;
		this.pImg = pImg;
		this.pDescribe = pDescribe;
		this.stock = stock;
		this.deliveryFee = deliveryFee;
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the pNo
	 */
	public int getpNo() {
		return pNo;
	}

	/**
	 * @param pNo the pNo to set
	 */
	public void setpNo(int pNo) {
		this.pNo = pNo;
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
	 * @return the cCount
	 */
	public int getcCount() {
		return cCount;
	}

	/**
	 * @param cCount the cCount to set
	 */
	public void setcCount(int cCount) {
		this.cCount = cCount;
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
	 * @return the pDescribe
	 */
	public String getpDescribe() {
		return pDescribe;
	}

	/**
	 * @param pDescribe the pDescribe to set
	 */
	public void setpDescribe(String pDescribe) {
		this.pDescribe = pDescribe;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the deliveryFee
	 */
	public int getDeliveryFee() {
		return deliveryFee;
	}

	/**
	 * @param deliveryFee the deliveryFee to set
	 */
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdersPage [name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", mileage=");
		builder.append(mileage);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", pNo=");
		builder.append(pNo);
		builder.append(", pName=");
		builder.append(pName);
		builder.append(", pPrice=");
		builder.append(pPrice);
		builder.append(", cCount=");
		builder.append(cCount);
		builder.append(", pImg=");
		builder.append(pImg);
		builder.append(", pDescribe=");
		builder.append(pDescribe);
		builder.append(", stock=");
		builder.append(stock);
		builder.append(", deliveryFee=");
		builder.append(deliveryFee);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append("]");
		return builder.toString();
	}

}
