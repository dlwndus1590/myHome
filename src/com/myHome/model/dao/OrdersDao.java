package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.OrdersMethod;
import com.myHome.model.dto.OrdersPage;
import com.myHome.model.dto.Product;

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
		String sql = "insert into cart values((select max(nvl(c_no, 0)) + 1 from cart), ?, ?, ?)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, pNo);
			stmt.setInt(3, count);
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
}
