<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="주문내역">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

	<!-- header -->
	<jsp:include page="/inc/header.jsp" />


	<!-- New Products -->
	<ul class="breadcrumb" style="width: 960px;">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">주문내역</li>
    </ul>
		<div class="well well-large" style="width: 960px">
	<h3>구매 이력</h3>
	<c:choose>
		<c:when test="${not empty requestScope.orderList}">
				<br>
				<div class="row-fluid">
					<ul class="thumbnails">
						<c:forEach var="index" items="${orderList}">
							<c:if test="${index.memberId == sessionScope.memberId}">
								<li class="span4" style="width: 220px; margin-left: 50px;">
									<div class="thumbnail">
										<a class="zoomTool"
											href="${CONTEXT_PATH}/orderList/orderListController?action=orderDetail&oNo=${index.oNo}&oDate=${index.oDate}"><span
											class="icon-search"></span> 구매 이력 상세</a> 
											<a href="${CONTEXT_PATH}/orderList/orderListController?action=orderDetail&oNo=${index.oNo}&oDate=${index.oDate}">
											<img src="${CONTEXT_PATH}/${index.pImg}"></a>
										<div class="caption cntr" style="height:130px;">
											<p>
												<strong>${index.pName}</strong>
											</p>
											<div class="actionList">
												<p style="margin-top: 5px;">
													<img src="${CONTEXT_PATH}/img/star.png">${index.pScore}
												</p>
												<p style="margin-top: 15px;">구매일 : ${index.oDate}</p>
											</div>
											<br class="clr">
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
			<br>
		</c:when>
		
		<c:otherwise>
			<table style="width: 940px;">
				<tr>
					<td rowspan="5" colspan="5" align="center" style="padding: 100px;">구매하신 이력이 없습니다.</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>
</html>