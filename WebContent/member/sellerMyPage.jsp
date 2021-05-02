<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/login.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />

<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/mypage.css">
</head>
<body>
<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<div class="container">
	<h1>강하영[gydyddl33] 님의 쇼핑정보</h1>
	<hr>
	<center>
			<input type="submit" class="mypagebtn" value="내 정보 변경">
				
			<h3>강하영 님은 [판매자 회원] 입니다.</h2>
			<p>0원 이상 구매시, 구매금액의 1%를 추가 적립해 드립니다.</p>
			
			<br>
			<br>
			<table>
			  <tr>
			    <th>마일리지 : 1,000원</th>
			    <th>쿠폰 : 0개</th>
			  </tr>
			</table>
	
	</center>
</div>
<br>
<br>
<br>
<div class="mypageForm">
	<div class="row">
	  <div class="column">
	    <div class="card">
	      <h3>profile</h3>
	      <a href="#"><p>회원정보</p></a>      
	    </div>
	  </div>
	
	  <div class="column">
	    <div class="card">
	      <h3>order list</h3>
	      <a href="#"><p>상품 등록내역</p></a>  
	    </div>
	  </div>
	  
	  <div class="column">
	    <div class="card">
	      <h3>myboard</h3>
	      <a href="#"><p>내 게시글</p></a>  
	    </div>
	  </div>
	</div> 

	<br>
	<br>
	<br>
	<br>
		
		<div class="column">
		   <div class="card">
		     <h3>widthdraw</h3>
		     <a href="#"><p>탈퇴하기</p></a>  
		   </div>
		</div>
	</div>
</div>
<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>
</body>
</html>