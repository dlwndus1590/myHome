<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>

<style type="text/css">
	body {font-family: Arial, Helvetica, sans-serif;}
	* {box-sizing: border-box;}
	
	img {
	    border: 1px solid black;   
		width: 100%;
		height:100%;
		opacity: 0.9;
	}
	
	.container {
	    position: relative;    
	}
	
	.topnav {
	   position: absolute;
	   top: 10px;
	   left: 10px;
	   padding: 5px;
	   background-color: #E8A798;   
	}
	
	/* Navbar links */
	.topnav a {
	  float: left;
	  color: black;
	  font-weight:bold;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	  font-size: 17px;
	}
	
	.topnav a:hover {
	  background-color: #ddd;
	  color: black;
	}
</style>

</head>
<body>
<!-- contents menu -->

<h2>관리자 전용 페이지</h2>
<hr>

  <div class="container">
	<img  alt="배경화면" src="${CONTEXT_PATH}/img/adminbg.jpg">
    <div class="topnav">
      <a href="${CONTEXT_PATH}/index.jsp">Home</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=memberList">전체 회원조회</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=productList">상품 전체목록</a>
      <a href="${CONTEXT_PATH}/notice/noticeController?action=noticeListForm">공지사항</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=?">질문과 답변</a>      
    </div>
  </div>


</body>
</html>