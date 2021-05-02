package com.myHome.model.dto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * JavaBeanComponent 설계 변경
 *
 */
public class MessageEntity {
	private HashMap<String, ArrayList<String>> messageList = new HashMap<String, ArrayList<String>>();
	
	private String url;
	private String linkTitle;
	private String type;  	// error, validation, success, message
	private int index;		// 메세지가 저장된 위치
	private String message;
	
	public MessageEntity() {

		ArrayList<String> error = new ArrayList<String>();
		error.add("[회원 등록 오류]");		// 0	
		error.add("[회원 검색 오류]");		// 1		
		error.add("[로그인 오류]");		// 2	
		error.add("[회원정보 수정 오류]");	// 3
		error.add("[회원 비밀번호 수정 오류]");	
		error.add("[회원 아이디 찾기 오류]");	
		error.add("[회원 비밀번호 찾기 오류]");	
		error.add("[게시글 카테고리 오류]");
		error.add("[게시글 등록 오류]");			
		error.add("[게시글 검색 오류]");	
		error.add("[회원 탈퇴 오류]");	// 10
		error.add("[게시글 삭제 오류]"); // 11
		
		ArrayList<String> validation = new ArrayList<String>();
		validation.add("[아이디 정보 오류]");		
		validation.add("[비밀번호 정보 오류]");		
		validation.add("[이름 정보 오류]");		
		validation.add("[휴대폰 정보 오류]");		
		validation.add("[이메일 정보 오류]");		
		validation.add("[가입일 정보 오류]");		
		validation.add("[등급 정보 오류]");		
		validation.add("[마일리지 정보 오류]");		
		validation.add("[담당자 정보 오류]");	
		
		validation.add("[게시글 제목 오류]");	// 9	
		
		ArrayList<String> success = new ArrayList<String>();
		success.add("[회원 등록 성공]");	
		success.add("[회원 로그인 성공]");		
		success.add("[회원 로그아웃 성공]");		
		success.add("[회원 수정 성공]");  // 3
		success.add("[회원 상세조회 성공]");
		success.add("[회원 탈퇴 성공]");
		success.add("[회원 아이디찾기 성공]");
		success.add("[회원 비밀번호찾기 성공]");  // 7
		success.add("[회원 우수회원 등업 성공]");
		success.add("[게시글 등록 성공]");	// 9	
		success.add("[회원 내정보조회 성공]"); // 10
		
		ArrayList<String> message = new ArrayList<String>();
		message.add("[이 페이지는 로그인이 필요합니다.]");
		message.add("[이 페이지는 관리자 권한이 필요합니다.]");
		
		messageList.put("error", error);
		messageList.put("validation", validation);
		messageList.put("success", success);
		messageList.put("message", message);
	}
	
	public MessageEntity(String type, int index) { // [회원 등록 오류] (error, 0)
		this();
		this.type = type;
		this.index = index;
	}

	public String getMessage() {
		return messageList.get(type).get(index);  // [회원 등록 오류]
	}

	public String getUrl() {
		return url;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public String getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	/**
	 * @return the messageList
	 */
	public HashMap<String, ArrayList<String>> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(HashMap<String, ArrayList<String>> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}


	
}
