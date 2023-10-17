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
			<h2 class="title">Cadastrar Paciente</h2>
			<form action="controladora" method="post">
				<div class="mb-3">
					<input class="form-control" placeholder="CPF" type="text" name="cpf" required>
				</div>
				<div class="mb-3">
					<input class="form-control" placeholder="Nome" type="text" name="nome" required>
				</div>
				<div class="mb-3">
					<input class="form-control" placeholder="Email" type="text" name="email" required>
				</div>
				<label class="mb-3 mr-1" for="sexo">Sexo: </label>
				<input class="btn-check" type="radio" name="sexo"
					id="masculino" value="M" checked required> 
				<label class="btn btn-sm btn-outline-secondary" for="masculino">Masculino</label>
				<input class="btn-check" type="radio" name="sexo"
					id="feminino" value="F" required> 
				<label class="btn btn-sm btn-outline-secondary" for="feminino">Feminino</label>
				<div class="mb-3">
					<label class="form-label">Data de Nascimento</label> 
					<input class="form-control" type="date" name="datanascimento" required>
				</div>
				<input type="text" hidden="true" name="logica"
					value="AdicionaPaciente"> <input class="btn btn-secondary"
					type="submit" value="Salvar">
			</form>
		</div>
	</div>
</body>
</html>