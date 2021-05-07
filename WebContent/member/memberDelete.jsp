<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈되</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/login.css">
<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style>
	#memberId, #memberPw,#memberPwConfirm{
		height: 50px;
		font-size: 20px;
	}
</style>

</head>
<body>
<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form class="container" action="${CONTEXT_PATH}/member/memberController?action=memberDelete" method="post">
	
	   <div class="row">
	      <h2 style="text-align:center">다음에 또 뵙고 싶습니다.</h2>
	      <div class="vl">
	        <span class="vl-innertext"></span>
	      </div>	
	      
		<div class="col">
	      	<input type="text" name="memberId" id="memberId" placeholder="User Id" required>
	        <input type="password" name="memberPw" id="memberPw" placeholder="User password" required>
        	<input type="password" id="memberPwConfirm" name="memberPwConfirm" placeholder="User password" required>
        	<!-- 비밀번호 보이기 -->
        	
			<input type="checkbox" name="memberPwShow" id="memberPwShow" 
					onclick="showMemberPw()">비밀번호 보이기
			<div id="memberPwConfirmMessage" name="memberPwConfirmMessage"></div>
			
	        <input type="submit" value="탈퇴하기">
		</div>	      

	
</form>
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