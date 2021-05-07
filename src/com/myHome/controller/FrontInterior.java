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

import com.myHome.model.biz.InteriorBiz;
import com.myHome.model.biz.MemberBiz;
import com.myHome.model.dto.Interior;
import com.myHome.model.dto.Member;
import com.myHome.model.dto.MessageEntity;

/**
 * 인테리어 관리 시스템
 */
@WebServlet(urlPatterns = {"/interior/interiorController"})
public class FrontInterior extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch(action) {
		case "interiorList":
			interiorList(request, response);
			break;
		case "insertInterior":
			insertInterior(request, response);
			break;
		case "selectInterior":
			selectInterior(request, response);
			break;
		case "updateInterior":
			updateInterior(request, response);
			break;
		case "deleteInterior":
			deleteInterior(request, response);
			break;
		case "sangdamApply":
			sangdamApply(request, response);
			break;
		case "billPage":
			billPage(request, response);
			break;
		}
	}
	
	/**
	 * 인테리어 업체 전체 목록
	 */
	protected void interiorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 인테리어 업체 전체 조회");
		
		ArrayList<Interior> list = new ArrayList<Interior>();
		InteriorBiz biz = new InteriorBiz();
		
		try {
			biz.selectInteriorList(list);
			request.setAttribute("list", list);			
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	/**
	 * 인테리어 업체 견적서 작성 화면
	 */
	protected void billPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 견적 페이지 신청");		
		String url = CONTEXT_PATH + "/interior/billPage.jsp";
		response.sendRedirect(url);
	}
		
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 등록 서비스
	 */
	protected void insertInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/** 인코딩 설정 */
		System.out.println("[dubug] 인테리어 등록요청");
		
		/** 데이터 추출 */
		int ino = Integer.parseInt(request.getParameter("ino"));
		String iname = request.getParameter("iname");
		int icareer = Integer.parseInt(request.getParameter("icareer"));		
		String idetail = request.getParameter("idetail");		
		String ilocation = request.getParameter("ilocation");	
		
		iname = iname.trim();		
		idetail = idetail.trim();		
		ilocation = ilocation.trim();		
		
		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior(ino,iname, icareer, idetail, ilocation);
							
		try {
			biz.insertInterior(interior);			
			request.getRequestDispatcher("/interior/interiorController?action=interiorList").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		}
		
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 수정 서비스
	 */
	protected void updateInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null) {
			// 미사용자 오류 처리
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		int ino = Integer.parseInt(request.getParameter("ino"));
		String iname = (String)session.getAttribute("iname");
		int icareer = Integer.parseInt(request.getParameter("icareer"));		
		String idetail = request.getParameter("idetail");		
		String ilocation = request.getParameter("ilocation");			

		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior(ino,iname, icareer, idetail, ilocation);
		
		try {
			biz.updateInterior(interior);
			session.setAttribute("iname", iname);
			session.setAttribute("ino", ino);
			
			session.setAttribute("interiordto", interior);
			request.getRequestDispatcher("/interior/interiorController?action=interiorList").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		}
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 상세조회 서비스
	 */
	protected void selectInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null) {
			// 미사용자 오류 처리
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		String iname = request.getParameter("iname");
		
		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior();
		interior.setIname(iname);
		
		try {
			biz.selectInterior(interior);		
			session.setAttribute("iname", iname);
			session.setAttribute("interiordto", interior);
			
			request.getRequestDispatcher("/interior/interiorDetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		}
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 삭제 서비스
	 */
	protected void deleteInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null) {
			// 미사용자 오류 처리
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		String iname = (String)session.getAttribute("iname");

		InteriorBiz biz = new InteriorBiz();	
		try{			
			biz.deleteInterior(iname);
			
			session.invalidate();
			response.sendRedirect(CONTEXT_PATH+"/index.jsp");
		} catch(Exception e) {
			request.getRequestDispatcher("/interior/interiorDetail.jsp").forward(request, response);
		}
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 등록 서비스
	 */
	protected void sangdamApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/** 인코딩 설정 */
		System.out.println("[dubug] 인테리어 상담신청 요청");
		
		// billPage에서 입력받은 값 추출
		
		// 계산한 비즈 호출
		
		// 체크 유무 확인 
	}
}
