<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- header menu -->
<jsp:include page="/inc/header.jsp" />
<style>
/* Full-width input fields */
input[type=text], input[type=password] {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
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
.container {
  padding: 16px;
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  width:680px;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

</style>
<script type="text/javascript">
	function goBack() {
		window.history.back();
	}
</script>
</head>
<body>
  <form action="${CONTEXT_PATH}/interior/interiorController?action=insertInterior" method="post">
    <div class="container">
      <h1>인테리어(업체) 등록</h1>
            
      <hr>
      <label><b>No</b></label>      
      <input type="text" placeholder="Enter No" name="ino" required>
      
      <label><b>회사명</b></label>
      
      <input type="text" placeholder="Enter Name" name="iname" required>

      <label><b>경력</b></label>
      <input type="text" placeholder="Enter Career" name="icareer" required>

      <label><b>회사 설명</b></label>
      <input type="text" placeholder="Enter Detail" name="idetail" required>
      
      <label><b>위치</b></label>
      <input type="text" placeholder="Enter Location" name="ilocation" required>

      <div class="clearfix">
        <input type="reset" class="cancelbtn" value="초기화"></input>
        <input type="submit" class="signupbtn" value="등록하기"></input>
        <input type="submit" class="signupbtn" value="이전으로"  onclick="goBack()"></input>
      </div>
    </div>
  </form>
  <!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>