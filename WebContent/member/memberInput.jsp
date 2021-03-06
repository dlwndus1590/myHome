<%@page import="com.myHome.model.dto.Member"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="로그인 페이지">
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
* {
  box-sizing: border-box;
}

form{
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

#idcheck ,#zipcodeCheck{
	background-color:#A0DAA9;
	width:100px;
	height: 30px;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

.hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
  width:800px;
}

.registerbtn, .idCheck_btn, .registerbtn{
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 80%;
  opacity: 0.9;
}

.registerbtn:hover{
  opacity: 1;
}

.btn-group {
	opacity: 1;
	width: 160px;
	height: 70px;
	margin-bottom: 15px;
	padding: 15px;
}


a {
  color: dodgerblue;
}

.signin {  
  text-align: center;
}

#memberId, #memberPw,#name,#email,#mobile,#zipcode,#address1,#address2{
	height: 40px;
}
</style>
<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	var idcheck=false;
	
	function idCheck(){
		 $.ajax({
				url:"/myHome/member/memberController?action=idCheck",
				type:"get",			
				data:{
					"memberId" : $("#memberId").val()  
				},				
				success:function(data){
					//alert("data: "+data);
					if(data == '1'){
						alert("해당 아이디는 사용중입니다.");	
						idcheck=false;
					}else {				
						alert("사용가능한 아이디입니다.");
						idcheck=true;
					}			
				}
		});	 
	};
	
	function postcodeTest() {
		   new daum.Postcode({
			    oncomplete: function(data) {			    	
			    	 document.getElementById('zipcode').value = data.zonecode;			    	 
			    	 document.getElementById("address1").value = data.roadAddress;
			         document.getElementById("address2").focus();
			    }
			}).open();
	}
</script>

</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form id="form" action="${CONTEXT_PATH}/member/memberController?action=memberInput"  method="post" style="padding-left:5%; width:960px;">
	
  <div>
  	<br>
    <h1>일반회원 회원가입</h1>    
    <hr class="hr">

    <label><b>아이디</b></label>
    <p>
    <input type="text" name=memberId id="memberId" placeholder="아이디"
		class="inline" autofocus="autofocus">
		<br>
	<input type="button" value="중복체크" class="shopBtn" onclick="idCheck()">	
  	<br><br>
  	
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
    <input type="text" placeholder="Enter mobile" name="mobile" id="mobile" placeholder=" '-'를 포함해서 입력해주세요. " pattern="010-\d{4}-\d{4}" maxlength="13" required>
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" id="zipcode" name="zipcode"  readonly="readonly" >
   	<input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck" onclick="postcodeTest()">
	<input type="text" placeholder="Enter address" name="address1" id="address1" readonly="readonly" >
	<input type="text" placeholder="Enter address" name="address2" id="address2" required="required" >	    
    
    <hr class="hr">

	<input type="submit" class="shopBtn" value="가입하기">        
    <input type="reset" class="defaultBtn"  onclick="location.href='${CONTEXT_PATH}/product/productController?action=storeHome'" value="취소하기">
  </div>
  
  <div class="container signin" >
    <p>Already have an account? <a href="${CONTEXT_PATH}/member/memberController?action=loginForm">Sign in</a>.</p>
  </div>
	
</form>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</html>