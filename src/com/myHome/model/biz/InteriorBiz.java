package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.InteriorDao;
import com.myHome.model.dto.Interior;
import com.myHome.model.dto.Member;
import com.myHome.util.Utility;


/**
 * <pre>
 * 		인테리어 테이블에 대한 Biz 클래스
 * </pre>
 * 
 * @author 강하영
 */
public class InteriorBiz {
	private InteriorDao dao = InteriorDao.getInstance();
	
	/**
	 * <pre>
	 * 인테리어 등록
	 * -- 회원가입 필수 데이터 : 업체명, 경력, 업체 부가설명, 위치
	 * </pre>
	 * @param dto 회원객체
	 * @return 성공시 true, 실패시 false
	 */
	public void insertInterior(Interior interior) {
		Connection con = JdbcTemplate.getConnection();
		
		try {			
			dao.insertInterior(con,interior);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
		} finally {
			JdbcTemplate.close(con);
		}

	}
	
	/**
	 * 인테리어 전체 조회
	 * 		- 경력 순으로 정렬
	 * @return ArrayList<Interior>
	 */
	public void selectInteriorList(ArrayList<Interior> list) throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selectInteriorList(conn, list);
			
		} catch (Exception e) {			
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 인테리어 업체 수정
	 * @param dto Interior
	 * @return 성공시 true, 실패시 false
	 */
	public void updateInterior(Interior interior) throws Exception{
		Connection con = JdbcTemplate.getConnection();
		
		try{
			dao.updateInterior(con, interior);
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
	 * 인테리어 회사 상세조회
	 *	@param iname 회사명
	 */
	public void selectInterior(Interior interior) throws Exception{
		Connection conn = JdbcTemplate.getConnection();
		
		try {
			dao.selectInterior(conn, interior);
			
		} catch (Exception e) {			
			throw e;
		} finally {
			JdbcTemplate.close(conn);	
		}
	}
	
	/**
	 * 인테리어 업체 삭제
	 * @param i_no 번호
	 * @return 성공시 true, 실패시 false
	 */
	public void deleteInterior(String iname) {
		Connection con = JdbcTemplate.getConnection();
		
		try{
			dao.deleteInterior(con, iname);
			JdbcTemplate.commit(con);
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e; 
			
		} finally {
			JdbcTemplate.close(con);	
		}
		
	}
}
