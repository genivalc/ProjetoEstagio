/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import application.TelaPrincipal;
import entities.Produto;
import java.util.ArrayList;

/**
 *
 * @author geniv
 */
public class CleanButton {
    
    public void zeraProdutos(ArrayList<Produto> produtos) {

		for (Produto produto : produtos) {

			produto.getSelecionado().clear();

		}

		//atualizaBotoes2(produtos);

	}
}
