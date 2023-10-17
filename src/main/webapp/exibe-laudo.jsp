<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="autenticacao-todos-medicos.jsp" %>
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
	<fmt:parseDate value="${requestScope.laudo.exame.paciente.dataNascimento}" type="date" pattern="yyyy-MM-dd" var="dataNascimento"/>
	<fmt:parseDate value="${requestScope.laudo.exame.data}" type="date" pattern="yyyy-MM-dd" var="dataExame"/>
	<c:import url="logout.jsp"/>
	<div class="holder" >
		<div class="content content-show">
			<h2 class="title">Paciente</h2>
			<div class="line">
				<span>CPF: ${requestScope.laudo.exame.paciente.cpf}</span>
				<span>Nome: ${requestScope.laudo.exame.paciente.nome}</span>
				<span>Sexo: ${requestScope.laudo.exame.paciente.sexo}</span>
			</div>
			<div class="line">
				<span>Data de Nascimento: <fmt:formatDate value="${dataNascimento}" pattern="dd/MM/yyyy"/></span>
				<span>Idade: ${requestScope.laudo.exame.paciente.idade}</span>
			</div>
			<hr>
			<h2 class="title title-show-laudo">Exame</h2>
			<div class="line">
				<span>Exame: ${requestScope.laudo.exame.tipoExame.tipoExame}</span>
			</div>
			<div class="line">
				<span>Data do exame: <fmt:formatDate value="${dataExame}" pattern="dd/MM/yyyy"/></span>
				<span>Hora do exame: ${requestScope.laudo.exame.hora}</span>
			</div>
			<hr>
			<h2 class="title title-show-laudo">Laudo</h2>
			<div class="line">
				<div>
					<p><label for="descricao">Descrição do laudo:</label></p>
					<textarea class="form-control" id="descricao" rows="4" cols="80">${requestScope.laudo.descricao}</textarea>
				</div>
			</div>
			<p style="text-align: left; margin-top: 20px;">Conclusão: ${requestScope.laudo.conclusao}</p>
		</div>
	</div>
</body>
</html>