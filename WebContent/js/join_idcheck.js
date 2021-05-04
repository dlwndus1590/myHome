/*
 * 회원가입 아이디 중복 체크 : join_idcheck.js 
 */
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
			// 존재하지 않으면 : 사용가능 아이디 오류 메시지 출력, "아이디 사용하기 버튼 " 활성화	
			document.getElementById("idUse").disabled = false;
		}
	}
	
	/* 아이디 사용하기 클릭 이벤트 처리 함수 */
	function idCheckTop() {
		// 자시창의 값을 부모창으로 옮기기
		var checkMemberId = document.getElementById("memberId").value;
		// 현재 창에 부모창 값 담기
		window.opener.document.getElementById("memberId").value = checkMemberId; 
		console.log('자식창 아이디:',checkMemberId);	
		window.opener.document.getElementById("memberId").setAttribute("readOnly", "readOnly");		
		window.opener.document.getElementById("memberId").setAttribute("focus", "focus");
		window.close();		
	}	