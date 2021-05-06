package com.myHome.model.biz;

import java.util.ArrayList;

import com.myHome.model.dao.OrderListDao;
import com.myHome.model.dto.OrderList;
import com.myHome.model.dto.OrdersDetail;

public class OrderListBiz {
	private OrderListDao dao = OrderListDao.getInstance(); // 3. singleton pattern
	
	/**
	 * 구매 이력 리스트 요청 메서드
	 * @param orderList 구매 이력 리스트
	 */
	public void getOrderList(ArrayList<OrderList> orderList) {
		dao.getInstance().getOrderList(orderList);
	}

	/**
	 * 구매 이력 상세 리스트 요청 메서드
	 * @param oNo 구매 번호
	 * @param orderDetailList 구매 이력 상세 리스트
	 */
	public void getOrderDetailList(int oNo, ArrayList<OrdersDetail> orderDetailList) {
		dao.getInstance().getOrderDetailList(oNo, orderDetailList);
	}
}
