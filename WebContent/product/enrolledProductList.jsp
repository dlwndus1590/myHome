<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>상품 등록내역</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">

	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">
  </head>
<body>

<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- Body Section -->
<div class="row">

<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">내 상품 관리 [회사명 : ${companyName}]</li>
   		<input type="button" class="shopBtn" value="상품 등록" style="float:right;" onclick="location.href='${CONTEXT_PATH}/product/productRegister.jsp'">
    </ul>
<div class="well well-small">
	<c:forEach var="productList" items="${productList}">
	<div class="row-fluid">	  
		<div class="span2">
			<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><img src="${CONTEXT_PATH}/${productList.pImg}"></a>
		</div>
		<div class="span6">
			<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><h5>${productList.pName}</h5></a>
			<p><img src="${CONTEXT_PATH}/img/star.png"> ${productList.pScore} </p>
			<p style="font-weight:bold;"> 판매량 : <fmt:formatNumber value="${productList.pSales }" pattern="###,###" /></p>
			<p style="font-weight:bold;"> 재고수량 : <fmt:formatNumber value="${productList.pCount }" pattern="###,###" /></p>
		</div>
		<div class="span4 alignR">
		<form class="form-horizontal qtyFrm">
		<h3> <fmt:formatNumber value="${productList.pPrice}" pattern="###,###" /> 원</h3>
		<div class="btn-group">
		  <a href="" class="defaultBtn">수정</a>
		  <a href="" class="shopBtn">삭제</a>
		</div>
		</form>
		</div>
	</div>
	<hr class="soften">
	</c:forEach>
</div>
</div>
</div>

<!-- footer -->
<jsp:include page="/inc/footer.jsp"/>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>

</html>
