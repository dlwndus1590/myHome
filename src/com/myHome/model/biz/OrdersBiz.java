package com.myHome.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.myHome.common.JdbcTemplate;
import com.myHome.model.dao.OrdersDao;
import com.myHome.model.dto.OrdersPage;

public class OrdersBiz {
	private OrdersDao dao = OrdersDao.getInstance();

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
	
	
}
