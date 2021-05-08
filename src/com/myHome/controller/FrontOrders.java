package com.myHome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myHome.model.biz.OrdersBiz;
import com.myHome.model.biz.ProductBiz;
import com.myHome.model.dto.OrdersPage;
import com.myHome.model.dto.Product;

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
		String memberId = (String) session.getAttribute("memberId");
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
		String memberId = (String) session.getAttribute("memberId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
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
		String memberId = (String) session.getAttribute("memberId");
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
	 * 단일 상품 결제페이지(상품상세에서 memberId - 세션, pNo, cCount, 가격 가져와서 가격 * 수량)
	 */
	private void ordersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		int deliveryFee = Integer.parseInt(request.getParameter("deliveryFee"));
		int total = (pPrice * count) + deliveryFee;
		
		ArrayList<OrdersPage> ordersList = new ArrayList<OrdersPage>();
		OrdersBiz ordersBiz = new OrdersBiz();
		// 장바구니 추가
		try {
			
			ordersBiz.cartInsert(memberId, pNo, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
	 * 결제하기
	 */
	private void orders(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		int orderMethod = Integer.parseInt(request.getParameter("optionsRadios"));
		int usedMileage = Integer.parseInt(request.getParameter("usedMileage"));
		int currentMileage = Integer.parseInt(request.getParameter("accumulateMileage"));
		String[] totalPrice = request.getParameterValues("totalPrice");
		int totalP = 0;
		String[] deliveryFee = request.getParameterValues("deliveryFee");
		int totalDeliveryFee = 0;
		int zipCode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		int accumulateMileage = currentMileage - usedMileage;
		String[] stock1 = request.getParameterValues("stock");
		String[] pNo1 = request.getParameterValues("pNo");
		String[] count1 = request.getParameterValues("count");
		OrdersBiz ordersBiz = new OrdersBiz();
		ProductBiz productBiz = new ProductBiz();
		
		for (int index = 0; index < totalPrice.length; index++) {
			System.out.println("totalPrice : " + totalPrice[index]);
			System.out.println("deliveryFee : " + deliveryFee[index]);
			totalP += Integer.parseInt(totalPrice[index]);
			totalDeliveryFee += Integer.parseInt(deliveryFee[index]);
			int stock = Integer.parseInt(stock1[index]);
			
			if (stock > 0) {
				// 알림
			}
		}
		if (memberId != null && accumulateMileage >= 0) {
			try {
				int result = ordersBiz.orders(memberId, orderMethod, totalP, totalDeliveryFee, usedMileage, accumulateMileage, zipCode, address1, address2);
				if (result == 1) {
					for (int index = 0; index < totalPrice.length; index++) {
						int pNo = Integer.parseInt(pNo1[index]);
						int count = Integer.parseInt(count1[index]);
						System.out.println("count : " + count);
						System.out.println("pNo : " + pNo);
						ordersBiz.cartDelete(memberId, pNo);
						// ordersDetail 추가(수량, pNo, oNo(dao), '미평가')
						
						
						//productBiz.plusPsales(count, pNo);
						Product product = new Product();
						product.setpNo(pNo);
						productBiz.selectProductOne(product);
						//productBiz.plusPsales(count, product);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 카테고리, 베스트 페이지에서 장바구니 담기
	 */
	private void cartInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = 1;
		
		OrdersBiz ordersBiz = new OrdersBiz();
		
		try {
			ordersBiz.cartInsert(memberId, pNo, count);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('장바구니에 등록되었습니다.'); location.href='" + CONTEXT_PATH + "/product/productController?action=productListByCategoryForm';</script>");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 등록된 상품입니다.'); location.href='" + CONTEXT_PATH + "/product/productController?action=productListByCategoryForm';</script>");
			writer.close();
		}
	}
	
	/**
	 * 상품 상세 페이지에서 장바구니 담기(안쓸듯)
	 */
	private void DetailsCartInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
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
