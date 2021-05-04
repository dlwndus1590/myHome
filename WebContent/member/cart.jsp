<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Home cart</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
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

	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- Body Section -->
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span
					class="divider">/</span></li>
				<li class="active">장바구니</li>
			</ul>
			<div class="well well-small">
				<h2>
					장바구니 <small class="pull-right">${fn:length(cartList)}개 상품
						담는 중</small>
				</h2>
				<hr class="soften" />

				<c:set var="cart" value="${cartList }" />
				<c:if test="${cart != null }">

					<form class="form-horizontal" action="#" method="post">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>상품</th>
									<th>상품명</th>
									<th>선택</th>
									<th>상품 가격</th>
									<th>배송비</th>
									<th>수량</th>
									<th>금액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="dto" items="${cartList }">
									<tr>
										<td><img width="100" src="${CONTEXT_PATH }/${dto.pImg }"></td>
										<td>${dto.pName }</td>
										<td><input type="checkbox" name="checkItem"
											id="checkItem" checked="checked"></td>
										<td>
											<input type="text" name="itemPrice" value="${dto.pPrice }">원
										</td>
										
										<td>
											<input type="text" name="deliveryFee" value="${dto.deliveryFee }">원
										</td>
										<td><input class="span1" style="max-width: 34px"
											placeholder="1" size="16" type="number"
											value="${dto.cCount }" min="1" id="itemCount"
											name="itemCount">
											<div class="input-append">
												<!-- 클릭하면 장바구니 목록에서 삭제 -->
												<button class="btn btn-mini btn-danger" type="button"
													onclick="">
													<span class="icon-remove"></span>
												</button>
											</div></td>
										<td>
											<input type="text" name="totalPrice" value="${dto.totalPrice }">원
										</td>
									</tr>
								</c:forEach>
								<tr>
									<c:set var="total" value="0" />
									<td colspan="6" class="alignR"
										style="text-align: right; font-size: 20px;">총 금액 :</td>
									<c:forEach var="i" begin="0" end="${fn:length(cartList) - 1}">
										<c:set var="total" value="${total + cart[i].totalPrice }" />
									</c:forEach>
									<td class="label label-primary" style="font-size: 20px"><c:out
											value="${total}" />원</td>
								</tr>
							</tbody>
						</table>
						<br /> <a href="${CONTEXT_PATH}/product/category.jsp"
							class="shopBtn btn-large"> <span class="icon-arrow-left"></span>
							쇼핑 계속하기
						</a> <a
							href="${CONTEXT_PATH}/member/ordersController?action=ordersPage"
							class="shopBtn btn-large pull-right">결제하기 <span
							class="icon-arrow-right"></span>
						</a>
					</form>
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
				console.log(index);
				$("input[name=itemCount]").change(function() {
					var itemCount = $("input[name=itemCount]:eq(" + index + ")").val();
					console.log(index + ", itemCount : " + itemCount);
					var itemPrice = $("input[name=itemPrice]:eq(" + index + ")").val();
					console.log(index + ", itemPrice : " + itemPrice);
					var deliveryFee = $("input[name=deliveryFee]:eq(" + index + ")").val();
					console.log(index + ", deliveryFee : " + deliveryFee);
					var price = itemCount * itemPrice;
					console.log("price : " + price);
					//var totalPrice = $("input[name=totalPrice]:eq(" + index + ")").val();
					//console.log(totalPrice);
					
					//var total = '<c:out value="${total}" />';
					//total = price + deliveryFee;
					//console.log("total : " + total);
				});
			});
		});
	</script>
</body>
</html>