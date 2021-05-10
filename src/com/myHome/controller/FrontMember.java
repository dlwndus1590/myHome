package com.myHome.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.myHome.model.biz.NoticeBiz;
import com.myHome.model.biz.OrderListBiz;
import com.myHome.model.dto.Member;
import com.myHome.model.dto.MessageEntity;
import com.myHome.model.dto.Qnotice;
import com.myHome.model.dto.Review;


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
		case "idCheck":
			idCheck(request, response);
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
		case "adminMyInfo":	
			adminMyInfo(request,response);
			break;	
		case "adminMyInfoUpdate":	
			adminMyInfoUpdate(request,response);
			break;	
		case "memberDelete":	
			memberDelete(request,response);
			break;
		case "memberAdminDelete":	
			memberAdminDelete(request,response);
			break;
		case "memberList":	
			memberList(request,response);
			break;
		case "memberDetail":	
			memberDetail(request,response);
			break;
		case "memberMyQNoticeList":	
			memberMyQNoticeList(request,response);
			break;
		case "memberMyReviewList":	
			memberMyReviewList(request,response);
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
		Member dto = new Member();
		HttpSession session = request.getSession();
		dto.setMemberId((String)session.getAttribute("memberId"));
		
		MemberBiz biz = new MemberBiz();
		
		try {
			biz.selectOneMember(dto);
			session.setAttribute("dto", dto);
			response.sendRedirect(CONTEXT_PATH + "/member/memberMyPage.jsp"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 마이페이지 일반/판매자 분리 화면 요청 서비스
	 */
	protected void sellerMyPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member dto = new Member();
		HttpSession session = request.getSession();
		dto.setMemberId((String)session.getAttribute("memberId"));
				
		MemberBiz biz = new MemberBiz();
		
		try {
			biz.selectOneSeller(dto);
			session.setAttribute("dto", dto);
			response.sendRedirect(CONTEXT_PATH + "/member/sellerMyPage.jsp"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);			
			return;
		} 
		
		if (memberPw == null || memberPw.trim().length() == 0) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
			return;
		} 
		
		memberId = memberId.trim();
		memberPw = memberPw.trim();

		MemberBiz biz = new MemberBiz();
		Member dto = new Member(); 
		dto.setMemberId(memberId);
		dto.setMemberPw(memberPw);		
		
		try {
			biz.login(dto);

			if (dto.getName() != null) {
				
				HttpSession session = request.getSession(true); 
				session.setAttribute("memberId", memberId);
				session.setAttribute("grade", dto.getGrade());
				session.setAttribute("name", dto.getName());
				session.setAttribute("companyName", dto.getCompanyName());
				
				session.setAttribute("dto", dto);	
				session.setAttribute("mileage", dto.getMileage());
				request.getRequestDispatcher("/index.jsp").forward(request,response);	
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('로그인에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/member/login.jsp';</script>");
				writer.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		response.sendRedirect(CONTEXT_PATH + "/index.jsp"); 
	}
		
	/**
	 * 아이디 중복 체크 화면 요청 서비스
	 */
	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		MemberBiz biz = new MemberBiz();			
		int row = biz.selectCheckId(memberId);		
		response.getWriter().write(""+row);
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
				
		if (memberId == null || memberId.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(memberPw == null || memberPw.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(name == null || name.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(email == null || email.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(zipcode == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(address1 == null || address1.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(address2 == null || address2.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(businessNumber == null || businessNumber.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
			
		} else if(companyName == null || companyName.trim().length() == 0) {
			request.getRequestDispatcher("/member/sellerInput.jsp").forward(request, response);
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
			dto.setBusinessNumber(businessNumber);
			dto.setCompanyName(companyName);
			dto.setGrade("판매자");			
								
			int result = biz.insertMember(dto);	
			try {
				if(result != 0) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원가입에 성공하셨습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=loginForm';</script>");
					writer.close();					
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원가입에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
					writer.close();	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
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

		if (memberId == null || memberId.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(memberPw == null || memberPw.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(name == null || name.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(email == null || email.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(zipcode == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(address1 == null || address1.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
		} else if(address2 == null || address2.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberInput.jsp").forward(request, response);
			
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
			dto.setMileage(1000);
			dto.setGrade("일반회원");			
								
			int result = biz.insertMember(dto);	
			try {
				if(result != 0) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원가입에 성공하셨습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=loginForm';</script>");
					writer.close();					
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원가입에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
					writer.close();	
				}
			} catch (Exception e) {
				e.printStackTrace();				
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
	
		
		/** 데이터 검증 : 필수입력 항목 미입력 오류 처리 */
		if(name == null || name.trim().length() == 0) {			
			request.getRequestDispatcher("/member/memberFind.jsp").forward(request,response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberFind.jsp").forward(request,response);
			
		}

		
		/** 데이터 요청 의뢰 */		
		MemberBiz biz = new MemberBiz();
		String selectByMemberId = biz.selectByMemberId(name, mobile);
		
		if(selectByMemberId != null) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원님의 아이디는 "+selectByMemberId+" 입니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=loginForm';</script>");
			writer.close();			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원님의 정보가 일치하지 않습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=loginForm';</script>");
			writer.close();			
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

		/** 데이터 검증 : 필수입력 항목 미입력 오류 처리 */
		if (memberId == null || memberId.trim().length() == 0) {			
			request.getRequestDispatcher("/member/memberFind.jsp").forward(request,response);
			
		} else if(name == null || name.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberFind.jsp").forward(request,response);
			
		} else if(mobile == null || mobile.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberFind.jsp").forward(request,response);			
		}
		
		memberId = memberId.trim();
		name = name.trim();
		mobile = mobile.trim();
		
		/** 데이터 요청 의뢰 */		
		MemberBiz biz = new MemberBiz();
		String selectByMemberPw = biz.selectByMemberPw(memberId, name, mobile);		
				
		if(selectByMemberPw != null){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원님의 임시 비밀번호는 "+selectByMemberPw+" 입니다. 비밀번호 변경을 부탁드립니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
			writer.close();			
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원님의 정보가 일치하지 않습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberFind.jsp';</script>");
			writer.close();			
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
			request.setAttribute("dto", dto);					
			request.getRequestDispatcher("/member/memberMyInfo.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/member/memberMyPage.jsp").forward(request, response);
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
		String entryDate = request.getParameter("entrydate");		
		int mileage = Integer.parseInt(request.getParameter("mileage"));		
		String grade = request.getParameter("grade");		

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
		dto.setMileage(mileage);
		dto.setGrade(grade);
		
		
		try {
			biz.updateMemberMyInfo(dto);
			biz.selectOneMember(dto);
			session.setAttribute("dto", dto);	
			session.setAttribute("name", name);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=memberMyPage&memberId="+memberId+"';</script>");
			writer.close();			
		} catch (Exception e) {
			e.printStackTrace();		
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberMyInfo.jsp';</script>");
			writer.close();
			
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
		dto.setMemberId(memberId);

		try {
			biz.selectOneSeller(dto);

			request.setAttribute("dto", dto);			
			request.getRequestDispatcher("/member/sellerMyInfo.jsp").forward(request, response);
		} catch (Exception e) {
			e.getMessage();
			request.getRequestDispatcher("/member/sellerMyPage.jsp").forward(request, response);
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
		String entryDate = request.getParameter("entrydate");	
		String grade = request.getParameter("grade");	

		MemberBiz biz = new MemberBiz();
		Member dto = new Member(memberId, memberPw, name, email, mobile, zipcode, address1, address2, businessNumber, companyName, entryDate, grade);
		
		
		try {
			biz.updateSellerMyInfo(dto);
			biz.selectOneSeller(dto);
			session.setAttribute("dto", dto);
			session.setAttribute("name", name);
						
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=sellerMyPage&memberId="+memberId+"';</script>");
			writer.close();			
		} catch (Exception e) {
			e.printStackTrace();		
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberMyInfo.jsp';</script>");
			writer.close();
			
		}
	}	
	
	/**
	 * 일반/판매자 : 회원탈퇴 요청
	 */
	protected void memberDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("memberId") == null || session.getAttribute("grade") == null) {
			// 로그인 하지 않은 사용자
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인이 필요한 서비스입니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
			writer.close();
		} 
		
		String memberId = request.getParameter("memberId");
		
		MemberBiz biz = new MemberBiz();
		boolean result = biz.deleteMember(memberId);
		
		if (result) {
			session.invalidate();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원탈퇴가 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
			writer.close();
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원탈퇴가 실패하였습니다.'); location.href= '"+CONTEXT_PATH+"/member/adminMyPage.jsp';</script>");
			writer.close();
		}
	}
	
	/**
	 * 관리자 : 회원탈퇴 요청
	 */
	protected void memberAdminDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("memberId") == null || session.getAttribute("grade") == null) {
			// 로그인 하지 않은 사용자
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인이 필요한 서비스입니다.'); location.href= '"+CONTEXT_PATH+"/index.jsp';</script>");
			writer.close();
		} 
		
		String memberId = request.getParameter("memberId");
		
		MemberBiz biz = new MemberBiz();
		boolean result = biz.deleteMember(memberId);
		
		if (result) {			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원탈퇴가 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=memberList';</script>");
			writer.close();
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원탈퇴가 실패하였습니다.'); location.href= '"+CONTEXT_PATH+"/member/adminMyPage.jsp';</script>");
			writer.close();
		}
	}
	
	/**
	 * 관리자 권한 
	 * 		-- 전체회원 조회 요청 서비스
	 */
	protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[dubug] 전체회원목록 요청");

		/** 데이터추출 */	
		ArrayList<Member> list = new ArrayList<Member>();
		MemberBiz biz = new MemberBiz();
		
		String searchKey = request.getParameter("searchKey");
		String keyWord = request.getParameter("keyWord");
		
		try {
			/** 응답 설정 */
			biz.selectMemberList(list,searchKey,keyWord);
			
			request.setAttribute("list", list);				
				
			request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
		} catch (Exception e) {
			/* 응답 페이지로 이동 */
			request.getRequestDispatcher("/member/adminMypage.jsp").forward(request, response);
		}		
	}
	
	/**
	 * 관리자 상세조회
	 */
	protected void adminMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			biz.selectOneAdmin(dto);	
			request.setAttribute("dto", dto);			
			request.getRequestDispatcher("/admin/adminMyInfo.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/admin/adminMypage.jsp").forward(request, response);
		}		
	}
	
	/**
	 * 관리자 정보 변경
	 */
	protected void adminMyInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		String memberPw = request.getParameter("memberPw");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String entryDate = request.getParameter("entrydate");	
		String grade = request.getParameter("grade");
		
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
		dto.setMemberPw(memberPw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setMobile(mobile);
		dto.setZipcode(zipcode);
		dto.setAddress1(address1);
		dto.setAddress2(address2);
		dto.setEntryDate(entryDate);
		dto.setGrade(grade);
		
		try {			
			biz.updateAdminMyInfo(dto);	
			biz.selectOneAdmin(dto);
			session.setAttribute("dto", dto);
			session.setAttribute("name", name);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경이 완료되었습니다.'); location.href= '"+CONTEXT_PATH+"/member/memberController?action=adminMyInfo&memberId="+memberId+"';</script>");
			writer.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('정보변경에 실패하셨습니다.'); location.href= '"+CONTEXT_PATH+"/admin/adminMyInfo.jsp';</script>");
			writer.close();			
		}		
	}
	
	/**
	 * 관리자 권한 
	 * 		-- 일반/판매자 상세조회 요청 서비스
	 */
	protected void memberDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[debug] 회원 상세조회 요청");
		HttpSession session = request.getSession();
				
		String memberId = request.getParameter("memberId");
		
		if (memberId == null || memberId.trim().length() == 0) {
			request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
			return;
		}
		
		memberId = memberId.trim();
		
		MemberBiz biz = new MemberBiz();
		Member dto = new Member();	
		
		try {
			biz.selectMemberDetail(dto,memberId);
			request.setAttribute("dto", dto);
			
			request.getRequestDispatcher("/member/memberDetail.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
		}
	}

	/**
	 * 내 게시글 리스트 화면 요청 서비스
	 * @author 김보성
	 */
	private void memberMyQNoticeList(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			NoticeBiz biz = new NoticeBiz();
			ArrayList<Qnotice> qnoticeList = new ArrayList<Qnotice>();
			biz.getQnoticeList((String)session.getAttribute("memberId"),qnoticeList);
			
			request.setAttribute("qnoticeList", qnoticeList);
			try {
				request.getRequestDispatcher("/member/myNoticeList.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 내 후기 리스트 화면 요청 서비스
	 * @author 김보성
	 */
	private void memberMyReviewList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OrderListBiz biz = new OrderListBiz();
		ArrayList<Review> reviewList = new ArrayList<Review>();
		
		biz.getReviewList((String)session.getAttribute("memberId"), reviewList);
		request.setAttribute("reviewList", reviewList);
		try {
			request.getRequestDispatcher("/member/myReviewList.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}

