<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="주문 상세 내역">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${CONTEXT_PATH}/css/notice/orderDetail.css" rel="stylesheet">
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<h3 class="headerTitle">주문 상세정보</h3>
	<table id = "orderInfoTable">
		<tr>
			<td id="orderInfoTableTd">
			<span class="orderInfoTitle">주문일자</span> 
			<span id="orderDate">${oDate}</span>
			<span id="division">|</span> 
			<span class="orderInfoTitle">주문번호</span> 
			<span id="orderNum">${oNo}</span>
			</td>
		</tr>
	</table>
	<hr class="hrBold">
	
	<table id="productDetailTable">
		<tr>
			<th id="productNum">
				상품주문번호
			</th>
			
			<td id="productInfo">
				상품정보
			</td>
			
			<td id="productPrice">
				상품금액(수량)
			</td>
			
			<td id="DeliveryFee" >
				배송비
			</td>
			
			<td id="currentStatus">
				진행상태
			</td>
		</tr>
		
		<tr><td colspan="5"><hr class="hrSoft"></td></tr>
		
		<c:set var="flag" value="0" />
		<c:forEach var="index" items="${orderDetailList}">
			<tr>
				<td style="text-align: center; padding-left: 40px;">${index.dNo}</td>
				<td><img src="${CONTEXT_PATH}/${index.pImg}" class="productPicture">${index.pName}</td>
				<td style="text-align: center">${index.pPrice}원<p></p>(${index.dCount}개)</td>
				<c:if test="${flag == 0}">
				<td style="text-align: center" class="grayColor" rowspan="${length}">${index.oDeliveryFee}</td>
				<c:set var="flag" value="1" />
				</c:if>
				<td class="grayColor">구매확정<span style="padding-left: 50px;"><input type="submit" value="리뷰쓰기"></span></td>
			</tr>
			<tr><td colspan="5"><hr class="hrSoft"></td></tr>
		</c:forEach>
	</table>
	
	<h3 class="headerTitle">주문/결제 금액 정보</h3>
	<hr class="hrBold">
	
	<table id="receiptTable1">
		<tr>
			<th style="width : 110px; padding : 20px 0px 30px 45px;">주문금액</th>
			<td style="width : 200px; padding-left: 50px;"></td>
			<th style="width : 130px; padding-left : 25px;">결제상세</th>
			<td style="width : 200px; padding-left: 50px;"></td>
		</tr>
		
		<tr>
			<td class = "priceTableInfo">상품금액</td>
			<td style="text-align: right">${orderDetailList.get(0).oTotalPrice}원</td>
			<td class = "priceTableInfo">포인트 사용</td>
			<td style="text-align: right; padding-right: 10px;">${orderDetailList.get(0).usedMileage}원</td>
		</tr>
		

		<tr>
			<td class="priceTableInfo">배송비</td>
			<td style="text-align: right">+${orderDetailList.get(0).oDeliveryFee}원</td>
			<td class="priceTableInfo">포인트 적립</td>
			<td style="text-align: right; padding-right: 10px;">+${orderDetailList.get(0).accumulateMileage}원</td>
		</tr>
	</table>
	
	<div id="receipt">
				<br>
				<span id="receiptPrice1">주문금액</span>
				<span id="receiptPrice2">${orderDetailList.get(0).totalAmount}원</span>
				<br><br>
				<span id="receiptPoint1">포인트 사용</span>
				<span id="receiptPoint2">${orderDetailList.get(0).usedMileage}원</span>
				<hr>
	</div>
	
	<div id="lineSpace"></div>
	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />
</body>
</html>