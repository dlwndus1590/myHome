<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
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
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=memberInput" style="border:1px solid #ccc" method="post">
	
  <div class="container">
    <h1>Welcome</h1>    
    <hr>

    <label><b>아이디</b></label>
    <p>
    <input type="text" name=memberId id="memberId" placeholder="아이디"
		class="inline" autofocus="autofocus">
	<input type="button" value="중복체크" class="inline">
	<div id="checkMessage"></div>
  	<br>
  	
    <label><b>비밀번호</b></label>
    <input type="password" placeholder="Enter your Password" name="memberPw" id="memberPw" required>    
    <label>
      <input type="checkbox" onclick="showMemberPw()" id="memberPwShow" name="memberPwShow" style="margin-bottom:15px"> 비밀번호 보이기
    </label>

	<br>
    <label><b>이름</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" required>
    
    <label><b>이메일</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required>

	<label><b>핸드폰</b></label>
    <input type="text" placeholder="Enter mobile" name="mobile" id="mobile" required>
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" id="zipcode" name="zipcode"  required="required" maxlength="5">
   	<input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck">
	<input type="text" placeholder="Enter address" name="address1"  required="required" required>
	<input type="text" placeholder="Enter address" name="address2"  required="required" required>	    
    
    <hr>

	<input type="submit" class="registerbtn" value="가입하기">        
    <button type="reset" class="registerbtn"  onclick="location.href='loginChoice.jsp'">취소하기</button>
  </div>
  
  <div class="container signin">
    <p>Already have an account? <a href="${CONTEXT_PATH}/member/memberController?action=loginForm">Sign in</a>.</p>
  </div>
	
</form>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>