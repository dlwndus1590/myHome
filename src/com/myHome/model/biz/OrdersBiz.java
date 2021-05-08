package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.OrdersDao;
import com.myHome.model.dto.OrdersPage;

public class OrdersBiz {
	private OrdersDao dao = OrdersDao.getInstance();

	public void getCartPage(String memberId, ArrayList<OrdersPage> cartList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getCartPage(conn, memberId, cartList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	public void getOrdersPage(String memberId, ArrayList<OrdersPage> ordersList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getOrdersPage(conn, memberId, ordersList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	public void getSingleOrdersPage(String memberId, int pNo, OrdersPage ordersPage) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getSingleOrdersPage(conn, memberId, pNo, ordersPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	public void cartDelete(String memberId, int pNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.cartDelete(conn, memberId, pNo);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void cartUpdate(String pNo, String count, String memberId) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		int pNo1 = Integer.parseInt(pNo);
		int count1 = Integer.parseInt(count);
		try {
			dao.cartUpdate(conn, pNo1, count1, memberId);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void cartInsert(String memberId, int pNo, int count) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.cartInsert(conn, memberId, pNo, count);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public int orders(String memberId, int orderMethod, int totalP, int totalDeliveryFee, int usedMileage,
			int accumulateMileage, int zipCode, String address1, String address2) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			int result = dao.orders(conn, memberId, orderMethod, totalP, totalDeliveryFee, usedMileage, accumulateMileage, zipCode, address1, address2);
			JdbcTemplate.commit(conn);
			return result;
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	public void ordersDetail(int count, int pNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.ordersDetail(conn, count, pNo);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}


}
