<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="aside">
		<h2>게시판</h2>
		<ul>
			<li><a href="${pageContext.request.contextPath}/gbc/addlist">일반 방명록</a></li>
			<li><a href="${pageContext.request.contextPath}/gbc/ajaxMain">ajax 방명록</a></li>
		</ul>
	</div>

</body>
</html>