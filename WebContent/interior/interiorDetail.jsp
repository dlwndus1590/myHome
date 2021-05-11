<%@page import="com.myHome.model.dto.Member"%>
<%@page import="com.myHome.model.dto.Interior"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>  
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
/* Full-width input fields */
.input {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;  
}

/* Add a background color when the inputs get focus */
.input:focus, .input:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
.button{
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 65%;
  opacity: 0.9;
}

.button:hover {
  opacity:1;
}


/* Add padding to container elements */
.container2 {
  padding: 16px;
}

/* Style the horizontal ruler */
.hr {
  border: 1px solid white;
  width:680px;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

.row{
	margin-left: 3%;
}

#ino,#iname,#icareer,#idetail,#ilocation,#imobile{
	height: 40px;
}
</style>
</head>
<body>
<!-- main menu : 로그인 전 -->
<c:if test="${empty dto}">
	<script type="text/javascript">
		alert('로그인을 먼저 시도해주세요');
		location.href="${CONTEXT_PATH}/member/login.jsp"
	</script>
</c:if>

<%
	Interior dto = (Interior)session.getAttribute("interiordto");	
%>
<form action="${CONTEXT_PATH}/interior/interiorController?action=updateInterior" method="post" style="width:960px; ">
<div class="row">
<div class="span9" style="width:100%; padding-right:50px;">
    <ul class="breadcrumb">
   		<li><a href="${CONTEXT_PATH}/index.jsp">Home</a> <span class="divider">></span></li>
   		<li><a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList">인테리어 모아보기</a> <span class="divider">></span></li>
   		<li class="active"><%=dto.getIname() %></li>
    </ul>

<div class="well well-small" style="width:100%; height:760px;">
  
  <h2><%=dto.getIname() %></h2>
  	
  	<%
           Member mainMember = null;
  		   mainMember = (Member)session.getAttribute("dto");
           if(mainMember.getGrade().equals("관리자")){
  	%>   
          		<div class="container2">            
			      <hr class="hr">
			      <label><b>No</b></label>      
			      <input type="text" placeholder="Enter No"  class="input" id="ino" name="ino" value="<%=dto.getIno() %>" readonly="readonly">
			      
			      <label><b>회사명</b></label>	      
			      <input type="text" placeholder="Enter Name"   class="input" id="iname" name="iname" value="<%=dto.getIname() %>">
			
			      <label><b>경력</b></label>
			      <input type="text" placeholder="Enter Career"  class="input" id="icareer" name="icareer" value="<%=dto.getIcareer()%>">
			
			      <label><b>회사 설명</b></label>
			      <input type="text" placeholder="Enter Detail"   class="input" id="idetail" name="idetail" value="<%=dto.getIdetail()%>">
			      
			      <label><b>위치</b></label>
			      <input type="text" placeholder="Enter Location"  class="input" id="ilocation" name="ilocation" value="<%=dto.getIlocation()%>">
			      
			      <label><b>연락처</b></label>
		      	  <input type="text" placeholder="' - ' 를 포함해서 작성해주세요." class="input" id="imobile" name="imobile" value="<%=dto.getImobile()%>">
			
				  <!-- 
				  		인테리어 등록/수정/삭제하기는 관리자 권한
				  		관리자 로그인 시에만 보이는 버튼  
				  		-->
				  		
				  		<%			                  
			                  if(session.getAttribute("dto")==null){
			            %>   
			                 	<p></p>                            
			            <%
			               	}else {
			                     mainMember = (Member)session.getAttribute("dto");                     
						%> 
			            <% if(mainMember.getGrade().equals("관리자")){  %>  
			            		<div class="clearfix">	            			   
					  				<input type="submit" class="button"  value="수정하기" >			  							         
			      				</div>   		      
		    	</div>		
			</form>		
			<form action="${CONTEXT_PATH}/interior/interiorController?action=deleteInterior" method="post" style="margin-right:90%;">
				<div class="clearfix" style="margin-left: 17%;">        
					<input type="submit" class="button" value="삭제하기">		         
				</div>	 
			</form>		
		<%
			    }	
			            }
		%>           
		                 
  		<%
        	} else if(mainMember.getGrade().equals("일반회원") || mainMember.getGrade().equals("판매자")){                                   	
		%> 
           		<div class="container2">            
			      <hr class="hr">
			      <label><b>No</b></label>      
			      <input type="text" placeholder="Enter No"  class="input" id="ino" name="ino" value="<%=dto.getIno() %>" readonly="readonly">
			      
			      <label><b>회사명</b></label>	      
			      <input type="text" placeholder="Enter Name"   class="input" id="iname" name="iname" value="<%=dto.getIname() %>" readonly="readonly">
			
			      <label><b>경력</b></label>
			      <input type="text" placeholder="Enter Career"  class="input" id="icareer" name="icareer" value="<%=dto.getIcareer()%>" readonly="readonly">
			
			      <label><b>회사 설명</b></label>
			      <input type="text" placeholder="Enter Detail"   class="input" id="idetail" name="idetail" value="<%=dto.getIdetail()%>" readonly="readonly">
			      
			      <label><b>위치</b></label>
			      <input type="text" placeholder="Enter Location"  class="input" id="ilocation" name="ilocation" value="<%=dto.getIlocation()%>" readonly="readonly">
			      
			      <label><b>연락처</b></label>
		      	  <input type="text" placeholder="' - ' 를 포함해서 작성해주세요." class="input" id="imobile" name="imobile" value="<%=dto.getImobile()%>" readonly="readonly">
    	
     
     	<%
        	}
     	%>  
     
      

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