<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="autenticacao-admin.jsp"%>
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
	<c:import url="logout.jsp"/>
	<div class="holder">
		<div class="content add">
			<h2 class="title">Cadastrar Usuário</h2>
			<form action="controladora" method="post">
				<div class="mb-3">
					<input class="form-control" placeholder="Usuário" type="text" name="login" required>
				</div>
				<div class="mb-3">
					<input class="form-control" placeholder="Senha "type="password" name="senha" required>
				</div>
				<input type="hidden" name="tipo" value="${requestScope.tipo}">
				<input type="text" hidden="true" name="logica"
					value="AdicionaUsuario"> <input class="btn btn-secondary btn-margin"
					type="submit" value="Salvar">
			</form>
		</div>
	</div>
</body>
</html>