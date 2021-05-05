function answerEdit(aNo, qNo, aContent) {
	var eddit = document.getElementById('content' + aNo);
	eddit.innerHTML =
		"<form action='"+ getContextPath() +"/notice/noticeController?action=updateComment' method='post'>"
		+ "<input type='hidden' value=" + aNo + " name='aNo'>"
		+ "<input type='hidden' value=" + qNo + " name='qNo'>"
		+ "<textarea name='edit_acontent" + aNo + "' style='width:900px; height:60px; resize:none;'>" + aContent + "</textarea>"
		+ "<div align='right' style='padding-right : 30px'><input type='submit' value='완료'> <a onClick='window.location.reload()'>취소</a></div>"
		+ "</form>";
};

function getContextPath() {
  var hostIndex = location.href.indexOf( location.host ) + location.host.length;
  return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};