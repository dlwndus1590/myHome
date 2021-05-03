package com.myHome.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myHome.model.biz.OrdersBiz;
import com.myHome.model.dto.OrdersPage;

/**
 * Servlet implementation class FrontOrders
 */
@WebServlet("/member/ordersController")
public class FrontOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String)application.getAttribute("CONTEXT_PATH");
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch(action) {
		case "ordersPage":
			ordersPage(request, response);	
			break;
		case "orders":
			orders(request, response);	
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 결제페이지
	 */
	private void ordersPage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ordersPage");
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.getOrdersPage(memberId, ordersList);
			
			session.setAttribute("ordersList", ordersList);
			System.out.println(ordersList);
			response.sendRedirect(CONTEXT_PATH + "/member/ordersPage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
		}
	}
	
	/**
	 * 결제하기
	 */
	private void orders(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
