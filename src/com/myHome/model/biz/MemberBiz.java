package com.myHome.model.biz;

import java.util.ArrayList;

import com.myHome.model.dao.MemberDao;
import com.myHome.model.dto.Member;

/**
 * <pre>
 * 		회원 테이블에 대한 Biz 클래스
 * </pre>
 * 
 * @author 강하영
 * @param <searchKey>
 */
public class MemberBiz<searchKey> {
	
	/** DAO 클래스 객체 선언 및 생성 */
	private MemberDao dao = MemberDao.getInstance();
	private ArrayList<Member> list = new ArrayList<Member>();
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 등급, 미존재시 null
	 */
	public String login(String memberId, String memberPw) {
		String grade = dao.login(memberId, memberPw);
		return grade;
	}
	
	/**
	 *	아이디 중복여부 검증 메서드 
	 *	@param memberId 아이디
	 *	@return 존재하면 true, 미존재시 false
	 */
	public boolean isMember(String memberId) {
		for(Member dto : list) {
			if(dto.getMemberId().equals(memberId)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 회원가입
	 * -- 회원가입 필수 데이터 : 아이디, 비밀번호, 이름, 휴대폰, 이메일, 우편번호, 기본주소, 상세주소
	 * -- 시스템 추가 데이터 : 
	 * 			가입일(현재날짜), 
	 * 			마일리지(일반회원 - 1000, 판매자 - 해당없음), 
	 * 			등급(일반 - G / 판매자 - S / 관리자 - A)
	 * </pre>
	 * @param dto 회원객체
	 * @return 성공시 true, 실패시 false
	 */
	public boolean insertMember(Member member) {
		//member.setEntryDate(Utility.getCurrentDate());
		member.setGrade("G");
		int rows = dao.insertMember(member);
		if (rows == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * 회원가입
	 * -- 회원가입 필수 데이터 : 아이디, 비밀번호, 이름, 휴대폰, 이메일, 우편번호, 기본주소, 상세주소
	 * -- 시스템 추가 데이터 : 
	 * 			가입일(현재날짜),
	 * 			등급(일반 - G / 판매자 - S / 관리자 - A)
	 * </pre>
	 * @param dto 회원객체
	 * @return 성공시 true, 실패시 false
	 */
	public boolean insertSeller(Member dto) {
		//dto.setEntryDate(Utility.getCurrentDate());
		dto.setGrade("S");
		int rows = dao.insertSeller(dto);
		if (rows == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 회원 상세조회
	 * 	-- 내 정보 조회 및 
	 * 		관리자의 상세조회 
	 * @param memberId 아이디
	 * @return 존재시 Member, 미존재시 null
	 */
	public Member selectOneMember(String memberId) {
		return dao.selectOne(memberId);
	}
	
	/** 
	 * 관리자 권한
	 * 		- 전체회원조회(일반 회원, 판매자 회원)
	 * @param keyWord2 
	 * @param searchKey 
	 * @return ArrayList<Member>
	 */
	public ArrayList<Member> selectMemberList(String searchKey, String keyWord) {
		return dao.selectList(searchKey,keyWord);
	}
	
	/**
	 * 아이디 찾기
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return 아이디, 미존재시 null
	 */
	public String selectByMemberId(String name, String mobile) {
		return dao.selectByMemberId(name, mobile);
	}
	
	/** 
	 * 비밀번호 찾기 : 
	 *  -- 사용자 인증 체킹 : dao
	 *  -- 사용자 인증 성공 : 임시비밀번호 발급 (Utility.java)
	 *  -- 임시비밀번호발급한 정보로 해당 회원의 비밀번호 변경 : dao
	 *  -- 반환 : 
	 *  	성공 : 변경완료된 임시발급비밀번호
	 *  	실패 : null 
	 */
	public String selectByMemberPw(String memberId, String name, String mobile) {
		System.out.println("[debug] biz : " + memberId + ", " + name + ", " + mobile);
		boolean isMember = dao.selectByMemberPw(memberId, name, mobile);
		
		System.out.println("[debug] biz : " + isMember);
		if (!isMember) {
			return null;
		}
		
		//String tempMemberPw = Utility.getSecureNumberAndString(8);
		//int result = dao.updateByMemberPw(memberId, tempMemberPw);
		
		//System.out.println("[debug] biz : " + result + ", " + tempMemberPw);
		//if (result == 1) {
		//	return tempMemberPw;
		//}
		
		return null;
	}
	
	/**
	 * 일반 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateMyInfo(Member dto) {
		return dao.updateMyInfo(dto);
	}
	
	/**
	 * 판매자 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateSellerInfo(Member dto) {
		return dao.updateSellerInfo(dto);
	}
	
	/** 
	 * 관리자 : 전체 회원정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateAll(Member dto) {
		return dao.updateAll(dto);
	}
	
	/**
	 * <pre>
	 * 관리자 회원 탈퇴 :
	 * 테이블 참조 관계 설정 : 회원(아이디), 게시글(작성자) 
	 * </pre>
	 * @param memberId 아이디
	 * @return 성공시 true, 실패시 false
	 */
	public boolean deleteMember(String memberId) {
		// 탈퇴회원이 작성한 게시글 전체 삭제
		//boardDao.removeBoard(memberId);
		return dao.deleteMember(memberId);
	}
}
