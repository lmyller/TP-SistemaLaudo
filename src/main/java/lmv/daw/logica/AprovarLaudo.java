package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.LaudoDAO;
import lmv.daw.enums.StatusLaudo;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Laudo;

public class AprovarLaudo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String descricao = req.getParameter("descricao");
		String conclusao = req.getParameter("conclusao");
		
		LaudoDAO dao = new LaudoDAO();
		Laudo laudo = new Laudo();
		Exame exame = new Exame();
		
		exame.setId(id);
		laudo.setExame(exame);
		laudo.setDescricao(descricao);
		laudo.setConclusao(conclusao);
		laudo.setStatusLaudo(StatusLaudo.DEFINITIVO);
		
		try {
			dao.altera(laudo);
			
			return "menu-medico-docente.jsp";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	
}
