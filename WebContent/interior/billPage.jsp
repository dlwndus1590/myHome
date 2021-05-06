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

</style>
</head>
<body>
	<h2>종합 리모델링 견적 계산</h2>
	<div>
		<img alt="견적서" src="${CONTEXT_PATH}/img/bill2.png">
	</div>
	<br>
	<p>주거유형(필수)</p>
	<div class="house">
		<button class="btn success">아파트</button>
		<button class="btn success">빌라</button>
		<button class="btn success">단독주택</button>
	</div>
	
	<br>
	<p>평수/공급면적(필수)</p>
	<div class="house">
		<button class="btn success">20평~</button>
		<button class="btn success">30평~</button>
		<button class="btn success">40평~</button>
		<button class="btn success">~59평</button>
	</div>
	
	<br>
	<p>시공 면적</p>
	<div class="house">		
		<button class="btn success">전체시공</button>
		<button class="btn success">부분시공</button>		
		<button class="btn success">필름시공</button>		
		<button class="btn success">폴딩도어</button>		
	</div>
	
	<br>
	<p>도배</p>
	<div class="house">		
		<button class="btn success">실크</button>
		<button class="btn success">합지</button>		
		<button class="btn success">실크 & 합지</button>				
	</div>
	
	<br>
	<p>바닥재</p>
	<div class="house">		
		<button class="btn success">장판</button>
		<button class="btn success">강화마루</button>		
		<button class="btn success">강마루</button>				
		<button class="btn success">원목마루</button>				
		<button class="btn success">포세린타일</button>				
		<button class="btn success">천연대리석</button>				
	</div>
	
	<br>
	<p>주방</p>
	<div class="house">		
		<button class="btn success">전체교체</button>
		<button class="btn success">부분교체</button>				
	</div>
	
	<br>
	<p>연락 받으실 번호</p>
	<div class="house">		
		<input type="text" placeholder=" '-'를 포함해서 입력바랍니다. " >			
	</div>
	
	<br>
	<br>	
	<h3>견적금액</h3>	
	<input type="text" value="0" readonly>
	<button class="button" id="myBtn"><span>상담신청</span></button>
	
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>