package com.myHome.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myHome.model.biz.InteriorBiz;
import com.myHome.model.dto.Interior;

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
			//insertInterior(request, response);
			break;
		case "sangdamApply":
			//sangdamApply(request, response);
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
			System.out.println("인테리어 목록 : "+list);
			request.getRequestDispatcher("/interior/interiorList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
