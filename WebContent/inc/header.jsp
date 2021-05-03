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
  </head>
<body>
<!-- Upper Header Section -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
				<a href="${CONTEXT_PATH}/member/login.jsp"><span class="icon-lock"></span> Login</a> 
				
				<!-- mypage menu : 마이페이지 분리 메뉴 : 로그인 후 다시 확인해보기 -->
					<c:choose>
						<c:when test="${empty grade}">
							<a href="${CONTEXT_PATH}/member/memberMyPage.jsp"><span class="icon-user"></span> 마이페이지</a> 
						</c:when>
					
						<c:when test="${grade == 'G'}">
							<a href="${CONTEXT_PATH}/member/sellerMyPage.jsp"><span class="icon-user"></span> 마이페이지</a> 
						</c:when>
					
						<c:when test="${grade == 'A'}">
							<a href="${CONTEXT_PATH}/admin/adminMypage.jsp"><span class="icon-user"></span> 마이페이지</a> 
						</c:when>
					</c:choose>				
				
				<a href="${CONTEXT_PATH}/member/loginChoice.jsp"><span class="icon-edit"></span> 회원가입 </a> 
				<a href="${CONTEXT_PATH}/member/cart.jsp"><span class="icon-shopping-cart"></span> 장바구니(2)</a>
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
					 		<a href="#">질문과 답변</a>
					 	</div>
					 </li>
					 <li class="dropdown">
					 	<a data-toggle="dropdown" class="dropdown-toggle" href="#">스토어 <b class="caret"></b></a>
					 	<div class="dropdown-menu">
					 		<a href="#">스토어 홈</a>
					 		<a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">카테고리</a>
					 		<a href="${CONTEXT_PATH}/product/best.jsp">베스트</a>
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