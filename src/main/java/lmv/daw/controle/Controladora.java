package lmv.daw.controle;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.logica.Logica;

@WebServlet("/controladora")
public class Controladora extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomeClasse = "lmv.daw.logica." + req.getParameter("logica");
		Class classe;
		String url = "";
		
		try {
			classe = Class.forName(nomeClasse);
			Logica logica = (Logica) classe.newInstance();
			url = logica.executa(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
