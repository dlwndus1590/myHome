<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/taglib_menu.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>header</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="${CONTEXT_PATH}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="${CONTEXT_PATH}/assets/style/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
   <link href="${CONTEXT_PATH}/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
   <!-- Favicons -->
    <link rel="shortcut icon" href="${CONTEXT_PATH}/assets/ico/favicon.ico">
    
    <link href="${CONTEXT_PATH}/css/header.css" rel="stylesheet"/>
    
  </head>
<body>
        <div class="navi" id="navbar">
            <nav class="nav__container">
                <div>    
                    <div class="nav__list">
                        <div class="nav__items">
                            <h3 class="nav__subtitle">Interiors</h3>
                            
                            <div class="nav__dropdown">
                            
                                <a href="#" class="nav__link">
                                    <i class='bx bx-user nav__icon' ></i>
                                    <span class="nav__name">Profile</span>
                                    <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>
                                </a>
								 <%              
								 	  Member mainMember = null;
					                  if(session.getAttribute("memberId")==null){
					               %>   
					                  <div class="nav__dropdown-collapse">
		                                    <div class="nav__dropdown-content">
		                                        <a href="javascript:alert('로그인이 필요한 서비스입니다.')" class="nav__dropdown-item">Interior store</a> 
		                                    </div>
		                              </div> 
					               <%
					                  } else {   
					                	  mainMember = (Member)session.getAttribute("dto");
					               %>
					               <%
					               		if(mainMember.getGrade().equals("일반회원") || mainMember.getGrade().equals("판매자") || mainMember.getGrade().equals("관리자")){
					               %>
	                                	<div class="nav__dropdown-collapse">
			                                    <div class="nav__dropdown-content">
			                                        <a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList" class="nav__dropdown-item">Interior store</a> 
			                                    </div>
			                            </div> 
                                <%
                                	}
					               } %>	
                            </div>

                        </div>                        
                    </div>
                </div>
            </nav>
        </div>

<!-- Upper Header Section -->
<div class="navbar navbar-inverse navbar-fixed-top">
   <div class="topNav">
      <div class="container">
         <div class="alignR">
		<%if(session.getAttribute("dto")==null) {%>
			<b></b>
		<%}else{ %>
			<b>[<%=session.getAttribute("name") %>님]</b>
		<%} %>
               						
               <!-- 회원가입 요청 서비스 -->   
               <%                  
                  if(session.getAttribute("memberId")==null){
               %>  
               <span class="dropdown">
               	  <a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="icon-edit" style="color:black;"></span>회원가입</a>
               	  <div class="dropdown-menu">
               	  	<a href="${CONTEXT_PATH}/member/memberInput.jsp">일반 회원가입</a>
               	  	<a href="${CONTEXT_PATH}/member/sellerInput.jsp">판매자 회원가입</a>
               	  </div>
               </span>
               <%
                  } else {                     
               %>
                  <a></a>
               <%
                  }
               %>
               
               
               <!-- 로그인 / 미 로그인 상태 변화 -->
               <%                  
                  if(session.getAttribute("dto")==null){
               %>   
                  <a href="${CONTEXT_PATH}/member/login.jsp"><span class="icon-lock" style="color:black;"></span> Login </a>                            
               <%
                  } else {
                     mainMember = (Member)session.getAttribute("dto");                     
               %>
                  <a href="${CONTEXT_PATH}/member/memberController?action=logout" onclick="javascript:alert('로그아웃 되었습니다.')"><span class="icon-lock" style="color:black;"></span> Logout </a>                               
               <%
                     if(mainMember.getGrade().equals("일반회원")){%>
                        <a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage"><span class="icon-user" style="color:black;"></span> 마이페이지</a>
               <%
                     } else if(mainMember.getGrade().equals("판매자")){%>
                        <a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage"><span class="icon-user" style="color:black;"></span> 마이페이지</a>                   
               <%
                     } %>
               <%
                     
                  }
               %>   
               
               <%                  
                  if(session.getAttribute("memberId")==null){
               %>   
                  <a ></a> 
               <%
                  } else {   
                	  mainMember = (Member)session.getAttribute("dto");
               %>
               <%
               		if(mainMember.getGrade().equals("일반회원")){
               %>
                  		<a href="${CONTEXT_PATH}/member/ordersController?action=cartPage"><span class="icon-shopping-cart" style="color:black;"></span> 장바구니 </a>
               <%
               		} else{               			
               	%>               	
               			<a></a>               	
               	<%
               		}
                  }
               %>
                
               
         </div>
      </div>
   </div>
</div>

<!-- Lower Header Section -->
<div class="container">
<div id="gototop"> </div>
<header id="header">
<div class="row">
   <div class="span4">
   <h1>
   <a class="logo" href="${CONTEXT_PATH}/index.jsp"><span>myHome</span> 
      <img src="${CONTEXT_PATH}/assets/img/myhome.png" style="width:200px; height:70px;">
   </a>
   </h1>
   </div>
</div>
</header>

<!-- Navigation Bar Section -->
<div class="navbar" style="width:960px;">
     <div class="navbar-inner">
      <div class="container">
        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
        </a>
        <div class="nav-collapse">
            <ul class="nav">
                <li class="dropdown">
                   <a data-toggle="dropdown" class="dropdown-toggle" href="#">커뮤니티 <b class="caret"></b></a>
                   <div class="dropdown-menu">
                      <a href="${CONTEXT_PATH}/notice/noticeController?action=noticeListForm">공지사항</a>
                      <a href="${CONTEXT_PATH}/notice/noticeController?action=qNoticeForm">질문과 답변</a>
                   </div>
                </li>
                <li class="dropdown">
                   <a data-toggle="dropdown" class="dropdown-toggle" href="#">스토어 <b class="caret"></b></a>
                   <div class="dropdown-menu">
                      <a href="${CONTEXT_PATH}/product/productController?action=storeHome">스토어 홈</a>
                      <a href="${CONTEXT_PATH}/product/productController?action=productListByCategoryForm">카테고리</a>
                      <a href="${CONTEXT_PATH}/product/productController?action=productListByBestForm">베스트</a>
                   </div>
                </li>
                <c:if test="${sessionScope.dto.grade == '관리자' }">
				<li class="dropdown">
                   <a data-toggle="dropdown" class="dropdown-toggle" href="#">회원관리 <b class="caret"></b></a>
                      <div class="dropdown-menu">
                      <a href="${CONTEXT_PATH}/member/memberController?action=memberList">전체 회원목록</a>
                      <a href="${CONTEXT_PATH}/member/memberController?action=adminMyInfo">내정보 변경</a>
                   </div>
                </li>
                </c:if>
            </ul>
        </div>
      </div>
     </div>
   </div>
  
</body>
</html>