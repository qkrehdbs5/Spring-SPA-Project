<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='css/guestbook.css'>
<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src='js/guestbook.js'></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>guestbook/guestbook_select.jsp</title>
</head>
<body>
	<div class='insert'>
				<!-- 정적삽입 -->
		 <%@include file ="guestbook_insert.jsp" %>
	
	</div>
	<form name='frm'class='frm' method='post'>
		
			<input name='findStr' type='text' value='${gVo.findStr }'/>
			<input   value='검색'   type='button' class='btnSearch'/> 
			<input type='text' name='nowPage' value='${gVo.nowPage }'/>
			 
			
	</form>
	
		<div class='guestbook_btn'>
		<c:if test="${gVo.startNo>1 }">
	<input type='button' value='&lt;' class='btnPrev'>
		</c:if>
		<c:if test="${gVo.totSize>gVo.endNo }">
	<input type='button' value='&gt;' class='btnNext'>
		</c:if>
	</div>
<div id ='guestbook_list'>
	<div class='guestbook_items'>
		<c:forEach var='vo' items='${list}'>
			<div class='item'>
		
		<form name='frm_guestbook' class='frm_guestbook' method="post">
		
		<div class='title'></div>
		<div class="card text-bg-dark mb-3" style="max-width: 40rem;">
		<nav>
			<div class="btnZone">
				<input type="button" class='btnUpdate' value='수정' onclick='gb.modifyView(this.form)'/>
				<input type="button" class='btnDelete' value='x'
					onclick='gb.modalView(this.form)' />
			</div>
			<div class="card-header">
			<label>작성자</label>
			<input type='text'   value='${vo.id }'/>
			<label>작성일</label>
			<input type="date" value='${vo.nal }'/>
			</div>	
		</nav>
	<div class="card-body">
	<textarea class="card-text" rows="7" name='doc' readonly>${vo.doc }</textarea>
		<div class='item'>			
			<div class='updateZone'>
			<label>암호</label>
			<input type="password" name='pwd' />
			<input type="button" value='수정' class='btnGuestbookUpdate'
			onclick ='gb.update(this.form)'/>
			<input type="button" value='취소' onclick='gb.modifyCancel(this.form)'/>
			</div>
			</div>
		</div>
		</div>
			<input type='hidden' name='sno' value='${vo.sno }'/>	
			</form>
	</div>
	</c:forEach>
	</div>
</div>

<div id='modal'>
	<div id ='content'>
		<input type='button' id='btnClose' value='x'/>
		<span>암호를 입력하세요</span>
		<input type='password' id ='pwd'/>
		<input type='button' value='확인' id='btnCheck'/>
	</div>

</div>

</body>
</html>