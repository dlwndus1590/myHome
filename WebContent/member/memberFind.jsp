<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/find.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

</head>
<body>

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<div class="container">
  <form action="${CONTEXT_PATH}/member/memberController?action=memberFindId">
    <div class="row">
      <h2 style="text-align:center">아이디 찾기</h2>
      <div class="vl">
        <span class="vl-innertext">or</span>
      </div>
      
	<div class="col">
      <input type="text" name="memberId" id="memberId" placeholder="User Id" required>
        <input type="text" name="mobile" placeholder="User Mobile" required>
        <input type="submit" value="아이디 찾기">
	</div>
	
</form>
		
      <div class="col">
        <div class="hide-md-lg">
          <p>Or</p>
        </div>
     </div>
     
<form action="${CONTEXT_PATH}/member/memberController?action=memberFindPw">

	      <h2 style="text-align:center">비밀번호 찾기</h2>
	      <div class="vl">
	        <span class="vl-innertext">or</span>
	      </div>	
	      
	     <div class="col">
	    	<input type="text" name="memberId" id="memberId" placeholder="User Id" required>
	        <input type="text" name="name" id="name" placeholder="User Name" required>
        	<input type="text" id="mobile" name="mobile" placeholder="User Mobile" required>
	        <input type="submit" value="비밀번호 찾기">
	     </div>
            
    </div>
 </form>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>