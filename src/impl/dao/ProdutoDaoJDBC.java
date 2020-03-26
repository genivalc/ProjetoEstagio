package impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import db.DBJava;
import db.DbException;
import entities.Produto;
import mode.dao.ProdutoDao;
public class ProdutoDaoJDBC implements ProdutoDao{

	private Connection conn;
	
	public  ProdutoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insertOpcoe(Produto opcoe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertMinimo(Produto minimo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertMaximo(Produto maximo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertDescricao(Produto descricao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTable(Produto table) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("CREATE TABLE (?)");
			
			st.setString(1, table.getNome());

			int rowsAffected = st.executeUpdate();		
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setNome("table");
					//return produto;
				}
				
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBJava.closeStatement(st);
		}
		
	}

}
