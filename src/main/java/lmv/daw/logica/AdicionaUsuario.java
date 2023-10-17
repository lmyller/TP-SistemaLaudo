package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.UsuarioDAO;
import lmv.daw.enums.TipoUsuario;
import lmv.daw.modelo.Usuario;
import lmv.daw.validacao.Valida;

public class AdicionaUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String tipo = req.getParameter("tipo");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(Valida.obterHashSenha(senha));
		usuario.setTipoUsuario(TipoUsuario.obterTipoUsuario(tipo));

		UsuarioDAO dao = new UsuarioDAO();
		
		try {
			dao.adiciona(usuario);
		} catch (Exception e) {
		}
		
		return "menu-admin.jsp";
	}
}
