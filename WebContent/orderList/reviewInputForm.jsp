<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="후기 작성 화면">
<meta name="author" content="김보성">
<link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet" />
<link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<style>
.star {
  font-size: 2rem;
  margin: 10px 0;
  cursor: pointer;
}

.star:not(.on) {
  color: white;
}

.star.on {
  color: orange;
}
</style>
<script type="text/javascript">
	var rate = 0;
$(document).on("click",".star", function(){
	var starEls = document.querySelectorAll('#star span.star');
	
    loop(starEls, function (el, index) {
        el.addEventListener('click', function () {
            rating(index + 1);
        });
    });

    function loop(list, func) {
        Array.prototype.forEach.call(list, func);
    }

    function rating(score) {
        loop(starEls, function (el, index) {
            if (index < score) {
                el.classList.add('on');
            } else {
                el.classList.remove('on');
            }
        });

        rate = score;
        document.querySelector('#score').setAttribute('value', score);
    }
})
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/inc/header.jsp" />

	<!-- contents menu -->
	<ul class="breadcrumb" style="width: 960px;">
   		<li><a href="${CONTEXT_PATH}/product/productController?action=storeHome">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage">마이페이지</a> <span class="divider">></span></li>
   		<li class="active">리뷰작성</li>
    </ul>
	<form action="${CONTEXT_PATH}/orderList/orderListController?action=reviewInput&pNo=${pNo}&memberId=${sessionScope.memberId}&pImg=${pImg}" method="post">
		<div class="row-fluid">
			<div class="span2" style="width: 160px;">
				<img src="${CONTEXT_PATH}/${pImg}"
					style="width: 160px; height: 190px; padding-top: 40px;">
			</div>
			<div class="span6">
				<h5 style="text-align: center; font-size: 20px;">${pName}</h5>
				<textarea cols="20" rows="7" style="width: 700px;"
					placeholder="리뷰 내용을 작성해주세요." name="reviewContent"></textarea>
			</div>
		</div>
		<br> <span style="font-size: 25px; font-weight: bolder; margin-left: 330px;">
			평점 : 
			</span>
			<span class="star-container" id="star">
				<span style="margin :20px 0px 0px 5px;">
 					 <span class="star">★</span>
  				  	 <span class="star">★</span>
  					 <span class="star">★</span>
			    	 <span class="star">★</span>
 				 	 <span class="star">★</span>
				</span>
			</span>
			<br>
			<input type="hidden" value="${score}" id="score" name="score">
			<input type="hidden" value="${oDate}" name="oDate">
			<br>
			<div class="btn-group">
				<input type="submit" class="shopBtn" style="margin-left: 800px;" value="작성완료">
			</div>
	</form>
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