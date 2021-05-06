<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="주문 상세 내역">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/orderDetail.css" rel="stylesheet">
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<h3 id="headerTitle">주문 상세정보</h3>
	<table id = "orderInfoTable">
		<tr>
			<td id="orderInfoTableTd">
			<span class="orderInfoTitle">주문일자</span> 
			<span id="orderDate">2021-05-06</span>
			<span id="division">|</span> 
			<span class="orderInfoTitle">주문번호</span> 
			<span id="orderNum">12345123</span>
			</td>
		</tr>
	</table>
	
	<hr id="hrBold">
	<br>
	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>