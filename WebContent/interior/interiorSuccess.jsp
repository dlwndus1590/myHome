<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="마이 페이지">
    <meta name="author" content="강하영">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">

	<!-- header menu -->
	<jsp:include page="/inc/header.jsp" />

<style>
body {
  font-family: Arial;
}

.coupon {
  border: 5px dotted #bbb;
  width: 80%;
  border-radius: 15px;
  margin: 0 auto;
  max-width: 100%;
}

.container2 {
  padding: 2px 16px;  
  width: 65%
}

.promo {
  background: #ccc;
  padding: 3px;
}

.application {
  color: red;
}
</style>

</head>
<body>
<!-- main menu : 로그인 전 -->
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

<div class="row">
<div class="span9" style="width:960px;">
<div class="well well-small">

	<div class="coupon">
	  <div class="container2">
	    <h3>my Home</h3>
	  </div>
	  <img src="${CONTEXT_PATH}/img/sangdam.jpg" alt="Avatar" style="width:100%;">
	  <div class="container" >
	    <h2><b>상담신청이 완료되었습니다.</b></h2> 
	    <p>항상 마음에 디자인을 담아 최상의 만족을 드리기 위해 최선을 다하겠습니다. 화이팅</p>
	  </div>
	  <div class="container">	    
	    <p class="application">Application date: <%=Utility.getCurrentDate("yyyy-MM-dd")%></p>
	  </div>
	</div>
</div>
</div>
</div>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>