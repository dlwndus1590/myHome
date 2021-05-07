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
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />
	
	<!-- contents menu -->
	<div class="well well-small">   
	<h3 align="left" style="padding-left: 50px;">공지사항</h3>
	<hr>

	<div align="center">
		<table class="commonTable">
			<c:if test="${sessionScope.memberId == 'admin'}">
				<tr>
					<td align="right" colspan="8"><a
						href="${CONTEXT_PATH}/notice/noticeController?action=noticeInputForm">
							<input type="button" value="글쓰기" id="write">
					</a></td>
				</tr>
			</c:if>
			<tr>
				<th align="center" class="nTitle" id="nNo"></th>
				<th align="center" class="nTitle">아이디</th>
				<th align="center" class="nTitle">제목</th>
				<th align="center" class="nTitle">등록일자</th>
				<th align="center" class="nTitle">조회수</th>
			</tr>
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach var="index" items="${list}">
						<tr>
							<td class="info">${index.nNo}</td>
							<td class="info">${index.memberId}</td>
							<td class="info"><a
								href="${CONTEXT_PATH}/notice/noticeController?action=noticeDetail&nNo=${index.nNo}&writeMemberId=${index.memberId}"
								id="link">${index.nTitle}</a></td>
							<td class="info">${index.nRegDate}</td>
							<td class="info">${index.nHits}</td>
						</tr>
					</c:forEach>
				</c:when>

				<c:otherwise>
					<tr>
						<td rowspan="5" colspan="5" align="center" id="noInfo">등록된
							게시글이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<br />

	<form
		action="${CONTEXT_PATH}/notice/noticeController?action=noticeSearch"
		method="post">
		<div align="center">
			<table class="commonTable">
				<tr>
					<td align="center" height="20px"><select name="searchType"
						id="searchType">
							<option value="titleContents">제목+내용</option>
							<option value="title">제목만</option>
							<option value="writer">작성자</option>
					</select></td>
					<td><input type="text" placeholder="검색어를 입력해주세요"
						name="searchInfo" id="searchInfo"></td>
					<td><input type="submit" value="검색" id="search"></td>
				</tr>
			</table>
		</div>
	</form>
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