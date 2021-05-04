	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 | Choice</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/input.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style type="text/css">
.line {
  border: 1px solid #f1f1f1;
  margin-bottom: 10px;
  width: 200px;
}
</style>
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<center>
	<div class="btn-group-div" style="width:100%" style="border:1px solid #ccc">		
	  <button type="button" style="width:50%;height: 80px" onclick="location.href='memberInput.jsp'" value="일반 회원가입">일반 회원가입</button>
	  <hr class=line>
	  <button type="button" style="width:50%; height: 80px" onclick="location.href='sellerInput.jsp'" value="판매자 회원가입">판매자 회원가입</button>
	</div>
</center>
<br>
<br>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>