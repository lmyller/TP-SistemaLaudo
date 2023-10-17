package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.LaudoDAO;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Laudo;

public class ExibeLaudo implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Laudo laudo = new Laudo();
		LaudoDAO dao = new LaudoDAO();
		Exame exame = new Exame();
		Long id = Long.parseLong(req.getParameter("id"));
		
		exame.setId(id);
		laudo.setExame(exame);
		
		try {
			req.setAttribute("laudo", dao.recupera(laudo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exibe-laudo.jsp";
	}
}
