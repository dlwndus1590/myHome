<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모아보기</title>
<style>
div{	
	margin-left: 80%;
	margin-top: 500px;	
}

.interiorBtn {
    display: inline-block;
    margin: 0;
    padding: 11px 26px 12px;
    border: none;
    text-decoration:none;
    background-color: #00A170;
    color: #fff;
    border-radius: 45px;
    font: inherit;
    font-size: 14px;
    font-weight: 700;
    line-height: 22px;
    box-shadow: 0 2px 6px 0 rgb(53 197 240 / 20%);
    transition: background-color .1s;
    white-space: nowrap;
}
</style>
</head>
<body>
<div>
	<a class="interiorBtn" href="${CONTEXT_PATH}/interior/interiorController?action=interiorList">인테리어 업체 모아보기</a>
</div>
</body>
</html>