package com.myHome.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myHome.model.biz.OrderListBiz;
import com.myHome.model.biz.ProductBiz;
import com.myHome.model.dto.Category;
import com.myHome.model.dto.Member;
import com.myHome.model.dto.Product;
import com.myHome.model.dto.Review;
import com.myHome.util.Utility;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 상품관리 서비스
 * @author 이주연
 */
@WebServlet("/product/productController")
public class FrontProduct extends HttpServlet {
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
			case "productListByCategoryForm":
				productListByCategoryForm(request, response);	
				break;
			case "productListByCategory":
				productListByCategory(request, response);
				break;
			case "productDetail":
				productDetail(request, response);
				break;
			case "productListByBestForm":
				productListByBestForm(request, response);
				break;
			case "productListByBest":
				productListByBest(request, response);
				break;
			case "enrolledProductListForm" :
				enrolledProductListForm(request, response);
				break;
			case "productRegisterForm":
				productRegisterForm(request, response);
				break;
			case "productRegist":
				productRegist(request, response);
				break;
			case "storeHome":
				storeHome(request, response);
				break;
			case "updateProductForm":
				updateProductForm(request, response);
				break;
			case "productUpdate":
				productUpdate(request, response);
				break;
			case "deleteProduct":
				deleteProduct(request, response);
				break;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * 카테고리별 상품조회 화면 요청 서비스
	 */
	protected void productListByCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBiz biz = new ProductBiz();
		ArrayList<Category> categoryList1 = new ArrayList<Category>();
		ArrayList<Product> productList1 = new ArrayList<Product>();
		
