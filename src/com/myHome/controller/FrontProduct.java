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

import com.myHome.model.biz.ProductBiz;
import com.myHome.model.dto.Category;
import com.myHome.model.dto.Member;
import com.myHome.model.dto.Product;
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
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			biz.getCategoryList(categoryList);
			biz.getProductList(productList);
			
			HttpSession session = request.getSession();
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("productList", productList);
			
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
		ArrayList<Product> productList = new ArrayList<Product>();
		
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.getProductListByCategory(categoryId, productList);
			
			HttpSession session = request.getSession();
			session.setAttribute("productList", productList);
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
		ArrayList<Product> productList1 = new ArrayList<Product>();
		
		ProductBiz biz = new ProductBiz();
		product.setpNo(pNo);
		
		try {
			biz.selectProductOne(pNo, product);
			HttpSession session = request.getSession();
			session.setAttribute("product", product);
			
			category.setCategoryId(product.getCategoryId());
			biz.getCategory(category);	
			session.setAttribute("category", category);
			
			biz.getRelatedProductList(product.getCategoryId(), product.getpNo(), productList1);
			request.setAttribute("productList1", productList1);
			
			request.getRequestDispatcher("/product/productDetails.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 베스트 상품 조회 화면요청 서비스
	 */
	protected void productListByBestForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> productList = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		ArrayList<Category> categoryList = new ArrayList<Category>();
		int number= 1;
		
		try {
			biz.productListbyBest(productList);
			biz.getCategoryList(categoryList);
			
			HttpSession session = request.getSession();
			session.setAttribute("productList", productList);
			session.setAttribute("categoryList", categoryList);
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
		
		ArrayList<Product> productList = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.productListbyBestCategory(categoryId, productList);
			HttpSession session = request.getSession();
			session.setAttribute("productList", productList);
			session.setAttribute("number", number);
			
			response.sendRedirect(CONTEXT_PATH + "/product/best.jsp");
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
		
		ArrayList<Product> productList = new ArrayList<Product>();
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.getEnrolledProductList(companyName, productList);
			session.setAttribute("productList", productList);
			session.setAttribute("companyName", companyName);
			
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
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ProductBiz biz = new ProductBiz();
		
		try {
			biz.getCategoryList(categoryList);
			session.setAttribute("categoryList", categoryList);
			
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
		String pRegDate = Utility.getCurrentDate("yyyy-mm-dd");
		
		ProductBiz biz = new ProductBiz();
		String directory = "C:/student_ucamp33/workspace_teamProject/myHome/WebContent/img/product";
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
			String pName = multi.getParameter("pName");
			String pPrice = multi.getParameter("pPrice");
			String pImg = "/img/product/" + multi.getOriginalFileName("pImg");
			String pDescribe = "/img/product/" + multi.getOriginalFileName("pDescribe");
			String deliveryFee = multi.getParameter("deliveryFee");
			String categoryId = multi.getParameter("categoryId");
			String pCount = multi.getParameter("pCount");
			int number = biz.getMaxProductNum();
			
			Product product = new Product(++number, pName, Integer.parseInt(pPrice), pImg, pDescribe, Integer.parseInt(deliveryFee), companyName, Integer.parseInt(categoryId), 0, 0, Integer.parseInt(pCount), pRegDate);
			biz.addProduct(product);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('상품이 등록되었습니다.'); location.href='" + CONTEXT_PATH + "/member/memberController?action=sellerMyPage';</script>");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void storeHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBiz biz = new ProductBiz();
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		try {
			biz.getCategoryList(categoryList);
			
			HttpSession session = request.getSession();
			session.setAttribute("categoryList1", categoryList);
			
			response.sendRedirect(CONTEXT_PATH + "/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	
}
