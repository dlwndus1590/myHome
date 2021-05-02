package com.myHome.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Interior;


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
}
