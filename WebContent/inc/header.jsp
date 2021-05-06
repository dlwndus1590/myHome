<%@page import="com.myHome.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
	<style>
	:root {
  --header-height: 3.5rem;
  --nav-width: 219px;

  /*========== Colors ==========*/
  --first-color: #6923D0;
  --first-color-light: #F4F0FA;
  --title-color: #19181B;
  --text-color: #58555E;
  --text-color-light: #A5A1AA;
  --body-color: #F9F6FD;
  --container-color: #FFFFFF;

  /*========== Font and typography ==========*/
  --body-font: 'Poppins', sans-serif;
  --normal-font-size: .938rem;
  --small-font-size: .75rem;
  --smaller-font-size: .75rem;

  /*========== Font weight ==========*/
  --font-medium: 500;
  --font-semi-bold: 600;

  /*========== z index ==========*/
  --z-fixed: 100;
}

@media screen and (min-width: 1024px) {
  :root {
    --normal-font-size: 1rem;
    --small-font-size: .875rem;
    --smaller-font-size: .813rem;
  }
}
*, ::before, ::after {
  box-sizing: border-box;
}
.navi {
  margin: var(--header-height) 0 0 0;
  padding: 1rem 1rem 0;
  font-family: var(--body-font);
  font-size: var(--normal-font-size);
  background-color: var(--body-color);
  color: var(--text-color);
}
.nav__subtitle {
  margin: 0;
}

.nav__linknav__logo, .nav__linkactive,.nav__link,.nav__dropdown-item,.nav__linknav__logout{
  text-decoration: none;
}

.navi {
  position: fixed;
  top: 0;
  left: -100%;
  height: 100vh;
  padding: 1rem 1rem 0;
  background-color: var(--container-color);
  box-shadow: 1px 0 0 rgba(22, 8, 43, 0.1);
  z-index: var(--z-fixed);
  transition: .4s;
}

.nav__container {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 3rem;
  overflow: auto;
  scrollbar-width: none; /* For mozilla */
}

.nav__container::-webkit-scrollbar {
  display: none;
}

.nav__logo {
  font-weight: var(--font-semi-bold);
  margin-bottom: 2.5rem;
}

.nav__list, 
.nav__items {
  display: grid;
}

.nav__list {
  row-gap: 2.5rem;
}

.nav__items {
  row-gap: 1.5rem;
}

.nav__subtitle {
  font-size: var(--normal-font-size);
  text-transform: uppercase;
  letter-spacing: .1rem;
  color: var(--text-color-light);
}

.nav__link {
  display: flex;
  align-items: center;
  color: var(--text-color);
}

.nav__link:hover {
  color: var(--first-color);
}

.nav__icon {
  font-size: 1.2rem;
  margin-right: .5rem;
}

.nav__name {
  font-size: var(--small-font-size);
  font-weight: var(--font-medium);
  white-space: nowrap;
}

.nav__logout {
  margin-top: 5rem;
}

/* Dropdown */
.nav__dropdown {
  overflow: hidden;
  max-height: 21px;
  transition: .4s ease-in-out;
}

.nav__dropdown-collapse {
  background-color: var(--first-color-light);
  border-radius: .25rem;
  margin-top: 1rem;
}

.nav__dropdown-content {
  display: grid;
  row-gap: .5rem;
  padding: .75rem 2.5rem .75rem 1.8rem;
}

.nav__dropdown-item {
  font-size: var(--smaller-font-size);
  font-weight: var(--font-medium);
  color: var(--text-color);
}

.nav__dropdown-item:hover {
  color: var(--first-color);
}

.nav__dropdown-icon {
  margin-left: auto;
  transition: .4s;
}

/* Show dropdown collapse */
.nav__dropdown:hover {
  max-height: 100rem;
}

/* Rotate icon arrow */
.nav__dropdown:hover .nav__dropdown-icon {
  transform: rotate(180deg);
}

  .navi {
    left: 0;
    padding: 1.2rem 1.5rem 0;
    width: 68px; /* Reduced navbar */
  }
  .nav__items {
    row-gap: 1.7rem;
  }
  .nav__icon {
    font-size: 1.3rem;
  }
  
    .nav__name, 
  .nav__subtitle, 
  .nav__dropdown-icon {
    opacity: 0;
    transition: .3s;
  }
  
    .navi:hover {
    width: var(--nav-width);
  }
  
    /* Visible elements */
  .navi:hover .nav__logo-name {
    opacity: 1;
  }
  .navi:hover .nav__subtitle {
    opacity: 1;
  }
  .navi:hover .nav__name {
    opacity: 1;
  }
  .navi:hover .nav__dropdown-icon {
    opacity: 1;
  }
	</style>
    
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

                                <div class="nav__dropdown-collapse">
                                    <div class="nav__dropdown-content">
                                        <a href="${CONTEXT_PATH}/interior/interiorController?action=interiorList" class="nav__dropdown-item">Interior store</a> 
                                    </div>
                                </div>
                                
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
					
						
               <!-- 로그인 / 미 로그인 상태 변화 -->
               <%
                  Member mainMember = null;
                  if(session.getAttribute("dto")==null){
               %>   
                  <a href="${CONTEXT_PATH}/member/login.jsp"><span class="icon-lock"></span> Login </a>                            
               <%
                  } else {
                     mainMember = (Member)session.getAttribute("dto");                     
               %>
                  <a href="${CONTEXT_PATH}/member/memberController?action=logout"><span class="icon-lock"></span> Logout </a>                  
               <%
                     if(mainMember.getGrade().equals("일반회원")){%>
                        <a href="${CONTEXT_PATH}/member/memberController?action=memberMyPage"><span class="icon-user"></span> 마이페이지</a>
               <%
                     } else if(mainMember.getGrade().equals("판매자")){%>
                        <a href="${CONTEXT_PATH}/member/memberController?action=sellerMyPage"><span class="icon-user"></span> 마이페이지</a>                   
               <%
                     } else if(mainMember.getGrade().equals("관리자")){%>
                        <a href="${CONTEXT_PATH}/member/memberController?action=adminMyPage"><span class="icon-user"></span> 마이페이지</a>
               <%
                     }
                  }
               %>   

               <!-- 회원가입 요청 서비스 -->   
               <%                  
                  if(session.getAttribute("memberId")==null){
               %>   
                  <a href="${CONTEXT_PATH}/member/loginChoice.jsp"><span class="icon-edit"></span> 회원가입 </a> 
               <%
                  } else {                     
               %>
                  <a href="${CONTEXT_PATH}/member/loginChoice.jsp"></a>
               <%
                  }
               %>
               
               
               <a href="${CONTEXT_PATH}/member/ordersController?action=cartPage"><span class="icon-shopping-cart"></span> 장바구니</a>

               
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
      <img src="${CONTEXT_PATH}/assets/img/logo.png">
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
            </ul>
         <form action="#" class="navbar-search pull-left" style="float:right">
           <input type="text" placeholder="Search" class="search-query span2">
         </form>
        </div>
      </div>
     </div>
   </div>
  
</body>
</html>