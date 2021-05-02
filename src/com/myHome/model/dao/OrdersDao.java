package com.myHome.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dto.Cart;
import com.myHome.model.dto.OrdersMethod;
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
}
