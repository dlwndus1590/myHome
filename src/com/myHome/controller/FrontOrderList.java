package com.myHome.controller;

import java.io.IOException;
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
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		try {
			request.getRequestDispatcher("/orderList/orderDetail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
