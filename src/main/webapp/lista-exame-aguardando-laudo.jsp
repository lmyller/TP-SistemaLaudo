<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="autenticacao-medico-residente.jsp" %>
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
	<jsp:useBean id="dao" class="lmv.daw.dao.ExameDAO" />
	<div class="holder holder-table" >
		<div class="content content-table">
			<h2 class="title">Exames Aguardando Laudo</h2>
			<c:choose>
				<c:when test="${dao.quantidade('Aguardando laudo') > 0}">
					<table class="table">
						<thead>
							<tr>
								<th>Nº Exame</th>
								<th>CPF Paciente</th>
								<th>Nome Paciente</th>
								<th>Tipo do Exame</th>
								<th>Data Realização</th>
								<th>Hora Realização</th>
								<th>Status</th>
							</tr>
						</thead>
						<c:forEach var="exame" items="${dao.listaExameAguardandoLaudo()}"
							varStatus="id">
							<tr>
								<td>${exame.id}</td>
								<td>${exame.paciente.cpf}</td>
								<td>${exame.paciente.nome}</td>
								<td>${exame.tipoExame.tipoExame}</td>
								<fmt:parseDate value="${exame.data}" type="date"
									pattern="yyyy-MM-dd" var="data" />
								<td><fmt:formatDate value="${data}" pattern="dd/MM/yyyy" /></td>
								<td>${exame.hora}</td>
								<td>${exame.statusExame.status}</td>
								<td>
									<form action="controladora" method="post">
										<input type="hidden" name="id" value="${exame.id}"> 
										<input type="hidden" name="logica" value="RecuperaExame"> 
										<input type="submit" value="Gerar Laudo" class="btn btn-secondary">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h3>Nenhum exame aguardando laudo!</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>