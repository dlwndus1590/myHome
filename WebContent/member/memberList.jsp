<%@page import="com.myHome.common.Pager"%>
<%@page import="com.myHome.model.dto.MessageEntity"%>
<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.myHome.model.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<Member> dao = (ArrayList<Member>)request.getAttribute("list");	
	Pager pager = new Pager();
	pager.init(request, dao);
%>
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
  width: 1250px;
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
	    <th>이메일</th>
	    <th>휴대폰</th>
	    <th>우편번호</th>
	    <th>기본주소</th>
    	<th>상세주소</th>	
		<th>사업자 번호</th>
	    <th>회사/점포명</th>	    
	    <th >가입일</th>      
	    <th>마일리지</th>
	    <th>등급</th>    
	  </tr>
		<%
			int num = 1;
		%>
		<c:forEach var="dto" items="${list}">
		  <tr>	    
		  	<td><%=num++ %></td>
		    <td><a href="${CONTEXT_PATH}/member/memberController?action=memberDetail&memberId=${dto.memberId}">${dto.memberId}</a></td>
		    <td>${dto.memberPw}</td>
		    <td>${dto.name}</td>
		    <td>${dto.email}</td>
		    <td>${dto.mobile}</td>
		    <td>${dto.zipcode}</td>  
		    <td>${dto.address1}</td>
	    	<td>${dto.address2}</td>
			<td>${dto.businessNumber}</td>
		    <td>${dto.companyName}</td>
		    <td>${dto.entryDate}</td>			    		    
		    <td>${dto.mileage}</td>
		    <td>${dto.grade}</td> 
	  </tr>
	  
	  	</c:forEach>
	</table>
	<p>
	<div>
		<table>
			<tr>
				<td>[1]</td>
			</tr>			
		</table>
	</div>
	
<p>
<p>
<p>
<form action="${CONTEXT_PATH}/member/memberController?action=memberList" style="max-width:200px" method="post">  	
  	<select name="searchKey">
		<option value="none">::선택::</option>
		<option value="member_id">아이디</option>
		<option value="name">이름</option>
		<option value="grade">등급</option>
	</select>
  <input type="text" placeholder="Search.." name="keyWord">  
  <input type="submit" class="signupbtn" value="검색" onclick="searchCheck(form)"></button>
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