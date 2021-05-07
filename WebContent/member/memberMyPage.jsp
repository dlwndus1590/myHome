<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/login.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/mypage.css">
</head>
<body>
<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<div class="container">
	<h1><%=dto.getName() %>[${dto.memberId }] 님의 쇼핑정보</h1>
	<hr>
	<center>
			<a href="${CONTEXT_PATH}/member/memberController?action=memberMyInfo" ><input type="submit" class="mypagebtn" value="내 정보 변경"></a>
				
			<h3><%=dto.getName() %> 님은 [<%=dto.getGrade()%>] 입니다.</h2>
			<p>0원 이상 구매시, 구매금액의 1%를 추가 적립해 드립니다.</p>
			
			<br>
			<br>
			<table>
			  <tr>
			    <th>마일리지 : ${dto.mileage }원</th>
			    <th>쿠폰 : 0개</th>
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
	      <h3>myboard</h3>
	      <a href="#"><p>내 게시글</p></a>  
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
	      <h3>myreview</h3>
	      <a href="#"><p>내 후기</p></a>  
	    </div>
	  </div>
	  
		<div class="column">
		   <div class="card">
		     <h3>cartlist</h3>
		     <a href="#"><p>장바구니</p></a>  
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