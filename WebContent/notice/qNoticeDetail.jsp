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
<script type="text/javascript">
/* 댓글 수정창 */
function cmUpdateOpen(aNo){
	window.name = "parentForm";
	window.open("/notice/noticeController?action=commentUpdate&aNo=" + aNo,
				  "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
}
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm">질문과
		답변</a>
	<br>

	<table>
		<tr>
			<td id="qTitle">${dto.qTitle}</td>
		</tr>

		<tr>
			<td>${dto.memberId}| ${dto.qRegDate} <span id="qHits">조회수:
					${dto.qHits}</span></td>
		</tr>

		<tr>
			<td><img src="${CONTEXT_PATH}${dto.qImg}"></td>
		</tr>

		<tr>
			<td>${dto.qContent}</td>
		</tr>
	</table>
	<hr>
	<form action="${CONTEXT_PATH}/notice/noticeController?action=addComment"
		method="post">
		<p>댓글</p>
		<input type="text" placeholder="댓글을 남겨 보세요" name="comment"
			style="width: 850px; height: 40px;"> <input type="submit"
			value="등록" id="commentButton">
	</form>

	<c:forEach var="index" items="${list}">
		<span style="float: right; margin-right: 25px;"><a href="#" onclick="cmUpdateOpen(${index.aNo})">수정</a>
			| <a href="#">삭제</a></span>
		<div>${index.memberId}</div>
		<div>${index.aContent}</div>
		<span>${index.aRegDate}</span>
	</c:forEach>

	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>