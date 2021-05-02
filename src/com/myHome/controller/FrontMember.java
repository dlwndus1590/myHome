package com.myHome.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myHome.model.biz.MemberBiz;
import com.myHome.model.dto.Member;
import com.myHome.model.dto.MessageEntity;


/**
 * 회원관리 시스템
 */
@WebServlet(urlPatterns = {"/member/memberController"},loadOnStartup = 1)
public class FrontMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public ServletContext application;
	public String CONTEXT_PATH;
	
	/**
	 * @see Servlet#init(context-root)
	 */
	public void init() throws ServletException {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();
		System.out.println("[loadOnStartup]CONTEXT_PATH : " + CONTEXT_PATH);
		application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
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
		case "loginForm":
			loginForm(request, response);
			break;
		case "loginChoice":
			loginChoice(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "memberInputForm":
			memberInputForm(request,response);
			break;
		case "memberInput":
			memberInput(request,response);
			break;
		case "sellerInputForm":
			sellerInputForm(request,response);
			break;
		case "sellerInput":
			sellerInput(request,response);
			break;
		case "memberFindForm":
			memberFindForm(request,response);
			break;
		case "memberFindId":
			//memberFindId(request,response);
			break;
		case "memberFindPw":
			//memberFindPw(request,response);
			break;
		case "memberMyInfo":	
			//memberMyInfo(request,response);
			break;
		case "memberMyPage":	
			memberMyPage(request,response);
			break;
		case "sellerMyPage":	
			sellerMyPage(request,response);
			break;		
		case "sellerMyInfo":	
			//sellerMyInfo(request,response);
			break;
		case "memberFind":
			//memberFind(request,response);
			break;
		case "memberDelete":	
			memberDelete(request,response);
			break;
		case "memberDetailForm":	
			//memberDetailForm(request,response);
			break;
		case "memberDetail":	
			//memberDetail(request,response);
			break;
		case "memberList":	
			memberList(request,response);
			break;
		}
	}

	/**
	 * 로그인 화면 요청 서비스
	 */
	protected void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String url = "/mms/member/login.jsp";
		String url = CONTEXT_PATH + "/member/login.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 회원가입 (일반/판매자) 선택 화면 요청 서비스
	 */
	protected void loginChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/loginChoice.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 일반 회원가입 화면 요청 서비스
	 */
	protected void memberInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/memberInput.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 판매자 회원가입 화면 요청 서비스
	 */
	protected void sellerInputForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/sellerInput.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 마이페이지 일반/판매자 분리 화면 요청 서비스
	 */
	protected void memberMyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/memberMyPage.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 마이페이지 일반/판매자 분리 화면 요청 서비스
	 */
	protected void sellerMyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/sellerMyPage.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 아이디/비밀번호 찾기 화면 요청 서비스
	 */
	protected void memberFindForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/memberFind.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 로그인 요청 : 성공 시 회원 등급 반환
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 로그인 요청");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		if (memberId == null || memberId.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 0);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/loginForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
			return;
		} 
		
		if (memberPw == null || memberPw.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 1);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/loginForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
			return;
		} 
		
		memberId = memberId.trim();
		memberPw = memberPw.trim();
		
		MemberBiz biz = new MemberBiz();
		String grade = biz.login(memberId, memberPw);
		
		if (grade != null) {
			HttpSession session = request.getSession(true); // true
			session.setAttribute("memberId", memberId);
			session.setAttribute("grade", grade);
			
			MessageEntity messageEntity = new MessageEntity("success", 1);
			messageEntity.setLinkTitle("회원전용 서비스메인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/mainService.jsp");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else {
			MessageEntity messageEntity = new MessageEntity("error", 2);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/loginForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		}
	}
	
	/**
	 * 로그아웃  서비스
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 로그아웃 요청");
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			if (session.getAttribute("memberId") != null) {
				session.removeAttribute("memberId");
			}
			
			if (session.getAttribute("grade") != null) {
				session.removeAttribute("grade");
			}
			
			session.invalidate();
		}
		
		response.sendRedirect(CONTEXT_PATH + "/member/index.jsp"); 
	}
	
	/**
	 *	판매자 회원
	 *		-- 회원가입 요청 서비스
	 */
	protected void memberInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/** 인코딩 설정 */
		System.out.println("[dubug] 회원가입 요청");
		
		/** 데이터 추출 */
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");		
		String name = request.getParameter("name");		
		String mobile = request.getParameter("mobile");		
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String businessNumber = request.getParameter("businessNumber");
		String companyName = request.getParameter("companyName");
		
		System.out.println("데이터 넘어오는데 베이스 확인 : "+memberId+memberPw);
		
		if (memberId == null || memberId.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 0);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(memberPw == null || memberPw.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 1);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(name == null || name.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 2);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 3);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(email == null || email.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(zipcode == null || zipcode.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(address1 == null || address1.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(address2 == null || address2.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(businessNumber == null || businessNumber.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(companyName == null || companyName.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else {
		
			MemberBiz biz = new MemberBiz();
			boolean result =biz.insertSeller(new Member(memberId, memberPw, name, email, mobile, zipcode, address1, address2, businessNumber, companyName));
			 						
			if (result) {
				MessageEntity messageEntity = new MessageEntity("success", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/member/loginForm");
				
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/member/message.jsp").forward(request, response);
				
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 0);
				messageEntity.setLinkTitle("회원가입");
				messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
				
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			}
		}
	}
	
	/**
	 *	일반 회원
	 *		-- 회원가입 요청 서비스
	 */
	protected void sellerInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/** 인코딩 설정 */
		System.out.println("[dubug] 회원가입 요청");
		
		/** 데이터 추출 */
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");		
		String name = request.getParameter("name");		
		String mobile = request.getParameter("mobile");		
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		System.out.println("데이터 넘어오는데 베이스 확인 : "+memberId+memberPw);
		
		if (memberId == null || memberId.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 0);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(memberPw == null || memberPw.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 1);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(name == null || name.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 2);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 3);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(email == null || email.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			
		} else if(zipcode == null || zipcode.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(address1 == null || address1.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else if(address2 == null || address2.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		} else {
		
			MemberBiz biz = new MemberBiz();			
			boolean result = biz.insertMember(new Member(memberId, memberPw, name, email, mobile, zipcode, address1, address2));
			
			if (result) {
				MessageEntity messageEntity = new MessageEntity("success", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/member/loginForm");
				
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/member/message.jsp").forward(request, response);
				
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 0);
				messageEntity.setLinkTitle("회원가입");
				messageEntity.setUrl(CONTEXT_PATH + "/member/memeberInputForm");
				
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			}
		}
	}
	
	/**
	 * 관리자 : 회원탈퇴 요청
	 */
	protected void memberDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("memberId") == null || session.getAttribute("grade") == null) {
			// 로그인 하지 않은 사용자
			MessageEntity messageEntity = new MessageEntity("message", 0);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH +"/member/loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			return;
		} else if (!session.getAttribute("grade").equals("A")) {
			// 로그인 한 사용자중에서 관리자가 아닌 사용자 : 일반회원, 우수회원
			MessageEntity messageEntity = new MessageEntity("message", 1);
			messageEntity.setLinkTitle("메인서비스");
			messageEntity.setUrl(CONTEXT_PATH + "/member/mainService.jsp");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
			return;
		}

		
		String memberId = request.getParameter("memberId");
		
		MemberBiz biz = new MemberBiz();
		boolean result = biz.deleteMember(memberId);
		
		if (result) {
			response.sendRedirect(CONTEXT_PATH+"/member/memberList");
		} else {
			MessageEntity messageEntity = new MessageEntity("error", 10); // error.add("[회원 탈퇴 오류]");	// 10
			messageEntity.setLinkTitle("회원전체조회");
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberList.jsp");
			
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request, response);
		}
	}
	
	/**
	 * 관리자 권한 
	 * 		-- 전체회원 조회 요청 서비스
	 */
	protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 전체회원목록 요청");
		HttpSession session = request.getSession();
		
		/** 데이터추출 */		
		MemberBiz biz = new MemberBiz();
		
		String searchKey = request.getParameter("searchKey");
		String keyWord = request.getParameter("keyWord");
				
		/** 응답 설정 */
		ArrayList<Member> list = biz.selectMemberList(searchKey, keyWord);				
		System.out.println("list"+list);
				
		/* 응답을 위한 설정 */
		session.setAttribute("list", list);				
		/* 응답 페이지로 이동 */
		RequestDispatcher nextView = request.getRequestDispatcher("/member/memberList.jsp");
		nextView.forward(request, response);				
	}
}

