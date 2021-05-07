package com.myHome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myHome.model.biz.OrderListBiz;
import com.myHome.model.dto.OrderList;
import com.myHome.model.dto.OrdersDetail;
import com.myHome.model.dto.Review;

/**
 * 구매 이력 및 후기 및 평점 관리 서비스
 * @author 김보성
 */
@WebServlet("/orderList/orderListController")
public class FrontOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch (action) {
			case "orderList":
				orderList(request, response);
				break;
		
			case "orderDetail":
				orderDetail(request, response);
				break;
		
			case "reviewInputForm":
				reviewInputForm(request, response);	
				break;
			
			case "reviewInput":
				reviewInput(request, response);	
				break;
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 구매 이력 리스트 요청 서비스
	 */
	private void orderList(HttpServletRequest request, HttpServletResponse response) {
		OrderListBiz biz = new OrderListBiz();
		ArrayList<OrderList> orderList = new ArrayList<OrderList>();
		
		try {
			biz.getOrderList(orderList);
			
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("/orderList/orderList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	/**
	 * 구매 이력 상세 조회 요청 서비스
	 */
	private void orderDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		String oDate = request.getParameter("oDate");
		OrderListBiz biz = new OrderListBiz();
		
		ArrayList<OrdersDetail> orderDetailList = new ArrayList<OrdersDetail>();
		biz.getOrderDetailList(oNo, orderDetailList);
		
		ArrayList<Integer> reviewList = new ArrayList<Integer>();
		biz.getReviewList(reviewList,memberId);
		int length = orderDetailList.size() * 2;
		request.setAttribute("oNo", oNo);
		request.setAttribute("oDate", oDate);
		request.setAttribute("orderDetailList", orderDetailList);
		request.setAttribute("length", length);
		try {
			request.getRequestDispatcher("/orderList/orderDetail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void reviewInputForm(HttpServletRequest request, HttpServletResponse response) {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		String pImg = request.getParameter("pImg");
		String pName = request.getParameter("pName");
		String oDate = request.getParameter("oDate");
		request.setAttribute("pNo", pNo);
		request.setAttribute("pImg", pImg);
		request.setAttribute("pName", pName);
		request.setAttribute("oDate", oDate);
		try {
			request.getRequestDispatcher("/orderList/reviewInputForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void reviewInput(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		String oDate = request.getParameter("oDate");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		String pImg = request.getParameter("pImg");
		String reviewContent = request.getParameter("reviewContent");
		int score = Integer.parseInt(request.getParameter("score"));
		Review dto = new Review(memberId, oDate, pNo, pImg,reviewContent, score);
		OrderListBiz biz = new OrderListBiz();
		
		biz.addReview(dto);
		PrintWriter writer;
		try {
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			writer.println("<script>alert('후기 작성이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberMyPage.jsp';</script>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
