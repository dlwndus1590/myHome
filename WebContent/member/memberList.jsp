<%@page import="com.myHome.common.Pager"%>
<%@page import="com.myHome.model.dto.MessageEntity"%>
<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.myHome.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/inc/taglib_menu.jsp" %>  
<%
	ArrayList<Member> dao = (ArrayList<Member>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="아이디/비밀번호 찾기">
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
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
  margin-right: 50px;
}

th, td {
  text-align: center;
  padding: 10px;
  font-size: 10px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
.well{
	background-color: white;
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
<!-- main menu : 로그인 전 -->
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

<div class="row">
<div class="span9" style="width:960px;backgroud-color:white;">
<div class="well well-small">
<h2>회원 전체목록</h2>
	<table>
	  <tr>
	    <th>No</th>
	    <th>아이디</th>
	    <th>비밀번호</th>
	    <th>이름</th>
	    <th>이메일</th>
	    <th>휴대폰</th>
	    <th>우편번호</th>
	    <th>기본주소</th>
    	<th>상세주소</th>	
		<th>사업자 번호</th>
	    <th>등급</th>    
	  </tr>
		<%
			int num = 1;			
		%>
		<c:forEach var="dto" items="${list}">
		  <tr>	    
		  	<td><%=num++ %></td>
		    <td><a href="${CONTEXT_PATH}/member/memberController?action=memberDetail&memberId=${dto.memberId}">${dto.memberId}</a></td>
		    <td><%=Utility.convertSecurePw("${dto.memberPw}") %></td>
		    <td>${dto.name}</td>
		    <td>${dto.email}</td>
		    <td>${dto.mobile}</td>
		    <td>${dto.zipcode}</td>  
		    <td>${dto.address1}</td>
	    	<td>${dto.address2}</td>
			<td>${dto.businessNumber}</td>
		    <td>${dto.grade}</td> 
	  </tr>
	  
	  	</c:forEach>
	</table>
	<p>
	
<p>
<p>
<p>
<form action="${CONTEXT_PATH}/member/memberController?action=memberList" style="max-width:200px; display:inline; padding-left:23%;" method="post">  	
  	<select name="searchKey" style="display:inline;">
		<option value="none">::선택::</option>
		<option value="member_id">아이디</option>
		<option value="name">이름</option>
		<option value="grade">등급</option>
	</select>
  <input type="text" placeholder="Search.." name="keyWord" style="display:inline; height:30px;">  
  <input type="submit" class="shopBtn" value="검색" onclick="searchCheck(form)" style="display:inline; height:30px;"></button>
</form>

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