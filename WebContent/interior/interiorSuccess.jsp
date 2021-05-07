<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>

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

.container {
  padding: 2px 16px;
  background-color: #f1f1f1;
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

<div class="coupon">
  <div class="container">
    <h3>my Home</h3>
  </div>
  <img src="${CONTEXT_PATH}/img/sangdam.jpg" alt="Avatar" style="width:100%;">
  <div class="container" >
    <h2><b>상담신청이 완료되었습니다.</b></h2> 
    <p>항상 마음에 디자인을 담아 최상의 만족을 드리기 위해 최선을 다하겠습니다. 화이팅</p>
  </div>
  <div class="container">
    <p>Use Code: <span class="promo"><%=Utility.getSecureString(6, true)%></span></p>
    <p class="application">Application date: <%=Utility.getCurrentDate("yyyy-MM-dd")%></p>
  </div>
</div>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>