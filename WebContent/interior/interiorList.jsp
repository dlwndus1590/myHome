<%@page import="com.myHome.model.dto.Interior"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Interior> interior = (ArrayList<Interior>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인테리어 업체 전체 목록</title>
<!-- header menu -->
<jsp:include page="/inc/header.jsp" />
<style>
* {
  box-sizing: border-box;
}

.row {
  margin-left:-5px;
  margin-right:-5px;
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
</style>
</head>
<body>
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
        <th>상담</th>
      </tr>
      <%
      		int num = 0;
      		for(Interior dto : interior){
      			num++;
      %>
	      <tr>
	        <td><%=num%></td>
	        <td><%=dto.getIname() %></td>
	        <td><%=dto.getIcareer() %></td>
	        <td><%=dto.getIdetail() %></td>
	        <td><%=dto.getIlocation() %></td>
	        <td class="applyBtn"><a class="applyBtn" href="${CONTEXT_PATH}/interior/interiorController?sangdamApply"> 신청 </a></td>
	      </tr>
      <%} %>
    </table>
  </div>
  
  <div class="column">
  </div>
  
</div>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>