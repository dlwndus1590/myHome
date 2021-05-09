<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myHome</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="질문 게시글 작성">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/noticeInputForm.css"
	rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.ico">
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
	<div class="row">
	<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm">질문과 답변</a> <span class="divider">></span></li>
   		<li class="active">질문 게시글 작성</li>
    </ul>

	<div class="well well-small">
	<h3 align="center">질문 게시글 작성</h3>
	<form action="${CONTEXT_PATH}/notice/noticeController?action=qNoticeInput" method="post" enctype="multipart/form-data">
		<table border="1px" align="center" width="400px" cellpadding="5px">
			<tr>
				<td align="center" height="20px"><input type="text"
					name="qTitle" id="qTitle" placeholder="게시글 제목을 입력하세요." size="95px"
					required="required" style="height: 30px" class="info"></td>
			</tr>
		
			<tr>
				<td align="left" height="20px"><input type="file" name="imgUrl" id ="imgUrl"></td>
			</tr>
			
			<tr>
				<td><textarea name="qContent" id ="qContent" cols="100" rows="30"
						placeholder="내용을 입력해주세요." required="required" class="info"></textarea></td>
			</tr>
			<tr align="right">
				<td id="buttonLine"><input type="submit" value="등록"
					size="100px" id="button" class="shopBtn"></td>
			</tr>
		</table>
	</form>
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