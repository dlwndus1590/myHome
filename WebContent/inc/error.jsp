<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="에러페이지">
    <meta name="author" content="이주연">
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">
</head>
<body>
<div style="background-color:white; width:500px; hieght:800px; margin:0 auto; padding:100px; margin-top:100px;">
   <a class="logo" href="${CONTEXT_PATH}/index.jsp">
      <img src="${CONTEXT_PATH}/assets/img/myhome.png" style="width:160px; height:50px;">
   </a>
   
	<h4> 죄송합니다. <br> 요청하신 페이지를 찾을 수 없습니다.</h4>
	<br>
	<p>방문하시려는 페이지의 주소가 잘못 입력되었거나,
	페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</p>
	<p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
	<p>감사합니다.</p>
	
	<a href="${CONTEXT_PATH}/product/productController?action=storeHome" class="shopBtn" style="float:right; text-decoration: none;">홈페이지 이동</a>
</div>
</body>
</html>