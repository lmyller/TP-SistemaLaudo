package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.LaudoDAO;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Laudo;

public class RecuperaLaudo implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.valueOf(req.getParameter("id"));
		LaudoDAO dao = new LaudoDAO();
		Laudo laudo = new Laudo();
		Exame exame = new Exame();
		
		exame.setId(id);
		laudo.setExame(exame);
		
		try {
			req.setAttribute("laudo", dao.recupera(laudo));
			
			return "revisa-laudo.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
