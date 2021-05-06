package com.myHome.model.dto;

import java.io.Serializable;

/** 
 * 카테고리 도메인 클래스
 * @author 이주연
 */
public class Category implements Serializable {
	/** 카테고리 번호 */
	private int categoryId;
	/** 카테고리 명 */
	private String categoryName;
	/** 카테고리 이미지 */
	private String categoryImg;
	
	/** 기본생성자 */
	public Category() {}

	/**
	 * 생성자
	 * @param categoryId
	 * @param categoryName
	 * @param categoryImg
	 */
	public Category(int categoryId, String categoryName, String categoryImg) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryImg = categoryImg;
	}

	/** getter/setter */
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryImg() {
		return categoryImg;
	}
	
	public void setCategoryImg(String categoryImg) {
		this.categoryImg = categoryImg;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryImg=" + categoryImg + "]";
	}	
}
