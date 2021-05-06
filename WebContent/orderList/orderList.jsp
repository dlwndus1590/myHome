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
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />


	<!-- New Products -->
	<div class="well well-large" style="width: 910px">
		<h3>구매 이력</h3>
		<br>
		<div class="row-fluid">
			<ul class="thumbnails">
				<c:forEach var="index" items="${orderList}">
					<c:if test="${index.memberId == sessionScope.memberId}">
						<li class="span4" style="width: 220px; margin-left: 50px;">
							<div class="thumbnail">
								<a class="zoomTool"
									href="${CONTEXT_PATH}/orderList/orderListController?action=orderDetail&oNo=${index.oNo}&oDate=${index.oDate}"><span
									class="icon-search"></span> 구매 이력 상세 조회</a> <a
									href="${CONTEXT_PATH}/orderList/orderListController?action=orderDetail&oNo=${index.oNo}&oDate=${index.oDate}"><img
									src="${CONTEXT_PATH}/${index.pImg}"></a>
								<div class="caption cntr">
									<p>
										<strong>${index.pName}</strong>
									</p>
									<h4>
										<a class="shopBtn" href="#" title="review"> 후기 작성 </a>
									</h4>
									<div class="actionList">
										<p>
											<img src="${CONTEXT_PATH}/img/star.png">${index.pScore}
										</p>
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