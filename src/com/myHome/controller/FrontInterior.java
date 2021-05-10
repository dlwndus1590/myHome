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
		case "billPage":
			billPage(request, response);
			break;
		case "interiorSuccess":
			interiorSuccess(request, response);
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
		HttpSession session = request.getSession(false);
		
		String icareer = request.getParameter("icareer");
		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior();
		interior.setIname(icareer);
		
		try {
			biz.selectInterior(interior);		
			session.setAttribute("icareer", icareer);
			
			request.getRequestDispatcher("/interior/billPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		}
	}
		
	/**
	 * 인테리어 상담신청 발송
	 */
	protected void interiorSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 상담신청");
		String url = CONTEXT_PATH + "/interior/interiorSuccess.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 등록 서비스
	 */
	protected void insertInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/** 인코딩 설정 */
		System.out.println("[dubug] 인테리어 등록요청");
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("관리자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		/** 데이터 추출 */
		int ino = Integer.parseInt(request.getParameter("ino"));
		String iname = request.getParameter("iname");
		int icareer = Integer.parseInt(request.getParameter("icareer"));		
		String idetail = request.getParameter("idetail");		
		String ilocation = request.getParameter("ilocation");	
		String imobile = request.getParameter("imobile");	
		
		iname = iname.trim();		
		idetail = idetail.trim();		
		ilocation = ilocation.trim();		
		imobile = imobile.trim();		
		
		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior(ino,iname, icareer, idetail, ilocation,imobile);
							
		try {
			biz.insertInterior(interior);	
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('등록이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorController?action=interiorList';</script>");
			writer.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('등록이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorList.jsp';</script>");
			writer.close();			
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
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("관리자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		int ino = Integer.parseInt(request.getParameter("ino"));
		String iname = request.getParameter("iname");		
		//String iname = (String)session.getAttribute("iname");
		int icareer = Integer.parseInt(request.getParameter("icareer"));		
		String idetail = request.getParameter("idetail");		
		String ilocation = request.getParameter("ilocation");
		String imobile = request.getParameter("imobile");	

		InteriorBiz biz = new InteriorBiz();
		Interior interior = new Interior();		
		
		interior.setIno(ino);
		interior.setIname(iname);
		interior.setIcareer(icareer);
		interior.setIdetail(idetail);
		interior.setIlocation(ilocation);
		interior.setImobile(imobile);
		
		try {
			biz.updateInterior(interior);			
			session.setAttribute("ino", ino);
			session.setAttribute("interiordto", interior);
						
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('수정이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorController?action=interiorList';</script>");
			writer.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('수정에 실패하였습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorController?action=interiorList';</script>");
			writer.close();			
		}
	}
	
	/**
	 *	관리자 권한
	 *		-- 인테리어 업체 상세조회 서비스
	 */
	protected void selectInterior(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
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
				session.getAttribute("memberId") == null ||
				!session.getAttribute("grade").equals("관리자")) {
			
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		String iname = (String)session.getAttribute("iname");
	
		InteriorBiz biz = new InteriorBiz();	
		try{			
			biz.deleteInterior(iname);			
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('삭제가 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorController?action=interiorList';</script>");
			writer.close();	
			
		} catch(Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('삭제에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/interior/interiorController?action=interiorList';</script>");
			writer.close();			
		}
	}

}
