package com.myHome.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Interior;
import com.myHome.model.dto.Member;

/**
 * <pre>
 * 		인테리어 테이블에 대한 DAO 클래스
 * </pre>
 * 
 * @author 강하영
 */
public class InteriorDao implements Serializable{

	private static InteriorDao instance = new InteriorDao(); 
	

	public static InteriorDao getInstance() {
		return instance;	
	}
	/**
	 * 인테리어 업체 등록
	 * @param dto 인테리어 
	 * @return 성공시 1, 실패시 0
	 */
	public void insertInterior(Connection con,Interior dto) {
		String sql = "insert into interior values(?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, dto.getIno());
			stmt.setString(2, dto.getIname());
			stmt.setInt(3, dto.getIcareer());
			stmt.setString(4, dto.getIdetail());
			stmt.setString(5, dto.getIlocation());			
			stmt.executeUpdate();
			
		} catch (SQLException e) {						
			e.printStackTrace();
		} finally {			
			JdbcTemplate.close(stmt);			
		}		
	}
	
	/**
	 * 인테리어 업체 수정
	 * @param dto Interior
	 * @return 성공시 true, 실패시 false
	 */
	public void updateInterior(Connection con,Interior interior) throws Exception{
		String sql = "update interior set i_name=?,i_career=?,i_detail=?,i_location=? where i_no=?";

		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, interior.getIname());
			stmt.setInt(2, interior.getIcareer());
			stmt.setString(3, interior.getIdetail());
			stmt.setString(4, interior.getIlocation());
			stmt.setInt(5, interior.getIno());			
			
			int rows =stmt.executeUpdate();
			if(rows == 0) {				
				throw new Exception();
			}
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);			
		}		
	}
	/**
	 * 인테리어 업체 상세조회
	 * @param iname 회사명
	 * @return 회원, 미존재시 null
	 */
	public void selectInterior(Connection conn, Interior interior) throws Exception{
		String sql = "select * from interior where i_name=?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, interior.getIname());
			rs = stmt.executeQuery();
			
			if(rs.next()) {				
				interior.setIno(rs.getInt("i_no"));
				interior.setIname(rs.getString("i_name"));
				interior.setIcareer(rs.getInt("i_career"));
				interior.setIdetail(rs.getString("i_detail"));
				interior.setIlocation(rs.getString("i_location"));
			}
			System.out.println("인테리어 상세조회 테스트 dao : "+interior);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}		
	}
	/**
	 * 인테리어 업체 삭제
	 * @param memberId
	 * @return
	 */
	public void deleteInterior(Connection con,String iname) {
		String sql = "delete from interior where i_name=?";

		PreparedStatement stmt = null;
		
		try {			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, iname);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);			
		}
			
	}
	
	/**
	 * 인테리어 전체 조회
	 * 		- 경력 순으로 정렬
	 * @return ArrayList<Interior>
	 */
	public void selectInteriorList(Connection conn,ArrayList<Interior> list) throws Exception{

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from interior order by i_career desc";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			Interior dto = null;		
			while(rs.next()) {
				dto = new Interior();
				dto.setIno(rs.getInt("i_no"));
				dto.setIname(rs.getString("i_name"));
				dto.setIcareer(rs.getInt("i_career"));
				dto.setIdetail(rs.getString("i_detail"));
				dto.setIlocation(rs.getString("i_location"));
		
				list.add(dto);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);			
		}

	}
}
