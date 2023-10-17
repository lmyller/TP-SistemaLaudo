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
	<jsp:useBean id="dao" class="lmv.daw.dao.LaudoDAO" />
	<div class="holder holder-table" >
		<div class="content content-table" style="width: 80%;">
			<h2 class="title">Exames Aguardando Laudo</h2>
			<c:choose>
				<c:when test="${dao.quantidade('Provisorio') > 0}">
					<table class="table" style="color: white;">
						<thead>
							<tr>
								<th>Nº Exame</th>
								<th>CPF Paciente</th>
								<th>Nome Paciente</th>
								<th>CRM Médico</th>
								<th>Tipo do Exame</th>
								<th>Conclusão</th>
								<th>Consulta</th>
							</tr>
						</thead>
						<c:forEach var="laudo" items="${dao.listaProvisorio()}" varStatus="id">
							<tr>
								<td>${laudo.exame.id}</td>
								<td>${laudo.exame.paciente.cpf}</td>
								<td>${laudo.exame.paciente.nome}</td>
								<td>${laudo.exame.medico.crm}</td>
								<td>${laudo.exame.tipoExame.tipoExame}</td>
								<td>${laudo.conclusao}</td>
								<td>
									<form action="controladora" method="post">
										<input type="hidden" name="id" value="${laudo.exame.id}"> <input
											type="hidden" name="logica" value="RecuperaLaudo"> <input
											type="submit" value="Consultar laudo" class="btn btn-secondary">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
					</c:when>
				<c:otherwise>
					<h3>Nenhum exame com laudo provisório!</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>