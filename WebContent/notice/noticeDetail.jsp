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
	<c:if test="${not empty dto}">
		<h3 style="padding-left: 100px;" align="center"">게시글 상세 조회</h3>
		<table id="commonTable">
			<tr>
				<td align="center" height="20px"><textarea id="infoTitle"
						cols="2" rows="3" readonly="readonly">${dto.nTitle}</textarea></td>
			</tr>

			<tr>
				<td align="left" height="20px" id="infoMemberIdGrade">아이디 :
					${dto.memberId} | 관리자</td>
			</tr>

			<tr>
				<td align="left" height="20px" id="infoMemberDateViews">${dto.nRegDate}조회수
					: ${dto.nHits}</td>
			</tr>
			<tr>
				<td align="center" height="20px"><textarea id="infoContent"
						class="info" cols="100" rows="30" readonly="readonly">${dto.nContent}</textarea></td>
			</tr>

			<tr align="right">
				<td><input type="submit" value="삭제" size="100px"
					style="background-color: #03C75A; color: white; border: 1px; font-size: 18px; padding-left: 20px; padding-right: 20px">
					<input type="submit" value="수정" size="100px"
					style="background-color: #03C75A; color: white; border: 1px; font-size: 18px; padding-left: 20px; padding-right: 20px">
				</td>
			</tr>
		</table>
	</c:if>

	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>