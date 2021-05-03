package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.MemberDao;
import com.myHome.model.dto.Member;
import com.myHome.util.Utility;



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
	public void login(Member dto) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		
		try{
			dao.login(con, dto);
			
		} catch(Exception e) {
			JdbcTemplate.rollback(con);
			throw e; // dao에서 던지 예외를 다시 controller에게 예외 던짐
			
		} finally {
			JdbcTemplate.close(con);
		}
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
	public void insertMember(Member dto) {
		Connection con = JdbcTemplate.getConnection();
		
		dto.setEntryDate(Utility.getCurrentDate("yyyy-MM-dd"));
		try {
			System.out.println("(일반 회원가입)데이터 서비스 단 확인 : "+dto);
			dao.insertMember(con,dto);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
		} finally {
			JdbcTemplate.close(con);
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
	public void insertSeller(Member dto) {
		Connection con = JdbcTemplate.getConnection();		
		dto.setEntryDate(Utility.getCurrentDate("yyyy-MM-dd"));
		try {
			System.out.println("(판매자 회원가입)데이터 서비스 단 확인 : "+dto);
			dao.insertMember(con,dto);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
		} finally {
			JdbcTemplate.close(con);
		}	
	}
	
	/**
	 * 일반회원 상세조회
	 * 	-- 내 정보 조회 및 
	 * 		관리자의 상세조회 
	 * @param memberId 아이디
	 * @return 존재시 Member, 미존재시 null
	 */
	public void selectOneMember(Member dto) throws Exception{
		Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.selectOneMember(con, dto);
			
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(con);	
		}		
	}
	
	/**
	 * 판매자회원 상세조회
	 * 	-- 내 정보 조회 및 
	 * 		관리자의 상세조회 
	 * @param memberId 아이디
	 * @return 존재시 Member, 미존재시 null
	 */
	public void selectOneSeller(Member dto, String memberId) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.selectOneSeller(con, dto);
			
		} catch (Exception e) {			
			throw e;
		} finally {
			JdbcTemplate.close(con);	
		}	
	}
	
	/**
	 * 일반 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public void updateMemberMyInfo(Member dto) throws Exception{
		Connection con = JdbcTemplate.getConnection();
		
		try{
			dao.updateMyInfo(con, dto);
			JdbcTemplate.commit(con);
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e; 
			
		} finally {
			JdbcTemplate.close(con);	
		}		
	}
	
	/**
	 * 판매자 회원
	 * 		-- 내정보 수정
	 * @param dto Member
	 * @return 성공시 true, 실패시 false
	 */
	public void updateSellerMyInfo(Member dto) throws Exception{
		Connection con = JdbcTemplate.getConnection();
		
		try{
			dao.updateSellerInfo(con, dto);
			JdbcTemplate.commit(con);
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e; 
			
		} finally {
			JdbcTemplate.close(con);	
		}		
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
		
		String tempMemberPw = Utility.getSecureNumberAndString(8);
		int result = dao.updateByMemberPw(memberId, tempMemberPw);
		
		System.out.println("[debug] biz : " + result + ", " + tempMemberPw);
		if (result == 1) {
			return tempMemberPw;
		}
		
		return null;
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
