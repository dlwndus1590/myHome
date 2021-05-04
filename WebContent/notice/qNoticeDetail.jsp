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
<script type="text/javascript"
	src="${CONTEXT_PATH}/js/notice/commentUpdate.js"></script>
</script>
<script type="text/javascript">
function fn_editReply(aNo, memberId, aContent) {
	alert('확인!');
	var htmls = "";
	htmls += '<div class="media text-muted pt-3" id="aNo' + aNo + '">';
	htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
	htmls += '<title>Placeholder</title>';
	htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
	htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
	htmls += '</svg>';
	htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
	htmls += '<span class="d-block">';
	htmls += '<strong class="text-gray-dark">' + memberId + '</strong>';
	htmls += '<span style="padding-left: 7px; font-size: 9pt">';
	htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + aNo + ', \'' + memberId + '\')" style="padding-right:5px">저장</a>';
	htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
	htmls += '</span>';
	htmls += '</span>';
	htmls += '<textarea name="editaContent" id="editaContent" class="form-control" rows="3">';
	htmls += aContent;
	htmls += '</textarea>';
	htmls += '</p>';
	htmls += '</div>';
	$("#aNo").replaceWith(htmls);
	$("#editaContent").focus();
};
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
			<td id="qTitle" class="info"><h3>${dto.qTitle}</h3></td>
		</tr>

		<tr>
			<td>${dto.memberId}|${dto.qRegDate}<span id="qHits" style="float: right">조회수:
					${dto.qHits}</span><div align="right"><a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeUpdateForm&qNo=${dto.qNo}">수정</a> | <a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeDelete&qNo=${dto.qNo}">삭제</a></div></td>
		</tr>
		
		<tr>
			<td class="info"><hr><img src="${CONTEXT_PATH}${dto.qImg}"></td>
		</tr>

		<tr>
			<td class="info">${dto.qContent}</td>
		</tr>
	</table>
	<hr>
	<form action="${CONTEXT_PATH}/notice/noticeController?action=addComment&qNo=${dto.qNo}" method="post">
		<p>댓글</p>
		<input type="text" placeholder="댓글을 남겨 보세요" name="comment"
			style="width: 850px; height: 40px;"> <input type="submit"
			value="등록" id="commentButton">
	</form>

	<c:forEach var="index" items="${list}">
		<span style="float: right; margin-right: 25px;"><a href="#"
			onclick="fn_editReply(${index.aNo},${index.memberId},${index.aContent})">수정</a> | <a href="${CONTEXT_PATH}/notice/noticeController?action=deleteComment&aNo=${index.aNo}&qNo=${dto.qNo}">삭제</a></span>
		<div id="${index.aNo}">
			<span>${index.memberId}</span> <br> ${index.aContent}
		</div>
		<span>${index.aRegDate}</span>
		<br>
	</c:forEach>

	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>