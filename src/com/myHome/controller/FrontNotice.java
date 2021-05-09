package com.myHome.controller;

import java.io.File;
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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 게시글 관리 서비스
 * @author 김보성
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

		case "qNoticeInputForm":
			qNoticeInputForm(request, response);
			break;

		case "qNoticeInput":
			qNoticeInput(request, response);
			break;

		case "addComment":
			addComment(request, response);
			break;

		case "updateComment":
			updateComment(request, response);
			break;

		case "deleteComment":
			deleteComment(request, response);
			break;

		case "qNoticeUpdateForm":
			qNoticeUpdateForm(request, response);
			break;

		case "qNoticeUpdate":
			qNoticeUpdate(request, response);
			break;

		case "qNoticeDelete":
			qNoticeDelete(request, response);
			break;

		case "qNoticeSearch":
			qNoticeSearch(request, response);
			break;
		
		case "qNoticeNewList":
			qNoticeNewList(request, response);
			break;
			
		case "qNoticePopularityList":
			qNoticePopularityList(request, response);
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
	 * @author 김보성
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
	 * @author 김보성
	 */
	protected void noticeDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[debug]게시판 상세조회 요청");
		HttpSession session = request.getSession(true);
		NoticeBiz biz = new NoticeBiz();
		Notice dto = new Notice();
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		/* 작성자 아이디 */
		String writeMemberId = request.getParameter("writeMemberId");
		
		if(session.getAttribute("memberId") != null && !writeMemberId.equals((String)session.getAttribute("memberId"))){
			/* 작성자가 아닌 회원이 해당 게시글 상세 조회를 요청 했을 경우*/
			biz.nHitsUp(nNo);
		}
		
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
	 * @author 김보성
	 */
	protected void noticeInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null || !session.getAttribute("grade").equals("관리자")) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/notice/noticeInputForm.jsp").forward(request, response);
	}

	/**
	 * 게시글 등록 요청 서비스
	 * @author 김보성
	 */
	protected void noticeInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null || !session.getAttribute("grade").equals("관리자")) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("[debug]게시글 등록 요청");

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
	 * @author 김보성
	 */
	private void noticeEditOrDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null || !session.getAttribute("grade").equals("관리자")) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
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
	 * @author 김보성
	 */
	private void noticeUpdate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null || !session.getAttribute("grade").equals("관리자")) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
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
	 * 공지사항 게시글 검색 요청 서블릿
	 * @author 김보성
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
	 * @author 김보성
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
	
	/**
	 * 질문 게시판 상세조회 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Answer> list = new ArrayList<Answer>();
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String writeMemberId = request.getParameter("writeMemberId");
		Qnotice qdto = new Qnotice();
		
		if(writeMemberId != null) {
		if(session.getAttribute("memberId") != null && !writeMemberId.equals((String)session.getAttribute("memberId"))){
			/* 작성자가 아닌 회원이 해당 게시글 상세 조회를 요청 했을 경우*/
			biz.qHitsUp(qNo);
		}}
		
		biz.qNoticeDetail(qNo, qdto);
		biz.answerList(qNo, list);
		request.setAttribute("qdto", qdto);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/notice/qNoticeDetail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 질문 게시글 작성 화면 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeInputForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			request.getRequestDispatcher("/notice/qNoticeInputForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 질문 게시글 작성 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeInput(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		NoticeBiz biz = new NoticeBiz();
		String directory = "C:/student_ucamp33/workspace_teamProject/myHome/WebContent/img/qNotice";
		
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		MultipartRequest multipartRequest = null;
		try {
			multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
					new DefaultFileRenamePolicy());
			String qTitle = multipartRequest.getParameter("qTitle");
			String imgUrl = "/img/qNotice/" + multipartRequest.getOriginalFileName("imgUrl");
			String qContent = multipartRequest.getParameter("qContent");
			Qnotice dto = new Qnotice(qTitle, qContent, imgUrl, "user01");
			biz.addQnotice(dto);
			try {
				request.getRequestDispatcher("/notice/noticeController?action=qNoticeForm").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 댓글 등록 요청 서비스
	 * @author 김보성
	 */
	private void addComment(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		String comment = request.getParameter("comment");
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		NoticeBiz biz = new NoticeBiz();
		Answer dto = new Answer();
		dto.setaContent(comment);
		dto.setMemberId((String)session.getAttribute("memberId"));
		dto.setqNo(qNo);

		biz.addComment(dto);
		try {
			request.getRequestDispatcher("/notice/noticeController?action=qNoticeDetail&qNo=" + qNo).forward(request,
					response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 수정 요청 서비스
	 * @author 김보성
	 */
	private void updateComment(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		int aNo = Integer.parseInt(request.getParameter("aNo"));
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String edditContent = request.getParameter("edit_acontent" + aNo);
		NoticeBiz biz = new NoticeBiz();
		biz.updateComment(aNo, edditContent);
		try {
			request.getRequestDispatcher("/notice/noticeController?action=qNoticeDetail&qNo=" + qNo).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 삭제 요청 서비스
	 * @author 김보성
	 */
	private void deleteComment(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		int aNo = Integer.parseInt(request.getParameter("aNo"));
		NoticeBiz biz = new NoticeBiz();
		biz.deleteComment(aNo);
		try {
			request.getRequestDispatcher("/notice/noticeController?action=qNoticeDetail&qNo=" + qNo).forward(request,
					response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 질문 게시글 수정 화면 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		NoticeBiz biz = new NoticeBiz();
		Qnotice dto = new Qnotice();

		biz.qNoticeDetail(qNo, dto);
		request.setAttribute("dto", dto);
		try {
			request.getRequestDispatcher("/notice/qNoticeUpdateForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 질문 게시글 수정 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeUpdate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String qTitle = request.getParameter("qTitle");
		String qContent = request.getParameter("qContent");
		NoticeBiz biz = new NoticeBiz();
		Qnotice dto = new Qnotice();

		dto.setqNo(qNo);
		dto.setqTitle(qTitle);
		dto.setqContent(qContent);

		biz.qNoticeUpdate(dto);
		try {
			request.getRequestDispatcher("/notice/noticeController?action=qNoticeForm").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 질문 게시글 삭제 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("memberId") == null) {
			try {
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		Qnotice dto = new Qnotice();
		NoticeBiz biz = new NoticeBiz();
		dto.setqNo(qNo);
		biz.selectQnoticeOne(dto);
		biz.qNoticeDelete(qNo);
		File file = new File("C:/student_ucamp33/workspace_teamProject" + CONTEXT_PATH + "/WebContent" + dto.getqImg());
		System.out.println(file);
		if(file.exists()) {
			if(file.delete()) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer;
				try {
					writer = response.getWriter();
					writer.println("<script>alert('게시글 삭제가 완료되었습니다.'); location.href='" + CONTEXT_PATH + "/notice/noticeController?action=qNoticeForm';</script>");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer;
				try {
					writer = response.getWriter();
					writer.println("<script>alert('게시글을 삭제하지 못했습니다. 다시 시도하세요.'); location.href='" + CONTEXT_PATH + "/notice/noticeController?action=qNoticeForm';</script>");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 질문 게시글 검색 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeSearch(HttpServletRequest request, HttpServletResponse response) {
		String searchInfo = request.getParameter("searchInfo");
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Qnotice> list = new ArrayList<Qnotice>();
		biz.searchQnoticeList(searchInfo, list);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/notice/qNoticeForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 질문 게시글 최신순(등록순) 리스트 요청 서비스
	 * @author 김보성
	 */
	private void qNoticeNewList(HttpServletRequest request, HttpServletResponse response) {
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Qnotice> list = new ArrayList<Qnotice>();
		String listType = "최신순";
		
		biz.qNoticeNewList(list);
		request.setAttribute("list", list);
		request.setAttribute(listType, listType);
		try {
			request.getRequestDispatcher("/notice/qNoticeForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 질문 게시글 인기순(조회수) 리스트 요청 서비스
	 * @author 김보성
	 */
	private void qNoticePopularityList(HttpServletRequest request, HttpServletResponse response) {
		NoticeBiz biz = new NoticeBiz();
		ArrayList<Qnotice> list = new ArrayList<Qnotice>();
		String listType = "인기순";

		biz.qNoticePopularityList(list);
		request.setAttribute("list", list);
		request.setAttribute("listType", listType);
		try {
			request.getRequestDispatcher("/notice/qNoticeForm.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}