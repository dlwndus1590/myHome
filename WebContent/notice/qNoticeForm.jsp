<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myHome</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="질문 게시판">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/qNotice.css" rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<style>
#userIcon {
	padding-bottom : 20px;
	width: 30px;
	height: 30px;
	margin-right: 10px;
	float: left;
}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<section id="qHeader">
		<form
			action="${CONTEXT_PATH}/notice/noticeController?action=qNoticeSearch"
			method="post">
			<table id="qtable">
				<tr>
					<td><br></td>
				</tr>

				<tr>
					<th align="center" id="qTextHader"><h2>질문과 답변</h2></th>
				</tr>

				<tr>
					<td align="center"><p id="qTextCaption">마이 홈 인테리어 고수들과
							전문가들에게 조언을 받으세요.</p></td>
				</tr>

				<tr>
					<td align="center"><input type="text"
						placeholder="궁금한 것을 검색해보세요." id="searchInfo" name="searchInfo"></td>
				</tr>
			</table>
		</form>
	</section>
	<br>

	<div align="right">
		<a
			href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeInputForm">
			<input type="button" value="질문하기" id="qButton">
		</a>
	</div>
	<ul style="list-style: none; padding-left: 0px;">
		<li class="dropdown"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#">정렬 <b class="caret"></b></a>
			<div class="dropdown-menu">
				<a
					href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeNewList">최신순</a>
				<a
					href="${CONTEXT_PATH}/notice/noticeController?action=qNoticePopularityList">인기순</a>
			</div></li>
	</ul>
	<div>
		<c:choose>
			<c:when test="${empty listType or listType  == '최신순'}">
				<h3 style="padding-left: 20px;">최신순</h3>
			</c:when>
			<c:otherwise>
				<h3 style="padding-left: 20px;">인기순</h3>
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach var="index" items="${list}">
				<article class="qItem">
					<table class="qTable">
						<tr>
							<td style="width: 820px">
								<h3>
									<a
										href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeDetail&qNo=${index.qNo}&writeMemberId=${index.memberId}">${index.qTitle}</a>
								</h3>
							</td>
							<td></td>
						</tr>

						<tr>
							<td style="width: 820px"><span class="qContent">${index.qContent}</span></td>
							<td><img src="${CONTEXT_PATH}${index.qImg}" class="picture">
							</td>
						</tr>

						<tr>
							<td style="width: 820px"><img
								src="${CONTEXT_PATH}/img/qNotice/userIcon.png" id="userIcon" />
								<span>${index.memberId}</span> | <span>조회수 ${index.qHits}</span> |
								<span style="padding-left: 5px;">${index.qRegDate}</span></td>
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
					<td rowspan="5" colspan="5" align="center" style="padding: 100px;">등록된
						게시글이 없습니다.</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>