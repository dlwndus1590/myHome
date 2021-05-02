<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
<link type="text/css" rel="stylesheet" href="/myHome/css/input.css">
<script type="text/javascript" src="/myHome/js/member_input.js"></script>
</head>
<body>
<!-- header menu -->

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="/myHome/member/memberInput" style="border:1px solid #ccc" method="post">
	
  <div class="container">
    <h1> 님 상세 조회</h1>    
    <hr>

    <label><b>아이디</b></label>
    <p>
    <input type="text" placeholder="Enter your ID" name="memberid" id="memberid" value="" readonly="readonly">	
  	<br>
  	
    <label><b>비밀번호</b></label>
    <input type="password" placeholder="Enter your Password" name="memberPw" id="memberPw" value="">    

	<br>
    <label><b>이름</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" value="">
    
    <label><b>이메일</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" value="">

	<label><b>핸드폰</b></label>
    <input type="text" placeholder="Enter Mobile" name="mobile" id="mobile" value="">
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" id="zipcode" name="zipcode"  value="" maxlength="5">
   	<input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck">
	<input type="text" placeholder="Enter address" name="address1"  value="">
	<input type="text" placeholder="Enter address" name="address2"  value="">	    
    
    <hr>

    <button type="submit" class="registerbtn" onclick="location.href='#'">내 정보 변경</button>
    <button type="submit" class="registerbtn" onclick="location.href='history.back()'">취소하기</button>
  </div>
	
</form>
<!-- footer menu -->
</body>
</html>