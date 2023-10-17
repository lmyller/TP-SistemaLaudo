<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<c:import url="logout.jsp"/>
	<jsp:useBean id="dao" class="lmv.daw.dao.LaudoDAO" />
	<div class="holder holder-table" >
		<div class="content content-table">
			<h2 class="title">Exames Com Laudo Definitivo</h2>
			<c:choose>
				<c:when test="${dao.quantidade('Definitivo') > 0}">
					<table class="table">
						<thead>
							<tr>
								<th>Nº Exame</th>
								<th>CRM Médico</th>
								<th>Tipo do Exame</th>
								<th>Conclusão</th>
								<th>Data Realização</th>
								<th>Hora Realização</th>
								<th>Consulta</th>
							</tr>
						</thead>
						<c:forEach var="laudo" items="${dao.listaLaudoPaciente(requestScope.cpf)}" varStatus="id">
							<tr>
								<td>${laudo.exame.id}</td>
								<td>${laudo.exame.medico.crm}</td>
								<td>${laudo.exame.tipoExame}</td>
								<td>${laudo.conclusao}</td>
								<fmt:parseDate value="${laudo.exame.data}" type="date" pattern="yyyy-MM-dd" var="data"/>
								<td><fmt:formatDate value="${data}" pattern="dd/MM/yyyy"/></td>
								<td>${laudo.exame.hora}</td>
								<td>
									<form action="controladora" method="post">
										<input type="hidden" name="id" value="${laudo.exame.id}"> 
										<input type="hidden" name="logica" value="ExibeLaudo"> 
										<input type="submit" class="btn btn-secondary" value="Consultar laudo">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
					</c:when>
				<c:otherwise>
					<h3>Nenhum exame com laudo definitivo</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>