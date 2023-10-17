package lmv.daw.logica;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.PacienteDAO;
import lmv.daw.modelo.Paciente;
import lmv.daw.validacao.Valida;

public class AdicionaPaciente implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PacienteDAO dao = new PacienteDAO();
		Paciente paciente = new Paciente();
		
		String cpf = req.getParameter("cpf");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String sexo = req.getParameter("sexo");
		String dataNascimento = req.getParameter("datanascimento");
		
		if (!Valida.validaCpf(cpf))
			return "adiciona-paciente.jsp";
		
		paciente.setCpf(cpf);
		paciente.setNome(nome);
		paciente.setEmail(email);
		paciente.setSexo(sexo.charAt(0));
		paciente.setDataNascimento(LocalDate.parse(dataNascimento));
		paciente.setIdade(calculaIdade(LocalDate.parse(dataNascimento)));
		
		try {
			dao.adiciona(paciente);
		} catch (Exception e) {
		}
		
		return "menu-admin.jsp";
	}
	
	private short calculaIdade(LocalDate data) {
		return (short) Period.between(data, LocalDate.now()).getYears();
	}
}
