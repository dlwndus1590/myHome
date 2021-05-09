package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 결제수단 도메인
 * @author 최인묵
 *
 */
public class OrdersMethod implements Serializable {
	private int oMethodId;
	private String oMethodName;
	
	public OrdersMethod() {}
	
	public OrdersMethod(int oMethodId, String oMethodName) {
		this.oMethodId = oMethodId;
		this.oMethodName = oMethodName;
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
	 * @return the oMethodName
	 */
	public String getoMethodName() {
		return oMethodName;
	}
	/**
	 * @param oMethodName the oMethodName to set
	 */
	public void setoMethodName(String oMethodName) {
		this.oMethodName = oMethodName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdersMethod [oMethodId=");
		builder.append(oMethodId);
		builder.append(", oMethodName=");
		builder.append(oMethodName);
		builder.append("]");
		return builder.toString();
	}
	
}
