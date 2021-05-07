package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.OrderList;
import com.myHome.model.dto.OrdersDetail;
import com.myHome.model.dto.Review;
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
		sql.append("SELECT o.O_NO, o.MEMBER_ID, TO_CHAR(o.O_DATE,'yyyy-mm-dd') ,p.P_NAME, p.P_IMG , p.P_PRICE, p.P_SCORE ");
		sql.append("FROM ORDERS o, ORDERS_DETAIL od, PRODUCT p ");
		sql.append("WHERE o.O_NO = od.O_NO and p.P_NO = od.P_NO ");
		sql.append("ORDER BY o.O_NO desc");
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
		sql.append("SELECT od.D_NO, o.MEMBER_ID, TO_CHAR(o.O_DATE,'yyyy-mm-dd'),p.p_No ,p.P_NAME, p.P_IMG , p.P_PRICE * od.D_COUNT AS total, od.D_COUNT, o.O_DELIVERY_FEE ");
		sql.append(", o.usedMileage, o.accumulateMileage,o.O_TOTAL_PRICE, o.O_TOTAL_PRICE + o.O_DELIVERY_FEE AS oTotalPricePlusFee ");
		sql.append(", o.O_TOTAL_PRICE, o.o_total_price + o.o_delivery_fee - o.usedmileage As totalAmount,o.ZIP_CODE, o.ADDRESS1, o.ADDRESS2, m.NAME, m.MOBILE ");
		sql.append("FROM ORDERS o, ORDERS_DETAIL od, PRODUCT p, MEMBER m ");
		sql.append("WHERE o.O_NO = od.O_NO and p.P_NO = od.P_NO and m.MEMBER_ID = o.MEMBER_ID and o.O_NO = ? ");
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
						 ,rs.getString("TO_CHAR(o.O_DATE,'yyyy-mm-dd')")
						 ,rs.getInt("p_No")
						 ,rs.getString("P_NAME")
						 ,rs.getString("P_IMG")
						 ,Utility.convertNumberString(rs.getInt("total")) 
						 ,rs.getInt("D_COUNT")
						 ,Utility.convertNumberString(rs.getInt("O_DELIVERY_FEE"))
						 ,Utility.convertNumberString(rs.getInt("usedMileage"))
						 ,Utility.convertNumberString(rs.getInt("accumulateMileage"))
						 ,Utility.convertNumberString(rs.getInt("O_TOTAL_PRICE"))
						 ,Utility.convertNumberString(rs.getInt("oTotalPricePlusFee"))
						 ,Utility.convertNumberString(rs.getInt("totalAmount"))
						 ,rs.getInt("ZIP_CODE")
						 ,rs.getString("ADDRESS1")
						 ,rs.getString("ADDRESS2")
						 ,rs.getString("NAME")
						 ,rs.getString("MOBILE")
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

	public int reviewLastNum() {
		String sql = "SELECT MAX(R_NO) FROM REVIEW";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return rs.getInt("MAX(R_NO)");
				}
			}
		} catch (SQLException e) {
			System.out.println("message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return -1;
	}
	
	/**
	 * 후기 등록 요청 메서드
	 * @param dto 후기 객체
	 */
	public void addReview(int row, Review dto) {
		String sql = "INSERT INTO REVIEW VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, row);
			pstmt.setInt(2, dto.getpNo());
			pstmt.setString(3, dto.getpImg());
			pstmt.setString(4, dto.getReviewContent());
			pstmt.setInt(5, dto.getScore());
			pstmt.setString(6, dto.getMemberId());
			pstmt.setString(7, dto.getoDate());
			pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
		}catch(SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println("Message : " +e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 해당 상품 후기 리스트 요청 메서드
	 * @param list 상품 후기 리스트
	 */
	public void reviewList(int pNo, ArrayList<Review> list) {
		String sql = "SELECT P_NO, R_IMG, R_CONTENT, R_SCORE, MEMBER_ID, TO_CHAR(R_REG_DATE,'yyyy-mm-dd') FROM REVIEW WHERE P_NO = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			Review dto = null;
			while(rs.next()) {
				dto = new Review(
							rs.getString("MEMBER_ID")
						   ,rs.getString("TO_CHAR(R_REG_DATE,'yyyy-mm-dd')")
						   ,rs.getInt("P_NO")
						   ,rs.getString("R_IMG")
						   ,rs.getString("R_CONTENT")
						   ,rs.getInt("R_SCORE")
						);
				list.add(dto);
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
	 * 현재 회원이 이미 후기를 등록한 상품 번호 리스트 요청 메서드 
	 * @param reviewList 후기등록된 상품 번호 리스트
	 * @param memberId 로그인한 회원 아이디
	 */
	public void getReviewList(ArrayList<Integer> reviewList, String memberId) {
		String sql = "SELECT P_NO FROM REVIEW WHERE MEMBER_ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			int pNo = 0;
			while(rs.next()) {
				pNo = rs.getInt("P_NO");
				reviewList.add(pNo);
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
