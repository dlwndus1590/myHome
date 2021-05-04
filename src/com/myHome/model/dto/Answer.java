package com.myHome.model.dto;

/**
 * 답변 도메인 클래스
 * 
 * @author 김보성
 * @since jdk1.8
 * @version 1.0
 */
public class Answer {
	private int aNo;
	private int qNo;
	private String aContent;
	private String memberId;
	private String aRegDate;

	/**
	 * 기본 생성자 메서드
	 */
	public Answer() {
	}

	/**
	 * 답변 등록 생성자 메서드
	 * @param qNo      답변 등록할 질문 게시글 번호
	 * @param aContent 답변 내용
	 * @param memberId 답변 작성자 아이디
	 */
	public Answer(int qNo, String aContent, String memberId) {
		super();
		this.qNo = qNo;
		this.aContent = aContent;
		this.memberId = memberId;
	}

	/**
	 * 생성자 오버로딩 메서드
	 * @param aNo      답변 번호
	 * @param qNo      질문 게시글 번호
	 * @param aContent 답변 내용
	 * @param memberId 답변 작성자 아이디
	 * @param aRegDate 답변 작성일
	 */
	public Answer(int aNo, int qNo, String aContent, String memberId, String aRegDate) {
		this(qNo, aContent, memberId);
		this.aNo = aNo;
		this.aRegDate = aRegDate;
	}

	/**
	 * @return the aNo
	 */
	public int getaNo() {
		return aNo;
	}

	/**
	 * @param aNo the aNo to set
	 */
	public void setaNo(int aNo) {
		this.aNo = aNo;
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
	 * @return the aContent
	 */
	public String getaContent() {
		return aContent;
	}

	/**
	 * @param aContent the aContent to set
	 */
	public void setaContent(String aContent) {
		this.aContent = aContent;
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
	 * @return the aRegDate
	 */
	public String getaRegDate() {
		return aRegDate;
	}

	/**
	 * @param aRegDate the aRegDate to set
	 */
	public void setaRegDate(String aRegDate) {
		this.aRegDate = aRegDate;
	}
}