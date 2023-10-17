package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.LaudoDAO;

public class PesquisaPaciente implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		
		LaudoDAO dao = new LaudoDAO();
		
		if(cpf.isBlank())
			return "pesquisa-paciente.jsp";
		
		req.setAttribute("cpf", cpf);
		
		try {
			if (dao.listaLaudoPaciente(cpf).size() > 0) 
				return "lista-laudo.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "laudo-inexistente.jsp";
	}
}
