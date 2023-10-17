package lmv.daw.logica;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.ExameDAO;
import lmv.daw.dao.LaudoDAO;
import lmv.daw.dao.PacienteDAO;
import lmv.daw.enums.StatusExame;
import lmv.daw.enums.StatusLaudo;
import lmv.daw.enums.TipoExame;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Laudo;
import lmv.daw.modelo.Paciente;

public class GeraLaudo implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		String descricao = req.getParameter("descricao");
		String conclusao = req.getParameter("conclusao");
		String tipoExame = req.getParameter("tipo");
		
		Laudo laudo = new Laudo();
		ExameDAO exameDAO = new ExameDAO();
		Exame exame = new Exame();
		
		try {
			exame.setPaciente(obterPaciente(cpf));
			exame.setTipoExame(TipoExame.obterTipoExame(tipoExame));
			
			exame = exameDAO.recuperaExamePaciente(exame);
			LaudoDAO laudoDAO = new LaudoDAO();
			
			if (exame != null) {
				laudo.setExame(exame);
				laudo.setDescricao(descricao);
				laudo.setConclusao(conclusao);
				laudo.setStatusLaudo(StatusLaudo.PROVISORIO);
				
				laudoDAO.adiciona(laudo);
			}
			
			exame.setStatusExame(StatusExame.LAUDO_REALIZADO);
			
			exameDAO.altera(exame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "lista-exame-aguardando-laudo.jsp";
	}

	private Paciente obterPaciente(String cpf) throws SQLException{
		Paciente paciente = new Paciente();
		PacienteDAO dao = new PacienteDAO();
		
		paciente.setCpf(cpf);
		
		return dao.recupera(paciente);
	}
	
}
