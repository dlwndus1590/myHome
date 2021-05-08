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
	 * @param memberId 구매자 아이디
	 * @param orderList 구매 이력 리스트
	 */
	public void getOrderList(String memberId, ArrayList<OrderList> orderList) {
		dao.getInstance().getOrderList(memberId, orderList);
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
	public int addReview(Review dto) {
		int row = reviewLastNum() + 1;
		return dao.getInstance().addReview(row, dto);
	}
	
	/**
	 * 해당 상품 후기 리스트 요청 메서드
	 * @param list 상품 후기 리스트
	 */
	public void reviewList(int pNo, ArrayList<Review> list) {
		dao.getInstance().reviewList(pNo, list);
	}

	/**
	 * 후기 작성 완료 후 주문 상세 테이블에 주문체크를 평가로 업데이트 요청 메서드
	 * @param score 평가한 점수
	 * @param pNo 주문할 상품 번호
	 * @param oNo 주문 번호
	 */
	public void reviewCheckTrue(int score,int pNo, int oNo) {
		dao.getInstance().reviewCheckTrue(score, pNo, oNo);
	}

	/**
	 * 로그인한 회원의 후기 리스트 요청 메서드
	 * @param memberId 로그인한 회원의 아이디
	 * @param reviewList 로그인한 회원의 후기 리스트
	 */
	public void getReviewList(String memberId, ArrayList<Review> reviewList) {
		dao.getInstance().getReviewList(memberId, reviewList);
	}
}
