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

import com.myHome.model.biz.NoticeBiz;
import com.myHome.model.dto.Notice;

/**
 * Servlet implementation class FrontNotice
 */
@WebServlet("/notice/noticeController")
public class FrontNotice extends HttpServlet {
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
		case "noticeListForm":
			noticeListForm(request, response);	
			break;
			
		case "noticeDetail":
			noticeDetail(request, response);	
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
	 * 게시글 페이지 요청 서비스
	 */
	protected void noticeListForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[debug] 전체 게시글 상세 조회 요청");
		
		NoticeBiz noticeBiz = new NoticeBiz();
		ArrayList<Notice> list = new ArrayList<Notice>();
		noticeBiz.noticeList(list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/notice/noticeList.jsp").forward(request, response);
	}
	
	/**
	 * 게시판 상세조회 요청 서비스
	 */
	protected void noticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[debug]게시판 상세조회 요청");
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		/* 작성자 아이디*/
		String writeMemberId = request.getParameter("writeMemberId");
		HttpSession session = request.getSession(true);
		
		NoticeBiz biz = new NoticeBiz();
		Notice dto = biz.NoticeDetail(nNo);
		//biz.viewsUp(nNo, writeMemberId, (String)session.getAttribute("memberId"));
		if(dto != null) {
			request.setAttribute("dto", dto);
			
			request.getRequestDispatcher("/notice/noticeDetail.jsp").forward(request, response);
			return;
		}
	}
}
