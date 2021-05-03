package com.myHome.model.dto;

/**질문게시판 도메인 클래스
 * @author 김보성
 * @since jdk1.8
 * @version 1.0
 */

public class Qnotice {
	private int qNo;
	private String qTitle;
	private String qContent;
	private String qImg;
	private String memberId;
	private String qRegDate;
	private int qHits = 0;
	
	/**
	 * 기본 생성자
	 */
	public Qnotice() {
	}

	/** 생성자 오버로딩
	 * @param qNO 질문 게시글 번호
	 * @param qTitle 질문 게시글 제목
	 * @param qContent 질문 게시글 내용
	 * @param qImg 질문 게시글 사진
	 * @param memberId 작성자 아이디
	 * @param qRegDate 질문 게시글 작성 날짜
	 * @param qHits 질문 게시글 조회수
	 */
	public Qnotice(int qNo, String qTitle, String qContent, String qImg, String memberId, String qRegDate, int qHits) {
		super();
		this.qNo = qNo;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qImg = qImg;
		this.memberId = memberId;
		this.qRegDate = qRegDate;
		this.qHits = qHits;
	}

	/**
	 * @return the qNo
	 */
	public int getqNo() {
		return qNo;
	}

	/**
	 * @param qNo the qNo to set
	 */
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	/**
	 * @return the qTitle
	 */
	public String getqTitle() {
		return qTitle;
	}

	/**
	 * @param qTitle the qTitle to set
	 */
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	/**
	 * @return the qContent
	 */
	public String getqContent() {
		return qContent;
	}

	/**
	 * @param qContent the qContent to set
	 */
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	/**
	 * @return the qImg
	 */
	public String getqImg() {
		return qImg;
	}

	/**
	 * @param qImg the qImg to set
	 */
	public void setqImg(String qImg) {
		this.qImg = qImg;
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
	 * @return the qRegDate
	 */
	public String getqRegDate() {
		return qRegDate;
	}

	/**
	 * @param qRegDate the qRegDate to set
	 */
	public void setqRegDate(String qRegDate) {
		this.qRegDate = qRegDate;
	}

	/**
	 * @return the qHits
	 */
	public int getqHits() {
		return qHits;
	}

	/**
	 * @param qHits the qHits to set
	 */
	public void setqHits(int qHits) {
		this.qHits = qHits;
	}
	
}
