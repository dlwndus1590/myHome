<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dao = (Member)session.getAttribute("dao");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 회원가입</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/input.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style>
* {
  box-sizing: border-box;
}

.container {
  padding: 16px;
  background-color: white;
}

input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

#zipcode, #memberid{
	width:30%;
}

#idcheck , #zipcodeCheck{
	background-color:#A0DAA9;
	width:100px;
	height: 30px;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

.registerbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.btn-group {
	opacity: 1;
	width: 160px;
	height: 70px;
	margin-bottom: 15px;
	padding: 15px;
}

.registerbtn:hover{
  opacity: 1;
}

a {
  color: dodgerblue;
}

.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>

<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function postcodeTest() {
	   new daum.Postcode({
		    oncomplete: function(data) {			    	
		    	 document.getElementById('zipcode').value = data.zonecode;			    	 
		    	 document.getElementById("address1").value = data.roadAddress;
		         document.getElementById("address2").focus();
		    }
		}).open();
}
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=sellerMyInfoUpdate" style="border:1px solid #ccc" method="post">
	
  <div class="container">
    <h1><%=dao.getName() %> 님 상세조회</h1>    
    <hr>

    <label><b>아이디</b></label>
    <p>
    <input type="text" name="memberId" id="memberId" placeholder="아이디" 
		class="inline" autofocus="autofocus" readonly="readonly" >
  	<br>
  	
    <label><b>비밀번호</b></label>
    <input type="password" placeholder="Enter your Password" name="memberPw" id="memberPw" value="<%=dao.getMemberPw()%>">

    <label><b>이름</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" value="<%=dao.getName()%>">
    
    <label><b>이메일</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" value="<%=dao.getEmail()%>">

	<label><b>핸드폰</b></label>
    <input type="text" placeholder="Enter Mobile" name="mobile" id="mobile" value="<%=dao.getMobile()%>">
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" name="zipcode" id="zipcode" value="<%=dao.getZipcode() %>" >
    <input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck">  
	<input type="text" placeholder="Enter address" name="address1"  required="required" value="<%=dao.getAddress1()%>">
	<input type="text" placeholder="Enter address" name="address2"  required="required" value="<%=dao.getAddress2()%>">	    
    
    <label><b>사업자 번호</b></label>
    <input type="text" placeholder=" '-'를 제외하고 입력해주세요. " name="businessNumber" id="businessNumber" maxlength="12" value="<%=dao.getBusinessNumber()%>">
    
    <label><b>회사/점포명</b></label>
    <input type="text" placeholder="Enter Company" name="companyName" id="companyName" value="<%=dao.getCompanyName()%>">
    
    <hr>

	<input type="submit" class="registerbtn" value="내 정보 변경"></input>
    <button type="submit" class="registerbtn" onclick="location.href='history.back()'">취소하기</button>
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