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
	  font-size: 17px;
	  font-family: verdana;
	}
	
	tr:nth-child(even) {
	  background-color: #f2f2f2;
	}
	.well{
		background-color: white;
	}
	.input{
	  background-color: #548235;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  cursor: pointer;
	  width: 100%;
	  opacity: 0.9;
	  border-radius:5px;
	}
	
	.input:hover {
	  opacity:1;
	}
	
	.a{
		font-family: verdana;
		font-size: 25px;
		font-weight: bold;
		color: black;
		margin-left: 86%;
	}

</style>

</head>
<body>

<div class="row">
<div class="span9" style="width:960px;backgroud-color:white;">
<div class="well well-small">
<h2>${dto.name}  님 정보</h2>
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
	<br>
	<a href="${CONTEXT_PATH}/member/memberController?action=memberAdminDelete&memberId=${dto.memberId}"
		class="a"	onclick="return confirm('회원님을 정말 탈퇴시키시겠습니까?')">탈퇴시키기</a>			
	 
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