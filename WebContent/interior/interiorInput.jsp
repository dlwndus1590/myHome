<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="마이 페이지">
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
/* Full-width input fields */
.input {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;  
}

/* Add a background color when the inputs get focus */
.input:focus, .input:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
.cancelbtn, .signupbtn{
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 228px;
}

/* Add padding to container elements */
.container2 {
  padding: 16px;
}

/* Style the horizontal ruler */
.hr {
  border: 1px solid white;
  width:680px;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

#ino,#iname,#icareer,#idetail,#ilocation,#imobile{
	height: 40px;
}

</style>
<script type="text/javascript">
	function goBack() {
		window.history.back();
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

  <form action="${CONTEXT_PATH}/interior/interiorController?action=insertInterior" method="post">
<div class="row">
<div class="span9" style="width:960px;">
<div class="well well-small" style="height:670px;">
    <div class="container2">
      <h1>인테리어(업체) 등록</h1>
            
      <hr class="hr">
      <label><b>No</b></label>      
      <input type="text" placeholder="Enter No" class="input" id="ino" name="ino" required>
      
      <label><b>회사명</b></label>
      
      <input type="text" placeholder="Enter Name" class="input" id="iname" name="iname" required>

      <label><b>경력</b></label>
      <input type="text" placeholder="Enter Career" class="input" id="icareer" name="icareer" required>

      <label><b>회사 설명</b></label>
      <input type="text" placeholder="Enter Detail" class="input" id="idetail"  name="idetail" required>
      
      <label><b>위치</b></label>
      <input type="text" placeholder="Enter Location" class="input" id="ilocation" name="ilocation" required>
      
      <label><b>연락처</b></label>
      <input type="text" placeholder="' - ' 를 포함해서 작성해주세요." class="input" id="imobile" name="imobile" required maxlength="13">

      <div class="clearfix">
        <input type="reset" class="shopBtn" value="초기화"></input>
        <input type="submit" class="shopBtn" value="등록하기"></input>
        <input type="submit" class="defaultBtn" value="이전으로"  onclick="goBack()"></input>
      </div>
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
  <!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>