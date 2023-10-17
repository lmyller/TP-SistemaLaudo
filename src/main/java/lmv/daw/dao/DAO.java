package lmv.daw.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	public void adiciona(T objeto) throws SQLException;
	public void altera(T objeto) throws SQLException;
	public void remove(T objeto) throws SQLException;
	public T recupera(T objeto) throws SQLException;
	public int quantidade(String status) throws SQLException;
	public List<T> lista() throws SQLException;
}
