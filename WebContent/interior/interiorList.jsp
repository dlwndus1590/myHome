<%@page import="com.myHome.model.dto.Member"%>
<%@page import="com.myHome.model.dto.Interior"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Interior> list = (ArrayList<Interior>)request.getAttribute("list");
%>
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
body {font-family: Arial, Helvetica, sans-serif;}

* {
  box-sizing: border-box;
}

.row {
  margin-left:-12px;
  margin-right:-6px;  
}
  
.column {
  float: left;
  width: 100%;
  padding: 5px;  
}

/* Clearfix (clear floats) */
.row::after {
  content: "";
  clear: both;
  display: table;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 950px;
  border: 1px solid #ddd;
}

th, td {
  text-align: center;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.applyBtn {	
	color: black;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
}
button{
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
  margin-left: 3%;
}

button:hover {
  opacity:1;
}
</style>
</head>
<body>
<div class="row">
<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li class="active">인테리어 모아보기</li>
    </ul>
<div class="well well-small">
<h2>인테리어 모아보기</h2>
<p>디자인은 아무것도 없는 곳에 무언가를 넣어 시간과 공간을 만드는 것이다.</p>

	<div class="row">
	  <div class="column">
	    <table>
	      <tr>
	        <th>No</th>
	        <th>업체명</th>
	        <th>경력</th>
	        <th>업체 설명</th>
	        <th>위치</th>
	        <th>연락처</th>
	        <th>상담</th>
	      </tr>
	      <%
	      		int num = 0;
	      		for(Interior dto : list){
	      			num++;
	      %>
		      <tr>
		        <td><%=dto.getIno() %></td>
		        <td><a href="${CONTEXT_PATH}/interior/interiorController?action=selectInterior&iname=<%=dto.getIname()%>"><%=dto.getIname() %></a></td>
		        <td><%=dto.getIcareer() %></td>
		        <td><%=dto.getIdetail() %></td>
		        <td><%=dto.getIlocation() %></td>
		        <td><%=dto.getImobile()%></td>
		        <td class="applyBtn"><a class="applyBtn" href="${CONTEXT_PATH}/interior/interiorController?action=billPage&icareer=<%=dto.getIcareer()%>">견적</a></td>
		      </tr>
	      <%} %>
	    </table>
	  </div>
	  
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
	                  	<button onclick="location.href='interiorInput.jsp'" style="width:auto;">등록하기</button>
	         	<%
	                }
	            }
	            %>
	            
	</div>
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