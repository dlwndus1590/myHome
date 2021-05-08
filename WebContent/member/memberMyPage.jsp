<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="일반회원 마이페이지">
    <meta name="author" content="강하영">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">   
	<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/mypage.css">
	
	<!-- header menu -->
	<jsp:include page="/inc/header.jsp" />	

	<style type="text/css">
	
		.mypagebtn{
  			background-color: #548235;
  			color: white;
  			padding: 14px 20px;
			margin: 8px 0;
			border: none;
			cursor: pointer;
			width: 50%;
			opacity: 0.9;
			border-radius:5px;
		}
	</style>
	
  </head>

<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<div class="row">
<div class="span9" style="width:960px;">
<div class="well well-small" style="height:850px;">
	<div class="container">
		<h1><%=dto.getName() %>[${dto.memberId }] 님의 쇼핑정보</h1>
		<hr>
		<center>
				<a href="${CONTEXT_PATH}/member/memberController?action=memberMyInfo" ><input type="submit" class="mypagebtn" value="내 정보 변경"></a>
					
				<h3><%=dto.getName() %> 님은 [<%=dto.getGrade()%>] 입니다.</h2>
				<p>구매금액의 1%를 추가 적립해 드립니다.</p>
				
				<br>
				<br>
				<table>
				  <tr>
				    <th>마일리지 : ${dto.mileage}원</th>				    
				  </tr>
				</table>
		
		</center>
	</div>
	<br>
	<br>
	<br>
	<div class="mypageForm">
		<div class="row">
		  <div class="column">
		    <div class="card">
		      <h3>profile</h3>
		      <a href="${CONTEXT_PATH}/member/memberController?action=memberMyInfo"><p>회원정보</p></a>      
		    </div>
		  </div>
		
		  <div class="column">
		    <div class="card">
		      <h3>order list</h3>
		      <a href="${CONTEXT_PATH}/orderList/orderListController?action=orderList"><p>주문내역</p></a>  
		    </div>
		  </div>
		  
		  <div class="column">
		    <div class="card">
		      <h3>my Qboard</h3>
		      <a href="${CONTEXT_PATH}/member/memberController?action=memberMyQNoticeList"><p>내 질문 게시글</p></a>  
		    </div>
		  </div>
		</div> 
	
		<br>
		<br>
		<br>
		<br>
	
		<div class="row">  
		  <div class="column">
		    <div class="card">
		      <h3>my Review</h3>
		      <a href="${CONTEXT_PATH}/member/memberController?action=memberMyReviewList"><p>내 후기</p></a>  
		    </div>
		  </div>
		  
			<div class="column">
			   <div class="card">
			     <h3>cartlist</h3>
			     <a href="${CONTEXT_PATH}/member/ordersController?action=cartPage"><p>장바구니</p></a>  
			   </div>
			 </div>
		
			
			<div class="column">
			   <div class="card">
			     <h3>widthdraw</h3>
			     <a href="${CONTEXT_PATH}/member/memberDelete.jsp"><p>탈퇴하기</p></a>  
			   </div>
			</div>
		</div>
	</div>
</div>
</div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>