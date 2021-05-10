<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="결제 완료 페이지">
    <meta name="author" content="최인묵">
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
  height: 200px;
  border-radius: 15px;
  margin: 0 auto;
  max-width: 100%;
}

.container2 {
  padding: 2px 16px;  
  width: 65%;
}

h2 {
  margin-left: 10px;
}

button {
  margin-left: 10px;
}
</style>

</head>
<body>
<div class="row">
<div class="span9" style="width:960px;">
<div class="well well-small">

	<div class="coupon">
	  <div class="container2">
	    <h3>my Home</h3>
	  </div>
	  <div class="container" >
	    <h2><b>결제가 완료되었습니다.</b></h2> 
	    <button class="btn btn-success" onclick="location.href='${CONTEXT_PATH}/member/memberController?action=memberMyPage'">마이페이지</button>
	  </div>
	</div>
</div>
</div>
</div>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>