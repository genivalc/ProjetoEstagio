package mode.dao;

import entities.Produto;

public interface ProdutoDao {

	void insertOpcoe (Produto opcoe) ;
	void insertMinimo (Produto minimo) ;
	void insertMaximo (Produto maximo) ;
	void insertDescricao (Produto descricao) ;
	void createTable (Produto table) ;
}
