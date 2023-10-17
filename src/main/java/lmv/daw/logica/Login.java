package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lmv.daw.dao.UsuarioDAO;
import lmv.daw.enums.TipoUsuario;
import lmv.daw.modelo.Usuario;
import lmv.daw.validacao.Valida;

public class Login implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario();
		Usuario usuarioRecuperado = new Usuario();
		
		usuario.setLogin(login);
		usuario.setSenha(Valida.obterHashSenha(senha));
		
		try {
			usuarioRecuperado = dao.recupera(usuario);

			if (usuarioRecuperado != null) {
				HttpSession sessao = req.getSession();
				sessao.setMaxInactiveInterval(4 * 60);
				sessao.setAttribute("usuario", usuarioRecuperado.getLogin());
				sessao.setAttribute("status", true);
				sessao.setAttribute("tipo", usuarioRecuperado.getTipoUsuario().getTipoUsuario().toLowerCase());
				
				if (usuarioRecuperado.getTipoUsuario() == TipoUsuario.ADMIN)
					return "menu-admin.jsp";
				
				if (usuarioRecuperado.getTipoUsuario() == TipoUsuario.MEDICO)
					return "menu-medico.jsp";
				
				if (usuarioRecuperado.getTipoUsuario() == TipoUsuario.MEDICO_DOCENTE)
					return "menu-medico-docente.jsp";
				
				else 
					return "menu-medico-residente.jsp";
			}
		} catch (Exception e) {
		}
		
		return "login-invalido.jsp";
	}
}
