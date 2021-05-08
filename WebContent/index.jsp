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
    
    <!-- javascript -->
    <script type="text/javascript">
    	window.onload = function() {
    		location.href="${CONTEXT_PATH}/product/productController?action=storeHome";
    	}
    </script>
  </head>
<body>
<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- Body Section -->
<div class="row">
	<div class="span9" style="width:960px;">
	<div class="well np">
		<div id="myCarousel" class="carousel slide homCar">
            <div class="carousel-inner">
			  <div class="item">
                <img style="width:100%" src="${CONTEXT_PATH}/img/interior/interior1.png">
              </div>
			  <div class="item">
                <img style="width:100%" src="${CONTEXT_PATH}/img/interior/interior2.jpg">
              </div>
			  <div class="item active">
                <img style="width:100%" src="${CONTEXT_PATH}/img/interior/interior3.jpg">
              </div>
              <div class="item">
                <img style="width:100%" src="${CONTEXT_PATH}/img/interior/interior4.png">
              </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
          </div>
        </div>
<!--New Products -->
	<div class="well well-small">
	<h3> 카테고리 </h3>
	<hr class="soften"/>
		<div class="row-fluid">
		<div id="newProductCar" class="carousel slide">
            <div class="carousel-inner">
			<div class="item active">
			  <ul class="thumbnails">
			  	<c:forEach var="categoryList" items="${categoryList3}" begin="0" end="3" step="1">
				<li class="span3">
				<div class="thumbnail">
					<a class="zoomTool" href="${CONTEXT_PATH}/product/productController?action=productListByCategory&categoryId=${categoryList.categoryId}" title="add to cart"><span class="icon-search"></span>${categoryList.categoryName}</a>
					<a href="${CONTEXT_PATH}/product/productController?action=productListByCategory&categoryId=${categoryList.categoryId}"><img src="${CONTEXT_PATH}/${categoryList.categoryImg}" style="height:200px;"></a>
						<div class="carousel-caption">
                      		<h5>${categoryList.categoryName}</h5>
                		</div>
				</div>
				</li>
				</c:forEach>
			  </ul>
			  </div>
		   <div class="item">
		  <ul class="thumbnails">
		  	<c:forEach var="categoryList" items="${categoryList3}" begin="4" end="7" step="1">
			<li class="span3">
			  <div class="thumbnail">
				<a class="zoomTool" href="${CONTEXT_PATH}/product/productController?action=productListByCategory&categoryId=${categoryList.categoryId}" title="add to cart"><span class="icon-search"></span>${categoryList.categoryName}</a>
				<a  href="${CONTEXT_PATH}/product/productController?action=productListByCategory&categoryId=${categoryList.categoryId}"><img src="${CONTEXT_PATH}/${categoryList.categoryImg}" style="height:200px;"></a>
					<div class="carousel-caption">
                      <h5>${categoryList.categoryName}</h5>
                	</div>
			  </div>
			</li>
			</c:forEach>
		  </ul>
		  </div>
		   </div>
		  <a class="left carousel-control" href="#newProductCar" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#newProductCar" data-slide="next">&rsaquo;</a>
		  </div>
		</div>
	</div>
	<!-- 베스트 -->
		<div class="well well-small">
		  <h3><a class="btn btn-mini pull-right" href="${CONTEXT_PATH}/product/productController?action=productListByBestForm" title="View more">더보기<span class="icon-plus"></span></a> 베스트 상품  </h3>
		  <hr class="soften"/>
		  <div class="row-fluid">
		  <ul class="thumbnails">
		  <c:forEach var="bestProduct" items="${productList4}"  begin="0" end="3" step="1">
			<li class="span4" style="width:220px;">
			  <div class="thumbnail">
				<a class="zoomTool" href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${bestProduct.pNo}" title="add to cart"><span class="icon-search"></span> <fmt:formatNumber value="${bestProduct.pPrice }" pattern="###,###"/> 원</a>
				<a  href="${CONTEXT_PATH}/product/productController?action=productDetail&pNo=${bestProduct.pNo}"><img src="${CONTEXT_PATH}/${bestProduct.pImg}"></a>
				<div class="caption cntr">
					<p><strong>${bestProduct.pName}</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> 장바구니 담기 </a></h4>
					<div class="actionList">
						<p><img src="${CONTEXT_PATH}/img/star.png">${bestProduct.pScore} </p> 
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