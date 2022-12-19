<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">



</head>
<body>
<div id ='main'>
	<form name='frm_guestbook_insert' class='frm_guestbook_insert' method="post">
	

 
	
	
		<div class='title'></div>
		<div class="card text-bg-dark mb-3" style="max-width: 50rem;">
		<nav class='abc'>
			<div class="card-header">
			<label>작성자</label>
			<input type='text'  name='id'/>
			<label>작성일</label>
			
			<output>
			
				<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>
			</output>
			</div>
		</nav>
	  <div class="card-body">
	<textarea rows="10" cols="80" name='doc' class="card-text"></textarea>
		<div class='item'>			
			<label>암호</label>
			<input  name='pwd' type="password"/>
			<input type="button" value='작성' class='btninsert'/>
		</div>
		</div>
	</div>	
	</form>
	
	</div>

	
</body>
</html>