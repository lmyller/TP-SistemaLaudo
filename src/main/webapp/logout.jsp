<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<div class="header">
		<div class="current-user">
			<h4>Usuário: ${sessionScope.usuario}</h4>
		</div>
		<div class="logout">
			<form action="controladora" method="post">
				<input type="hidden" name="logica" value="LogOut">
				<input type="submit" class="btn btn-secondary btn-logout" value="Sair">
			</form>
		</div>
	</div>
</body>
</html>