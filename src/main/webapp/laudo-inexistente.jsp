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
			<h3>Nenhum laudo do paciente de cpf nº ${requestScope.cpf}</h3>
		</div>
	</div>
</body>
</html>