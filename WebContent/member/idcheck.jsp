<%@page import="com.myHome.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
</head>
<body>
<%
	String memberId = request.getParameter("memberId");
	System.out.println("idcheck.jsp 의 memberid : "+memberId);
	
	MemberDao dao = new MemberDao();
	boolean check = dao.selectCheckId(memberId);
	
	if(check){%>
		<center>
		<h4>이미 존재하는 아이디 입니다.</h4>
		</center>
	<%} else{%>
		<center>
		<h4>[<%=memberId %>] 는 사용가능한 아이디 입니다.</h4>
		</center>
	<%}
%>
	<center>
		<b><%=memberId %></b>
		<%
			if(check){
				out.println("이미 존재하는 아이디 입니다.");
			} else{
				out.println("사용가능한 아이디 입니다.");
			}
		%>
		<a href="#" onclick="self.close()">닫기</a>
	</center>
</body>
</html>