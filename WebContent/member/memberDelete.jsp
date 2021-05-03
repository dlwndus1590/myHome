<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈되</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/find.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
<script type="text/javascript">
	<!-- 제이쿼리 이용해서 회원 탈퇴 시도해보기 -->
//	$(function(){		
//		var bt_del=$("input[type='button']")[0]; //삭제
//		$(bt_del).click(function(){
//			del();
//		});
		
//	});
	
//	function del(){
//		if(confirm("삭제하시겠어요?")){
//			location.href="/member/memberDelete?member_id=<%=dto.getMemberId()%>";		
//		}	
//	}
</script>
</head>
<body>
<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form class="container" action="${CONTEXT_PATH}/member/memberController?action=memberDelete" method="post">
	
	   <div class="row">
	      <h2 style="text-align:center">다음에 또 뵙고 싶습니다.</h2>
	      <div class="vl">
	        <span class="vl-innertext"></span>
	      </div>	
	      
		<div class="col">
	      	<input type="text" name="memberId" id="memberId" placeholder="User Id" required>
	        <input type="password" name="memberPw" id="memberPw" placeholder="User password" required>
        	<input type="password" id="memberPwConfirm" name="memberPwConfirm" placeholder="User password" required>
        	<!-- 비밀번호 보이기 -->
        	
			<input type="checkbox" name="memberPwShow" id="memberPwShow" 
					onclick="showMemberPw()">비밀번호 보이기
			<div id="memberPwConfirmMessage" name="memberPwConfirmMessage"></div>
			
	        <input type="submit" value="탈퇴하기">
		</div>	      

	
</form>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>