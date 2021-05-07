<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 회원가입</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/input.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style type="text/css">
	input[type=text], input[type=password]{
		height: 35px;		
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
</script>
</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=memberMyInfoUpdate" style="border:1px solid #ccc" method="post">
	
  <div class="container">
    <h1><%=dto.getName() %> 님 상세 조회</h1>    
    <hr>

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
   	<input type="button" value="우편찾기" id="zipcodeCheck" name="zipcodeCheck">
	<input type="text" placeholder="Enter address" name="address1"  value="<%=dto.getAddress1()%>">
	<input type="text" placeholder="Enter address" name="address2"  value="<%=dto.getAddress2()%>">	    
    
    <label><b>가입일자</b></label>
    <input type="text" placeholder="Enter Mobile" name="entrydate" id="entrydate" value="<%=dto.getEntryDate()%>">
    
    <label><b>마일리지</b></label>
    <input type="text" placeholder="Enter Mobile" name="mileage" id="mileage" value="<%=dto.getMileage()%>">
        
    <label><b>등급</b></label>
    <input type="text" placeholder="Enter Mobile" name="grade" id="grade" value="<%=dto.getGrade()%>">
    <hr>

    <input type="submit" class="registerbtn" value="내 정보 변경"></input>
    <input type="submit" class="registerbtn" onclick="location.href='history.back()'" value="취소하기"></input>
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