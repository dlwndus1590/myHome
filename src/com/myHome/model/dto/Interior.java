package com.myHome.model.dto;

/**
 * 인테리어 도메인 클래스
 * 
 * -- DTO Pattern 
 * @author 강하영
 */
public class Interior {
	private int ino;
	private String iname;
	private int icareer;
	private String idetail;
	private String ilocation;
	
	/** 기본생성자 */
	public Interior() {}

	/**
	 * 인체리어 업체 등록 필수 데이터
	 * @param ino			인테리어 등록번호
	 * @param iname		인테리어 업체명
	 * @param icareer		경력
	 * @param idetail		인테리어 업체 설명
	 * @param ilocation	인테리어 근무 위치
	 */
	public Interior(int ino, String iname, int icareer, String idetail, String ilocation) {
		super();
		this.ino = ino;
		this.iname = iname;
		this.icareer = icareer;
		this.idetail = idetail;
		this.ilocation = ilocation;
	}

	/** 인테리어 정보에 대한 반환처리 및 변경 */
	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public int getIcareer() {
		return icareer;
	}

	public void setIcareer(int icareer) {
		this.icareer = icareer;
	}

	public String getIdetail() {
		return idetail;
	}

	public void setIdetail(String idetail) {
		this.idetail = idetail;
	}

	public String getIlocation() {
		return ilocation;
	}

	public void setIlocation(String ilocation) {
		this.ilocation = ilocation;
	}

	/** 인테리어 정보 출력 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interior [ino=");
		builder.append(ino);
		builder.append(", iname=");
		builder.append(iname);
		builder.append(", icareer=");
		builder.append(icareer);
		builder.append(", idetail=");
		builder.append(idetail);
		builder.append(", ilocation=");
		builder.append(ilocation);
		builder.append("]");
		return builder.toString();
	}
	
	
}
