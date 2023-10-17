<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="autenticacao-medico.jsp"%>
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
	<jsp:useBean id="medicoDAO" class="lmv.daw.dao.MedicoDAO" />
	<jsp:useBean id="pacienteDAO" class="lmv.daw.dao.PacienteDAO" />
	<div class="holder">
		<div class="content">
			<h2 class="title">Cadastrar Exame</h2>
			<form action="controladora" method="post">
				<label for="medico">Médico</label> 
				<select class="form-select" aria-label="Default select example" name="crm" id="medico">
					<c:forEach var="medico" items="${medicoDAO.listaMedicosHospital()}">
						<option value="${medico.crm}" selected>${medico.nome}</option>
					</c:forEach>
				</select> <label for="paciente">Paciente</label> 
				<select class="form-select" aria-label="Default select example" name="cpf" id="paciente">
					<c:forEach var="paciente" items="${pacienteDAO.lista()}">
						<option value="${paciente.cpf}">${paciente.nome}</option>
					</c:forEach>
				</select>
				<div class="mb-3">
					<label for="text-area-recomendacao" class="form-label">Recomendações</label>
					<textarea class="form-control" name="recomendacao" id="text-area-recomendacao"
						rows="4"></textarea>
				</div>
				<label class="form-label">Exame</label> <br>
				<div class="form-check-inline">
					<input class="form-check-input" type="radio" name="tipo"
						id="ecocardiograma" value="ecocardiograma" checked required>
					<label class="form-check-label" for="ecocardiograma">Ecocardiograma</label>
				</div>
				<div class="form-check-inline">
					<input class="form-check-input" type="radio" name="tipo"
						id="eletrocardiograma" value="eletrocardiograma" required> <label
						class="form-check-label" for="eletrocardiograma">Eletrocardiograma</label>
				</div>
				<br/> 
				<input type="text" hidden="true" name="logica" value="AdicionaExame">
				<input class="btn btn-secondary btn-margin" type="submit" value="Salvar">
			</form>
		</div>
	</div>
</body>
</html>
