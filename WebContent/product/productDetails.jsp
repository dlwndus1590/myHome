<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Twitter Bootstrap shopping cart</title>
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
    <script type="text/javascript" src="${CONTEXT_PATH}/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
    	function messageChange() {
    		var messageElement = document.getElementById("message");
    		var countElement = document.getElementById("count");
    		var count = countElement.value;
    		count = count.trim();
    		
    		if(count.length == 0) {
    			messageElement.innerHTML = "주문금액 : 0 원";
    		} else {
    			count = parseInt(count);
    			messageElement.innerHTML = "주문금액 : " + (count* ${product.pPrice}) + " 원";
    		}
    	}
    </script>
  </head>
<body>

<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- Body Section -->
<div class="row">
<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">Products</a> <span class="divider">></span></li>
   		<li class="active">${category.categoryName}</li>
    </ul>
    	
	<div class="well well-small" style="width:940px;">
	<div class="row-fluid">
		<div class="span5">
        	<div class="carousel-inner">
            	<div class="item active">
                	<img src="${CONTEXT_PATH}/${product.pImg}" style="width:100%">
                </div>
            </div>
		</div>
		<div class="span7">
			<h2>${product.pName}</h2>
			<p><img src="${CONTEXT_PATH}/img/star.png">${product.pScore}</p><br>
			<h3>가격 : ${product.pPrice }</h3>
			<p> 배송비 : ${product.deliveryFee }</p>
			<p> 회사/점포명 : ${product.companyName}</p>
			<hr class="soft"/>
				
			<form class="form-horizontal qtyFrm">
			  <div class="control-group">
				<label class="control-label"> 수량</label>
				<input type="number" id="count" class="span6" placeholder="1" min="1" style="width:100px" onmouseup="messageChange()" onkeyup="messageChange()">
			  </div>
			  <h5> 재고수량 : ${product.pCount} </h5>
			  <button type="button" class="defaultBtn"><span class=" icon-shopping-cart"></span> 장바구니 </button>
			  <button type="submit" class="shopBtn"><span class=" icon-shopping-cart"></span> 바로구매 </button>
			</form>
			<h3 style="float:right; padding-right:100px;"> </h3>
			<div id="message" style="float:right; font-size:20px; font-weight:bold; color:#548235;"></div>
		</div>
	</div>
	
	<hr class="softn clr"/>


	<ul id="productDetail" class="nav nav-tabs">
		<li class="active"><a href="#detail" data-toggle="tab">상품 정보</a></li>
		<li class=""><a href="#profile" data-toggle="tab">관련상품</a></li>
        <li class=""><a href="#review" data-toggle="tab">상품 후기</a></li>
	</ul>
	<div id="myTabContent" class="tab-content tabWrapper">
		<div class="tab-pane fade active in" id="detail">
		<h4>상품 정보</h4>
			<p style="padding-left:60px;"><img src="${CONTEXT_PATH}/${product.pDescribe}"></p>
		</div>
		
		<div class="tab-pane fade" id="profile">
		<c:forEach var="productList" items="${productList1}"> 
			<div class="row-fluid">	  
				<div class="span2">
					<img src="${CONTEXT_PATH}/${productList.pImg}">
				</div>
				
				<div class="span6">
				<h5> ${productList.pName} </h5>
					<p> <img src="${CONTEXT_PATH}/img/star.png">${productList.pScore} </p>
					<p>배송비 : ${productList.deliveryFee }</p>
				</div>
				<div class="span4 alignR">
					<form class="form-horizontal qtyFrm">
						<h3> ${productList.pPrice } </h3>
						<div class="btn-group">
						  <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> 장바구니 추가</a>
				 		  <a href="product_details.html" class="shopBtn">상세보기</a>
						</div>
					</form>
				</div>
			</div>
			<hr class="soft">
			</c:forEach>
		</div>
		
		<div class="tab-pane fade" id="review">
			<div class="row-fluid">	  
				<div class="span2">
					<img src="${CONTEXT_PATH}/assets/img/b.jpg" alt="">
				</div>
				<div class="span6">
					<h5> 리뷰제목 </h5>
						<p>정말 좋아요!!</p>
					</div>
				<div class="span4 alignR">
					<p> 작성자 : </p>
					<p> 구매일자 : </p>
				</div>
			</div>
			<hr class="soften"/>
		</div>
	</div>

</div>
</div>
</div>


<br><br>
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