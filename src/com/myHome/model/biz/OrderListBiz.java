package com.myHome.model.biz;

import java.util.ArrayList;

import com.myHome.model.dao.OrderListDao;
import com.myHome.model.dto.OrderList;
import com.myHome.model.dto.OrdersDetail;
import com.myHome.model.dto.Review;

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
	
	/**
	 * 마지막 리뷰 번호 반환 메서드
	 * @return 마지막 리뷰 번호
	 */
	public int reviewLastNum() {
		return dao.getInstance().reviewLastNum();
	}
	
	/**
	 * 후기 등록 요청 메서드
	 * @param dto 후기 객체
	 */
	public void addReview(Review dto) {
		int row = reviewLastNum() + 1;
		dao.getInstance().addReview(row, dto);
	}
	
	/**
	 * 해당 상품 후기 리스트 요청 메서드
	 * @param list 상품 후기 리스트
	 */
	public void reviewList(int pNo, ArrayList<Review> list) {
		dao.getInstance().reviewList(pNo, list);
	}

	/**
	 * 현재 회원이 이미 후기를 등록한 상품 번호 리스트 요청 메서드 
	 * @param reviewList 후기등록된 상품 번호 리스트
	 * @param memberId 로그인한 회원 아이디
	 */
	public void getReviewList(ArrayList<Integer> reviewList, String memberId) {
		dao.getInstance().getReviewList(reviewList, memberId);
	}
}