		try {
			biz.getCategoryList(categoryList1);
			biz.getProductList(productList1);
			
			HttpSession session = request.getSession();
			session.setAttribute("categoryList1", categoryList1);
			session.setAttribute("productList1", productList1);
			
			response.sendRedirect(CONTEXT_PATH + "/product/category.jsp");
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	/**
	 * 카테고리별 상품조회 서비스
	 */
	protected void productListByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(categoryIdStr);
		ArrayList<Product> productList1 = new ArrayList<Product>();
		
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.getProductListByCategory(categoryId, productList1);
			
			HttpSession session = request.getSession();
			session.setAttribute("productList1", productList1);
			request.getRequestDispatcher("/product/category.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품 상세조회 화면요청 서비스
	 */
	protected void productDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNoStr = request.getParameter("pNo");
		int pNo = Integer.parseInt(pNoStr);
		
		Product product = new Product();
		Category category = new Category();
		ArrayList<Product> productList2 = new ArrayList<Product>();
		
		ProductBiz biz = new ProductBiz();
		product.setpNo(pNo);
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		OrderListBiz reviewBiz = new OrderListBiz();
		try {
			biz.selectProductOne(product);
			HttpSession session = request.getSession();
			session.setAttribute("product", product);
			
			category.setCategoryId(product.getCategoryId());
			biz.getCategory(category);	
			session.setAttribute("category", category);
			
			biz.getRelatedProductList(product.getCategoryId(), product.getpNo(), productList2);
			request.setAttribute("productList2", productList2);
			
			reviewBiz.reviewList(pNo, reviewList);
			request.setAttribute("reviewList", reviewList);
			request.getRequestDispatcher("/product/productDetails.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 베스트 상품 조회 화면요청 서비스
	 */
	protected void productListByBestForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> productList3 = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		ArrayList<Category> categoryList2 = new ArrayList<Category>();
		int number= 1;
		
		try {
			biz.productListbyBest(productList3);
			biz.getCategoryList(categoryList2);
			
			HttpSession session = request.getSession();
			session.setAttribute("productList3", productList3);
			session.setAttribute("categoryList2", categoryList2);
			session.setAttribute("number", number);
			
			response.sendRedirect(CONTEXT_PATH + "/product/best.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 카테고리별 베스트 상품 조회 서비스
	 */
	protected void productListByBest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(categoryIdStr);
		int number= 1;
		
		ArrayList<Product> productList3 = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.productListbyBestCategory(categoryId, productList3);
			HttpSession session = request.getSession();
			session.setAttribute("productList3", productList3);
			session.setAttribute("number", number);
			
			response.sendRedirect(CONTEXT_PATH + "/product/best.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 스토어홈 요청 서비스
	 */
	protected void storeHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBiz biz = new ProductBiz();
		ArrayList<Category> categoryList3 = new ArrayList<Category>();
		ArrayList<Product> productList4 = new ArrayList<Product>();
		
		try {
			biz.productListbyBest(productList4);
			biz.getCategoryList(categoryList3);
			
			HttpSession session = request.getSession();
			session.setAttribute("categoryList3", categoryList3);
			session.setAttribute("productList4", productList4);
			
			request.getRequestDispatcher("/product/storeHome.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	/**
	 * 내 상품 관리
	 */
	protected void enrolledProductListForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		Member member = (Member)session.getAttribute("dto");
		String companyName = member.getCompanyName();
		
		ArrayList<Product> productList5 = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		HashMap<Integer, Category> categoryMap = biz.getCategoryMap();
		
		try {
			biz.getEnrolledProductList(companyName, productList5);
			session.setAttribute("productList5", productList5);
			session.setAttribute("companyName", companyName);
			session.setAttribute("categoryMap", categoryMap);
			
			response.sendRedirect(CONTEXT_PATH + "/product/enrolledProductList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품등록 화면요청 서비스
	 */
	protected void productRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		ArrayList<Category> categoryList4 = new ArrayList<Category>();
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.getCategoryList(categoryList4);
			session.setAttribute("categoryList4", categoryList4);
			
			response.sendRedirect(CONTEXT_PATH + "/product/productRegister.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품등록 서비스
	 */
	protected void productRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		String companyName = (String)session.getAttribute("companyName");
		
		ProductBiz biz = new ProductBiz();
		String directory = "C:/student_ucamp33/workspace_teamProject/myHome/WebContent/img/product";
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
			String pName = multi.getParameter("pName");
			String pPriceStr = multi.getParameter("pPrice");
			String pImg = "/img/product/" + multi.getOriginalFileName("pImg");
			String pDescribe = "/img/product/" + multi.getOriginalFileName("pDescribe");
			String deliveryFeeStr = multi.getParameter("deliveryFee");
			String categoryIdStr = multi.getParameter("categoryId");
			String pCountStr = multi.getParameter("pCount");
			
			int pPrice = Integer.parseInt(pPriceStr);
			int deliveryFee = Integer.parseInt(deliveryFeeStr);
			int categoryId = Integer.parseInt(categoryIdStr);
			int pCount = Integer.parseInt(pCountStr);
			
			Product product = new Product(pName, pPrice, pImg, pDescribe, deliveryFee, companyName, categoryId, pCount);
			biz.addProduct(product);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품이 등록되었습니다.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품을 등록하지 못했습니다. 다시 시도하세요.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();

		}
	}
	
	/**
	 * 상품수정 화면 요청 서비스
	 */
	protected void updateProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		String pNoStr = request.getParameter("pNo");
		int pNo = Integer.parseInt(pNoStr);
		
		Product product = new Product();
		Category category = new Category();
		ProductBiz biz = new ProductBiz();
		product.setpNo(pNo);		
		
		try {
			biz.selectProductOne(product);
			category.setCategoryId(product.getCategoryId());
			biz.getCategory(category);
			
			session.setAttribute("product", product);
			session.setAttribute("category", category);
			response.sendRedirect(CONTEXT_PATH + "/product/updateProduct.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
	}
	
	/**
	 * 상품등록 요청 서비스 
	 */
	protected void productUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		String pNoStr = request.getParameter("pNo");
		int pNo = Integer.parseInt(pNoStr);
		
		String pName = request.getParameter("pName");
		
		String pPriceStr = request.getParameter("pPrice");
		int pPrice = Integer.parseInt(pPriceStr);
		
		String deliveryFeeStr = request.getParameter("deliveryFee");
		int deliveryFee = Integer.parseInt(deliveryFeeStr);
		
		String categoryIdStr = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(categoryIdStr);
		
		String pCountStr = request.getParameter("pCount");
		int pCount = Integer.parseInt(pCountStr);
		
		ProductBiz biz = new ProductBiz();
		Product product = new Product(pNo, pName, pPrice, deliveryFee, categoryId, pCount);
		
		try {
			biz.updateProduct(product);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품이 수정되었습니다.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품을 수정하지 못했습니다. 다시 시도하세요.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();
		}
	}
	
	/**
	 * 상품 삭제 요청서비스
	 */
	protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("판매자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		String pNoStr = request.getParameter("pNo");
		int pNo = Integer.parseInt(pNoStr);
		
		ProductBiz biz = new ProductBiz();
		Product product = new Product();
		product.setpNo(pNo);
		
		try {
			biz.selectProductOne(product);
			
			biz.deleteProduct(pNo);
			
			System.out.println("상품 : " + product);
			
			File file1 = new File("C:/student_ucamp33/workspace_teamProject" + CONTEXT_PATH + "/WebContent/" + product.getpImg());
			File file2 = new File("C:/student_ucamp33/workspace_teamProject" + CONTEXT_PATH + "/WebContent/" + product.getpDescribe());
			System.out.println(file1 + "        " + file2);
			
			if(file1.exists() && file2.exists()) {
				if(file1.delete() && file2.delete()) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('상품 삭제가 완료되었습니다.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
					writer.close();
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('상품을 삭제하지 못했습니다. 다시 시도하세요.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
					writer.close();
				}
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('상품이 존재하지 않습니다.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
				writer.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품을 삭제하지 못했습니다. 다시 시도하세요.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();
		}
	}
}
