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
			<form action="controladora" method="post">
				<h2 class="title">Cadastrar Médico</h2>
				<div class="mb-3">
					<input class="form-control" placeholder="CRM" type="text"
						name="crm" required>
				</div>
				<div class="mb-3">
					<input class="form-control" placeholder="Nome" type="text"
						name="nome" required>
				</div>
				<div class="mb-3">
					<label class="mb-3 mr-1" for="tipo">Médico: </label> 
		
					<input class="btn-check" type="radio" name="tipo" id="medico"
						value="medico" autocomplete="off" checked required> <label
						class="btn btn-sm btn-outline-secondary" for="medico">Médico</label>
					<input class="btn-check" type="radio" name="tipo"
						id="medico-docente" value="medico-docente" required> <label
						class="btn btn-sm btn-outline-secondary" for="medico-docente">Médico
						Docente</label> 
					<input class="btn-check" type="radio" name="tipo"
						id="medico-residente" value="medico-residente" required> <label
						class="btn btn-sm btn-outline-secondary" for="medico-residente">Médico
						Residente</label>
				</div>
				<div class="mb-3">
					<select class="form-select mt-3"
						aria-label="Default select example" name="titulacao"
						id="titulacao">
						<option selected disabled>Selecione a titulação</option>
						<option value="doutor">Doutor</option>
						<option value="assistente">Assistente</option>
						<option value="livre-docente">Livre-Docente</option>
						<option value="titular">Titular</option>
					</select>
				</div>
				<div class="mb-3">
					<input min="2010" value="2023" type="number" id="ano-inicio"
						class="form-control" name="ano-inicio" /> <label
						class="form-label" for="typeNumber">Ano início residência
						(caso seja residente)</label>
				</div>
				<input type="text" hidden="true" name="logica"
					value="AdicionaMedico"> <input class="btn btn-secondary"
					type="submit" value="Salvar">
			</form>
		</div>
	</div>
</body>
</html>
