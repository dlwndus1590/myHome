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
	public int insertInterior(Interior dto) {
		String sql = "insert into interior values(?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, dto.getIno());
			stmt.setString(2, dto.getIname());
			stmt.setInt(3, dto.getIcareer());
			stmt.setString(4, dto.getIdetail());
			stmt.setString(5, dto.getIlocation());			
					
			JdbcTemplate.commit(conn);
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {			
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;		
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
