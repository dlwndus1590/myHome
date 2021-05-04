<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 회원가입</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/input.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style>
<style>
* {
  box-sizing: border-box;
}

.container {  
  padding: 16px;
  background-color: white;
}

input[type=text], input[type=password] {
  width: 90%;
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
  width:800px;
}

.registerbtn, .idCheck_btn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 80%;
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
<script
     src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js";></script>
<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	/* 아이디 중복 체크 */
	function idCheck() {		
		var memberId = document.getElementById("memberId");
		if(memberId.value=""){// 왜 불러올수 없지?
			alert("아이디를 먼저 입력해주세요.");
			return;
		} 
		url = "idcheck.jsp?memberId="+memberId.value;
		open(url,"confirm","width=350, height=200");
			
	}
	
	function postcodeTest() {
		   new daum.Postcode({
			    oncomplete: function(data) {
			    	 document.getElementById('zipcode').value = data.zonecode;			    	 
			         document.getElementById("address1").value = data.jibunAddress;
			         document.getElementById("address2").focus();
			    }
			}).open();
		}
</script>
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=sellerInput" style="border:1px solid #ccc" method="post">
	
  <div class="container">
    <h1>Welcome</h1>    
    <hr>

    <label><b>아이디</b></label>
    <p>
    <input type="text" name=memberId id="memberId" placeholder="아이디"
		class="inline" autofocus="autofocus">
	<input type="button" value="중복체크" class="idCheck_btn" onclick="idCheck(this.form.memberId.value)">
	<div id="checkMessage" name="checkMessage"></div>
  	<br>
  	
    <label><b>비밀번호</b></label>
    <input type="password" placeholder="Enter your Password" name="memberPw" id="memberPw" required>
    <label>
      <input type="checkbox" onclick="showMemberPw()" id="memberPwShow" name="memberPwShow" style="margin-bottom:15px"> 비밀번호 보이기
    </label>

    <label><b>이름</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" required>
    
    <label><b>이메일</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" required>

	<label><b>핸드폰</b></label>
    <input type="text" placeholder="Enter mobile" name="mobile" id="mobile" required>
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" name="zipcode" id="zipcode" readonly="readonly" >
    <input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck" onclick="postcodeTest()">  
	<input type="text" placeholder="Enter address" name="address1" id="address1" readonly="readonly" >
	<input type="text" placeholder="Enter address" name="address2" id="address2" required="required" >	    
    
    <label><b>사업자 번호</b></label>
    <input type="text" placeholder=" '-'를 제외하고 입력해주세요. " name="businessNumber" id="businessNumber" maxlength="12" required>
    
    <label><b>회사/점포명</b></label>
    <input type="text" placeholder="Enter Company" name="companyName" id="companyName" required>
    
    <hr>

	<input type="submit" class="registerbtn" class="regist" value="가입하기">        
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