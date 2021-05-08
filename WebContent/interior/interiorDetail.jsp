<%@page import="com.myHome.model.dto.Interior"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="마이 페이지">
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
.container2 {
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

.row{
	margin-left: 15%;
}
</style>
</head>
<body>
<%
	Interior dto = (Interior)session.getAttribute("interiordto");	
%>
<form action="${CONTEXT_PATH}/interior/interiorController?action=updateInterior" method="post">
<div class="row">
  <div class="column">
  
  <h2><%=dto.getIname() %> 정보조회</h2>
      <div class="container2">            
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
			  <input type="submit"  value="수정하기" >			         
	      </div>
    </div>
    
  </div>
</div>
</form>
<form action="${CONTEXT_PATH}/interior/interiorController?action=deleteInterior" method="post">
	<div class="clearfix" style="margin-left: 17%;">        
			  <input type="submit"  value="삭제하기">		         
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