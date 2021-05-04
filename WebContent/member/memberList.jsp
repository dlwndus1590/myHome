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
	  </tr>

		<%
         //++혹은 --할 대상은 변수로 받아놓고 처리해야 편하다
         int curPos = pager.getCurPos();
         int num = pager.getNum();
      	%>
      	<%for(int i = 1; i<=pager.getPageSize();i++){ %>
      	<%if(num<1)break; %>
      	<%
      		Member dto = dao.get(curPos++);
      	%>
		<c:forEach var="dto" items="${list}">
		  <tr>	    
		  	<td><%=num-- %></td>
		    <td><a href="${CONTEXT_PATH}/member/memberController?action=memberDetail&memberId=${dto.memberId}">${dto.memberId}</a></td>
		    <td>${dto.memberPw}</td>
		    <td>${dto.name}</td>
		    <td>${dto.email}</td>
		    <td>${dto.mobile}</td>
		    <td>${dto.zipcode}</td>   
		  </tr>
	  	</c:forEach>
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
		
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.address1}</td>
		    	<td>${dto.address2}</td>
				<td>${dto.businessNumber}</td>
			    <td>${dto.companyName}</td>
			    <td>${dto.entryDate}</td>			    		    
			    <td>${dto.mileage}</td>
			    <td>${dto.grade}</td>	
			</tr>
		</c:forEach>
		<%} %>
		
		<tr>
         <td colspan="7" style="text-align:center">
            <div align="center">
				<a href="${CONTEXT_PATH}/member/memberController?action=memberList?currentPage=<%if(pager.getFirstPage()==1){%>1<%}else{ %><%=pager.getFirstPage()-1%><%}%>">◀</a>
				<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
				<%if(i>pager.getTotalPage())break;%>   
					<a  <%if(pager.getCurrentPage()==i){%>class="pageNum"<%}%>   href="${CONTEXT_PATH}/member/memberController?action=memberList?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<a href="${CONTEXT_PATH}/member/memberController?action=memberList?currentPage=<%if(pager.getLastPage()>=pager.getTotalPage()){%><%=pager.getTotalPage()%><%}else{ %><%=pager.getLastPage()+1%><%}%>">▶</a>
			</div>
         </td>
      	</tr>
		
	</table>
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
	

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>