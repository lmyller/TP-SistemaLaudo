package lmv.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.enums.StatusLaudo;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Laudo;

public class LaudoDAO implements DAO<Laudo> {

	private Connection connection;
	
	public LaudoDAO() {
		connection = Conexao.getConnection();
	}
	
	@Override
	public void adiciona(Laudo laudo) throws SQLException {
		String sql = "insert into laudo (exame_id, descricao, conclusao, status)" +
				 "values (?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setLong(1, laudo.getExame().getId());
			statement.setString(2, laudo.getDescricao());
			statement.setString(3, laudo.getConclusao());
			statement.setString(4, laudo.getStatusLaudo().getStatusLaudo());
			statement.execute();
		}
	}

	@Override
	public void altera(Laudo laudo) throws SQLException {
		String sql = "update laudo set descricao = ?, conclusao = ?, status = ? where exame_id = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, laudo.getDescricao());
			statement.setString(2, laudo.getConclusao());
			statement.setString(3, laudo.getStatusLaudo().getStatusLaudo());
			statement.setLong(4, laudo.getExame().getId());
			statement.execute();
		}
	}

	@Override
	public void remove(Laudo laudo) throws SQLException {}

	@Override
	public int quantidade(String status) throws SQLException {
		String sql = "select count(*) as count from laudo where status=?";
		int count = 0;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, status);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
				count = rs.getInt("count");
			
			return count;
		}
	}
	
	@Override
	public Laudo recupera(Laudo laudo) throws SQLException {
		String sql = "select * from laudo where exame_id=?";
		Laudo laudoEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, laudo.getExame().getId());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				laudoEncontrado = new Laudo();
				laudoEncontrado.setExame(obterExame(rs.getLong("exame_id")));
				laudoEncontrado.setDescricao(rs.getString("descricao"));
				laudoEncontrado.setConclusao(rs.getString("conclusao"));
				laudoEncontrado.setStatusLaudo(StatusLaudo.obterStatusLaudo(rs.getString("status")));
			}
		}
		return laudoEncontrado;
	}

	@Override
	public List<Laudo> lista() throws SQLException {
		return null;
	}
	
	public List<Laudo> listaLaudoPaciente(String cpf) throws SQLException {
		List<Laudo> laudos = new ArrayList<>();
		String sql = "select * from laudo";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				if (obterExame(rs.getLong("exame_id")).getPaciente().getCpf().equalsIgnoreCase(cpf)) {
					Laudo laudo = new Laudo();
					laudo.setExame(obterExame(rs.getLong("exame_id")));
					laudo.setDescricao(rs.getString("descricao"));
					laudo.setConclusao(rs.getString("conclusao"));
					laudos.add(laudo);
				}
			}
		}
		return laudos;
	}
	
	public List<Laudo> listaProvisorio() throws SQLException {
		List<Laudo> laudos = new ArrayList<>();
		String sql = "select * from laudo where status=?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, StatusLaudo.PROVISORIO.getStatusLaudo());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Laudo laudo = new Laudo();
				laudo.setExame(obterExame(rs.getLong("exame_id")));
				laudo.setDescricao(rs.getString("descricao"));
				laudo.setConclusao(rs.getString("conclusao"));
				laudo.setStatusLaudo(StatusLaudo.obterStatusLaudo(rs.getString("status")));
				laudos.add(laudo);
			}
		}
		return laudos;
	}
	
	public List<Laudo> listaDefinitivo() throws SQLException {
		List<Laudo> laudos = new ArrayList<>();
		String sql = "select * from laudo where status=?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, StatusLaudo.DEFINITIVO.getStatusLaudo());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Laudo laudo = new Laudo();
				laudo.setExame(obterExame(rs.getLong("exame_id")));
				laudo.setDescricao(rs.getString("descricao"));
				laudo.setConclusao(rs.getString("conclusao"));
				laudo.setStatusLaudo(StatusLaudo.obterStatusLaudo(rs.getString("status")));
				laudos.add(laudo);
			}
		}
		return laudos;
	}
	
	private Exame obterExame(long id) throws SQLException{
		ExameDAO exameDAO = new ExameDAO();
		Exame exame = new Exame();
		
		exame.setId(id);
		
		exame = exameDAO.recupera(exame);
		
		return exame;
	}
}
