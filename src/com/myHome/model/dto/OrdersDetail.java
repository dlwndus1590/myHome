package com.myHome.model.dto;

import java.io.Serializable;

public class OrdersDetail implements Serializable {
	private int dNo;
	private int dCount;
	private int pNo;
	
	public OrdersDetail() {}
	
	public OrdersDetail(int dNo, int dCount, int pNo) {
		this.dNo = dNo;
		this.dCount = dCount;
		this.pNo = pNo;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdersDetail [dNo=");
		builder.append(dNo);
		builder.append(", dCount=");
		builder.append(dCount);
		builder.append(", pNo=");
		builder.append(pNo);
		builder.append("]");
		return builder.toString();
	}
	
}
