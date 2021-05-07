<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My Home category</title>
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
<div id="sidebar" class="span3" style="width:200px">
<div class="well well-small">
	<ul class="nav nav-list">
		<li><a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">
			<span class="icon-chevron-right"></span>전체</a></li>
		<c:forEach var="categoryList" items="${categoryList1}">
		<li><a href="${CONTEXT_PATH}/product/productController?action=productListByCategory&categoryId=${categoryList.categoryId}">
			<span class="icon-chevron-right"></span>${categoryList.categoryName}</a></li>
		</c:forEach>
		<li style="border:0"> &nbsp;</li>
	</ul>
</div>
</div>

<div class="span9">

<!-- New Products -->
<div class="well well-small" style="width:730px">
<h3> 전체 </h3>
	<div class="row-fluid">	
		<ul class="thumbnails">
		  <c:forEach var="productList" items="${productList1}">
			<li class="span4" style="width:220px; ">
				<div class="thumbnail">
					<a class="zoomTool" href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><span class="icon-search"></span> <fmt:formatNumber value="${productList.pPrice }" pattern="###,###"/> 원</a>
					<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><img src="${CONTEXT_PATH}/${productList.pImg}"></a>
						<div class="caption cntr">
							<p><strong>${productList.pName}</strong></p>
							<h4><a class="shopBtn" href="${CONTEXT_PATH}/member/ordersController?action=cartInsert&pNo=${productList.pNo}" title="add to cart"> 장바구니 담기 </a></h4>
								<div class="actionList">
									<p><img src="${CONTEXT_PATH}/img/star.png">${productList.pScore}  </p> 
								</div> 
								<br class="clr">
						</div>
			  	</div>
			  </li>
			</c:forEach>			
		</ul>
	</div>
</div>
</div>
<br>	
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