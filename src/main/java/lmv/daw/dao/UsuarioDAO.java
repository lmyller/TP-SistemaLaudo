package lmv.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.enums.TipoUsuario;
import lmv.daw.modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario>{
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection = Conexao.getConnection();
	}
	
	@Override
	public void adiciona(Usuario usuario) throws SQLException {
		String sql = "insert into usuario (login, senha, tipo)" +
				 "values (?, ?, ?)";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getTipoUsuario().getTipoUsuario());
			statement.execute();
		}
	}

	@Override
	public void altera(Usuario usuario) throws SQLException {
		String sql = "update usuario set login = ? where senha = ?";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.execute();
		}
	}

	@Override
	public void remove(Usuario usuario) throws SQLException {
		String sql = "delete from usuario where login=?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, usuario.getLogin());
			statement.execute();
		}
	}

	@Override
	public int quantidade(String status) throws SQLException {
		return 0;
	}
	
	@Override
	public Usuario recupera(Usuario usuario) throws SQLException {
		String sql = "select * from usuario where login=? and senha=?";
		Usuario usuarioEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setLogin(rs.getString("login"));
				usuarioEncontrado.setSenha(rs.getString("senha"));
				usuarioEncontrado.setTipoUsuario(TipoUsuario.obterTipoUsuario(rs.getString("tipo")));
			}
		}
		return usuarioEncontrado;
	}

	@Override
	public List<Usuario> lista() throws SQLException {
		return null;
	}
}
