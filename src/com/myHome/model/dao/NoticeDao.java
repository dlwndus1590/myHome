package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Notice;

public class NoticeDao {
	public void selectList(ArrayList<Notice> list) {
		String sql = "SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS FROM NOTICE ORDER BY N_NO DESC";
		
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
				Notice dto = null;
				while (rs.next()) {
					dto = new Notice(
							rs.getInt("N_NO")
							, rs.getString("MEMBER_ID")
							, rs.getString("N_TITLE")
							, rs.getString("N_CONTENT")
							, rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')")
							, rs.getInt("N_HITS")
							);
					list.add(dto);
				}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 상세보기 메서드
	 * @param noticeNum 게시글 번호
	 * @return Notice
	 */
	public Notice NoticeDetail(int nNo) {
		Notice dto = new Notice();
		StringBuffer sql =  new StringBuffer();
		sql.append("SELECT N_NO, MEMBER_ID, N_TITLE, N_CONTENT, TO_CHAR(N_REG_DATE,'yyyy-mm-dd'), N_HITS ");
		sql.append("FROM NOTICE ");
		sql.append("WHERE N_NO = ?");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, nNo);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					dto.setnNo(rs.getInt("N_NO"));
					dto.setMemberId(rs.getString("MEMBER_ID"));
					dto.setnTitle(rs.getString("N_TITLE"));
					dto.setnContent(rs.getString("N_CONTENT"));
					dto.setnRegDate(rs.getString("TO_CHAR(N_REG_DATE,'yyyy-mm-dd')"));
					dto.setnHits(rs.getInt("N_HITS"));
					return dto;
				}
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
}
