<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
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
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
		<div class="row" style="padding-left:20px;">
	<ul class="breadcrumb" style="width: 960px;">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">주문상세내역</li>
    </ul>
	<div class="well well-small" style="width: 960px;">
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
	<hr class="hrBold" style="width: 920px;">
	
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
				총배송비
			</td>
			
			<td id="currentStatus">
				진행상태
			</td>
		</tr>
		
		<tr><td colspan="5"><hr class="hrSoft"></td></tr>
		<c:set var="flag" value= "미입력" />
		<c:forEach var="index" items="${orderDetailList}">
		<form action="${CONTEXT_PATH}/orderList/orderListController?action=reviewInputForm&oNo=${index.oNo}&pNo=${index.pNo}&pName=${index.pName}&pImg=${index.pImg}&oDate=${index.oDate}&dCount=${index.dCount}" method="post">
			<tr>
				<td style="text-align: center; padding-left: 40px;">${index.dNo}</td>
				<td><img src="${CONTEXT_PATH}/${index.pImg}" class="productPicture">${index.pName}</td>
				<td style="text-align: center">${index.pPrice}원<p></p>(${index.dCount}개)</td>
				<c:if test="${flag == '미입력'}">
				<td style="text-align: center" class="grayColor" rowspan="${length}">${index.oDeliveryFee}원</td>
				<c:set var="flag" value="입력"/>
				</c:if>
				<c:choose>
					<c:when test="${index.reviewCheck == 0}">
						<td class="grayColor">구매확정<span style="padding-left: 30px;"><input type="submit" value="후기작성" class="shopBtn" style="width: 100px"></span></td>
					</c:when>
					<c:otherwise>
						<td class="grayColor">구매확정<input type="button" value="후기작성완료" style="width: 100px; margin-left: 30px; height: 30px; text-align: center" class="shopBtn"></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr><td colspan="5"><hr class="hrSoft"></td></tr>
		</form>
				</c:forEach>
	</table>
	
	<h3 class="headerTitle">주문/결제 금액 정보</h3>
	<hr class="hrBold" style="width: 920px;">
	
	<table id="receiptTable1">
		<tr>
			<th style="width : 110px; padding : 20px 10px 30px 15px;">주문금액</th>
			<td style="width : 200px; padding-left: 50px;"></td>
			<th style="width : 130px; padding-right : 20px;">결제상세</th>
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
			<td style="text-align: right;">+${orderDetailList.get(0).oDeliveryFee}원</td>
			<td class="priceTableInfo">포인트 적립</td>
			<td style="text-align: right; padding-right: 10px;">+${orderDetailList.get(0).accumulateMileage}원</td>
		</tr>
	</table>
	
	<div id="receipt">
				<br>
				<span id="receiptOrderPrice1">주문금액</span>
				<span id="receiptOrderPrice2">${orderDetailList.get(0).oTotalPricePlusFee}원</span>
				<br><br>
				<span id="receiptPoint1">포인트 사용</span>
				<span id="receiptPoint2">${orderDetailList.get(0).usedMileage}원</span>
				<hr>
				<span id="receiptPrice1">결제 금액</span>
				<span id="receiptPrice2">${orderDetailList.get(0).totalAmount}원</span>
	</div>
	
	<div id="lineSpaceTop"></div>
	
	<h3 class="headerTitle">배송지 정보</h3>
	<hr class="hrBold" style="width: 920px;">
	<table style="width: 400px;">
		<tr>
			<th style="padding : 30px 40px 15px 0px; width: 150px; font-weight: bolder;">수령인</th>
			<td style="padding : 30px 30px 15px 0px;">${orderDetailList.get(0).memberName}</td>
		</tr>
		
		<tr>
			<th style="padding : 0px 40px 15px 0px; width: 150px; font-weight: bolder;">연락처</th>
			<td style="padding : 0px 30px 15px 0px;">${orderDetailList.get(0).mobile}</td>
		</tr>
		
		<tr>
			<th style="padding : 0px 40px 15px 0px; width: 150px; font-weight: bolder;">배송지</th>
			<td style="padding : 0px 30px 15px 0px;">${orderDetailList.get(0).zipCode}
				<br>${orderDetailList.get(0).address1}
				<br>${orderDetailList.get(0).address2}
			</td>
		</tr>
	</table>
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
</body>
</html>