package com.myHome.model.dto;

import java.io.Serializable;

/**
 * 회원 도메인 클래스
 * 
 * -- DTO Pattern 
 * @author 강하영
 */
public class Member implements Serializable {
	/** 일반회원, 판매자회원 필수 입력 데이터 */
	private String memberId;
	private String memberPw;
	private String name;
	private String email;
	private String mobile;
	private int zipcode;
	private String address1;
	private String address2;

	/** 판매자 회원 추가 입력 데이터 */
	private String businessNumber;
	private String companyName;	
	
	/** 일반 회원 조회 데이터 */
	private String entryDate;
	private int mileage;
	private String grade;
	
	/** 기본 생성자 */
	public Member() {}

	/**
	 * 일반회원 필수 입력 생성자
	 * @param memberId		회원 아이디
	 * @param memberPw	회원 비밀번호
	 * @param name			회원 이름
	 * @param email			회원 이메일
	 * @param mobile			회원 휴대폰번호
	 * @param zipcode			회원 우편번호
	 * @param address1		회원 기본주소
	 * @param address2		회원 상세주소
	 */
	public Member(String memberId, String memberPw, String name, String email, String mobile, int zipcode,
			String address1, String address2,String entryDate,int mileage,String grade) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.entryDate = entryDate;
		this.mileage = mileage;
		this.grade = grade;
	}


	/**
	 * 판매자 회원 필수 입력 생성자
	 * @param memberId				판매자 아이디
	 * @param memberPw			판매자 비밀번호
	 * @param name					판매자 이름
	 * @param email					판매자 이메일
	 * @param mobile					판매자 휴대폰번호	
	 * @param zipcode					판매자 우편번호
	 * @param address1				판매자 기본주소
	 * @param address2				판매자 상세주소
	 * @param businessNumber		판매자 사업자번호
	 * @param companyName		판매자 회사명
	 */
	public Member(String memberId, String memberPw, String name, String email, String mobile, int zipcode,
			String address1, String address2, String businessNumber, String companyName,String entryDate,int mileage,String grade) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.businessNumber = businessNumber;
		this.companyName = companyName;
		this.entryDate = entryDate;
		this.mileage = mileage;
		this.grade = grade;
	}

	/**
	 * 회원 데이터 반환처리 및 변경
	 */
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	/** 회원 관리 출력 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [memberId=");
		builder.append(memberId);
		builder.append(", memberPw=");
		builder.append(memberPw);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", businessNumber=");
		builder.append(businessNumber);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", entryDate=");
		builder.append(entryDate);
		builder.append(", mileage=");
		builder.append(mileage);
		builder.append(", grade=");
		builder.append(grade);
		builder.append("]");
		return builder.toString();
	}
}
