<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<title>My Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="공지사항 게시판">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/noticeList.css" rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<h3 align="center">전체 게시글 상세 조회</h3>
	<hr>
	
	<div align="center">
		<table class="commonTable">
			<tr>
				<td align="right" colspan="8"><a
					href="${CONTEXT_PATH}/mms04/NoticeController?action=noticeInputForm">
						<input type="button" value="글쓰기" id="write">
				</a></td>
			</tr>
			<tr>
				<th align="center" class="nTitle">아이디</th>
				<th align="center" class="nTitle">제목</th>
				<th align="center" class="nTitle">등록일자</th>
				<th align="center" class="nTitle">조회수</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
					<td class="info">${dto.memberId}</td>
					<td class="info"><a
						href="${CONTEXT_PATH}/notice/noticeController?action=noticeDetail&nNo=${dto.nNo}&writeMemberId=${dto.memberId}"
						id="link">${dto.nTitle}</a></td>
					<td class="info">${dto.nRegDate}</td>
					<td class="info">${dto.nHits}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br />

	<form
		action="${CONTEXT_PATH}/mms04/NoticeController?action=noticeSearchForm"
		method="post">
		<div align="center">
			<table class="commonTable">
				<tr>
					<td align="center" height="20px"><select name="noticeType"
						id="noticeType">
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

	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>