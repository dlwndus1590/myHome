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
 * 장바구니, 결제 관련 controller
 * @author 최인묵
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
		case "order":
			order(request, response);	
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
	 * 단일 상품 결제페이지
	 */
	private void ordersPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		int deliveryFee = Integer.parseInt(request.getParameter("deliveryFee"));
		int total = (pPrice * count) + deliveryFee;
		
		OrdersPage ordersPage = new OrdersPage();
		OrdersBiz ordersBiz = new OrdersBiz();
		try {
			if (ordersBiz.isValid(memberId, pNo)) {
				ordersBiz.cartUpdate(pNo, count, memberId);
			} else {
				ordersBiz.cartInsert(memberId, pNo, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			ordersBiz.getSingleOrdersPage(memberId, pNo, ordersPage);
			
			session.setAttribute("ordersPage", ordersPage);
			session.setAttribute("totalCost", total);
			response.sendRedirect(CONTEXT_PATH + "/member/ordersPage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 다중 결제하기
	 */
	private void orders(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		int getMileage = 0;
		String[] stock1 = request.getParameterValues("stock");
		String[] pNo1 = request.getParameterValues("pNo");
		String[] count1 = request.getParameterValues("count");
		OrdersBiz ordersBiz = new OrdersBiz();
		ProductBiz productBiz = new ProductBiz();
		
		for (int index = 0; index < totalPrice.length; index++) {
			totalP += Integer.parseInt(totalPrice[index]);
			totalDeliveryFee += Integer.parseInt(deliveryFee[index]);
			getMileage = (int) (accumulateMileage + (totalP * 0.1));
			int stock = Integer.parseInt(stock1[index]);
			int pNo = Integer.parseInt(pNo1[index]);
			
			if (stock < 0) {
				try {
					ordersBiz.cartDelete(memberId, pNo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('재고가 없습니다.'); location.href='" + CONTEXT_PATH + "/product/productController?action=productListByCategoryForm';</script>");
				writer.close();
				return;
			}
		}
		if (memberId != null && accumulateMileage >= 0) {
			try {
				int result = ordersBiz.orders(memberId, orderMethod, totalP, totalDeliveryFee, usedMileage, accumulateMileage, zipCode, address1, address2);
				if (result == 1) {
					for (int index = 0; index < totalPrice.length; index++) {
						int pNo = Integer.parseInt(pNo1[index]);
						int count = Integer.parseInt(count1[index]);
						ordersBiz.cartDelete(memberId, pNo);
						ordersBiz.ordersDetail(count, pNo);
						ordersBiz.updateMileage(memberId, getMileage);
						Product product = new Product();
						product.setpNo(pNo);
						productBiz.selectProductOne(product);
						productBiz.plusPsales(count, product);
						
					}
					response.sendRedirect(CONTEXT_PATH + "/member/ordersSuccess.jsp");
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
	 * 상품 상세 페이지에서 장바구니 담기 - 상품상세에서 버튼 클릭시 count값 가져와야됨
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
		}
	}
	
	/**
	 * 단일 결제하기
	 */
	private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		int orderMethod = Integer.parseInt(request.getParameter("optionsRadios"));
		int usedMileage = Integer.parseInt(request.getParameter("usedMileage"));
		int currentMileage = Integer.parseInt(request.getParameter("accumulateMileage"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int deliveryFee = Integer.parseInt(request.getParameter("deliveryFee"));
		int zipCode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		int accumulateMileage = currentMileage - usedMileage;
		int getMileage = (int) (accumulateMileage + (totalPrice * 0.1));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		OrdersBiz ordersBiz = new OrdersBiz();
		ProductBiz productBiz = new ProductBiz();
		
		if (stock < 0) {
			try {
				ordersBiz.cartDelete(memberId, pNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('재고가 없습니다.'); location.href='" + CONTEXT_PATH + "/product/productController?action=productListByCategoryForm';</script>");
			writer.close();
			return;
		}
		
		if (memberId != null && accumulateMileage >= 0) {
			try {
				int result = ordersBiz.orders(memberId, orderMethod, totalPrice, deliveryFee, usedMileage, accumulateMileage, zipCode, address1, address2);
				if (result == 1) {
					ordersBiz.cartDelete(memberId, pNo);
					ordersBiz.ordersDetail(count, pNo);
					ordersBiz.updateMileage(memberId, getMileage);
					
					Product product = new Product();
					product.setpNo(pNo);
					productBiz.selectProductOne(product);
					productBiz.plusPsales(count, product);
					
					response.sendRedirect(CONTEXT_PATH + "/member/ordersSuccess.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
