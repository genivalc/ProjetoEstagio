/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entities.Produto;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author geniv
 */
public class ButtonColor {
    public void atualizaCorDoBotoes(ArrayList<Produto> produtos) {

		for (Produto produto : produtos) {

			for (JButton b : produto.getBotoes()) {

				if (produto.getSelecionado().contains(b.getText())) {
					b.setBackground(Color.GREEN);
				} else {
					b.setBackground(Color.GRAY);
				}

			}

		}

	}
}
