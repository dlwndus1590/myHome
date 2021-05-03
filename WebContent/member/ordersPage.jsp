<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
// 우편번호찾기 버튼 클릭시 우편번호 검색창 오픈
function postcodeTest() {
   new daum.Postcode({
	    oncomplete: function(data) {
	    	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	    	// data.zonecode에 우편번호값, data.roadAddress에 도로명주소값, data.jibunAddress에 지번주소값 담겨있습니다.
	    	 document.getElementById('zipcode').value = data.zonecode;
	    	 document.getElementById("address1").value = data.roadAddress;
	         document.getElementById("address2").value = data.jibunAddress;
	         document.getElementById("address3").focus();
	    }
	}).open();
}
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<ul class="breadcrumb">
		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span
			class="divider">/</span></li>
		<li class="active">결제하기</li>
	</ul>
	<fieldset>
		<legend>상품정보</legend>
	</fieldset>
	<c:set var="orders" value="${ordersList }" />
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
			<c:forEach var="dto" items="${ordersList }">
			<tr>
				<td><img width="100" src="${CONTEXT_PATH }/${dto.pImg }"></td>
				<td>${dto.pName }</td>
				<td>
					<input type="checkbox" name="">
				</td>
				<td>${dto.pPrice }원</td>
				<td>${dto.deliveryFee }원</td>
				<td><input class="span1" style="max-width: 34px"
					placeholder="1" size="16" type="number"
					value="2" min="1">
					<div class="input-append">
						<button class="btn btn-mini btn-danger" type="button" onclick="">
							<span class="icon-remove"></span>
						</button>
					</div>
				</td>
				<td>${dto.totalPrice }원</td>
			</tr>
			</c:forEach>
			<tr>
				<c:set var = "total" value = "0" />
				<td colspan="6" class="alignR" style="text-align: right; font-size: 20px;">총 금액 :</td>
				<c:forEach var="i" begin="0" end="${fn:length(ordersList) - 1}">
					<c:set var= "total" value="${total + orders[i].totalPrice }"/>
				</c:forEach>
				<td class="label label-primary" style="font-size: 20px">
					<c:out value="${total}원"/>
				</td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${orders != null }">
		<form class="form-horizontal" action="#" method="post">
			<fieldset>
				<legend>구매자정보</legend>
			</fieldset>
			<div class="control-group">
				<label class="control-label">이름</label>
				<div class="controls">
					<input type="text" value="${orders[0].name }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">이메일</label>
				<div class="controls">
					<input type="text" value="${orders[0].email }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">휴대폰</label>
				<div class="controls">
					<input type="text" value="${orders[0].mobile }">
				</div>
			</div>
			<fieldset>
				<legend>배송지정보</legend>
			</fieldset>
			<%-- 
			<div class="control-group">
				<label class="control-label">우편번호</label>
				<div class="controls">
					<input type="text" value="${orders[0].zipCode }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">지번주소</label>
				<div class="controls">
					<input type="text" value="${orders[0].address1 }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">상세주소</label>
				<div class="controls">
					<input type="text" value="${orders[0].address2 }">
				</div>
			</div>
			--%>
			<table width="700" border="1" cellspacing="0" cellpadding="3" align="center" >
			    <tr> 
			       <td width="200">우편번호</td>
			       <td> 
			       		<input type="text" name="zipcode" id="zipcode" size="7" readonly="readonly" value="${orders[0].zipCode }">
			            <input type="button" value="우편번호찾기" onClick="postcodeTest()">
			       </td>
			   </tr>
			   
			   <tr> 
			       <td>주소</td>
			       <td>
			       		<input type="text" name="address" id="address1" size="70" readonly="readonly" placeholder="도로명주소">
			       		<input type="text" name="address" id="address2" size="70" readonly="readonly" placeholder="지번주소" value="${orders[0].address1 }">
			       		<input type="text" name="address" id="address3" size="70" placeholder="상세주소" value="${orders[0].address2 }">
			       </td>
			   </tr>
		  	</table>
		  	
			<fieldset>
				<legend>마일리지</legend>
			</fieldset>
			<div class="control-group">
				<label class="control-label">보유 마일리지</label>
				<div class="controls">
					<input type="text" id="currentMileage" value="${orders[0].mileage }" readonly="readonly">
				</div>
				<label class="control-label">사용할 마일리지</label>
				<div class="controls">
					<input type="text" name="useMileage" id="useMileage" value="0" min="0" max="${orders[0].mileage }">
					<input class="checkbox" id="checkedMileage" type="checkbox">전액 사용
				</div>
			</div>
			<fieldset>
				<legend>결제수단</legend>
			</fieldset>
			<label class="radio"> <input type="radio"
				name="optionsRadios" value="1" checked>
				무통장입금
			</label> 
			<label class="radio"> <input type="radio"
				name="optionsRadios" value="2">
				신용카드
			</label>
			<label class="radio"> <input type="radio"
				name="optionsRadios" value="3">
				휴대폰결제
			</label>
			<div class="control-group" style="text-align: center;">
				<div class="controls">
					<button type="submit" class="btn btn-large btn-success" style="width: 250px">결제하기</button>
					<button type="button" class="btn btn-large" style="width: 200px" onclick="location.href='${CONTEXT_PATH}/index.jsp'">취소</button>
				</div>
			</div>
		</form>
	</c:if>
	
	<!-- footer -->
	<jsp:include page="/inc/footer.jsp" />

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
	<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		    $("#checkedMileage").change(function(){
		        if($("#checkedMileage").is(":checked")){
		            $("#useMileage").attr("value", "${orders[0].mileage }");
		        }else{
		        	$("#useMileage").attr("value", 0);
		        }
		    });
		});
	</script>
</body>
</html>