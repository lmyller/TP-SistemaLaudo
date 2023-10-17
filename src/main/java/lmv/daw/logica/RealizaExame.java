package lmv.daw.logica;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.ExameDAO;
import lmv.daw.enums.StatusExame;
import lmv.daw.modelo.Exame;
import lmv.daw.pdf.Pdf;

public class RealizaExame implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		ExameDAO dao = new ExameDAO();
		Exame exame = new Exame();
		exame.setId(id);

		try {
			exame = dao.recupera(exame); 
			
			Pdf.criarPdf(exame);
			
			exame.setHora(LocalTime.now());
			exame.setStatusExame(StatusExame.AGUARDANDO_LAUDO);
			
			dao.altera(exame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "lista-exame.jsp";
	}
}
