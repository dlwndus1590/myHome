<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
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
    * {box-sizing: border-box}

	.input {
	  width: 50%;
	  padding: 15px;
	  margin: 5px 0 22px 0;
	  display: inline-block;
	  border: none;
	  background: #f1f1f1;
	}
	
	.input:focus {
	  background-color: #ddd;
	  outline: none;
	}
	
	.hr {
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
	button{
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
	
	button:hover {
	  opacity:1;
	}
	
	.cancelbtn {
	  padding: 14px 20px;
	  background-color: #f44336;
	}
	
	.cancelbtn, .signupbtn {
	  float: left;
	  width: 50%;
	}
	
	.container2 {
	  padding: 16px;
	}
	
	.clearfix::after {
	  content: "";
	  clear: both;
	  display: table;
	}
	#memberId, #memberPw{
		height: 50px;
		font-size: 20px;
	}
	
	.loginMain{
		margin-left: 10%;
	}
	</style>
<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
</head>
<body>



<!-- contents menu -->
<div class="row">
<div class="span9" style="width:960px;">

<div class="well well-small">
	<form action="${CONTEXT_PATH}/member/memberController?action=login" method="post" style="padding-left:23%;">
	  <div class="container2">
	    <h1>로그인</h1>
	    <hr class="hr"> 
	
	    <label><b>아이디</b></label>    
	    <input type="text" class="input" id="memberId" name="memberId" required>
	    
	    <br>
	    <label><b>비밀번호</b></label>    
	    <input type="password" class="input" id="memberPw" name="memberPw" required>
	    <div id="memberPwConfirmMessage"></div>	
	
	    <label>
	      <input type="checkbox" onclick="showMemberPw()" id="memberPwShow" name="memberPwShow" style="margin-bottom:15px"> 비밀번호 보이기
	    </label>
	
	
	    <div class="clearfix">      
	      <button type="submit" class="signupbtn">Sign Up </button>
	    </div>
	    
		<div class="loginMain">
			<a  href="${CONTEXT_PATH}/member/memberController?action=memberFindForm"><b>아이디 / 비밀번호 찾기</b></a>
			<a  href="${CONTEXT_PATH}/member/memberController?action=loginChoice"><b>회원가입</b></a>
		</div>
	  </div>
	</form>
</div>
</div>
</div>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>
</html>