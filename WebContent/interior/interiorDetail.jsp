<%@page import="com.myHome.model.dto.Member"%>
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
.input {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;  
}

/* Add a background color when the inputs get focus */
.input:focus, .input:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
.button{
  background-color: #648E49;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
  border-radius:5px;
}

.button2{
  background-color: #E6E6E6;
  color: black;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
  border-radius:5px;
}

.button2:hover {
  opacity:1;
  background-color:#333333;
  color:white;
}

.button:hover {
  opacity:1;
  background-color:#385723;
}


/* Add padding to container elements */
.container2 {
  padding: 16px;
}

/* Style the horizontal ruler */
.hr {
  border: 1px solid white;
  width:680px;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}



.clearfix {
	display:inline;
}

#ino,#iname,#icareer,#idetail,#ilocation,#imobile{
	height: 40px;
}
</style>
</head>
<body>
<%
	Interior dto = (Interior)session.getAttribute("interiordto");	
%>
<form action="${CONTEXT_PATH}/interior/interiorController?action=updateInterior" method="post" style="width:960px; ">
<div class="row">
<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList">인테리어 모아보기</a> <span class="divider">></span></li>
   		<li class="active">업체명 : [<%=dto.getIname() %>]</li>
    </ul>

<div class="well well-small" style="width:960px; height:760px;">
  
  <h2><%=dto.getIname() %></h2>
	      <hr class="hr">
	      <label><b>No</b></label>      
	      <input type="text" placeholder="Enter No"  class="input" id="ino" name="ino" value="<%=dto.getIno() %>" readonly="readonly">
	      
	      <label><b>회사명</b></label>	      
	      <input type="text" placeholder="Enter Name"   class="input" id="iname" name="iname" value="<%=dto.getIname() %>">
	
	      <label><b>경력</b></label>
	      <input type="text" placeholder="Enter Career"  class="input" id="icareer" name="icareer" value="<%=dto.getIcareer()%>">
	
	      <label><b>회사 설명</b></label>
	      <input type="text" placeholder="Enter Detail"   class="input" id="idetail" name="idetail" value="<%=dto.getIdetail()%>">
	      
	      <label><b>위치</b></label>
	      <input type="text" placeholder="Enter Location"  class="input" id="ilocation" name="ilocation" value="<%=dto.getIlocation()%>">
	      
	      <label><b>연락처</b></label>
      	  <input type="text" placeholder="' - ' 를 포함해서 작성해주세요." class="input" id="imobile" name="imobile" value="<%=dto.getImobile()%>">
	
		  <!-- 
		  		인테리어 등록/수정/삭제하기는 관리자 권한
		  		관리자 로그인 시에만 보이는 버튼  
		  		-->
		  		
		  		<%
	                  Member mainMember = null;
	                  if(session.getAttribute("dto")==null){
	            %>   
	                 	<p></p>                            
	            <%
	               	}else {
	                     mainMember = (Member)session.getAttribute("dto");                     
				%> 
	            <% if(mainMember.getGrade().equals("관리자")){  %>  
	           <br> 
			   <input type="submit" class="button"  value="수정하기">			  							         

</form>

<form action="${CONTEXT_PATH}/interior/interiorController?action=deleteInterior" method="post" style="display:inline; margin:auto;">
	<div class="clearfix" style="margin-left: 1%; display:inline-block;">        
		<input type="submit" class="button2" value="삭제하기">		         
	</div>	 
</form>

<%
	    }	
	            }
%>

</div>
</div>
</div>
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