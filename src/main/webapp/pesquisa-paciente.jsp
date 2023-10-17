<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="autenticacao-todos-medicos.jsp"%>   
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
	<jsp:useBean id="pacienteDAO" class="lmv.daw.dao.PacienteDAO" />
	<div class="holder">
		<div class="content">
			<h2 class="title">Pesquisa Paciente</h2>
			<form action="controladora" method="post">
				<select class="form-select" aria-label="Default select example" name="cpf" id="paciente">
					<option value="">Escolha o paciente</option>
					<c:forEach var="paciente" items="${pacienteDAO.lista()}">
						<option value="${paciente.cpf}">${paciente.nome}</option>
					</c:forEach>
				</select>
				<input type="text" hidden="true" name="logica" value="PesquisaPaciente"> 
				<input class="btn btn-secondary btn-margin" type="submit" value="Pesquisar">
			</form>
		</div>
	</div>
</body>
</html>