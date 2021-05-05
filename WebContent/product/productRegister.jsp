<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>상품 등록</title>
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

  </head>
<body>
<!-- header -->
<jsp:include page="/inc/header.jsp" />

<!-- 
Body Section 
-->
<div class="row">
	<div class="span9" style="width:960px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider"></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage">마이페이지</a> <span class="divider"></span></li>
   		<li class="active">상품등록</li>
    </ul>
	<div class="well">
	<form class="form-horizontal">
		<h3>상품 등록</h3>
		<div class="control-group">
		<label class="control-label">상품명 <sup>*</sup></label>
		<div class="controls">
			<input type="text" placeholder="상품명을 입력하세요." style="width:300px;">
		</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 가격 <sup>*</sup></label>
			<div class="controls">
			  <input type="number" max="50000" min="0" placeholder="숫자만 입력" style="width:300px;">
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputLname"> 상품이미지 <sup>*</sup></label>
			<div class="controls">

            	  <input type="file">
				
			</div>
		 </div>
		<div class="control-group">
		<label class="control-label" for="inputEmail"> 상품설명 <sup>*</sup></label>
		<div class="controls">
		  <input type="text" placeholder="Email">
		</div>
	  </div>	  
		<div class="control-group">
		<label class="control-label"> 배송비 <sup>*</sup></label>
		<div class="controls">
		  <input type="number" max="50000" min="0" placeholder="숫자만 입력">
		</div>
	   </div>
		<div class="control-group">
		<label class="control-label"> 카테고리 <sup>*</sup></label>
		<div class="controls">
		  <select class="span1" name="days">
				<option value="">-</option>
			</select>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label"> 재고수량 <sup>*</sup></label>
		<div class="controls">
		  <input type="password" placeholder="Password">
		</div>
	   </div>
	<div class="control-group">
		<div class="controls">
		 <input type="submit" name="submitAccount" value="Register" class="exclusive shopBtn">
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