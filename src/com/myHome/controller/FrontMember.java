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
			memberFindId(request,response);
			break;
		case "memberFindPw":
			memberFindPw(request,response);
			break;
		// 일반회원 내정보 조회
		case "memberMyInfo":	
			memberMyInfo(request,response);
			break;
		// 일반회원 내정보 정보 변경
		case "memberMyInfoUpdate":	
			memberMyInfoUpdate(request,response);
			break;
		case "memberMyPage":	
			memberMyPage(request,response);
			break;
		case "sellerMyPage":	
			sellerMyPage(request,response);
			break;
			// 판매자회원 내정보 조회
		case "sellerMyInfo":	
			sellerMyInfo(request,response);
			break;
			// 판매자 회원 내정보 정보 변경
		case "sellerMyInfoUpdate":	
			sellerMyInfoUpdate(request,response);
			break;
		case "adminMyPage":	
			adminMyPage(request,response);
			break;	
		case "memberDelete":	
			memberDelete(request,response);
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
	 * 마이페이지 관리자 분리 화면 요청 서비스
	 */
	protected void adminMyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/admin/adminMypage.jsp";
		response.sendRedirect(url); 
	}
	
	/**
	 * 아이디/비밀번호 찾기 화면 요청 서비스
	 */
	protected void memberFindForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = CONTEXT_PATH + "/member/memberFind.jsp";
		response.sendRedirect(url); 
	}
	
	

	/** --------------------------------------------------------------------------------- */
	
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
		
		System.out.println("로그인 요청 : "+memberId + memberPw);
		
		MemberBiz biz = new MemberBiz();
		Member dto = new Member(); 
		dto.setMemberId(memberId);
		dto.setMemberPw(memberPw);
		
		
		try {
			biz.login(dto);
			if (dto.getName() != null) {
				
				HttpSession session = request.getSession(true); // true
				session.setAttribute("memberId", memberId);
				session.setAttribute("grade", dto.getGrade());
				session.setAttribute("name", dto.getName());
				session.setAttribute("dto", dto);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else {
				MessageEntity messageEntity = new MessageEntity("error", 2);
				messageEntity.setLinkTitle("로그인");
				request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			HttpSession session = request.getSession(true); // true
			session.setAttribute("memberId", memberId);
			session.setAttribute("grade", dto.getGrade());
			session.setAttribute("dto", dto);

			request.getRequestDispatcher("/index.jsp").forward(request, response);
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
		response.sendRedirect(CONTEXT_PATH + "/member/memberController?action=loginForm"); 
	}
	
	/**
	 *	판매자 회원
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
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String businessNumber = request.getParameter("businessNumber");
		String companyName = request.getParameter("companyName");
				
		System.out.println("(판매자회원가입)데이터 넘어오는데 베이스 확인 : "+memberId+memberPw);
		
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
			
		} else if(zipcode == 0) {
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
		
			memberId = memberId.trim();
			memberPw = memberPw.trim();
			mobile = mobile.trim();			
			
			MemberBiz biz = new MemberBiz();
			Member dto = new Member();
			System.out.println("(판매자회원가입) 서블릿 member 정보 확인 : "+memberId);						
			dto.setMemberId(memberId);
			dto.setMemberPw(memberPw);
			dto.setName(name);
			dto.setEmail(email);
			dto.setMobile(mobile);
			dto.setZipcode(zipcode);			
			dto.setAddress1(address1);
			dto.setAddress2(address2);
			dto.setBusinessNumber(businessNumber);
			dto.setCompanyName(companyName);
			dto.setGrade("판매자");			
								
			try {
				biz.insertMember(dto);	
				request.getRequestDispatcher("/member/memberController?action=loginForm").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/member/memberController?action=loginChoice").forward(request, response);
			}			
		}
	}
	
	/**
	 *	일반 회원
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
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		System.out.println("(일반회원가입)데이터 넘어오는데 베이스 확인 : "+memberId+memberPw);
		
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
			
		} else if(zipcode == 0) {
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
		
			memberId = memberId.trim();
			memberPw = memberPw.trim();
			mobile = mobile.trim();		
			
			MemberBiz biz = new MemberBiz();
			Member dto = new Member();
			
			dto.setMemberId(memberId);
			dto.setMemberPw(memberPw);
			dto.setName(name);
			dto.setEmail(email);
			dto.setMobile(mobile);
			dto.setZipcode(zipcode);
			dto.setAddress1(address1);
			dto.setAddress2(address2);
			dto.setGrade("일반회원");			
								
			try {
				biz.insertMember(dto);
				
				request.getRequestDispatcher("/member/memberController?action=loginForm").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/member/memberController?action=loginChoice").forward(request, response);
			}
		}
	}
	
	/**
	 *	일반회원 및 판매자 회원
	 *		-- 아이디 찾기 
	 */
	protected void memberFindId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 아이디/비밀번호 찾기 요청");
			
		/** 아이디 찾기 : 데이터 추출 */		
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		
		System.out.println();
		
		/** 데이터 검증 : 필수입력 항목 미입력 오류 처리 */
		if(name == null || name.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation",0);
			messageEntity.setLinkTitle("아이디/비밀번호 찾기");
			
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");	
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation",0);
			messageEntity.setLinkTitle("아이디/비밀번호 찾기");
			
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");	
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);			
		}

		
		/** 데이터 요청 의뢰 */		
		MemberBiz biz = new MemberBiz();
		String selectByMemberId = biz.selectByMemberId(name, mobile);
		System.out.println("아이디 찾기 서블릿 단 확인 : "+selectByMemberId);
		if(selectByMemberId != null) {
			/** messageEntity 객체 생성 및 이동페이지 관련 설정 */
			MessageEntity messageEntity = new MessageEntity("success",6);
			messageEntity.setLinkTitle("[회원님의 비밀번호는 :"+selectByMemberId+"]");

			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);			
			request.getRequestDispatcher("/member/mainService.jsp").forward(request,response);
			return;
		}else {
			MessageEntity messageEntity = new MessageEntity("error",4);
			messageEntity.setLinkTitle("아이디 찾기");
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);	
			return;
		}
	}

	/**
	 *	일반회원 및 판매자 회원
	 *		-- 비밀번호 찾기 
	 */
	protected void memberFindPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 아이디/비밀번호 찾기 요청");
			
		/** 아이디 찾기 / 비밀번호 찾기 : 데이터 추출 */
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		
		System.out.println("데이터 추출:"+memberId);
		System.out.println("데이터 추출:"+name);
		System.out.println("데이터 추출:"+mobile);

		/** 데이터 검증 : 필수입력 항목 미입력 오류 처리 */
		if (memberId == null || memberId.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation",0);
			messageEntity.setLinkTitle("아이디/비밀번호 찾기");
			
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");	
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);
			
		} else if(name == null || name.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation",0);
			messageEntity.setLinkTitle("아이디/비밀번호 찾기");
			
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");	
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation",0);
			messageEntity.setLinkTitle("아이디/비밀번호 찾기");
			
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");	
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);			
		}
		
		memberId = memberId.trim();
		name = name.trim();
		mobile = mobile.trim();
		
		/** 데이터 요청 의뢰 */		
		MemberBiz biz = new MemberBiz();
		String selectByMemberPw = biz.selectByMemberPw(memberId, name, mobile);		
		System.out.println("비밀번호 찾기 서블릿 단 확인 : "+selectByMemberPw);		
		
		if(selectByMemberPw != null){
			/** messageEntity 객체 생성 및 이동페이지 관련 설정 */
			MessageEntity messageEntity = new MessageEntity("success",6);
			messageEntity.setLinkTitle("[회원님의 비밀번호는 :"+selectByMemberPw+"]");

			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);			
			request.getRequestDispatcher("/member/mainService.jsp").forward(request,response);
			return;
		} else {
			/** messageEntity 객체 생성 및 이동페이지 관련 설정 */
			MessageEntity messageEntity = new MessageEntity("error",4);
			messageEntity.setLinkTitle("비밀번호 찾기");
			messageEntity.setUrl(CONTEXT_PATH+"/member/memberController?action=memberFindForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/member/message.jsp").forward(request,response);		
		}
	}
	
	
	/**
	 * 일반회원 내 정보 조회
	 */
	protected void memberMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		if (session == null || session.getAttribute("memberId") == null ||
				session.getAttribute("grade") == null) {
			request.getRequestDispatcher("/member/login.jsp")
					.forward(request, response);
			return;
		}
		
		memberId = memberId.trim();
		MemberBiz biz = new MemberBiz();
		Member dto = new Member();
		dto.setMemberId(memberId);
				
		try {
			
			biz.selectOneMember(dto);	
			session.setAttribute("dto", dto);		
			
			System.out.println("(일반회원) 내정보조회 데이터 확인 : "+dto);
			request.getRequestDispatcher("/member/memberMyInfo.jsp")
					.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/member/memberMyPage.jsp")
					.forward(request, response);
		}		
	}
	
	/**
	 * 일반회원 내 정보변경
	 */
	protected void memberMyInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				session.getAttribute("grade") == null) {
			// 미사용자 오류 처리
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}

		String memberId = (String)session.getAttribute("memberId");
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");		

		MemberBiz biz = new MemberBiz();
		
		Member dto = new Member();
		dto.setMemberId(memberId);
		dto.setMemberPw(memberPw);
		dto.setName(name);
		dto.setMobile(mobile);
		dto.setEmail(email);
		dto.setZipcode(zipcode);
		dto.setAddress1(address1);
		dto.setAddress2(address2);	
	
		
		try {
			biz.updateMemberMyInfo(dto);
			session.setAttribute("dto", dto);
			
			System.out.println("(일반회원) 내정보변경 데이터 확인 : "+dto);
			request.getRequestDispatcher("/member/memberMyPage.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			request.getRequestDispatcher("/member/memberMyInfo.jsp").forward(request, response);
		}
	}
	
	/**
	 * 판매자회원 내 정보  조회
	 */
	protected void sellerMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				session.getAttribute("grade") == null) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		}
		
		memberId = memberId.trim();
		
		MemberBiz biz = new MemberBiz();
		Member dto = new Member();
		
		try {
			biz.selectOneSeller(dto, memberId);
			
			session.setAttribute("dto", dto);			
			request.getRequestDispatcher("/member/memberMyInfo.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.getMessage();
			request.getRequestDispatcher("/member/memberMyPage.jsp")	.forward(request, response);
		}		
		
	}
	
	/**
	 * 판매자 회원 내 정보변경
	 */
	protected void sellerMyInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 사용자 여부 체킹
		HttpSession session = request.getSession(false);
		
		if (session == null || 
				session.getAttribute("memberId") == null ||
				session.getAttribute("grade") == null) {
			// 미사용자 오류 처리
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		}

		String memberId = (String)session.getAttribute("memberId");
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String businessNumber = request.getParameter("businessNumber");
		String companyName = request.getParameter("companyName");		

		MemberBiz biz = new MemberBiz();
		
		Member dto = new Member();
		dto.setMemberId(memberId);
		dto.setMemberPw(memberPw);
		dto.setName(name);
		dto.setMobile(mobile);
		dto.setEmail(email);
		dto.setZipcode(zipcode);
		dto.setAddress1(address1);
		dto.setAddress2(address2);
		dto.setBusinessNumber(businessNumber);
		dto.setCompanyName(companyName);
	
		
		try {
			biz.updateSellerMyInfo(dto);
			session.setAttribute("dto", dto);
			request.getRequestDispatcher("/member/memberMyPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.getMessage();
			request.getRequestDispatcher("/member/memberMyInfo.jsp").forward(request, response);
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
		} 
		
		String memberId = request.getParameter("memberId");
		
		MemberBiz biz = new MemberBiz();
		boolean result = biz.deleteMember(memberId);
		
		if (result) {
			session.invalidate();
			response.sendRedirect(CONTEXT_PATH+"/index.jsp");
		} else {
			request.getRequestDispatcher("/member/memberMyPage.jsp").forward(request, response);
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

