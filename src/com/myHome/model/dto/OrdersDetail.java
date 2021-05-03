package com.myHome.model.dto;

import java.io.Serializable;

public class OrdersDetail implements Serializable {
	private int dNo;
	private int dCount;
	private int pNo;
	private int oNo;
	
	public OrdersDetail() {}
	
	public OrdersDetail(int dNo, int dCount, int pNo, int oNo) {
		this.dNo = dNo;
		this.dCount = dCount;
		this.pNo = pNo;
		this.oNo = oNo;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdersDetail [dNo=");
		builder.append(dNo);
		builder.append(", dCount=");
		builder.append(dCount);
		builder.append(", pNo=");
		builder.append(pNo);
		builder.append(", oNo=");
		builder.append(oNo);
		builder.append("]");
		return builder.toString();
	}
	
}
