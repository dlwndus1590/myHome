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

import com.myHome.model.biz.NoticeBiz;
import com.myHome.model.dto.Answer;
import com.myHome.model.dto.Notice;
import com.myHome.model.dto.Qnotice;

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
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch (action) {
		case "noticeListForm":
			noticeListForm(request, response);
			break;

		case "noticeInputForm":
			noticeInputForm(request, response);
			break;

		case "noticeDetail":
			noticeDetail(request, response);
			break;

		case "noticeInput":
			noticeInput(request, response);
			break;

		case "noticeEditOrDelete":
			noticeEditOrDelete(request, response);
			break;

		case "noticeUpdate":
			noticeUpdate(request, response);
			break;

		case "noticeSearch":
			noticeSearch(request, response);
			break;
		
		case "qNoticeForm":
			qNoticeForm(request, response);
			break;
		
		case "qNoticeDetail":
			qNoticeDetail(request, response);
			break;
		
		case "addComment":
			addComment(request, response);
			break;
			
		case "commentUpdate":
			commentUpdate(request, response);
			break;
		}
			}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 게시글 페이지 요청 서비스
	 */
	protected void noticeListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	protected void noticeDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[debug]게시판 상세조회 요청");
		int nNo = Integer.parseInt(request.getParameter("nNo"));

		/* 작성자 아이디 */
//		String writeMemberId = request.getParameter("writeMemberId");
//		HttpSession session = request.getSession(true);

		NoticeBiz biz = new NoticeBiz();
		Notice dto = new Notice();
		biz.NoticeDetail(nNo, dto);
		// biz.viewsUp(nNo, writeMemberId, (String)session.getAttribute("memberId"));
		if (dto != null) {
			request.setAttribute("dto", dto);

			request.getRequestDispatcher("/notice/noticeDetail.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * 게시글 등록 화면 페이지 요청 서비스 게시글등록화면 forward 이동
	 */
	protected void noticeInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/notice/noticeInputForm.jsp").forward(request, response);
	}

	/**
	 * 게시글 등록 요청 서비스
	 */
	protected void noticeInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("[debug]게시글 등록 요청");

		HttpSession session = request.getSession();
		NoticeBiz noticeBiz = new NoticeBiz();
		// 데이터 추출
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");

		Notice dto = new Notice();
		dto.setMemberId("admin");
		dto.setnTitle(nTitle);
		dto.setnContent(nContent);
		boolean flag = noticeBiz.addNotice(dto);
		if (flag) {
			/* 게시글 등록 완료 */
			request.getRequestDispatcher("/notice/noticeController?action=noticeListForm").forward(request, response);
		} else {
			/* 게시글 등록 실패 */
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('게시글 등록을 실패 했습니다!');</script>");
			writer.close();
		}
	}

	/**
	 * 공지사항 게시글 삭제 또는 수정 요청 서비스
	 */
	private void noticeEditOrDelete(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String submit = request.getParameter("submit");
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeBiz biz = new NoticeBiz();

		if (submit.equals("삭제")) {
			/* 관리자가 삭제 버튼을 클릭 했을 시 */
			boolean flag = biz.noticeDelete(nNo);
			if (flag) {
				/* 게시글 삭제 완료 시 */
				try {
					request.getRequestDispatcher("/notice/noticeController?action=noticeListForm").forward(request,
							response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else {
				/* 게시글 삭제 실패 시 */
				PrintWriter writer;
				try {
					writer = response.getWriter();
					writer.println("<script>alert('게시글 등록을 실패 했습니다!');</script>");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			Notice dto = new Notice();
			/* 관리자가 수정 버튼을 클릭 했을 시 */
			biz.NoticeDetail(nNo, dto);
			request.setAttribute("dto", dto);
			try {
				request.getRequestDispatcher("/notice/noticeUpdateForm.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 공지사항 게시글 수정 요청 서비스
	 */
	private void noticeUpdate(HttpServletRequest request, HttpServletResponse response) {
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeBiz biz = new NoticeBiz();

		biz.noticeUpdate(nNo, nTitle, nContent);
		try {
			request.getRequestDispatcher("/notice/noticeController?action=noticeListForm").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글 검색 요청 서블릿
	 */
	protected void noticeSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchInfo = request.getParameter("searchInfo");
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Notice> list = new ArrayList<Notice>();
		biz.searchList(searchType, searchInfo, list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/notice/noticeList.jsp").forward(request, response);
	}

	/**
	 * 질문 게시판 화면 요청 서비스
	 */
	private void qNoticeForm(HttpServletRequest request, HttpServletResponse response) {
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Qnotice> list = new ArrayList<Qnotice>();
		biz.qNoticeList(list);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/notice/qNoticeForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void qNoticeDetail(HttpServletRequest request, HttpServletResponse response) {
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Answer> list = new ArrayList<Answer>();
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		Qnotice dto = new Qnotice();

		biz.qNoticeDetail(qNo, dto);
		biz.answerList(qNo, list);
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/notice/qNoticeDetail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addComment(HttpServletRequest request, HttpServletResponse response) {

	}
	

	private void commentUpdate(HttpServletRequest request, HttpServletResponse response) {
		
	}
}