package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 후기 도메인 클래스
 * @author 김보성
 */
public class Review implements Serializable {
	private String memberId;
	private String oDate;
	private int pNo;
	private String pImg;
	private String reviewContent;
	private int score;
	
	/**
	 * 기본생성자
	 */
	public Review() {}

	/** 후기 등록 생성자 메서드
	 * @param memberId 작성자 아이디
	 * @param oDate 구매 날짜
	 * @param pNo 상품 번호
	 * @param reviewContent 후기 내용
	 * @param score 평점 점수
	 */
	public Review(String memberId, String oDate, int pNo, String pImg, String reviewContent, int score) {
		super();
		this.memberId = memberId;
		this.oDate = oDate;
		this.pNo = pNo;
		this.pImg = pImg;
		this.reviewContent = reviewContent;
		this.score = score;
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
	 * @return the reviewContent
	 */
	public String getReviewContent() {
		return reviewContent;
	}

	/**
	 * @param reviewContent the reviewContent to set
	 */
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
