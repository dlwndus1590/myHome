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
<meta name="description" content="단일 결제 페이지">
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	// 우편번호찾기 버튼 클릭시 우편번호 검색창 오픈
	function postcodeTest() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				// data.zonecode에 우편번호값, data.roadAddress에 도로명주소값, data.jibunAddress에 지번주소값 담겨있습니다.
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById("address1").value = data.roadAddress;
				//document.getElementById("address2").value = data.jibunAddress;
				document.getElementById("address2").focus();
			}
		}).open();
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
	
	<div class="row">
	<div class="span9" style="width:960px;">

	<ul class="breadcrumb">
		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span
			class="divider">/</span></li>
		<li><a href="${CONTEXT_PATH}/member/ordersController?action=cartPage">장바구니</a> <span
			class="divider">/</span></li>
		<li class="active">결제하기</li>
	</ul>
	
	<div class="well well-small">
	<fieldset>
		<legend>상품정보</legend>
	</fieldset>
	<c:set var="orders" value="${ordersPage }" />
	<c:if test="${orders != null }">
		<form class="form-horizontal" action="${CONTEXT_PATH}/member/ordersController?action=order" method="post">
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
					<%-- <c:forEach var="dto" items="${ordersPage }" varStatus="status"> --%>
					<c:set var="dto" value="${ordersPage }" />
						<tr>
							<td>
								<input type="hidden" name="pNo" value="${dto.pNo }">
								<input type="hidden" name="stock" value="${dto.stock }">
								<img width="100" src="${CONTEXT_PATH }/${dto.pImg }">
							</td>
							<td>${dto.pName }</td>
							<td>
								<fmt:formatNumber value="${dto.pPrice }" pattern="###,###" />원
							</td>
							<td><input type="hidden" name="deliveryFee" value="${dto.deliveryFee }">
								<fmt:formatNumber value="${dto.deliveryFee }" pattern="###,###" />원
							</td>
							<td><input class="span1" name="count" style="max-width: 40px"
								placeholder="1" size="16" type="number" value="${dto.cCount }"
								min="1" readonly="readonly"></td>
							<td><input type="hidden" name="totalPrice" value="${dto.totalPrice }">
								<fmt:formatNumber value="${dto.totalPrice }" pattern="###,###" />원
							</td>
						</tr>
					<%-- </c:forEach> --%>
					<tr>
						<c:set var="total" value="0" />
						<td colspan="6" class="alignR"
							style="text-align: right; font-size: 20px;">총 금액 :</td>
						<td class="label label-primary" style="font-size: 20px">
							<input type="hidden" name="totalCost" id="totalCost" value="${totalCost }">
							<fmt:formatNumber value="${totalCost }" pattern="###,###" />원
						</td>
					</tr>
				</tbody>
			</table>

			<fieldset>
				<legend>구매자정보</legend>
			</fieldset>
			<div class="control-group">
				<label class="control-label">이름</label>
				<div class="controls">
					<input type="text" value="${orders.name }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">이메일</label>
				<div class="controls">
					<input type="text" value="${orders.email }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">휴대폰</label>
				<div class="controls">
					<input type="text" value="${orders.mobile }" readonly="readonly">
				</div>
			</div>
			<fieldset>
				<legend>배송지정보</legend>
			</fieldset>
			<table width="700" border="1" cellspacing="0" cellpadding="3"
				align="center">
				<tr>
					<td width="200">우편번호</td>
					<td><input type="text" name="zipcode" id="zipcode" size="7"
						readonly="readonly" value="${orders.zipCode }"> <input
						type="button" value="우편번호찾기" onClick="postcodeTest()"></td>
				</tr>

				<tr>
					<td>주소</td>
					<td><input type="text" name="address1" id="address1" size="70"
						readonly="readonly" placeholder="도로명주소" value="${orders.address1 }"> <input type="text"
						name="address2" id="address2" size="70" placeholder="상세주소"
						value="${orders.address2 }">
					</td>
				</tr>
			</table>
	
			<fieldset>
				<legend>마일리지</legend>
			</fieldset>
			<div class="control-group">
				<label class="control-label">보유 마일리지</label>
				<div class="controls">
					<input type="text" name="accumulateMileage" id="accumulateMileage"
						value="${orders.mileage }" readonly="readonly">
				</div>
				<br>
				<label class="control-label">사용할 마일리지</label>
				<div class="controls">
					<input type="text" name="usedMileage" id="usedMileage" value="0"
						min="0" max="${orders.mileage }"> 
					<input class="checkbox" id="checkedMileage" type="checkbox">전액 사용
				</div>
			</div>
			<fieldset>
				<legend>결제수단</legend>
			</fieldset>
			<label class="radio"> <input type="radio"
				name="optionsRadios" value="1" checked> 무통장입금
			</label> <label class="radio"> <input type="radio"
				name="optionsRadios" value="2"> 신용카드
			</label> <label class="radio"> <input type="radio"
				name="optionsRadios" value="3"> 휴대폰결제
			</label>
			<div class="control-group" style="text-align: center;">
				<div class="controls">
					<button type="submit" class="btn btn-large btn-success"
						style="width: 250px">결제하기</button>
					<button type="button" class="btn btn-large" style="width: 200px"
						onclick="location.href='${CONTEXT_PATH}/index.jsp'">취소</button>
				</div>
			</div>
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
			$("#checkedMileage").change(function() {
				if ($("#checkedMileage").is(":checked")) {
					var totalCost = $("#totalCost").val();
					var mileage = $("#accumulateMileage").val();
					totalCost = parseInt(totalCost);
					mileage = parseInt(mileage);
					console.log(totalCost, typeof totalCost);
					console.log(mileage, typeof mileage);
					if (totalCost >= mileage) {
						$("#usedMileage").attr("value", "${orders.mileage }");
					} else {
						$("#usedMileage").attr("value", "${totalCost }");
					}
				} else {
					$("#usedMileage").attr("value", 0);
				}
			});
		});
	</script>
</body>
</html>