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
		<div class="content">
			<h2 class="title">Exame: ${requestScope.laudo.exame.tipoExame.tipoExame}</h2>
			<h2 class="title">paciente: ${requestScope.laudo.exame.paciente.nome}</h2>
			<form action="controladora" method="post">
				<div class="mb-3">
					<textarea class="form-control" id="text-area-descricao"  name="descricao"
						rows="4">${requestScope.laudo.descricao}</textarea>
				</div>
				<label for="conclusao">Conclusão</label> <select class="form-select"
					aria-label="Default select example" name="conclusao" id="conclusao">
					<option value="I46" ${requestScope.laudo.conclusao == 'I46' ? "selected" : ""}>Parada cardíaca</option>
					<option value="I47" ${requestScope.laudo.conclusao == 'I47' ? "selected" : ""}>Taquicardia paroxística</option>
					<option value="I48" ${requestScope.laudo.conclusao == 'I48' ? "selected" : ""}>Flutter e fibrilacao atrial</option>
					<option value="I49" ${requestScope.laudo.conclusao == 'I49' ? "selected" : ""}>Outras arritmias cardíacas</option>
					<option value="I42" ${requestScope.laudo.conclusao == 'I42' ? "selected" : ""}>Cardiomiopatias</option>
				</select>
				<input type="text" hidden="true" name="id" value="${laudo.exame.id}">
				<input type="text" hidden="true" name="logica" value="AprovarLaudo">
				<input class="btn btn-secondary btn-margin" type="submit" value="Aprovar">
			</form>
		</div>
	</div>
</body>
</html>