<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<style>
	* {
  box-sizing: border-box;	/*contents 들어갈 부분 줄어도 괜찮으니까, padding을 추가해도 box크기는 키우지 않는다*/
}

html {
  scroll-behavior: smooth;	/*스크롤 넘어갈때 부드럽게 넘어감*/
}

html, body {
  width: 100%;
  height: 100vh;
  margin: 0;
}

body {
  font-family: "DM Sans", sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  overflow: hidden;
  overflow-x: hidden;
  background-image: linear-gradient(to top, #a3bded 0%, #6991c7 100%);
  background-position: center;
  background-size: cover;
  padding: 20px;
}

.app-container {
  position: relative;
  border-radius: 10px;
  width: 100%;
  height: 100%;
  max-width: 1100px;
  max-height: 800px;
  background: linear-gradient(180deg, #e0e9fd 0%, #e9ecf1 90%);
  box-shadow: 0 0 0 10px rgba(255, 255, 255, 0.4);
  display: flex;
  overflow: hidden;
}

.left-area {
  padding: 32px;
  flex-basis: 1 0 132px;
  background: linear-gradient(180deg, #e0e9fd 0%, #e9ecf1 90%);
  display: flex;
  flex-direction: column;
  align-items: center;
  transition: all 300ms cubic-bezier(0.19, 1, 0.56, 1);
  position: relative;
  overflow: auto;
}
.left-area.show {
  transform: translateX(0);
  opacity: 1;
}

.item-link {
  color: var(--light-font);
  margin-bottom: 32px;
  transition: 0.2s;
}

.btn-logout {
  border: none;
  background-color: transparent;
  color: var(--light-font);
  margin-top: auto;
  cursor: pointer;
  transition: 0.2s;
}

.main-area {
  flex: 1;
  height: 100%;
  overflow-y: auto;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 0 10px 10px 0;
  padding-bottom: 24px;
  position: relative;
}

.right-area {
  flex-basis: 290px;
  flex-grow: 0;
  background: linear-gradient(180deg, #e0e9fd 0%, #e9ecf1 90%);
  transition: all 300ms cubic-bezier(0.19, 1, 0.56, 1);
}

.content-section {
  display: block;
  margin-top: 32px;
  overflow-x: hidden;
  padding: 0 40px;
}

.access-links {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin: 0 -8px;
}

.access-icon {
  width: 100%;
  height: 100%;
  border-radius: 12px;
  padding: 20px;
  color: #808000;
  display: flex;
  align-items: center;
  justify-content: center;
}

.access-link-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 8px;
}

.content-section-line,
.content-part-line {
  display: flex;
  justify-content: space-between;
}

.content-part-line {
  height: 100%;
}

.content-section-line {
  margin: 0 -8px;
}

.section-part {
  flex-basis: 49%;
}

.image-wrapper {
  border-radius: 12px;
  overflow: hidden;
  width: 100%;
  height: auto;
  position: relative;
  flex-basis: 48%;
  display: flex;
}

.image-wrapper img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: 0.2s ease-in;
}

.image-wrapper:hover img {
  transform: scale(1.125);
}

.image-overlay {
  position: absolute;
  z-index: 1;
  width: 100%;
  height: 100%;
  background: linear-gradient(0deg, rgba(0, 16, 34, 0.8) 0%, rgba(240, 244, 253, 0.2) 90%);
  padding: 12px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: flex-end;
}

.video-info-text p {
  margin: 0;
}

.video-name, .video-subtext {
  color: #fff;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.video-name.medium, .video-subtext.medium {
  font-size: 14px;
  line-height: 22px;
}

.video-name.tiny, .video-subtext.tiny {
  font-size: 14px;
  line-height: 22px;
}

.video-info {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.right-area {
  padding: 24px;
  overflow: auto;
}

.btn-show-left-area,
.btn-show-right-area {
  position: absolute;
  top: 24px;
  width: 32px;
  height: 40px;
  border-radius: 4px;
  background-color: #fff;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  outline: none;
  cursor: pointer;
  display: none;
}

.btn-close-left,
.btn-close-right {
  border: none;
  background-color: transparent;
  position: absolute;
  top: 4px;
  right: 4px;
  color: var(--light-font);
  outline: none;
  cursor: pointer;
  display: none;
}

table {
  border: 3px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 0px 10px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: #73685d;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
}
td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}   

</style>
<script src="jquery-3.6.1.min.js"></script>
</head>
<body>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo&family=Stylish&display=swap" rel="stylesheet">
<div class="app-container">
  <div class="left-area">
    <div class="app-name">메뉴</div>
    <br>
    	 <a href="member/Master.jsp" class="item-link active" id="pageLink">
     	 	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="feather feather-grid" viewBox="0 0 24 24">
       	 		<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z">
       	 		</path>
     	 	</svg>
     	 </a>
   
    <button class="btn-logout">
    <a href="adminLogout.do" class="item-link" id="pageLink">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="feather feather-log-out" viewBox="0 0 24 24">
        <defs/>
        <path d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9"/>
      </svg>
    </a>
    </button>
  </div>
  <div class="main-area">
  <section class="content-section">
		<div>
    	<h1>회원 정보</h1>
    	<form action="multiDelete.do" method="post">
		<table style="width:100%; align:center" >
		    <tr align=center>
		        <th><input type="checkbox" name="chk" value="allchk" onclick="allchk(this)"></th>
		        <th>번호</th>
		        <th>이름</th>
		        <th>아이디</th>
		        <th>비밀번호</th>
		        <th>이메일</th>
		        <th>전화번호</th>
		        
		    </tr>
		    
		    <c:forEach var="member" items="${memberList}">
				<tr>
		        	<td><input type="checkbox" name="chk" value="1번"></td>
					<td>${member.code}</td>
					<td>${member.name}</td>
					<td>${member.userid}</td>
					<td>${member.pwd}</td>
					<td>${member.email}</td>
					<td>${member.phone}</td>
				    <td><a href="updateMemberAdmin.do?userid=${member.userid}">수정</a></td>
				    <td><a href="deleteMember.do?userid=${member.userid}">삭제</a></td>
				</tr>
			</c:forEach>
			<tr>
				<input type="submit" value="삭제" >
			</tr>
		</table>
		</form>
		</div>
		<div>
			<form action="searchMember.do">
				<select name="userid">
					<option ${(param.userid=="userid")?"selected":""} value="userid">아이디</option>
					<option ${(param.userid=="name")?"selected":""} value="name">이름</option>
				</select>
				<input type="text" name="name" value="${param.name}">
				<input type="submit" value="검색">
			</form>
    	</div>
    	
    </section>

</body>
</html>
<script>
function allchk(allchk)  {
	  const checkboxes 
      = document.getElementsByName('chk');
 
 checkboxes.forEach((checkbox) => {
   checkbox.checked = allchk.checked;
 })
}
</script>


