package lmv.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.enums.StatusExame;
import lmv.daw.enums.TipoExame;
import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Medico;
import lmv.daw.modelo.MedicoResidente;
import lmv.daw.modelo.Paciente;

public class ExameDAO implements DAO<Exame> {
	
	private Connection connection;
	
	public ExameDAO() {
		connection = Conexao.getConnection();
	}

	@Override
	public void adiciona(Exame exame) throws SQLException {
		String sql = "insert into exame (medico_crm, paciente_cpf, tipo_exame, status, data, hora)" +
					 "values (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, exame.getMedico().getCrm());
			statement.setString(2, exame.getPaciente().getCpf());
			statement.setString(3, exame.getTipoExame().getTipoExame());
			statement.setString(4, StatusExame.AGUARDANDO_EXAME.getStatus());
			statement.setDate(5, Date.valueOf(LocalDate.now()));
			statement.setTime(6, Time.valueOf(LocalTime.now()));
			statement.execute();
		}
	}

	@Override
	public void altera(Exame exame) throws SQLException {
		String sql = "update exame set medico_crm = ?, paciente_cpf = ?, tipo_exame = ? where id = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, exame.getMedico().getCrm());
			statement.setString(2, exame.getPaciente().getCpf());
			statement.setString(3, exame.getTipoExame().getTipoExame());
			statement.setLong(4, exame.getId());
			statement.execute();
		}
	}

	@Override
	public void remove(Exame exame) throws SQLException {
		String sql = "delete from exame where id=?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setLong(1, exame.getId());
			statement.execute();
		}
	}

	@Override
	public Exame recupera(Exame exame) throws SQLException {
		String sql = "select * from exame where id=?";
		Exame exameEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, exame.getId());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				exameEncontrado = new Exame();
				exameEncontrado.setId(rs.getLong("id"));
				exameEncontrado.setPaciente(obterPaciente(rs.getString("paciente_cpf")));
				exameEncontrado.setMedico(obterMedico(rs.getString("medico_crm")));
				exameEncontrado.setTipoExame(TipoExame.obterTipoExame(rs.getString("tipo_exame")));
			}
		}
		return exameEncontrado;
	}

	@Override
	public List<Exame> lista() throws SQLException {
		List<Exame> exames = new ArrayList<>();
		String sql = "select * from exame";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Exame exame = new Exame();
				exame.setId(rs.getLong("id"));
				exame.setPaciente(obterPaciente(rs.getString("cpf")));
				exame.setMedico(obterMedico(rs.getString("nome")));
				exames.add(exame);
			}
		}
		return exames;
	}
	
	private Paciente obterPaciente(String cpf) throws SQLException{
		PacienteDAO dao = new PacienteDAO();
		Paciente paciente = new Paciente();
		
		paciente.setCpf(cpf);
		
		return dao.recupera(paciente);
	}
	
	private Medico obterMedico(String crm) throws SQLException{
		MedicoDAO dao = new MedicoDAO();
		Medico medico = new MedicoResidente();
		
		medico.setCrm(crm);
		
		return dao.recupera(medico);
	}
}