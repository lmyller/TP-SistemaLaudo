package lmv.daw.logica;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.ExameDAO;
import lmv.daw.dao.MedicoDAO;
import lmv.daw.dao.PacienteDAO;
import lmv.daw.email.Email;
import lmv.daw.enums.StatusExame;
import lmv.daw.enums.TipoExame;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Medico;
import lmv.daw.modelo.Paciente;

public class AdicionaExame implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String crm = req.getParameter("crm");
		String cpf = req.getParameter("cpf");
		String tipoExame = req.getParameter("tipo");
		String recomendacao = req.getParameter("recomendacao");
		
		ExameDAO dao = new ExameDAO();
		Exame exame = new Exame();
		
		exame.setMedico(obterMedico(crm));
		exame.setPaciente(obterPaciente(cpf));
		exame.setTipoExame(TipoExame.obterTipoExame(tipoExame));
		exame.setRecomendacao(recomendacao);
		exame.setStatusExame(StatusExame.AGUARDANDO_EXAME);
		exame.setData(LocalDate.now().plusDays(3));
		
		try {
			if (dao.buscaExamePaciente(exame))
				return "erro-cadastro-exame.jsp";
			
			dao.adiciona(exame);
			Email.criarEmail(exame, "Confirmação do exame", "Confirmado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "menu-medico.jsp";
	}

	private Medico obterMedico(String crm) {
		MedicoDAO dao = new MedicoDAO();
		Medico medico = new Medico();
		
		medico.setCrm(crm);
		
		try {
			return dao.recupera(medico);
		} catch (Exception e) {
		}
		return null;
	}
	
	private Paciente obterPaciente(String cpf) {
		PacienteDAO dao = new PacienteDAO();
		Paciente paciente = new Paciente();
		
		paciente.setCpf(cpf);
		
		try {
			return dao.recupera(paciente);
		} catch (Exception e) {
		}
		
		return null;
	}
}
