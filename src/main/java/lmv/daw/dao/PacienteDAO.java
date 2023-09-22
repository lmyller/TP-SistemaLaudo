package lmv.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lmv.daw.bd.Conexao;
import lmv.daw.modelo.Paciente;

public class PacienteDAO implements DAO<Paciente> {

	private Connection connection;
	
	public PacienteDAO() {
		connection = Conexao.getConnection();
	}

	@Override
	public void adiciona(Paciente paciente) throws SQLException {
		String sql = "insert into paciente (cpf, nome, email, sexo, idade, data_nascimento)" +
					 "values (?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, paciente.getCpf());
			statement.setString(2, paciente.getNome());
			statement.setString(3, paciente.getEmail());
			statement.setString(4, String.valueOf(paciente.getSexo()));
			statement.setShort(5, paciente.getIdade());
			statement.setDate(6, Date.valueOf(paciente.getDataNascimento()));
			statement.execute();
		}
	}

	@Override
	public void altera(Paciente paciente) throws SQLException {
		String sql = "update paciente set nome = ?, email = ?, sexo = ?, idade = ?" +
					 "data_nascimento = ? where cpf = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, paciente.getNome());
			statement.setString(2, paciente.getEmail());
			statement.setString(3, String.valueOf(paciente.getSexo()));
			statement.setShort(4, paciente.getIdade());
			statement.setDate(5, Date.valueOf(paciente.getDataNascimento()));
			statement.setString(6,paciente.getCpf());
			statement.execute();
		}
	}

	@Override
	public void remove(Paciente paciente) throws SQLException {
		String sql = "delete from paciente where cpf=?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, paciente.getCpf());
			statement.execute();
		}
	}

	@Override
	public Paciente recupera(Paciente paciente) throws SQLException {
		String sql = "select * from paciente where cpf=?";
		Paciente pacienteEncontrado = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, paciente.getCpf());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				pacienteEncontrado = new Paciente();
				pacienteEncontrado.setCpf(rs.getString("cpf"));
				pacienteEncontrado.setNome(rs.getString("nome"));
				pacienteEncontrado.setEmail(rs.getString("email"));
				pacienteEncontrado.setSexo(rs.getString("endereco").charAt(0));
				pacienteEncontrado.setIdade(rs.getShort("idade"));
				pacienteEncontrado.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
			}
		}
		return pacienteEncontrado;
	}

	@Override
	public List<Paciente> lista() throws SQLException {
		List<Paciente> pacientes = new ArrayList<>();
		String sql = "select * from paciente";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setEmail(rs.getString("email"));
				paciente.setSexo(rs.getString("endereco").charAt(0));
				paciente.setIdade(rs.getShort("idade"));
				paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				pacientes.add(paciente);
			}
		}
		return pacientes;
	}
}
