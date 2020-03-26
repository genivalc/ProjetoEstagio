package mode.dao;

import db.DBJava;
import impl.dao.ProdutoDaoJDBC;

public class DaoFactory {
	public static ProdutoDaoJDBC createProduto() {
		return new ProdutoDaoJDBC(DBJava.getConnection());
	}
}
