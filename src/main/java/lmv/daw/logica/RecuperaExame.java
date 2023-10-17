package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.ExameDAO;
import lmv.daw.modelo.Exame;

public class RecuperaExame implements Logica{
	
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		ExameDAO dao = new ExameDAO();
		Exame exame = new Exame();
		
		exame.setId(id);
		
		try {
			req.setAttribute("exame", dao.recupera(exame));
			
			return "gera-laudo.jsp";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
