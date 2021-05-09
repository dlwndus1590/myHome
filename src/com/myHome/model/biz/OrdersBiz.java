package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.OrdersDao;
import com.myHome.model.dto.OrdersPage;

/**
 * 장바구니, 결제 관련 biz
 * @author 최인묵
 */
public class OrdersBiz {
	private OrdersDao dao = OrdersDao.getInstance();

	/**
	 * 장바구니 페이지 조회
	 * @param memberId
	 * @param cartList
	 * @throws Exception
	 */
	public void getCartPage(String memberId, ArrayList<OrdersPage> cartList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getCartPage(conn, memberId, cartList);
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 다중 결제 페이지 조회
	 * @param memberId
	 * @param ordersList
	 * @throws Exception
	 */
	public void getOrdersPage(String memberId, ArrayList<OrdersPage> ordersList) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getOrdersPage(conn, memberId, ordersList);
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 단일 결제 페이지 조회
	 * @param memberId
	 * @param pNo
	 * @param ordersPage
	 * @throws Exception
	 */
	public void getSingleOrdersPage(String memberId, int pNo, OrdersPage ordersPage) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getSingleOrdersPage(conn, memberId, pNo, ordersPage);
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 장바구니 목록 삭제
	 * @param memberId
	 * @param pNo
	 * @throws Exception
	 */
	public void cartDelete(String memberId, int pNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.cartDelete(conn, memberId, pNo);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 장바구니 수량 변경
	 * @param pNo
	 * @param count
	 * @param memberId
	 * @throws Exception
	 */
	public void cartUpdate(String pNo, String count, String memberId) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		int pNo1 = Integer.parseInt(pNo);
		int count1 = Integer.parseInt(count);
		try {
			dao.cartUpdate(conn, pNo1, count1, memberId);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 장바구니 담기
	 * @param memberId
	 * @param pNo
	 * @param count
	 * @throws Exception
	 */
	public void cartInsert(String memberId, int pNo, int count) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.cartInsert(conn, memberId, pNo, count);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 결제하기
	 * @param memberId
	 * @param orderMethod
	 * @param totalP
	 * @param totalDeliveryFee
	 * @param usedMileage
	 * @param accumulateMileage
	 * @param zipCode
	 * @param address1
	 * @param address2
	 * @return
	 * @throws Exception
	 */
	public int orders(String memberId, int orderMethod, int totalP, int totalDeliveryFee, int usedMileage,
			int accumulateMileage, int zipCode, String address1, String address2) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			int result = dao.orders(conn, memberId, orderMethod, totalP, totalDeliveryFee, usedMileage, accumulateMileage, zipCode, address1, address2);
			JdbcTemplate.commit(conn);
			return result;
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 주문상세
	 * @param count
	 * @param pNo
	 * @throws Exception
	 */
	public void ordersDetail(int count, int pNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.ordersDetail(conn, count, pNo);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 마일리지 적립
	 * @param memberId
	 * @param getMileage
	 * @throws Exception
	 */
	public void updateMileage(String memberId, int getMileage) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.updateMileage(conn, memberId, getMileage);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 바로구매시 장바구니에 동일한 상품이 있는지 확인
	 * @param memberId
	 * @param pNo
	 * @return
	 * @throws Exception
	 */
	public boolean isValid(String memberId, int pNo) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			return dao.isValid(conn, memberId, pNo);
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/**
	 * 바로구매시 동일 상품 수량 변경
	 * @param pNo
	 * @param count
	 * @param memberId
	 * @throws Exception
	 */
	public void cartUpdate(int pNo, int count, String memberId) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.cartUpdate(conn, pNo, count, memberId);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(conn);
		}
	}
}
