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
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />
	<!-- contents menu -->
	<section id="qHeader">
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
					placeholder="궁금한 것을 검색해보세요." id="searchInfo"></td>
			</tr>

			<tr>
				<td align="left">#<a href="" class="keyWord">리모델링</a> #<a
					href="" class="keyWord">20평대</a> #<a href="" class="keyWord">아파트</a>
					#<a href="" class="keyWord">도배</a> <span id="keyWordMore"
					style="float: right">키워드 더보기</span></td>
			</tr>
		</table>
	</section>
	<br>

	<div align="right">
		<input type="button" value="질문하기" id="qButton">
	</div>
	<ul style="list-style: none; padding-left: 0px;">
		<li class="dropdown"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#">정렬 <b class="caret"></b></a>
			<div class="dropdown-menu">
				<a href="#">최신순</a> <a href="#">인기순</a>
			</div></li>
	</ul>
	<div>
		<h3 style="margin-left: 20px;">최신순</h3>
	</div>
	<br>
	<c:forEach var="dto" items="${list}">
		<article class="qItem">
			<table class="qTable">
				<tr>
					<td style="width: 820px">
						<h3>
							<a
								href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeDetail&qNo=${dto.qNo}">${dto.qTitle}</a>
						</h3>
					</td>
					<td></td>
				</tr>

				<tr>
					<td style="width: 820px"><span class="qContent">${dto.qContent}</span></td>
					<td><img src="${CONTEXT_PATH}${dto.qImg}" class="picture">
					</td>
				</tr>

				<tr>
					<td style="width: 820px"><span>${dto.memberId}</span> <span>조회
							수${dto.qHits}</span></td>
				</tr>
			</table>
			<br>
		</article>
		<hr>
	</c:forEach>
	
	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>