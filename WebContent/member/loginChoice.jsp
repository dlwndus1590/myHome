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

</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<center>
	<div class="btn-group-div" style="width:100%" style="border:1px solid #ccc">
	  <button type="button" class="btn-group" style="width:50%" onclick="location.href='memberInput.jsp'" value="일반 회원가입">일반 회원가입</button>
	  <button type="button" class="btn-group" style="width:50%" onclick="location.href='sellerInput.jsp'" value="판매자 회원가입">판매자 회원가입</button>
	</div>
</center>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>