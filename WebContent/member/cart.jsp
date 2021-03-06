<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="장바구니">
<meta name="author" content="최인묵">
<!-- Bootstrap styles -->
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<!-- Customize styles -->
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<!-- font awesome styles -->
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">

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
		<div class="span12" style="width:960px;">
			<ul class="breadcrumb">
				<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">/</span></li>
				<li class="active">장바구니</li>
			</ul>
			<div class="well well-small">
				<c:set var="cart" value="${cartList }" />
				<c:if test="${cart[0].pNo != null }">
				<h2>
					장바구니 <small class="pull-right">${fn:length(cartList)}개 상품
						담는 중</small>
				</h2>
				<hr class="soften" />

					<form class="form-horizontal" name="cartfrm" action="${CONTEXT_PATH}/member/ordersController?action=cartOrdersPage" method="post">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>상품</th>
									<th>상품명</th>
									<th>상품 가격</th>
									<th>배송비</th>
									<th>수량</th>
									<th>금액</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="test" value="0" /> 
								<c:forEach var="dto" items="${cartList }">
									<tr>
										<td>
											<input type="hidden" name="pNo" value="${dto.pNo }">
											<img width="100" src="${CONTEXT_PATH }/${dto.pImg }">
										</td>
										<td width="150">${dto.pName }</td>
										<td width="120">
											<input class="input-small" type="hidden" name="itemPrice" value="${dto.pPrice }">
											<fmt:formatNumber value="${dto.pPrice }" pattern="###,###" />원
										</td>
										
										<td width="120">
											<input class="input-small" type="hidden" name="deliveryFee" value="${dto.deliveryFee }">
											<fmt:formatNumber value="${dto.deliveryFee }" pattern="###,###" />원
										</td>
										<td width="90"><input class="span1" style="max-width: 50px"
											placeholder="1" size="16" type="number"
											value="${dto.cCount }" min="1" id="itemCount"
											name="itemCount">
											<div class="input-append">
												<a class="btn btn-mini btn-danger" type="button"
													href="${CONTEXT_PATH}/member/ordersController?action=cartDelete&pNo=${dto.pNo}">
													<span class="icon-remove"></span>
												</a>
											</div></td>
										<td width="120">
											<input class="input-small" type="text" name="totalPrice" value="${dto.totalPrice }" readonly="readonly">원
										</td>
									</tr>
									<input type="hidden" name="totalCost[]" value="${(dto.pPrice * dto.cCount) + dto.deliveryFee }">
								</c:forEach>
								<tr>
									<c:set var="total" value="0" />
									<td colspan="6" class="alignR"
										style="text-align: right; font-size: 20px;">총 금액 :</td>
									<c:forEach var="i" begin="0" end="${fn:length(cartList)}">
										<c:set var="total" value="${total + cart[i].totalPrice + cart[i].deliveryFee }" />
									</c:forEach>
									<td class="label label-primary" style="font-size: 20px">
										<input class="input-small" type="text" name="sum_qty" value="<fmt:formatNumber value="${total}" pattern="###,###" />" 
										style="background-color: #999999; 
										color: #FFFFFF; font-size: 20px; border: none;">원
									</td>
								</tr>
							</tbody>
						</table>
						<br /> <a href="${CONTEXT_PATH}/product/category.jsp"
							class="shopBtn btn-large"> <span class="icon-arrow-left"></span>
							쇼핑 계속하기
						</a> 
						<input class="shopBtn btn-large pull-right" type="submit" value="결제하기">
					</form>
				</c:if>
				<c:if test="${cart[0].pNo == null }">
					<h2>
					장바구니 <small class="pull-right">0 개 상품
						담는 중</small>
					</h2>
					<hr class="soften" />
					
					<p>장바구니에 담은 상품이 없습니다.</p>
				</c:if>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[name=itemCount]").each(function(index) {
				$("input[name=itemCount]").change(function() {
					var itemCount = $("input[name=itemCount]:eq(" + index + ")").val();
					var itemPrice = $("input[name=itemPrice]:eq(" + index + ")").val();
					var deliveryFee = $("input[name=deliveryFee]:eq(" + index + ")").val();
					var price = itemCount * itemPrice;
					//price = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
					console.log("price : " + price);
					var totalPrice = $("input[name=totalPrice]:eq(" + index + ")").val(price);
					//totalPrice = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
					//console.log("totalPrice : " + totalPrice.val());
					totalPrice = parseInt(totalPrice.val());
					var totalCost = totalPrice + parseInt(deliveryFee);
					console.log("totalCost : " + totalCost);
					//totalCost = totalCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
					$("input[name='totalCost[]']:eq(" + index + ")").val(totalCost);
				});
			});
			
			$("input[name=itemCount]").change(function() {
				var obj = $("input[name='totalCost[]']");
				var frm = document.cartfrm;
			    var sum = 0;
			    if(obj.length) {
				    for (i = 0; i < obj.length; i++) {
				        if (obj[i].value * 0 == 0) {
				        	sum += obj[i].value * 1;
				        }
				    }
				    sum = sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				    frm.sum_qty.value = sum;
				}
			});
		});
	</script>
</body>
</html>