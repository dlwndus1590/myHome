<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="로그인 페이지">
    <meta name="author" content="강하영">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
	<!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">
    
    <link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/login.css">
    
    <style>
		#memberId, #memberPw{
			height: 50px;
			font-size: 20px;
		}
	</style>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/member_input.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	
	<!-- 카카오 스크립트 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
	Kakao.init('ef7d648c9d8cef88d6c092d4942eee41'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	function kakaoLogin() {
	    Kakao.Auth.login({
	      scope: 'account_email',
	      success: function (response) {
	        Kakao.API.request({
	          url: '/v2/user/me',
	          success: function (response) {
	        	  const kakao_account = response.kakao_account;
	        	  //$("#id").text(response.id);
	        	  //("#email").text(response.kakao_account.email);
	        	  console.log(response)
	        	  
	          },
	          fail: function (error) {
	            console.log(error)
	          },
	        })
	      },
	      fail: function (error) {
	        console.log(error)
	      },
	    })
	  }
	
	</script>
</head>
<body>

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />


<!-- contents menu -->
<div class="row">
<div class="span9" style="width:960px;">

<div class="well well-small">
	<form action="${CONTEXT_PATH}/member/memberController?action=login" method="post" style="padding-left:23%;">
	  <div class="container">
	    <h1>로그인</h1>
	    <hr>
	
	    <label><b>아이디</b></label>    
	    <input type="text" id="memberId" name="memberId" required>
	    
	    <br>
	    <label><b>비밀번호</b></label>    
	    <input type="password" id="memberPw" name="memberPw" required>
	    <div id="memberPwConfirmMessage"></div>	
	
	    <label>
	      <input type="checkbox" onclick="showMemberPw()" id="memberPwShow" name="memberPwShow" style="margin-bottom:15px"> 비밀번호 보이기
	    </label>
	
	
	    <div class="clearfix">      
	      <button type="submit" class="signupbtn">Sign Up </button>
	    </div>
	    
		<div class="loginMain">
			<a  href="${CONTEXT_PATH}/member/memberController?action=memberFindForm"><b>아이디 / 비밀번호 찾기</b></a>
			<a  href="${CONTEXT_PATH}/member/memberController?action=loginChoice"><b>회원가입</b></a>
		</div>
	  </div>
	  <table>
	  		<td align="center" onclick="kakaoLogin();">
				<a href="javascript:void(0)">
					<img alt="카카오 로그인" src="/myHome/img/kakao_login_medium_wide.png" id="kakaoLogin">
				</a>
			</td>
		</table>
	
		<h3>네이버 로그인</h3>
		  <!-- 네이버아이디로로그인 버튼 노출 영역 -->
		  <div id="naver_id_login"></div>
	
	</form>
</div>
</div>
</div>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp"/>


<script type="text/javascript">
  	var naver_id_login = new naver_id_login("aTSJqkAHtCJDk4iOio2e", "http://localhost:8090/myHome/member/naver_callback.html");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 6,40);
  	naver_id_login.setDomain("http://localhost:8090");
  	naver_id_login.setState(state);
  	/* naver_id_login.setPopup(); */
  	naver_id_login.init_naver_id_login();
</script>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${CONTEXT_PATH}/assets/js/jquery.js"></script>
<script src="${CONTEXT_PATH}/assets/js/bootstrap.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.easing-1.3.min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
<script src="${CONTEXT_PATH}/assets/js/shop.js"></script>
</body>
</html>