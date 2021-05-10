<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
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

<style type="text/css">
	* {
	  box-sizing: border-box;
	}
	
	form{
		background-color: white;
		margin-left: 3%;
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
	#memberid, #memberPw,#name,#email,#mobile,#zipcode,#address1,#address2,#entrydate,#grade{
		height: 40px;
	}
</style>

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
</script>
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=adminMyInfoUpdate"  method="post" style="padding-left:4%;">
	
  <div class="container">
  	<br>
    <h1><%=dto.getName() %> 님 상세 조회</h1>    
    <hr class="hr">

    <label><b>아이디</b></label>
    <p>
    <input type="text" placeholder="Enter your ID" name="memberid" id="memberid" value="<%=dto.getMemberId() %>" readonly="readonly">	
  	<br>
  	
    <label><b>비밀번호</b></label>
    <input type="password" placeholder="Enter your Password" name="memberPw" id="memberPw" value="<%=dto.getMemberPw()%>">    

	<br>
    <label><b>이름</b></label>
    <input type="text" placeholder="Enter Name" name="name" id="name" value="<%=dto.getName()%>">
    
    <label><b>이메일</b></label>
    <input type="text" placeholder="Enter Email" name="email" id="email" value="<%=dto.getEmail()%>">

	<label><b>핸드폰</b></label>
    <input type="text" placeholder="Enter Mobile" name="mobile" id="mobile" value="<%=dto.getMobile()%>">
    
    <label><b>주소</b></label>
    <br>
    <input type="text" placeholder="Enter Zipcode" id="zipcode" name="zipcode"  value="<%=dto.getZipcode() %>" maxlength="5">
   	<input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck" onclick="postcodeTest()">
	<input type="text" placeholder="Enter address" id="address1" name="address1"  value="<%=dto.getAddress1()%>" readonly="readonly">
	<input type="text" placeholder="Enter address" id="address2" name="address2"  value="<%=dto.getAddress2()%>">	    
    
    <label><b>가입일자</b></label>
    <input type="text" placeholder="Enter Mobile" name="entrydate" id="entrydate" value="<%=dto.getEntryDate()%>" readonly="readonly">
            
    <label><b>등급</b></label>
    <input type="text" placeholder="Enter Mobile" name="grade" id="grade" value="<%=dto.getGrade()%>" readonly="readonly">
    <hr class="hr">

    <input type="submit" class="registerbtn" value="내 정보 변경"></input>
    <input type="button" class="registerbtn" onclick="history.back();" value="취소하기"></input>
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