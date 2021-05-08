<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myHome</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="질문 게시글 상세 보기">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/qNoticeDetail.css"
	rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript"
	src="${CONTEXT_PATH}/js/notice/edditComment.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<div class="row">
	<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm">질문과 답변</a> <span class="divider">></span></li>
   		<li class="active">질문 게시글 상세 조회</li>
    </ul>

	<div class="well well-small">
	<br>	
	<table>
		<tr>
			<td class="info"><p id="qTitle">${qdto.qTitle}</p></td>
		</tr>

		<tr>
			<td>
			<img src="${CONTEXT_PATH}/img/qNotice/userIcon.png" id="userIcon"/><p></p>
			<span id="qmemberIdRegDate">${qdto.memberId} | ${qdto.qRegDate}</span>
			<span id="qHits" style="float: right">조회수: ${qdto.qHits}</span>
				<c:if test="${qdto.memberId == sessionScope.memberId or sessionScope.memberId == 'admin'}">
				<div align="right">
					<a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeUpdateForm&qNo=${qdto.qNo}" class="shopBtn">수정</a>
					| <a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeDelete&qNo=${qdto.qNo}"
					 class="shopBtn" onclick="return confirm('한번 삭제한 게시글은 다시 되돌릴 수 없습니다. 해당 게시글을 정말 삭제하시겠습니까?')">삭제</a>
				</div>
				</c:if>
			</td>
		</tr>

		<tr>
			<td class="info"><hr> <img src="${CONTEXT_PATH}${qdto.qImg}"></td>
		</tr>

		<tr>
			<td class="info"><br>${qdto.qContent}</td>
		</tr>
	</table>
	<hr>
	<h3>댓글</h3>
	<c:if test="${not empty memberId}">
		<form action="${CONTEXT_PATH}/notice/noticeController?action=addComment&qNo=${qdto.qNo}" method="post">
			<input type="text" placeholder="댓글을 남겨 보세요" name="comment"
				style="width: 850px; height: 40px;"> <input type="submit"
				value="등록" class="shopBtn" style="	width: 50px;height: 40px;margin-bottom: 10px;">
		</form>
	</c:if>
	
	<c:forEach var="index" items="${list}">
		<c:if test="${memberId == index.memberId or memberId == 'admin'}">
		<span style="float: right; margin-right: 25px;" id="edditButton${index.aNo}"> 
			<a onclick="answerEdit(${index.aNo}, ${index.qNo},'${index.aContent}');">수정</a>
			| <a href="${CONTEXT_PATH}/notice/noticeController?action=deleteComment&aNo=${index.aNo}&qNo=${index.qNo}">삭제</a>
			</span>
		</c:if>
		<img src="${CONTEXT_PATH}/img/qNotice/userIcon.png" id="userIcon"/>
		<span>${index.memberId}</span>
		<br>
		<div id="content${index.aNo}">${index.aContent}</div>
		<div >${index.aRegDate}</div>
		<br>
	</c:forEach>
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