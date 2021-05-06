package com.myHome.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		case "cartPage":
			cartPage(request, response);	
			break;
		case "ordersPage":
			ordersPage(request, response);	
			break;
		case "cartOrdersPage":
			cartOrdersPage(request, response);	
			break;
		case "orders":
			orders(request, response);	
			break;
		case "cartDelete":
			cartDelete(request, response);	
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
	 * 장바구니페이지
	 */
	private void cartPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		ArrayList<OrdersPage> cartList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.getCartPage(memberId, cartList);
			session.setAttribute("cartList", cartList);
			
			response.sendRedirect(CONTEXT_PATH + "/member/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
			
		}
	}
	
	/**
	 * 장바구니 삭제
	 */
	private void cartDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		System.out.println(pNo);
		String memberId = "user01";
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.cartDelete(memberId, pNo);
			response.sendRedirect(CONTEXT_PATH + "/member/ordersController?action=cartPage");
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
		}
	}
	
	private void cartOrdersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// String memberId = (String) session.getAttribute("memberId");
		// 수량, 상품 번호를 받아서 update 쿼리문 실행 후 select
		String memberId = "user01";
		String[] pNo = request.getParameterValues("pNo");
		String[] count = request.getParameterValues("itemCount");
		//String[] totalCost = request.getParameterValues("totalCost[]");
		//int total = 0;
		//ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		for (int i = 0; i < pNo.length; i++) {
			System.out.println("pNo.length : " + pNo.length);
			System.out.println(i + ", pNo : " + pNo[i]);
			System.out.println(i + ", count : " + count[i]);
			//total += Integer.parseInt(totalCost[i]);
			try {
				System.out.println("[debug1]");
				ordersBiz.cartUpdate(pNo[i], count[i], memberId);
				//response.sendRedirect(CONTEXT_PATH + "/member/ordersController?action=ordersPage");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * try { ordersBiz.getOrdersPage(memberId, ordersList);
		 * session.setAttribute("ordersList", ordersList);
		 * session.setAttribute("totalCost", total); response.sendRedirect(CONTEXT_PATH
		 * + "/member/cartOrdersPage.jsp"); } catch (Exception e) { e.printStackTrace();
		 * }
		 */
		
	}
	
	/**
	 * 장바구니 결제페이지(금액, 최종수량, 총합계) 
	 */
	private void ordersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		// 상품 번호에 따른 최종수량 -> view 최종수량 * 상품금액
		// HashMap<Integer, String> map = new HashMap<Integer, String>();
		//HashMap<String, String> map = new HashMap<String, String>();
		//String[] pNo1 = request.getParameterValues("pNo");
		//int pNo = 0;
		//System.out.println(pNo1[0]);
		//System.out.println(pNo1[1]);
		//String[] totalPrice = request.getParameterValues("totalPrice");
		String[] totalCost = request.getParameterValues("totalCost[]");
		//String[] count = request.getParameterValues("itemCount");
		//System.out.println("count : " + count);
		int total = 0;
		for (int i = 0; i < totalCost.length; i++) {
			System.out.println(i + ", " + totalCost[i]);
			//pNo = Integer.parseInt(pNo1[i]);
			//map.put("totalPrice" + i, totalPrice[i]);
			total += Integer.parseInt(totalCost[i]);
		}
		System.out.println("total : " + total);
		//System.out.println(map);
		//System.out.println("pNo : " + pNo);
		String memberId = "user01";
		ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.getOrdersPage(memberId, ordersList);
			
			session.setAttribute("ordersList", ordersList);
			//session.setAttribute("map", map);
			session.setAttribute("totalCost", total);
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
