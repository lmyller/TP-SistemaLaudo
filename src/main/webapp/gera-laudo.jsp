<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="autenticacao-medico-residente.jsp"%>
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
		<div class="content">
			<h2 class="title">Exame: ${requestScope.exame.tipoExame.tipoExame}</h2>
			<h2 class="title">paciente: ${requestScope.exame.paciente.nome}</h2>
			<form action="controladora" method="post">
				<input type="hidden" name="cpf" value="${requestScope.exame.paciente.cpf}">
				<input type="hidden" name="tipo" value="${requestScope.exame.tipoExame.tipoExame}">
				<div class="mb-3">
					<label for="text-area-descricao" class="form-label">Descricao</label>
					<textarea class="form-control" id="text-area-descricao"  name="descricao"
						rows="4"></textarea>
				</div>
				<label for="conclusao">Conclusão</label> <select class="form-select"
					aria-label="Default select example" name="conclusao" id="conclusao">
					<option value="I46">Parada cardíaca</option>
					<option value="I47">Taquicardia paroxística</option>
					<option value="I48">Flutter e fibrilacao atrial</option>
					<option value="I49">Outras arritmias cardíacas</option>
					<option value="I42">Cardiomiopatias</option>
				</select>
				<input type="text" hidden="true" name="logica" value="GeraLaudo">
				<input class="btn btn-secondary btn-margin" type="submit" value="Gerar">
			</form>
		</div>
	</div>
</body>
</html>