<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.myHome.model.dto.MessageEntity" %> 
<%@ include file="/inc/taglib_menu.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답결과</title>
<link type="text/css" rel="stylesheet" href="${initParam.CONTEXT_PATH}/css/common.css">

</head>
<body>


<h3>${requestScope.messageEntity.message}</h3>
<h3><a href="${messageEntity.url}">${messageEntity.linkTitle}</a></h3>

<!-- footer menu -->
<jsp:include page="/inc/footer.jsp" />

</body>
</html>












