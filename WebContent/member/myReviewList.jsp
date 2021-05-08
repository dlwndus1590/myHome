<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="내 후기 보기">
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
			alert('로그인을 먼저 시도해주세요!');
			location.href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm"
		</script>
	</c:if>
	
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<ul class="breadcrumb" style="width: 960px;">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">내 후기 목록</li>
    </ul>
    
	<div class="row">
	<div class="span9" style="width:960px;">
	<div class="well well-small">
	<h2 align="center" style="font-size: 25px;">후기 작성 목록</h2>
	<br>
	<c:choose>
		<c:when test="${not empty reviewList}">
			<c:forEach var="index" items="${reviewList}">
				<div class="row-fluid">
					<div class="span2">
						<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${index.pNo}">
							<img src="${CONTEXT_PATH}/${index.pImg}" title="상품 상세 보기">
						</a>
					</div>
					<div class="span6">
					<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${index.pNo}">
						<p style="font-size: 15px; font-weight: bold; padding-left: 10px;">
							<br>${index.reviewContent}</p></a>
					</div>
					<div class="span4 alignR" style="padding-right: 10px;">
						<p style="font-weight: bolder;">작성자 : ${index.memberId}</p>
						<p style="font-weight: bolder;">구매일자 : ${index.oDate}</p>
						<p style="font-weight: bolder;">평가점수 : ${index.score}점</p>
					</div>
				</div>
				<br>
				<hr class="soften" />
				<br>
			</c:forEach>
		</c:when>

		<c:otherwise>
			<table style="width: 940px;">
				<tr>
					<td rowspan="5" colspan="5" align="center" style="padding: 100px;">등록한 후기가 없습니다.</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
	</div>
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