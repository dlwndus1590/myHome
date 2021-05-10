<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="내 게시글 보기">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/qNotice.css" rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<style>
#userIcon {
	width: 30px;
	height: 30px;
	margin-right: 10px;
	float: left;
}
</style>
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
   		<li class="active">내 게시글 목록</li>
    </ul>
    
	<div class="row">
		<div class="span9" style="width: 960px;">
			<div class="well well-small">
				<h2 align="center" style="font-size: 25px;">내 질문 게시글</h2>
				<br>
				<div align="right">
					<a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeInputForm">
					<input type="button" value="질문하기" id="qButton" style="margin : 20px 55px 20px 0px;">
					</a>
				</div>
				<c:choose>
					<c:when test="${not empty qnoticeList}">
						<c:forEach var="index" items="${qnoticeList}">
							<article class="qItem" style="padding-left: 15px;">
								<table class="qTable">
									<tr>
										<td style="width: 680px">
											<h3>
												<a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeDetail&qNo=${index.qNo}&writeMemberId=${index.memberId}">
													<span>${index.qTitle}</span>
												</a>
											</h3>
										</td>
										<td></td>
									</tr>

									<tr>
										<td style="width: 680px"><span class="qContent">${index.qContent}</span></td>
										<td><img src="${CONTEXT_PATH}${index.qImg}" class="picture"></td>
									</tr>

									<tr>
										<td style="width: 680px"><img src="${CONTEXT_PATH}/img/qNotice/userIcon.png" id="userIcon" />
											<span>${index.memberId}</span> | <span>조회수
												${index.qHits}</span> | <span style="padding-left: 5px;">${index.qRegDate}</span></td>
									</tr>
								</table>
							</article>
							<br>
							<hr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<table style="width: 940px;">
							<tr>
								<td rowspan="5" colspan="5" align="center" style="padding: 100px;">등록된 게시글이 없습니다.</td>
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