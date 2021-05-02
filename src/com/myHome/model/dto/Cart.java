package com.myHome.model.dto;

import java.io.Serializable;

public class Cart implements Serializable {
	private int cNo;
	private String memberId;
	private int pNo;
	private int cCount;
	
	public Cart() {}
	
	public Cart(int cNo, String memberId, int pNo, int cCount) {
		this.cNo = cNo;
		this.memberId = memberId;
		this.pNo = pNo;
		this.cCount = cCount;
	}

	/**
	 * @return the cNo
	 */
	public int getcNo() {
		return cNo;
	}
	/**
	 * @param cNo the cNo to set
	 */
	public void setcNo(int cNo) {
		this.cNo = cNo;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cart [cNo=");
		builder.append(cNo);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append(", pNo=");
		builder.append(pNo);
		builder.append(", cCount=");
		builder.append(cCount);
		builder.append("]");
		return builder.toString();
	}
	
}
