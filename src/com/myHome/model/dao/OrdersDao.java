package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.OrdersPage;

/**
 * 장바구니, 결제 관련 dao
 * @author 최인묵
 */
public class OrdersDao {
	private static OrdersDao instance = new OrdersDao();
	
	public OrdersDao() {
	}
	
	public static OrdersDao getInstance() {
		return instance;
	}
	
	/**
	 * 장바구니 페이지 조회
	 */
	public void getCartPage(Connection conn, String memberId, ArrayList<OrdersPage> cartList) throws Exception {
		String sql = "select p.p_no, p.p_name, p.p_price, c.c_count, p.p_img, p.delivery_fee,(p.p_price * c.c_count) as total_price, p.p_count " + 
				"from cart c join product p on (c.p_no = p.p_no) " + 
				"where member_id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			OrdersPage ordersPage = null;
			
			while(rs.next()) {
				ordersPage = new OrdersPage(
						rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				
				cartList.add(ordersPage);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 결제 페이지 조회
	 */
	public void getOrdersPage(Connection conn, String memberId, ArrayList<OrdersPage> ordersList) throws Exception {
		String sql = "select m.name, m.email, m.mobile, m.mileage, m.zip_code, m.address1, m.address2, " + 
				   		"p.p_no, p.p_name, p.p_price, c.c_count, p.p_img, p.p_describe, " + 
				   		"p.p_count - c.c_count as stock, p.delivery_fee, (p.p_price * c.c_count) as total_price " + 
				   	"from product p join cart c on (p.p_no = c.p_no) " + 
				   	"join member m on (m.member_id = c.member_id) " + 
				   	"where m.member_id = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			OrdersPage ordersPage = null;
			
			while(rs.next()) {
				ordersPage = new OrdersPage();
				
				ordersPage.setName(rs.getString(1));
				ordersPage.setEmail(rs.getString(2));
				ordersPage.setMobile(rs.getString(3));
				ordersPage.setMileage(rs.getInt(4));
				ordersPage.setZipCode(rs.getInt(5));
				ordersPage.setAddress1(rs.getString(6));
				ordersPage.setAddress2(rs.getString(7));
				ordersPage.setpNo(rs.getInt(8));
				ordersPage.setpName(rs.getString(9));
				ordersPage.setpPrice(rs.getInt(10));
				ordersPage.setcCount(rs.getInt(11));
				ordersPage.setpImg(rs.getString(12));
				ordersPage.setpDescribe(rs.getString(13));
				ordersPage.setStock(rs.getInt(14));
				ordersPage.setDeliveryFee(rs.getInt(15));
				ordersPage.setTotalPrice(rs.getInt(16));
				
				ordersList.add(ordersPage);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/**
	 * 단일 결제 페이지 조회
	 */
	public void getSingleOrdersPage(Connection conn, String memberId, int pNo, OrdersPage ordersPage) throws Exception {
		String sql = "select m.name, m.email, m.mobile, m.mileage, m.zip_code, m.address1, m.address2, " + 
				   		"p.p_no, p.p_name, p.p_price, c.c_count, p.p_img, p.p_describe, " + 
				   		"p.p_count - c.c_count as stock, p.delivery_fee, (p.p_price * c.c_count) as total_price " + 
				   	"from product p join cart c on (p.p_no = c.p_no) " + 
				   	"join member m on (m.member_id = c.member_id) " + 
				   	"where m.member_id = ? and p.p_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, pNo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				ordersPage.setName(rs.getString(1));
				ordersPage.setEmail(rs.getString(2));
				ordersPage.setMobile(rs.getString(3));
				ordersPage.setMileage(rs.getInt(4));
				ordersPage.setZipCode(rs.getInt(5));
				ordersPage.setAddress1(rs.getString(6));
				ordersPage.setAddress2(rs.getString(7));
				ordersPage.setpNo(rs.getInt(8));
				ordersPage.setpName(rs.getString(9));
				ordersPage.setpPrice(rs.getInt(10));
				ordersPage.setcCount(rs.getInt(11));
				ordersPage.setpImg(rs.getString(12));
				ordersPage.setpDescribe(rs.getString(13));
				ordersPage.setStock(rs.getInt(14));
				ordersPage.setDeliveryFee(rs.getInt(15));
				ordersPage.setTotalPrice(rs.getInt(16));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 장바구니 삭제
	 */
	public void cartDelete(Connection conn, String memberId, int pNo) throws Exception {
		String sql = "delete from cart where member_id = ? and p_no = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, pNo);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 결제하기 버튼 클릭시 수량정보 변경
	 */
	public void cartUpdate(Connection conn, int pNo1, int count1, String memberId) throws Exception {
		String sql = "update cart set c_count = ? where p_no = ? and member_id = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, count1);
			stmt.setInt(2, pNo1);
			stmt.setString(3, memberId);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 장바구니 담기
	 */
	public void cartInsert(Connection conn, String memberId, int pNo, int count) throws Exception {
		String sql = "insert into cart (c_no, member_id, p_no, c_count) " + 
						"select " + 
						"(select nvl(max(c_no), 0) + 1 from cart), ?, ?, ? " + 
						"from dual " + 
						"where not exists (select * from cart where p_no = ? and member_id = ?)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, pNo);
			stmt.setInt(3, count);
			stmt.setInt(4, pNo);
			stmt.setString(5, memberId);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 결제하기
	 */
	public int orders(Connection conn, String memberId, int orderMethod, int totalPrice, int deliveryFee,
			int usedMileage, int accumulateMileage, int zipCode, String address1, String address2) throws Exception {
		String sql = "insert into orders " + 
				"values ((select nvl(max(o_no), 0) + 1 from orders), ?, ?, ?, " + 
				"to_char(sysdate, 'yyyy-mm-dd'), ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, orderMethod);
			stmt.setInt(3, totalPrice);
			stmt.setInt(4, deliveryFee);
			stmt.setInt(5, usedMileage);
			stmt.setInt(6, accumulateMileage);
			stmt.setInt(7, zipCode);
			stmt.setString(8, address1);
			stmt.setString(9, address2);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 주문상세
	 */
	public void ordersDetail(Connection conn, int count, int pNo) throws Exception {
		String sql = "insert into orders_detail values ((select nvl(max(d_no), 0) + 1 from orders_detail), ?, ?, "
				+ "(select nvl(max(o_no), 0) from orders), 0)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, count);
			stmt.setInt(2, pNo);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 마일리지 적립
	 */
	public void updateMileage(Connection conn, String memberId, int getMileage) throws Exception {
		String sql = "update member set mileage = ? where member_id = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getMileage);
			stmt.setString(2, memberId);
			int rows = stmt.executeUpdate();
			if (rows == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	
	public boolean isValid(Connection conn, String memberId, int pNo) throws Exception {
		String sql = "select * from cart where member_id = ? and p_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, pNo);
			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
}
