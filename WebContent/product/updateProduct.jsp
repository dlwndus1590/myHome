<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>상품 정보 수정</title>
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
		input[type="number"]::-webkit-outer-spin-button,
		input[type="number"]::-webkit-inner-spin-button {
			-webkit-appearance: none;
			margin: 0;
		}
    </style>
    
    <!-- javascript -->
    <script type="text/javascript">
    	function changeCategory(){
    		var categoryNameElement = document.getElementById("categoryName");
    		var categoryId = document.getElementById("categoryId").value;
    		
    		if(categoryId==1) {
    			categoryNameElement.value = "가구/가전";
    		} else if(categoryId==2) {
    			categoryNameElement.value = "패브릭";
    		} else if(categoryId==3) {
    			categoryNameElement.value = "홈데코/조명";
    		} else if(categoryId==4) {
    			categoryNameElement.value = "수납/정리";
    		} else if(categoryId==5) {
    			categoryNameElement.value = "생활용품";
    		} else if(categoryId==6) {
    			categoryNameElement.value = "주방";
    		} else {
    			categoryNameElement.value = "DIY/공구";
    		}
    	}
    </script>
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
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider"></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage">마이페이지</a> <span class="divider"></span></li>
   		<li class="active">상품수정</li>
    </ul>
	<div class="well">
	<form class="form-horizontal" action="${CONTEXT_PATH}/product/productController?action=productUpdate&pNo=${product.pNo}" method="post">
		<h3>상품 수정</h3>
		<div class="control-group">
		<label class="control-label">상품명 <sup>*</sup></label>
		<div class="controls">
			<input type="text" name="pName" id="pName" value="${product.pName}" required="required" style="width:300px; height:30px;">
		</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 가격 <sup>*</sup></label>
			<div class="controls">
			  <input type="number" name="pPrice" id="pPrice" min="0" required="required" value="${product.pPrice}" style="height:30px;">
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputLname"> 상품이미지 <sup>*</sup></label>
			<div class="controls">
				  <input type="button" value="파일 선택"> ${product.pImg}<p style="color: red;">이미지는 보안상의 이유로 수정이 불가능합니다.</p>
			</div>
		 </div>
		<div class="control-group">
		<label class="control-label" for="inputEmail"> 상품설명 <sup>*</sup></label>
		<div class="controls">
			 <input type="button" value="파일 선택"> ${product.pDescribe}<p style="color: red;">이미지는 보안상의 이유로 수정이 불가능합니다.</p>
		</div>
	  </div>	  
		<div class="control-group">
		<label class="control-label"> 배송비 <sup>*</sup></label>
		<div class="controls">
		  <input type="number" name="deliveryFee" id="deliveryFee" max="50000" min="0" required="required" value="${product.deliveryFee}" style="height:30px;">
		</div>
	   </div>
		<div class="control-group">
		<label class="control-label"> 카테고리 <sup>*</sup></label>
		<div class="controls">
			<input type="text" name="categoryName" id="categoryName" readonly="readonly" value="${category.categoryName}" style="height:30px;"> 
		  	<select name="categoryId" id="categoryId" style="width:220px;" onchange="changeCategory()">
		  	<c:forEach var="categoryList" items="${categoryList}">
				<option value="${categoryList.categoryId}">${categoryList.categoryName}</option>
			</c:forEach>
			</select>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label"> 재고수량 <sup>*</sup></label>
		<div class="controls">
		  <input type="number" name="pCount" id="pCount" value="${product.pCount}" required="required" style="height:30px;">
		</div>
	   </div>
	<div class="control-group">
		<div class="controls">
		 <input type="submit" value="상품 수정" class="exclusive shopBtn">
		</div>
	</div>
	</form>
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
