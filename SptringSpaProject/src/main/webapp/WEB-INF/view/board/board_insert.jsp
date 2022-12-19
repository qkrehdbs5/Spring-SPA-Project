<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPA Mysql</title>
<link rel='stylesheet' href='css/board.css'>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script defer src='js/board.js'></script>
</head>
<body>
<div id='board'>
    <h2>게시판</h2>
    
    <form class='frm frm_insert' method='post' enctype='multipart/form-data'>
        <label>작성자</label>
        <input type='text' name='id' value='hong'/><br/>
       
        <label>제목</label>
        <input type='text' name='subject' class='subject' value='제목'/><br/>
        <label></label>
        <textarea rows="5" cols="50" name='doc' class='doc'>내용입니다.</textarea><br/>
  		<input class="form-control form-control-sm" id="formFileSm" type="file" name='attFile' multiple="multiple">
		
        <div class='btnZone'>
            <input type='button' value='취소' class='btnSelect'>
            <input type='button' value='저장' class='btnInsertR'>
            <input type='hidden' name='nowPage' value='${pVo.nowPage }'>
            <input type='hidden' name='findStr' value='${pVo.findStr }'>
      
    	</div>
    </form>
</div>
</body>
</html>
