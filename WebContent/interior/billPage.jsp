<%@page import="com.myHome.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="마이 페이지">
    <meta name="author" content="강하영">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">

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

#mobile {
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

.innerText{
	font-size: 45px;
	font-weight: bold;
	font-family: verdana;
}
</style>

<script type="text/javascript">

	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	            results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	var param = getParameterByName('icareer');	
	
	var total = 0;
	var sum  = 0;
	var num1 = 0;
	var num2 = 0;
	var num3 = 0;
	var num4 = 0;
	var num5 = 0;
	var num6 = 0;		
	var num7 = 0;	
	
	function totalPrice(clickValue){		
		var action = $(clickValue.parentNode).attr("class");		
		switch(action) {
		case "title":
			num1 = parseInt(clickValue.value);			
			break;
		case "width":
			num2 = parseInt(clickValue.value);
			break;
		case "all":
			num3 = parseInt(clickValue.value);
			break;
		case "silke":
			num4 = parseInt(clickValue.value);			
			break;
		case "floor":
			num5 = parseInt(clickValue.value);			
			break;
		case "kitchen":
			num6 = parseInt(clickValue.value);
			break;			
		}
		
		total = num1 + num2 + num3 + num4 + num5 + num6;		
		total = total * (param * 0.21);		
		total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		
		document.getElementById("innerText").innerHTML = total+" 원";		
	}

	function sangdamCheck(){
		var mobileElement = document.getElementById("mobile");
		var mobile = mobileElement.value;
	
		if(num1==0){
			alert("주거유형을 선택해 주세요.");
			return false;
		} else if(num2==0){
			alert("평수/공급면적을 선택해 주세요.");
			return false;
		} else if(num3==0){
			alert("시공 면적을 선택해 주세요.");
			return false;
		} else if(num4==0){
			alert("도배를 선택해 주세요.");
			return false;
		} else if(num5==0){
			alert("바닥재를 선택해 주세요.");
			return false;
		} else if(num6==0){
			alert("주방을 선택해 주세요.");
			return false;
		} else if(mobile.length ==0 ){			
			alert("연락처를 입력해주세요.");
			mobileElement.focus();
			return false;
		} else if(num1 !=0 || num2 != 0 || num3 !=0 || num4 !=0 || num5 != 0 || num6 != 0){
			alert("작성해 주신 연락처로 연락을 드리도록 하겠습니다.");
			return true;						
		}
	}
</script>
</head>
<body>
<div class="row">
<div class="span9" style="width:960px;">
    <ul class="breadcrumb" style="width:960px;">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList">인테리어 모아보기</a> <span class="divider">></span></li>
   		<li class="active">종합 리모델링 견적 계산</li>
    </ul>
  	<div class="well well-small" style="width:960px;">
    
	<h2>종합 리모델링 견적 계산</h2>
	<div>
		<img alt="견적서" src="${CONTEXT_PATH}/img/bill2.png">
	</div>
	<br>
	<p>주거유형</p>
	<div class="title">
		<button id="titie1" class="btn success" value="1000000" onclick="totalPrice(this)">아파트</button>
		<button id="titie2" class="btn success" value="900000" onclick="totalPrice(this)">빌라</button>
		<button id="titie3" class="btn success" value="1200000" onclick="totalPrice(this)">단독주택</button>
	</div>
	
	<br>
	<p>평수/공급면적</p>
	<div class="width">
		<button id="width1" class="btn success" value="300000" onclick="totalPrice(this)">20평~</button>
		<button id="width2" class="btn success" value="350000" onclick="totalPrice(this)">30평~</button>
		<button id="width3" class="btn success" value="380000" onclick="totalPrice(this)">40평~</button>
		<button id="width4" class="btn success" value="420000" onclick="totalPrice(this)">~59평</button>
	</div>
	
	<br>
	<p>시공 면적</p>
	<div class="all">		
		<button id="all1" class="btn success" value="5000000" onclick="totalPrice(this)">전체시공</button>
		<button id="all2" class="btn success" value="2800000" onclick="totalPrice(this)">부분시공</button>		
		<button id="all3" class="btn success" value="1900000" onclick="totalPrice(this)">필름시공</button>		
		<button id="all4" class="btn success" value="2100000" onclick="totalPrice(this)">폴딩도어</button>		
	</div>
	
	<br>
	<p>도배</p>
	<div class="silke">		
		<button id="silke1" class="btn success" value="1100000" onclick="totalPrice(this)">실크</button>
		<button id="silke2" class="btn success" value="700000" onclick="totalPrice(this)">합지</button>		
		<button id="silke3" class="btn success" value="900000" onclick="totalPrice(this)">실크 & 합지</button>				
	</div>
	
	<br>
	<p>바닥재</p>
	<div class="floor">		
		<button id="floor1" class="btn success" value="1000000" onclick="totalPrice(this)">장판</button>
		<button id="floor2" class="btn success" value="1300000" onclick="totalPrice(this)">강화마루</button>		
		<button id="floor3" class="btn success" value="1500000" onclick="totalPrice(this)">강마루</button>				
		<button id="floor4" class="btn success" value="900000" onclick="totalPrice(this)">원목마루</button>				
		<button id="floor5" class="btn success" value="1100000" onclick="totalPrice(this)">포세린타일</button>				
		<button id="floor6" class="btn success" value="1500000" onclick="totalPrice(this)">천연대리석</button>				
	</div>
	
	<br>
	<p>주방</p>
	<div class="kitchen">		
		<button id="kitchen1" class="btn success" value="4800000" onclick="totalPrice(this)">전체교체</button>
		<button id="kitchen2" class="btn success" value="2500000" onclick="totalPrice(this)">부분교체</button>				
	</div>
	
	<br>
	<p>연락 받으실 번호</p>
	<div class="house">		
		<input type="text" id="mobile" placeholder=" '-'를 포함해서 입력바랍니다. " maxlength="13" pattern="010-\d{4}-\d{4}">			
	</div>
	
	<br>
	<br>	
	<h3>견적금액</h3>	
	<br>	
	<div id="innerText" class="innerText"></div>
	
	<br>	
<form action="${CONTEXT_PATH}/interior/interiorSuccess.jsp" method="get">	
	<button class="button" id="myBtn" onclick="return sangdamCheck()"><span>상담신청</span></button>
</form>
</div>
</div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>