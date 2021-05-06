package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.OrderList;
import com.myHome.model.dto.OrdersDetail;
import com.myHome.util.Utility;

import oracle.net.aso.r;

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
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 구매 이력 상세 리스트 요청 메서드
	 * @param oNo 구매 번호
	 * @param orderDetailList 구매 이력 상세 리스트
	 */
	public void getOrderDetailList(int oNo, ArrayList<OrdersDetail> orderDetailList) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT od.D_NO, o.MEMBER_ID, p.P_NAME, p.P_IMG , p.P_PRICE * od.D_COUNT AS total, od.D_COUNT, o.O_DELIVERY_FEE, o.usedMileage, o.accumulateMileage,o.O_TOTAL_PRICE,o.O_TOTAL_PRICE, o.o_total_price + o.o_delivery_fee - o.usedmileage As totalAmount ");
		sql.append("FROM ORDERS o, ORDERS_DETAIL od, PRODUCT p ");
		sql.append("WHERE o.O_NO = od.O_NO and p.P_NO = od.P_NO and o.O_NO = ? ");
		sql.append("ORDER BY od.D_NO ASC");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();
			OrdersDetail dto = null;
			while(rs.next()) {
				dto = new OrdersDetail(
						  rs.getInt("D_NO")
						 ,rs.getString("MEMBER_ID")
						 ,rs.getString("P_NAME")
						 ,rs.getString("P_IMG")
						 ,Utility.convertNumberString(rs.getInt("total")) 
						 ,rs.getInt("D_COUNT")
						 ,Utility.convertNumberString(rs.getInt("O_DELIVERY_FEE"))
						 ,Utility.convertNumberString(rs.getInt("usedMileage"))
						 ,Utility.convertNumberString(rs.getInt("accumulateMileage"))
						 ,Utility.convertNumberString(rs.getInt("O_TOTAL_PRICE"))
						 ,Utility.convertNumberString(rs.getInt("totalAmount"))
						);
				orderDetailList.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("Message : " + e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}
}
