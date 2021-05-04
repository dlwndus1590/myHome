<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>header</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">
  </head>
<body>
<!-- Upper Header Section -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
			
					<a href="${CONTEXT_PATH}/index.jsp"><span class="icon-lock"></span> Home </a>
					<!-- 로그인 / 미 로그인 상태 변화 -->
					<%
						Member mainMember = null;
						if(session.getAttribute("dto")==null){
					%>	
						<a href="${CONTEXT_PATH}/member/login.jsp"><span class="icon-lock"></span> Login </a>						
					<%
						} else {
							mainMember = (Member)session.getAttribute("dto");
					%>
						<a href="${CONTEXT_PATH}/member/memberController?action=logout"><span class="icon-lock"></span> Logout </a>
					<%
						}
					%>
						
					<!-- 마이페이지 일반회원 / 판매자 회원 분리 -->		
					<%
						if(session.getAttribute("grade")=="일반회원"){
					%>
						 <a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage"><span class="icon-user"></span> 마이페이지</a>
					<%
						} else if(session.getAttribute("grade")=="판매자"){
					%>								
						<a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage"><span class="icon-user"></span> 마이페이지</a> 
					<%
						} else if(session.getAttribute("grade")=="관리자"){
					%>
						<a href="${CONTEXT_PATH}/member/memberController?action=adminMypage"><span class="icon-user"></span> 마이페이지</a> 
					<%
						} else {
					%>
						<a onclick="alert('로그인이 필요한 서비스 입니다.')'"><span class="icon-user"></span> 마이페이지</a>
					<%
						}
					%>
					
					
					<!-- 회원가입 요청 서비스 -->	
					<%						
						if(session.getAttribute("memberId")==null){
					%>	
						<a href="${CONTEXT_PATH}/member/loginChoice.jsp"><span class="icon-edit"></span> 회원가입 </a> 
					<%
						} else {							
					%>
						<a href="${CONTEXT_PATH}/member/loginChoice.jsp"></a>
					<%
						}
					%>
					
					
					<a href="${CONTEXT_PATH}/member/ordersController?action=cartPage"><span class="icon-shopping-cart"></span> 장바구니</a>
					
			</div>
		</div>
	</div>
</div>

<!-- Lower Header Section -->
<div class="container">
<div id="gototop"> </div>
<header id="header">
<div class="row">
	<div class="span4">
	<h1>
	<a class="logo" href="${CONTEXT_PATH}/index.jsp"><span>myHome</span> 
		<img src="${CONTEXT_PATH}/assets/img/logo.png">
	</a>
	</h1>
	</div>
	<div class="span4">
	</div>
</div>
</header>

<!-- Navigation Bar Section -->
<div class="navbar" style="width:960px;">
	  <div class="navbar-inner">
		<div class="container">
		  <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </a>
		  <div class="nav-collapse">
				<ul class="nav">
					 <li class="dropdown">
					 	<a data-toggle="dropdown" class="dropdown-toggle" href="#">커뮤니티 <b class="caret"></b></a>
					 	<div class="dropdown-menu">
					 		<a href="${CONTEXT_PATH}/notice/noticeController?action=noticeListForm">공지사항</a>
					 		<a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm">질문과 답변</a>
					 	</div>
					 </li>
					 <li class="dropdown">
					 	<a data-toggle="dropdown" class="dropdown-toggle" href="#">스토어 <b class="caret"></b></a>
					 	<div class="dropdown-menu">
					 		<a href="#">스토어 홈</a>
					 		<a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">카테고리</a>
					 		<a href="${CONTEXT_PATH}/product/productController?action=productListByBestForm">베스트</a>
					 	</div>
					 </li>
				</ul>
			<form action="#" class="navbar-search pull-left" style="float:right">
			  <input type="text" placeholder="Search" class="search-query span2">
			</form>
		  </div>
		</div>
	  </div>
	</div>
</body>
</html>