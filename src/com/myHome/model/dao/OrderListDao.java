package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.OrderList;

public class OrderListDao {
	private static OrderListDao instance = new OrderListDao(); // 1. singleton pattern
	
	public static OrderListDao getInstance() {
		return instance; // 2. singleton pattern
	}

	/**
	 * 구매 이력 리스트 요청 메서드
	 * @param orderList 구매 이력 리스트
	 */
	public void getOrderList(ArrayList<OrderList> orderList) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT o.O_NO, o.MEMBER_ID, TO_CHAR(o.O_DATE,'yyyy-mm-dd'), p.P_NAME, p.P_IMG , p.P_PRICE, p.P_SCORE ");
		sql.append("FROM ORDERS o, ORDERS_DETAIL od, PRODUCT p ");
		sql.append("WHERE o.O_NO = od.O_NO and p.P_NO = od.P_NO ");
		sql.append("ORDER BY o.O_NO");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			OrderList dto = null;
			while(rs.next()) {
				dto = new OrderList(
							rs.getInt("O_NO")
							,rs.getString("MEMBER_ID")
							,rs.getString("TO_CHAR(o.O_DATE,'yyyy-mm-dd')")
							,rs.getString("P_NAME")
							,rs.getString("P_IMG")
							,rs.getInt("P_PRICE")
							,rs.getDouble("P_SCORE")
						);
				orderList.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
