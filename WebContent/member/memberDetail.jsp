<%@page import="com.myHome.model.dto.MessageEntity"%>
<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.myHome.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<Member> dto = (ArrayList<Member>)request.getAttribute("list");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세조회</title>

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: center;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>

</head>
<body>
<hr>

<h2>${dto.name}  님 상세조회</h2>
	<table>
	  <tr>
	    <th>No</th>
	    <th>아이디</th>
	    <th>비밀번호</th>
	    <th>이름</th>
	    <th>이메일</th>
	    <th>휴대폰</th>
	    <th>우편번호</th>    
	  </tr>
		<%
			int num  = 0;
			num++;
		%>
		  <tr>	    
		  	<td><%=num %></td>
		    <td>${dto.memberId}</td>
		    <td>${dto.memberPw}</td>
		    <td>${dto.name}</td>
		    <td>${dto.email}</td>
		    <td>${dto.mobile}</td>
		    <td>${dto.zipcode}</td>   
		  </tr>
	</table>
	<p>
	<table>
		<tr>
			<th>기본주소</th>
	    	<th>상세주소</th>	
			<th>사업자 번호</th>
		    <th>회사/점포명</th>	    
		    <th >가입일</th>      
		    <th>마일리지</th>
		    <th>등급</th>
		</tr>

		<tr>
			<td>${dto.address1}</td>
	    	<td>${dto.address2}</td>
			<td>${dto.businessNumber}</td>
		    <td>${dto.companyName}</td>
		    <td>${dto.entryDate}</td>			    		    
		    <td>${dto.mileage}</td>
		    <td>${dto.grade}</td>	
		</tr>
	</table>
	

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>