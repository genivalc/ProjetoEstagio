/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entities.Produto;
import java.util.ArrayList;

/**
 *
 * @author geniv
 */
public class printSelect {
    public void imprimeSelecionados(ArrayList<Produto> produtos) {

		// System.out.println("PRODUTOS SELECIONADOS");
//     for (String valor : SELECIONADOS) {
//         
//
//         System.out.println(valor);
//       
//     }
		for (Produto produto : produtos) {

			// System.out.println("DESCRIÃ‡ÃƒO DO PRODUTO: " + produto.descricao);
			System.out.println(produto.getDescricao() + ":");
			for (String x : produto.getSelecionado()) {

				// System.out.println("OPÃ‡ÃƒO SELECIONADA: " + x);
				System.out.println(x);

			}

		}
	}
}
