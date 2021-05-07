<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My Home</title>
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
    
    <style>
    	.img_content{
    		position:relative;
    		color:#385723;
    		text-align: center;
    		background-color:#FFD966;
    		width:28px;
    		border-radius:10px;
    	}	
  }
    </style>
  </head>
<body>

<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- Body Section -->
<div class="row">
<div class="span9" style="width:960px;">
<!-- New Products -->
<div class="well well-small">
	<div class="well well-small">
		<a href="${CONTEXT_PATH}/product/productController?action=productListByBestForm" style="padding-right:30px; color:#385723; font-weight:bold;">
			<img src="${CONTEXT_PATH}/img/circle.png" style="padding-right:5px;">전체</a>
		<c:forEach var="categoryList" items="${categoryList2}"> 
		<a href="${CONTEXT_PATH}/product/productController?action=productListByBest&categoryId=${categoryList.categoryId}" style="padding-right:30px; color:#385723; font-weight:bold;">
			<img src="${CONTEXT_PATH}/img/circle.png" style="padding-right:5px;">${categoryList.categoryName}</a>
		</c:forEach>
	</div>
	
	<h3> 베스트 상품 </h3>
	<hr class="soften"/>
		<div class="row-fluid">
		  <ul class="thumbnails">
		  <% int number = (int)session.getAttribute("number"); %>
		  <c:forEach var="productList" items="${productList3}" begin="0" end="29" step="1">
			<li class="span4" style="width:22%;">
			  <div class="thumbnail">
				<a class="zoomTool" href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><span class="icon-search"></span> <fmt:formatNumber value="${productList.pPrice }" pattern="###,###"/> 원 </a>
				<div class="img">
					<div class="img_content">
						<h3><%= number++ %></h3>
					</div>
					<a href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${productList.pNo}"><img src="${CONTEXT_PATH}/${productList.pImg}"></a>
				</div>
				<div class="caption cntr">
					<p><strong>${productList.pName}</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> 장바구니 담기 </a></h4>
					<div class="actionList">
						<p><img src="${CONTEXT_PATH}/img/star.png">${productList.pScore} </p> 
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