<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="상품 등록내역">
    <meta name="author" content="이주연">
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
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- Body Section -->
<div class="row">

<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">내 상품 관리 [회사명 : ${companyName}]</li>
    </ul>
<div class="well well-small">
	<c:forEach var="productList" items="${productList5}">
	<div class="row-fluid">	  
		<div class="span2">
			<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><img src="${CONTEXT_PATH}/${productList.pImg}"></a>
		</div>
		<div class="span6">
			<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><h5>${productList.pName}</h5></a>
			<p><img src="${CONTEXT_PATH}/img/star.png"> ${productList.pScore} </p>
			<p style="font-weight:bold;"> 판매량 : <fmt:formatNumber value="${productList.pSales }" pattern="###,###" /></p>
			<p style="font-weight:bold;"> 재고수량 : <fmt:formatNumber value="${productList.pCount }" pattern="###,###" /></p>
			<p>상품 등록일 : ${productList.pRegDate}</p>
		</div>
		<div class="span4 alignR">
		<form class="form-horizontal qtyFrm">
		<h3> <fmt:formatNumber value="${productList.pPrice}" pattern="###,###" /> 원</h3>
		<div class="btn-group">
		  <a href="${CONTEXT_PATH}/product/productController?action=updateProductForm&pNo=${productList.pNo}" class="defaultBtn">수정</a>
		  <a href="${CONTEXT_PATH}/product/productController?action=deleteProduct&pNo=${productList.pNo}"
		  		 class="shopBtn" onclick="return confirm('한번 삭제한 상품은 다시 되돌릴 수 없습니다. 해당 상품을 정말 삭제하시겠습니까? ')">삭제</a>
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
