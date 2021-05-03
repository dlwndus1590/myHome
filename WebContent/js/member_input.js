/** 회원가입 : member_input.js */

/* 비밀번호, 비밀번호확인 보이기/감추기 */
function showMemberPw() {
	var memberPwShowElement = document.getElementById("memberPwShow");
	var memberPwElement = document.getElementById("memberPw");
	var memberPwConfirmElement = document.getElementById("memberPwConfirm");

	// 체크박스 체크여부 확인 : checked type="text" 보이기/ un cheked type="password"
	if (memberPwShowElement.checked) {
		memberPwElement.type = "text";
		memberPwConfirmElement.type = "text";
	} else {
		memberPwElement.type = "password";
		memberPwConfirmElement.type = "password";
	}
}

/* 이름입력항목에 포커스가 올때 비밀번호와 비밀번호확인 매핑 체크 */
function checkMemberPw() {
	var memberPwElement = document.getElementById("memberPw");
	var memberPwConfirmElement = document.getElementById("memberPwConfirm");

	var memberPwMessageElement = document.getElementById("memberPwMessage");
	var memberPwConfirmMessageElement = document.getElementById("memberPwConfirmMessage");

	var memberPw = memberPwElement.value;
	var memberPwConfirm = memberPwConfirmElement.value;
	
	if (memberPw == null || memberPw == "" || memberPw.length == 0) {
		memberPwMessageElement.style.color = "red";
		memberPwMessageElement.innerHTML = "비밀번호를 입력하세요.";
		memberPwElement.focus();
		return;
	} else {
		memberPwMessageElement.innerHTML = "";
	}
	
	if (memberPwConfirm == null || memberPwConfirm == "" || memberPwConfirm.length == 0) {
		memberPwConfirmMessageElement.style.color = "red";
		memberPwConfirmMessageElement.innerHTML = "비밀번호확인을 입력하세요.";
		memberPwConfirmElement.focus();
		return;
	} else {
		memberPwConfirmMessageElement.innerHTML = "";
	}
	
	if (memberPw != memberPwConfirm) {
		memberPwConfirmMessageElement.style.color = "red";
		memberPwConfirmMessageElement.innerHTML = "비밀번호와 비밀번호확인이 일치하지 않습니다.";
	} else {
		memberPwConfirmMessageElement.style.color = "green";
		memberPwMessageElement.innerHTML = "";
		memberPwConfirmMessageElement.innerHTML = "비밀번호와 비밀번호확인이 일치합니다.";
	}
}

/* 아이디 중복체크  */
function idCheckSub() {
	// 아이디 존재 여부 확인? 입력란 정보 가져오기
	var checkMemberId = document.getElementById("memberId");
	var checkId = checkMemberId.value;
	console.log('debug checkId:',checkId);
	
	for(i=0;i<memberArray.length;i++){
		if(memberArray[i]==checkId){
				// 존재여부 체크 : 존재하면 : 사용불가 아이디 오류메시지 출력
				document.getElementById("checkMessage").innerHTML = "존재하는 아이디 입니다.";
				document.getElementById("checkMessage").style.color = "red";
				document.getElementById("idUse").disabled = true;
				return;
		}
		document.getElementById("checkMessage").innerHTML = "사용가능한 아이디 입니다.";
		document.getElementById("checkMessage").style.color = "green";
	}
}