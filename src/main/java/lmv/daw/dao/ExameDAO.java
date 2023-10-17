package lmv.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
		String sql = "insert into exame (medico_crm, paciente_cpf, tipo_exame, recomendacao, status, data)" +
					 "values (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, exame.getMedico().getCrm());
			statement.setString(2, exame.getPaciente().getCpf());
			statement.setString(3, exame.getTipoExame().getTipoExame());
			statement.setString(4, exame.getRecomendacao());
			statement.setString(5, exame.getStatusExame().getStatus());
			statement.setDate(6, Date.valueOf(exame.getData()));
			statement.execute();
		}
	}

	@Override
	public void altera(Exame exame) throws SQLException {
		String sql = "update exame set hora = ?,status = ? where id = ?";
		
		if (exame.getHora() == null)
			sql = "update exame set status = ? where id = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			if (exame.getHora() == null) {
				statement.setString(1, exame.getStatusExame().getStatus());
				statement.setLong(2, exame.getId());
			}
			
			else {
				System.out.println("Aqui");
				statement.setTime(1, Time.valueOf(exame.getHora()));
				statement.setString(2, exame.getStatusExame().getStatus());
				statement.setLong(3, exame.getId());
			}
			
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
	public int quantidade(String status) throws SQLException {
		String sql = "select count(*) as count from exame where status=?";
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
				exameEncontrado.setRecomendacao(rs.getString("recomendacao"));
				exameEncontrado.setStatusExame(StatusExame.obterStatusExame(rs.getString("status")));
				exameEncontrado.setData(rs.getDate("data").toLocalDate());
				
				if (rs.getTime("hora") != null)
					exameEncontrado.setHora(rs.getTime("hora").toLocalTime());
			}
		}
		return exameEncontrado;
	}
	
	public boolean buscaExamePaciente(Exame exame) throws SQLException {
		String sql = "select status, tipo_exame from exame where paciente_cpf=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, exame.getPaciente().getCpf());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getString("status").equalsIgnoreCase(StatusExame.AGUARDANDO_EXAME.getStatus())
					&& rs.getString("tipo_exame").equalsIgnoreCase(exame.getTipoExame().getTipoExame()))
					return true;
			}
			return false;
		}
	}
	
	public Exame recuperaExamePaciente(Exame exame) throws SQLException {
		String sql = "select * from exame where paciente_cpf=? and tipo_exame=?";
		Exame exameEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, exame.getPaciente().getCpf());
			stmt.setString(2, exame.getTipoExame().getTipoExame());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				exameEncontrado = new Exame();
				exameEncontrado.setId(rs.getLong("id"));
				exameEncontrado.setMedico(obterMedico(rs.getString("medico_crm")));
				exameEncontrado.setPaciente(obterPaciente(rs.getString("paciente_cpf")));
				exameEncontrado.setRecomendacao(rs.getString("recomendacao"));
				exameEncontrado.setStatusExame(StatusExame.obterStatusExame(rs.getString("status")));
				exameEncontrado.setData(rs.getDate("data").toLocalDate());
				exameEncontrado.setTipoExame(TipoExame.obterTipoExame(rs.getString("tipo_exame")));
				exameEncontrado.setHora(rs.getTime("hora").toLocalTime());
			}
			return exameEncontrado;
		}
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
				exame.setPaciente(obterPaciente(rs.getString("paciente_cpf")));
				exame.setMedico(obterMedico(rs.getString("medico_crm")));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setStatusExame(StatusExame.obterStatusExame(rs.getString("status")));
				exame.setData(rs.getDate("data").toLocalDate());
				exame.setTipoExame(TipoExame.obterTipoExame(rs.getString("tipo_exame")));
				exames.add(exame);
			}
		}
		return exames;
	}
	
	public List<Exame> listaExameAguardando() throws SQLException {
		List<Exame> exames = new ArrayList<>();
		String sql = "select * from exame where status=?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, StatusExame.AGUARDANDO_EXAME.getStatus());
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Exame exame = new Exame();
				exame.setId(rs.getLong("id"));
				exame.setPaciente(obterPaciente(rs.getString("paciente_cpf")));
				exame.setMedico(obterMedico(rs.getString("medico_crm")));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setStatusExame(StatusExame.obterStatusExame(rs.getString("status")));
				exame.setData(rs.getDate("data").toLocalDate());
				exame.setTipoExame(TipoExame.obterTipoExame(rs.getString("tipo_exame")));
				
				if (rs.getTime("hora") != null)
					exame.setHora(rs.getTime("hora").toLocalTime());
				
				exames.add(exame);
			}
		}
		return exames;
	}
	
	public List<Exame> listaExameAguardandoLaudo() throws SQLException {
		List<Exame> exames = new ArrayList<>();
		String sql = "select * from exame where status=?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, StatusExame.AGUARDANDO_LAUDO.getStatus());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Exame exame = new Exame();
				exame.setId(rs.getLong("id"));
				exame.setPaciente(obterPaciente(rs.getString("paciente_cpf")));
				exame.setStatusExame(StatusExame.obterStatusExame(rs.getString("status")));
				exame.setData(rs.getDate("data").toLocalDate());
				exame.setHora(rs.getTime("hora").toLocalTime());
				exame.setTipoExame(TipoExame.obterTipoExame(rs.getString("tipo_exame")));
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