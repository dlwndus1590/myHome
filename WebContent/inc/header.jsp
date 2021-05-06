<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    <style type="text/css">
		.sidenav {
		  height: 100%;
		  width: 0;
		  position: fixed;
		  z-index: 1;
		  top: 0;
		  left: 0;
		  background-color: #111;
		  overflow-x: hidden;
		  transition: 0.5s;
		  padding-top: 60px;
		}
		
		.sidenav a {
		  padding: 8px 8px 8px 32px;
		  text-decoration: none;
		  font-size: 25px;
		  color: #818181;
		  display: block;
		  transition: 0.3s;
		}
		
		.sidenav a:hover {
		  color: #f1f1f1;
		}
		
		.sidenav .closebtn {
		  position: absolute;
		  top: 0;
		  right: 25px;
		  font-size: 36px;
		  margin-left: 50px;
		}
		
		.interiorMenu{
			
		}
		
		@media screen and (max-height: 450px) {
		  .sidenav {padding-top: 15px;}
		  .sidenav a {font-size: 18px;}
		}

    </style>
    
  </head>
<body>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">X</a>
  <br>
  <a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList">모아보기</a>
</div>

<!-- Upper Header Section -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">

					<!-- 로그인 / 미 로그인 상태 변화 -->
					<%
						Member mainMember = null;
						if(session.getAttribute("dto")==null){
					%>	
						<a href="${CONTEXT_PATH}/member/login.jsp"><span class="icon-lock"></span> Login </a>	
						<a href="javascript:alert('로그인이 필요한 서비스 입니다.')"><span class="icon-user"></span> 마이페이지</a>					
					<%
						} else {
							mainMember = (Member)session.getAttribute("dto");
							System.out.println("정보 : " + mainMember);
					%>
						<a href="${CONTEXT_PATH}/member/memberController?action=logout"><span class="icon-lock"></span> Logout </a>						
					<%
							if(mainMember.getGrade().equals("일반회원")){%>
								<a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage"><span class="icon-user"></span> 마이페이지</a>
					<%
							} else if(mainMember.getGrade().equals("판매자")){%>
								<a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage"><span class="icon-user"></span> 마이페이지</a> 						
					<%
							} else if(mainMember.getGrade().equals("관리자")){%>
								<a href="${CONTEXT_PATH}/member/memberController?action=adminMyPage"><span class="icon-user"></span> 마이페이지</a>
					<%
							}
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
					 		<a href="${CONTEXT_PATH}/index.jsp">스토어 홈</a>
					 		<a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">카테고리</a>
					 		<a href="${CONTEXT_PATH}/product/productController?action=productListByBestForm">베스트</a>
					 	</div>
					 </li>
					 <li class="dropdown">
					 	<span class="interiorMenu" style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776; 인테리어</span>
					 </li>
				</ul>
			<form action="#" class="navbar-search pull-left" style="float:right">
			  <input type="text" placeholder="Search" class="search-query span2">
			</form>
		  </div>
		</div>
	  </div>
	</div>
    
	<script>
		function openNav() {
		  document.getElementById("mySidenav").style.width = "250px";
		}
		
		function closeNav() {
		  document.getElementById("mySidenav").style.width = "0";
		}
	</script>    
</body>
</html>