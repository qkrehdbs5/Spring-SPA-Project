<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src='js/index.js'></script>
<title>index.jsp</title>
</head>
<body>
<div id ='main'>
	<header>
	
		<div class='title'></div>
		<nav>
			<a href="/">HOME</a>
			<a href="#" class="btnGuestBook">방명록</a>
			<a href="#" class="btnBoard">게시판</a>
		</nav>
	</header>
	<div id='section'>
		<div class='guestbook'>최신 방명록</div>
		<div class='board'>최신 게시물</div>
	</div>

</div>
</body>



<style>
#main>*{
	box-sizing: border-box;
}
#main{
	width:100%;
	min-height:400px;

	padding:30px;
	margin:30px auto;
}
#main>header{
	position :relative;
	height:120px;
	background-color:#ccc;
}
header>nav{
		
	position:absolute;
	bottom:4px;
	right:6px;
	
}
header>.title{
	color:#f00;
	font-size:1.5em;
	font-weight:bolder;
	padding:20px;
}
div#section{
  
    display:flex;
   
   padding: 20px 0px;
   
}


div.guestbook{
   display:inline-block;
   width:50%;
   padding: 10px 0px;
}

div.board{
   display:inline-block;
   width:50%;
   padding:10px 0px;
   
}

</style>
</html>