package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Cart;
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
	
	/*
	 * 장바구니 리스트 조회
	 */
	public void getCartList(Connection conn, ArrayList<Cart> cartList) throws Exception {
		String sql = "select * from cart";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			Cart cart = null;
			
			while(rs.next()) {
				cart = new Cart();
				cart.setcNo(rs.getInt("c_no"));
				cart.setMemberId(rs.getString("member_id"));
				cart.setpNo(rs.getInt("p_no"));
				cart.setcCount(rs.getInt("c_count"));
				
				cartList.add(cart);
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
	
	/*
	 * 장바구니 상품 정보
	 */
	public void getCartInfo(Connection conn, ArrayList<Product> list) {
		String sql = "select * from cart c join product p on (c.p_no = p.p_no)";
	}
	
	/*
	 * 결제수단 리스트 조회
	 */
	public void getMethodList(Connection conn, ArrayList<OrdersMethod> methodList) throws Exception {
		String sql = "select * from orders_method";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			OrdersMethod ordersMethod = null;
			
			while(rs.next()) {
				ordersMethod = new OrdersMethod();
				ordersMethod.setoMethodId(rs.getInt("o_method_id"));
				ordersMethod.setoMethodName(rs.getString("o_method_name"));
				
				methodList.add(ordersMethod);
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

	/*
	 * 결제페이지 조회
	 */
	public void getOrdersPage(Connection conn, String memberId, ArrayList<OrdersPage> ordersList) throws Exception {
		String sql = "select m.name, m.email, m.mobile, m.mileage, m.zip_code, m.address1, m.address2, " + 
				   "p.p_no, p.p_name, p.p_price, c.c_count, p.p_img, p.p_describe, " + 
				   "p.p_count - c.c_count as stock, p.delivery_fee, p.p_price + p.delivery_fee as total_price " + 
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
			System.out.println(memberId);
			
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
}
