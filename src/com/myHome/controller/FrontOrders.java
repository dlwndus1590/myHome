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
		case "cartInsert":
			cartInsert(request, response);	
			break;
		case "DetailsCartInsert":
			DetailsCartInsert(request, response);	
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
	
	/**
	 * 장바구니 결제페이지
	 */
	private void cartOrdersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		String[] pNo = request.getParameterValues("pNo");
		String[] count = request.getParameterValues("itemCount");
		String[] totalCost = request.getParameterValues("totalCost[]");
		int total = 0;
		ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		for (int i = 0; i < pNo.length; i++) {
			total += Integer.parseInt(totalCost[i]);
			try {
				ordersBiz.cartUpdate(pNo[i], count[i], memberId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			ordersBiz.getOrdersPage(memberId, ordersList);
			session.setAttribute("ordersList", ordersList);
			session.setAttribute("totalCost", total);
			response.sendRedirect(CONTEXT_PATH + "/member/cartOrdersPage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 단일 상품 결제페이지(상품상세에서 memberId - 세션, pNo, cCount)
	 */
	private void ordersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		//String[] totalPrice = request.getParameterValues("totalPrice");
		String[] totalCost = request.getParameterValues("totalCost[]");
		//String[] count = request.getParameterValues("itemCount");
		int total = 0;
		for (int i = 0; i < totalCost.length; i++) {
			System.out.println(i + ", " + totalCost[i]);
			//pNo = Integer.parseInt(pNo1[i]);
			total += Integer.parseInt(totalCost[i]);
		}
		System.out.println("total : " + total);
		String memberId = "user01";
		ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.getOrdersPage(memberId, ordersList);
			
			session.setAttribute("ordersList", ordersList);
			session.setAttribute("totalCost", total);
			response.sendRedirect(CONTEXT_PATH + "/member/ordersPage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
			
		}
	}
	
	/**
	 * 다중 결제하기
	 */
	private void orders(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		String optionsRadios = request.getParameter("optionsRadios");
		String usedMileage = request.getParameter("usedMileage");
		String currentMileage = request.getParameter("accumulateMileage");
		String[] totalPrice = request.getParameterValues("totalPrice");
		String[] deliveryFee = request.getParameterValues("deliveryFee");
		String zipCode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		int i = Integer.parseInt(currentMileage);
		int j = Integer.parseInt(usedMileage);
		System.out.println("optionsRadios : " + optionsRadios);
		System.out.println("currentMileage : " + i);
		System.out.println("usedMileage : " + j);
		int accumulateMileage = i - j;
		System.out.println("accumulateMileage : " + accumulateMileage);
		System.out.println("zipCode : " + zipCode);
		System.out.println("address1 : " + address1);
		System.out.println("address2 : " + address2);
		
		for (int index = 0; index < totalPrice.length; index++) {
			System.out.println("totalPrice : " + totalPrice[index]);
			System.out.println("deliveryFee : " + deliveryFee[index]);
			// insert 처리
			if (accumulateMileage > 0) {
				
			}
		}
	}
	
	/**
	 * 카테고리, 베스트 페이지에서 장바구니 담기
	 */
	private void cartInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = 1;
		
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.cartInsert(memberId, pNo, count);
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
		}
	}
	
	/**
	 * 상품 상세 페이지에서 장바구니 담기
	 */
	private void DetailsCartInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//String memberId = (String) session.getAttribute("memberId");
		String memberId = "user01";
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			ordersBiz.cartInsert(memberId, pNo, count);
		} catch (Exception e) {
			e.printStackTrace();
			// 에러처리 페이지로 이동
		}
	}
}
