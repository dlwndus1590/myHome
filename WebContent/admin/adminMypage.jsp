<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/adminMyPage.css">
</head>
<body>
<!-- contents menu -->

<h2>관리자 전용 페이지</h2>
<hr>
<div class="bg-img">
  <div class="container">
    <div class="topnav">
      <a href="${CONTEXT_PATH}/member/index.jsp">Home</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=memberList">전체 회원조회</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=productList">상품 전체목록</a>
      <a href="${CONTEXT_PATH}/notice/noticeController?action=noticeListForm">공지사항</a>
      <a href="${CONTEXT_PATH}/member/memberController?action=?">질문과 답변</a>      
    </div>
  </div>
<img  alt="배경화면" src="${CONTEXT_PATH}/img/interior.jpg">
</div>

</body>
</html>