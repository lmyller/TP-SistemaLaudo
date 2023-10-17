<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="autenticacao-medico-docente.jsp" %>
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
		<div class="content links">
			<div class="list-group list-group-light">
				<a href="avalia-laudo.jsp" class="btn btn-outline-primary btn-margin">Avaliar Laudo</a>
				<a href="pesquisa-paciente.jsp" class="btn btn-outline-primary btn-margin">Consultar Laudo Paciente</a>
			</div>
		</div>
	</div>
</body>
</html>