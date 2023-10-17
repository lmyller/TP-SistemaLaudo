package lmv.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.modelo.MedicoResidente;

public class MedicoResidenteDAO implements DAO<MedicoResidente> {

private Connection connection;
	
	public MedicoResidenteDAO() {
		connection = Conexao.getConnection();
	}

	@Override
	public void adiciona(MedicoResidente medico) throws SQLException {
		String sql = "insert into medico_residente (crm, ano_inicio, matricula)" +
				 "values (?, ?, ?)";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, medico.getCrm());
			statement.setShort(2, medico.getAnoInicio());
			statement.setString(3, medico.getMatricula());
			statement.execute();
		}
	}

	@Override
	public void altera(MedicoResidente medico) throws SQLException {
		String sql = "update medico_residente set ano_inicio = ?, matricula" +
				 "where crm = ?";
	
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setShort(1, medico.getAnoInicio());
			statement.setString(2, medico.getMatricula());
			statement.setString(3, medico.getCrm());
			statement.execute();
		}
	}

	@Override
	public void remove(MedicoResidente medico) throws SQLException {
		String sql = "delete from medico_residente where crm = ?";
		
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
	public MedicoResidente recupera(MedicoResidente medico) throws SQLException {
		String sql = "select * from medico_residente where crm = ?";
		MedicoResidente medicoEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, medico.getCrm());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				medicoEncontrado = new MedicoResidente();
				medicoEncontrado.setCrm(rs.getString("crm"));
				medicoEncontrado.setAnoInicio(rs.getShort("ano_inicio"));
				medicoEncontrado.setMatricula(rs.getString("matricula"));
			}
		}
		return medicoEncontrado;
	}

	@Override
	public List<MedicoResidente> lista() throws SQLException {
		List<MedicoResidente> medicos = new ArrayList<>();
		String sql = "select * from medico_residente";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				MedicoResidente medico = new MedicoResidente();
				medico.setCrm(rs.getString("crm"));
				medico.setAnoInicio(rs.getShort("ano_inicio"));
				medico.setMatricula(rs.getString("matricula"));
				medicos.add(medico);
			}
		}
		return medicos;
	}
}
