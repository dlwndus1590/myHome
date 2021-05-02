<%@page import="com.myHome.model.dto.MessageEntity"%>
<%@page import="com.myHome.common.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.myHome.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원전체조회</title>

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
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>
<script type="text/javascript">
	function searchCheck(frm){
	    //검색
	   
	    if(frm.keyWord.value ==""){
	        alert("검색 단어를 입력하세요.");
	        frm.keyWord.focus();
	        return;
	    }
	    frm.submit();      
	}
</script>
</head>
<body>
<hr>

<h2>회원 전체목록</h2>
	<table>
	  <tr>
	    <th>No</th>
	    <th>아이디</th>
	    <th>비밀번호</th>
	    <th>이름</th>
	    <th>휴대폰</th>
	    <th>가입일</th>
	    <th>등급</th>
	    <th>마일리지</th>
	    <th>담당자</th>
	  </tr>
		
		<c:forEach var="dto" items="${list}">
		  <tr>	    
		  	<td>1</td>
		    <td><a href="${CONTEXT_PATH}/member/memberController?action=memberDetail?memberId=${dto.memberId}">${dto.memberId}</a></td>
		    <td>${dto.memberPw }</td>
		    <td>${dto.name}</td>
		    <td>${dto.mobile}</td>
		    <td>${dto.entryDate}</td>
		    <td>${dto.grade}</td>
		    <td>${dto.mileage}</td>
		    <td>${dto.manager}</td>	    
		  </tr>
	  </c:forEach>  

	</table>

<form action="${CONTEXT_PATH}/member/memberController?action=memberList" style="max-width:200px" method="post">  	
  	<select name="searchKey">
		<option value="none">::선택::</option>
		<option value="memberid">아이디</option>
		<option value="name">이름</option>
		<option value="grade">등급</option>
	</select>
  <input type="text" placeholder="Search.." name="keyWord">  
  <input type="submit" class="signupbtn" value="검색" onclick="searchCheck(form)"></button>
</form>
	

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>