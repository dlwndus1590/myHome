<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
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
		/** 아이디/비밀번호 찾기 디자인 */
		body {
		  font-family: Arial, Helvetica, sans-serif;
		}
		
		* {
		  box-sizing: border-box;
		}
		
		.container2 {
		  position: relative;
		  border-radius: 5px;		  
		  padding: 20px 0 30px 0;
		} 
		
		.input,
		.btn {
		  width: 100%;
		  padding: 12px;
		  border: none;
		  border-radius: 4px;
		  margin: 5px 0;
		  opacity: 0.85;
		  display: inline-block;
		  font-size: 17px;
		  line-height: 20px;
		  text-decoration: none; 
		}
		
		.input:hover,
		.btn:hover {
		  opacity: 1;
		}
		
		/* style the submit button */
		.input[type=submit] {
		  background-color: #04AA6D;
		  color: white;
		  cursor: pointer;
		}
		
		.input[type=submit]:hover {
		  background-color: #45a049;
		}
		
		#name,#mobile,#memberId {
		 	width:100%;
		 	height:50px;
		}
		
		.col {
		  float: left;
		  width: 50%;
		  margin: auto;
		  padding: 0 50px;
		  margin-top: 6px;
		}
		
		.row:after {
		  content: "";
		  display: table;
		  clear: both;
		}
		
		.vl {
		  position: absolute;
		  left: 50%;
		  transform: translate(-50%);
		  border: 2px solid #ddd;
		  height: 175px;
		}
		
		.vl-innertext {
		  position: absolute;
		  top: 50%;
		  transform: translate(-50%, -50%);
		  background-color: #f1f1f1;
		  border: 1px solid #ccc;
		  border-radius: 50%;
		  padding: 8px 10px;
		}
		
		.hide-md-lg {
		  display: none;
		}
		
		.bottom-container {
		  text-align: center;
		  background-color: #666;
		  border-radius: 0px 0px 4px 4px;
		}
		
		
		 .col {
		    width: 100%;
		    margin-top: 0;
		}
		
		 .vl {
		    display: none;
		}
		
		 .hide-md-lg {
		    display: block;
		    text-align: center;
		 }
		#memberPwConfirmMessage, #memberPwShow{
			position: relative;
			float: left;
		}

	</style>
  </head>
<body>

<!-- contents menu -->
<div class="row">
<div class="span9" style="width:960px;">
<div class="well well-small" style="height:600px;">
	<div class="container2">
	  	<form action="${CONTEXT_PATH}/member/memberController?action=memberFindId" method="post">
		    <div class="row">
		      <h2 style="text-align:center">아이디 찾기</h2>
		      <div class="vl">
		        <span class="vl-innertext">or</span>
		      </div>
		     </div>
		      
			<div class="col">
		      <input type="text" class="input" name="name" id="name" placeholder="User Name" required>
		        <input type="text" class="input" name="mobile" id="mobile" placeholder="User Mobile" required>
		        <input type="submit" class="shopBtn" value="아이디 찾기">
			</div>
		
		</form>
	

	     
		<form action="${CONTEXT_PATH}/member/memberController?action=memberFindPw" method="post">
	
		      <h2 style="text-align:center">비밀번호 찾기</h2>
		      <div class="vl">
		        <span class="vl-innertext">or</span>
		      </div>	
		      
		     <div class="col">
		    	<input type="text" class="input" name="memberId" id="memberId" placeholder="User Id" required>
		        <input type="text" class="input" name="name" id="name" placeholder="User Name" required>
	        	<input type="text" class="input" id="mobile" name="mobile" placeholder="User Mobile" required>
		        <input type="submit" class="shopBtn" value="비밀번호 찾기">
		     </div>
	            
	 	</form>
	 </div>
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