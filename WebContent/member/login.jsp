<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="/myHome/css/login.css">
<script type="text/javascript" src="/myHome/js/member_input.js"></script>
<style>
	a{
		margin-left: 140px;
	}
</style>
</head>
<body>
<!-- header menu -->

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="/myHome/member/login" >
  <div class="container" style="border:1px solid #ccc">
    <h1>로그인</h1>
    <hr>

    <label><b>아이디</b></label>
    <br>
    <input type="text" id="memberId" name="memberId" required>
    
    <br>
    <label><b>비밀번호</b></label>
    <br>
    <input type="password" id="memberPw" name="memberPw" required>
    <div id="memberPwConfirmMessage"></div>	

    <label>
      <input type="checkbox" onclick="showMemberPw()" id="memberPwShow" name="memberPwShow" style="margin-bottom:15px"> 비밀번호 보이기
    </label>


    <div class="clearfix">      
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
    
	<a href="#"><b>아이디 / 비밀번호 찾기</b></a>
	<a href="#"><b>회원가입</b></a>

  </div>

</form>
<!-- footer menu -->

</body>
</html>