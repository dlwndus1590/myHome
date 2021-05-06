<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>견적서 작성하기</title>

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<style>
.btn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 14px 28px;
  font-size: 16px;
  cursor: pointer;
}

/* Green */
.success {
  border-color: #04AA6D;
  color: green;
}

.button {
  border-radius: 4px;
  background-color: #04AA6D;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 17px;
  width: 130px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

input[type='text'] {
	font-size: 20px;
	width: 280px;
	height: 53px;
}

h3{
	text-decoration: underline;
}

.button span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.button span:after {
  content: '\00bb';
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.button:hover span {
  padding-right: 25px;
}

.button:hover span:after {
  opacity: 1;
  right: 0;
}

.class button{
	border: 2px;
	border-color: black;
	
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	
	var total = 0;
	function totalPrice(clickValue){
		total += parseInt(clickValue.value);
		
		document.getElementById("innerText").innerHTML = total;
		alert(clickValue);
	}

</script>
</head>
<body>
	<h2>종합 리모델링 견적 계산</h2>
	<div>
		<img alt="견적서" src="${CONTEXT_PATH}/img/bill2.png">
	</div>
	<br>
	
	<p>주거유형(필수)</p>
	<div class="house">
		<button id="titie1" class="btn success" value="1000000" onclick="totalPrice(this)">아파트</button>
		<button id="titie2" class="btn success" value="900000" onclick="totalPrice(this)">빌라</button>
		<button id="titie3" class="btn success" value="1200000" onclick="totalPrice(this)">단독주택</button>
	</div>
	
	<br>
	<p>평수/공급면적(필수)</p>
	<div class="house">
		<button id="width1" class="btn success" value="300000" onclick="totalPrice(this)">20평~</button>
		<button id="width2" class="btn success" value="350000" onclick="totalPrice(this)">30평~</button>
		<button id="width3" class="btn success" value="380000" onclick="totalPrice(this)">40평~</button>
		<button id="width4" class="btn success" value="420000" onclick="totalPrice(this)">~59평</button>
	</div>
	
	<br>
	<p>시공 면적</p>
	<div class="house">		
		<button id="all1" class="btn success" value="5000000" onclick="totalPrice(this)">전체시공</button>
		<button id="all2" class="btn success" value="2800000" onclick="totalPrice(this)">부분시공</button>		
		<button id="all3" class="btn success" value="1900000" onclick="totalPrice(this)">필름시공</button>		
		<button id="all4" class="btn success" value="2100000" onclick="totalPrice(this)">폴딩도어</button>		
	</div>
	
	<br>
	<p>도배</p>
	<div class="house">		
		<button id="silke1" class="btn success" value="1100000">실크</button>
		<button id="silke2" class="btn success" value="700000">합지</button>		
		<button id="silke3" class="btn success" value="900000">실크 & 합지</button>				
	</div>
	
	<br>
	<p>바닥재</p>
	<div class="house">		
		<button id="floor1" class="btn success" value="1000000">장판</button>
		<button id="floor2" class="btn success" value="1300000">강화마루</button>		
		<button id="floor3" class="btn success" value="1500000">강마루</button>				
		<button id="floor4" class="btn success" value="900000">원목마루</button>				
		<button id="floor5" class="btn success" value="1100000">포세린타일</button>				
		<button id="floor6" class="btn success" value="1500000">천연대리석</button>				
	</div>
	
	<br>
	<p>주방</p>
	<div class="house">		
		<button id="kitchen1" class="btn success" value="4800000">전체교체</button>
		<button id="kitchen2" class="btn success" value="2500000">부분교체</button>				
	</div>
	
	<br>
	<p>연락 받으실 번호</p>
	<div class="house">		
		<input type="text" placeholder=" '-'를 포함해서 입력바랍니다. " >			
	</div>
	
	<br>
	<br>	
	<h3>견적금액</h3>	
	<div id="innerText"></div>
	<input type="text" id="totalPrice" value="" readonly>
	<button class="button" id="myBtn" onclick="location.href='interiorSuccess.jsp'"><span>상담신청</span></button>
	
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>