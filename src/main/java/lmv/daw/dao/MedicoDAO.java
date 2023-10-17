package lmv.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.modelo.Medico;

public class MedicoDAO implements DAO<Medico> {

private Connection connection;
	
	public MedicoDAO() {
		connection = Conexao.getConnection();
	}
	
	@Override
	public void adiciona(Medico medico) throws SQLException {
		String sql = "insert into medico (crm, nome, titular_hospital)" +
				 "values (?, ?, ?)";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getCrm());
			statement.setString(2, medico.getNome());
			statement.setBoolean(3, medico.isTitularHospital());
			statement.execute();
		}
	}

	@Override
	public void altera(Medico medico) throws SQLException {
		String sql = "update medico set nome = ? where crm = ?";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getNome());
			statement.setString(2, medico.getCrm());
			statement.execute();
		}
	}

	@Override
	public void remove(Medico medico) throws SQLException {
		String sql = "delete from medico where crm=?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getCrm());
			statement.execute();
		}
	}
	
	@Override
	public int quantidade(String status) throws SQLException {
		return 0;
	}

	@Override
	public Medico recupera(Medico medico) throws SQLException {
		String sql = "select * from medico where crm=?";
		Medico medicoEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, medico.getCrm());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				medicoEncontrado = new Medico();
				medicoEncontrado.setCrm(rs.getString("crm"));
				medicoEncontrado.setNome(rs.getString("nome"));
			}
		}
		return medicoEncontrado;
	}

	@Override
	public List<Medico> lista() throws SQLException {
		List<Medico> medicos = new ArrayList<>();
		String sql = "select * from medico";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Medico medico = new Medico();
				medico.setCrm(rs.getString("crm"));
				medico.setNome(rs.getString("nome"));
				medicos.add(medico);
			}
		}
		return medicos;
	}
	
	public List<Medico> listaMedicosHospital() throws SQLException {
		List<Medico> medicos = new ArrayList<>();
		String sql = "select * from medico where titular_hospital=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setBoolean(1, true);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Medico medico = new Medico();
				medico.setCrm(rs.getString("crm"));
				medico.setNome(rs.getString("nome"));
				medicos.add(medico);
			}
		}
		return medicos;
	}
}
