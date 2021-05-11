<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<%
	Member dto = (Member)session.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="로그인 페이지">
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
	#memberId, #memberPw,#memberPwConfirm{
		height: 50px;		
		font-size: 20px;
	}
	
	.col{
		margin-left: 2%;
	}
	
	* {box-sizing: border-box}

	.input, .input {
	  width: 50%;
	  padding: 15px;
	  margin: 5px 0 22px 0;
	  display: inline-block;
	  border: none;
	  background: #f1f1f1;
	}
	
	.input:focus, .input:focus {
	  background-color: #ddd;
	  outline: none;
	}
	
	hr {
	  border: 1px solid #f1f1f1;
	  margin-bottom: 25px;
	}
	.mypagebtn{
	  background-color: #548235;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  cursor: pointer;
	  width: 50%;
	  opacity: 0.9;
	  border-radius:5px;
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
	
	.cancelbtn {
	  padding: 14px 20px;
	  background-color: #f44336;
	}

	
</style>

</head>
<body>
<!-- main menu : 로그인 전 -->
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

<!-- contents menu -->
<form class="container" action="${CONTEXT_PATH}/member/memberController?action=memberDelete" method="post" >
<div class="row">
<div class="span9" style="width:960px;" >
<div class="well well-small">
	
	   <div class="row">
	      <h2 style="text-align:center">다음에 또 뵙고 싶습니다.</h2>
	      <div class="vl">
	        <span class="vl-innertext"></span>
	      </div>	
	      
		<div class="col">
	      	<input type="text" class="input" name="memberId" id="memberId" placeholder="User Id" required>
	        <input type="password" class="input" name="memberPw" id="memberPw" placeholder="User password" required>
        	<input type="password" class="input" id="memberPwConfirm" name="memberPwConfirm" placeholder="User password" required>
        	
        	<!-- 비밀번호 보이기 -->        	
			<input type="checkbox"  name="memberPwShow" id="memberPwShow" 
					onclick="showMemberPw()">비밀번호 보이기
			<div id="memberPwConfirmMessage" name="memberPwConfirmMessage"></div>
			
	        <input type="submit" class="input" value="탈퇴하기">
		</div>	      

</div>
</div>
</div>	
</form>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</html>