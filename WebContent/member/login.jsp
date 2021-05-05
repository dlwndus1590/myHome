<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="${CONTEXT_PATH}/css/login.css">

<!-- header menu -->
<jsp:include page="/inc/header.jsp" />
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

<!-- main menu : 로그인 전 메뉴 -->

<!-- contents menu -->
<form action="${CONTEXT_PATH}/member/memberController?action=login" method="post">
  <div class="container" style="border:1px solid #ccc">
    <h1>로그인</h1>
    <hr>

    <label><b>아이디</b></label>
    <br>
    <input type="text" id="memberId" name="memberId" required>
    
    <br>
    <label><b>비밀번호</b></label>
    <br>
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
</body>
</html>