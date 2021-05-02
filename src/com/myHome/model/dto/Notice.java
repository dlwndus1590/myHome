package com.myHome.model.dto;

import java.io.Serializable;

/**공지사항 도메인 클래스
 * @author 김보성
 * @since jdk1.8
 * @version 1.0
 */
public class Notice implements Serializable{
	private int nNo;
	private String memberId;
	private String nTitle;
	private String nContent;
	private String nRegDate;
	private int nHits = 0;
	
	/**
	 * 공지사항 기본 생성자
	 */
	public Notice() {
	}

	/**
	 * 공지사항 등록 생성자
	 * @param nNo 게시판번호
	 * @param nTitle 제목
	 * @param nContent 내용
	 * @param nRegDate 등록일자
	 * @param nHits 조회수
	 */
	public Notice(int nNo, String memberId, String nTitle, String nContent, String nRegDate, int nHits) {
		super();
		this.nNo = nNo;
		this.memberId = memberId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nRegDate = nRegDate;
		this.nHits = nHits;
	}

	
	/**
	 * @return the nNo
	 */
	public int getnNo() {
		return nNo;
	}

	/**
	 * @param nNo the nNo to set
	 */
	public void setnNo(int nNo) {
		this.nNo = nNo;
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
	 * @return the nTitle
	 */
	public String getnTitle() {
		return nTitle;
	}

	/**
	 * @param nTitle the nTitle to set
	 */
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	/**
	 * @return the nContent
	 */
	public String getnContent() {
		return nContent;
	}

	/**
	 * @param nContent the nContent to set
	 */
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	/**
	 * @return the nRegDate
	 */
	public String getnRegDate() {
		return nRegDate;
	}

	/**
	 * @param nRegDate the nRegDate to set
	 */
	public void setnRegDate(String nRegDate) {
		this.nRegDate = nRegDate;
	}

	/**
	 * @return the nHits
	 */
	public int getnHits() {
		return nHits;
	}

	/**
	 * @param nHits the nHits to set
	 */
	public void setnHits(int nHits) {
		this.nHits = nHits;
	}
}
