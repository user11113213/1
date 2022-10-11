<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 수정 페이지</title>
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
	<link
		href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo&family=Stylish&display=swap"
		rel="stylesheet">
	<div class="app-container">
		<div class="left-area">
			<div class="app-name">메뉴</div>
			<br> <a href="member/Master.jsp" class="item-link active"
				id="pageLink"> <svg xmlns="http://www.w3.org/2000/svg"
					width="24" height="24" fill="none" stroke="currentColor"
					stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
					class="feather feather-grid" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2"
						d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
      </svg>
			</a> <a href="member/Form.jsp" class="item-link" id="pageLink"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="none" stroke="currentColor" stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2"
					class="feather feather-folder" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2"
						d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"></path>
      </svg>
			</a> <a href="member/Form2.jsp" class="item-link" id="pageLink"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="none" stroke="currentColor" stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2"
					class="feather feather-folder" viewBox="0 0 24 24">
		<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
						d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
	  </svg>
			</a> <a href="Index_Airplane.jsp" class="item-link" id="pageLink"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="none" stroke="currentColor" stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2"
					class="feather feather-settings" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path>
      </svg>
			</a>
			<button class="btn-logout">
				<a href="adminLogout.do" class="item-link" id="pageLink"> <svg
						xmlns="http://www.w3.org/2000/svg" width="24" height="24"
						fill="none" stroke="currentColor" stroke-linecap="round"
						stroke-linejoin="round" stroke-width="2"
						class="feather feather-log-out" viewBox="0 0 24 24">
        <defs />
        <path
							d="M9 21H5a2 2 0 01-2-2V5a2 2 0 012-2h4M16 17l5-5-5-5M21 12H9" />
      </svg>
				</a>
			</button>
		</div>
		<div class="main-area">
    <section class="content-section">
    	<h1>게시판 수정</h1>
    	<form action="updateBoardAdmin.do" method="post" enctype="multipart/form-data" name="frm">
    	<table >
    		<tr>
				<td style="width:310px">
					<c:choose>
						<c:when test="${empty board.pictureurl}">
							<img src="image/noimage.jpg">
						</c:when>
						<c:otherwise>
							<img src="upload/${board.pictureurl}">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
    		<tr>
   				<td>번호</td>
   				<td>${board.code}</td>
    		</tr>
    		<tr>
    			<td>글제목</td>
    			<td><input type="text" name="title" value="${board.title}"></td>
    		</tr>
    		<tr>
    			<td>작성자</td>
    			<td><input type="text" name="name" value="${board.name}"></td>
    		</tr>
    		<tr>
    			<td>첨부파일</td>
    			<td>
					<input type="file" name="pictureurl" value="${board.pictureurl }">
				</td>
    		</tr>
    		<tr>
    			<td>내용</td>
    			<td>
    				<textarea cols="40" rows="5" name="description">${board.description}</textarea>
    			</td>
    		</tr>
    		<tr>
				<th>등록 일자</th>
				<td><input type="text" name="reg_date" value="${board.reg_date}"></td>
			</tr>
    	</table>	
    		<input type="hidden" name="code" value="${board.code}"> 
    		<input type="submit" value="수정">
			<input type="button" value="취소" onclick="location.href='adminBoardList.do'">
		</form>
    </section>
  </div>
  
</body>
</html>