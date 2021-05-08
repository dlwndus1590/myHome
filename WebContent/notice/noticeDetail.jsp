<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="공지사항 게시글 상세 조회">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/noticeDetail.css"
	rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<div class="row">
	<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/notice/noticeController?action=noticeListForm">공지사항</a> <span class="divider">></span></li>
   		<li class="active">공지사항 게시글 상세 조회</li>
    </ul>

	<div class="well well-small">
	<c:if test="${not empty dto}">
		<h3 align="center"">공지사항 게시글 상세 조회</h3>
		<form action="${CONTEXT_PATH}/notice/noticeController?action=noticeEditOrDelete&nNo=${dto.nNo}" method="post">
			<table id="commonTable">
				<tr>
					<td align="center" height="20px" class="info"><textarea
							id="infoTitle" cols="2" rows="3" readonly="readonly">${dto.nTitle}</textarea></td>
				</tr>

				<tr>
					<td align="left" height="20px" id="infoMemberIdGrade" class="info"><span id="memberId">아이디
						: ${dto.memberId} | 관리자</span></td>
				</tr>

				<tr>
					<td align="left" height="20px" id="infoMemberDateViews"
						class="info"><span id="nRegDate">${dto.nRegDate}</span><span id="nHits">조회수: ${dto.nHits}</span></td>
				</tr>
				<tr>
					<td align="center" height="20px"><textarea id="infoContent"
							class="info" cols="100" rows="30" readonly="readonly"
							class="info">${dto.nContent}</textarea></td>
				</tr>
				
				<c:if test="${sessionScope.memberId == 'admin'}">
				<tr align="right">
					<td><input type="submit" value="삭제" name ="submit" size="100px"  class="shopBtn">
						<input type="submit" value="수정" name ="submit" size="100px" class="shopBtn">
					</td>
				</tr>
				</c:if>
			</table>
		</form>
	</c:if>
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