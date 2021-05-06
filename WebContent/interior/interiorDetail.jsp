<%@page import="com.myHome.model.dto.Interior"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인테리어 업체 </title>
<!-- header menu -->
<jsp:include page="/inc/header.jsp" />
<style>
/* Full-width input fields */
input[type=text], input[type=password] {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
.cancelbtn, .signupbtn{
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 228px;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  width:680px;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}
</style>
</head>
<body>
<%
	Interior dto = (Interior)session.getAttribute("interiordto");
	System.out.println("detail jsp:"+dto);
%>
<form action="${CONTEXT_PATH}/interior/interiorController?action=updateInterior" method="post">
<div class="row">
  <div class="column">
  <h2><%=dto.getIname() %> 정보조회</h2>
      <div class="container">            
      <hr>
      <label><b>No</b></label>      
      <input type="text" placeholder="Enter No" name="ino" value="<%=dto.getIno() %>" readonly="readonly">
      
      <label><b>회사명</b></label>
      
      <input type="text" placeholder="Enter Name" name="iname" value="<%=dto.getIname() %>">

      <label><b>경력</b></label>
      <input type="text" placeholder="Enter Career" name="icareer" value="<%=dto.getIcareer()%>">

      <label><b>회사 설명</b></label>
      <input type="text" placeholder="Enter Detail" name="idetail" value="<%=dto.getIdetail()%>">
      
      <label><b>위치</b></label>
      <input type="text" placeholder="Enter Location" name="ilocation" value="<%=dto.getIlocation()%>">

	  <!-- 
	  		인테리어 등록/수정/삭제하기는 관리자 권한
	  		관리자 로그인 시에만 보이는 버튼  
	  		-->
      <div class="clearfix">        
		  <input type="submit"  value="수정하기">
		  <input type="submit"  value="삭제하기">        
      </div>
    </div>
  </div>
</div>
</form>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>