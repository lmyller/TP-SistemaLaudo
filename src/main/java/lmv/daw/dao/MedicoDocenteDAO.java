package lmv.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.enums.Titulacao;
import lmv.daw.modelo.MedicoDocente;

public class MedicoDocenteDAO implements DAO<MedicoDocente>{

	private Connection connection;
	
	public MedicoDocenteDAO() {
		connection = Conexao.getConnection();
	}

	@Override
	public void adiciona(MedicoDocente medico) throws SQLException {
		String sql = "insert into medico_docente (crm, titulacao)" +
				 "values (?, ?)";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getCrm());
			statement.setString(2, medico.getTitulacao().getTitulacao());
			statement.execute();
		}
	}

	@Override
	public void altera(MedicoDocente medico) throws SQLException {
		String sql = "update medico_docente set titulacao = ?, " +
				 "where crm = ?";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getTitulacao().getTitulacao());
			statement.setString(2, medico.getCrm());
			statement.execute();
		}
	}

	@Override
	public void remove(MedicoDocente medico) throws SQLException {
		String sql = "delete from medico_docente where crm = ?";
		
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
	public MedicoDocente recupera(MedicoDocente medico) throws SQLException {
		String sql = "select * from medico_docente where crm = ?";
		MedicoDocente medicoEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, medico.getCrm());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				medicoEncontrado = new MedicoDocente();
				medicoEncontrado.setCrm(rs.getString("crm"));
				medicoEncontrado.setTitulacao(Titulacao.obterTitulacao(rs.getString("titulacao")));
			}
		}
		return medicoEncontrado;
	}

	@Override
	public List<MedicoDocente> lista() throws SQLException {
		List<MedicoDocente> medicos = new ArrayList<>();
		String sql = "select * from medico_docente";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				MedicoDocente medico = new MedicoDocente();
				medico.setCrm(rs.getString("crm"));
				medico.setTitulacao(Titulacao.obterTitulacao(rs.getString("titulacao")));
				medicos.add(medico);
			}
		}
		return medicos;
	}
}
