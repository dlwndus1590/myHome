<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div id="sidebar" class="span3">
<div class="well well-small">
	<ul class="nav nav-list">
		<li><a href="#"><span class="icon-chevron-right"></span>전체</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>가구/가전</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>패브릭</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>홈데코/조명</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>수납/정리</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>생활용품</a></li>
		<li><a href="#"><span class="icon-chevron-right"></span>주방</a></li>
		<li><a href="products.html"><span class="icon-chevron-right"></span>조명</a></li>
		<li style="border:0"> &nbsp;</li>
	</ul>
</div>
</div>

<div class="span9">

<!-- New Products -->
<div class="well well-small">
<h3> 전체 </h3>
	<div class="row-fluid">
		<ul class="thumbnails">
		  
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> 가격 : 13000</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/b.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
			  	</div>
			</li>
			
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/c.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
			 	 </div>
			</li>
			
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/a.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
				  </div>
			</li>
		</ul>
	</div>
	
	<div class="row-fluid">
		<ul class="thumbnails">
		  
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/b.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
			  	</div>
			</li>
			
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/c.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
			 	 </div>
			</li>
			
			<li class="span4">
				<div class="thumbnail">
					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
					<a href="product_details.html"><img src="${CONTEXT_PATH}/assets/img/a.jpg" alt=""></a>
						<div class="caption cntr">
							<p>Manicure & Pedicure</p>
							<p><strong> $22.00</strong></p>
							<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
								<div class="actionList">
									<a class="pull-left" href="#">Add to Wish List </a> 
									<a class="pull-left" href="#"> Add to Compare </a>
								</div> 
								<br class="clr">
						</div>
				  </div>
			</li>
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