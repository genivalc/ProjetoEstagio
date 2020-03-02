/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controle.ButtonColor;
import controle.CleanButton;
import static controle.RemoveAcento.removeAcentos;
import entities.Produto;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.excepion.ImprimeTexto;
import model.excepion.leitorProduto;

/**
 *
 * @author geniv
 */
public class TelaSecundaria extends javax.swing.JDialog {

    CleanButton clean = new CleanButton();
    ButtonColor buttonColor = new ButtonColor();
    String NOME = "";
    String IMPRESSAO = "";
    ArrayList<Produto> PRODUTOS;

    /**
     * Creates new form TelaSecundaria
     */
    public TelaSecundaria(java.awt.Frame parent, boolean modal, String x) {
        super(parent, modal);
        initComponents();
        NOME = x;
        criaProdutos();

        setSize(1280, 780);
    }

    /**
     * Creates new form Quetinha10
     */
    public void criaProdutos() {

        leitorProduto f = new leitorProduto();
        try {
            PRODUTOS = f.lerTexto("D:\\produtos.txt", NOME);
            criaBotoes(PRODUTOS);
        } catch (IOException ex) {
            Logger.getLogger(TelaSecundaria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void criaBotoes(ArrayList<Produto> produtos) {
        panel.removeAll();

        int cont = 1;
        int altura = 9;
        int largura = 78;
        int x = 10;
        int y = 30;
        int contador = 2;
        int aux = 1;
        try {

            int X = 10;
            int Y = 10;

            for (Produto produto : produtos) {

                JPanel painel = new JPanel();
                painel.setLayout(new GridLayout(countComponents(), countComponents()));

                painel.setSize(1200, 90);
                painel.setLocation(X, Y);

                if (produtos.equals(produto.getValorDaQuetinha())) {
                    painel.remove(painel);

                }

                for (String valor : produto.getOpcoes()) {

                    final JButton button = new JButton(valor);// modificar
                    removeAcentos(button.getText());
                    button.setFont(new Font("Tahoma", Font.BOLD, 10));
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setHorizontalTextPosition(SwingConstants.CENTER);
                    button.setVerticalAlignment(SwingConstants.CENTER);
                    button.setVerticalTextPosition(SwingConstants.CENTER);
                    button.setBackground(Color.gray);
                    button.addActionListener(new ActionListener() {

                        @Override

                        public void actionPerformed(java.awt.event.ActionEvent e) {

                            if (produto.getSelecionado().contains(button.getText())) {

                                produto.getSelecionado().remove(button.getText());
                            } else if (produto.getSelecionado().size() >= produto.getQuantidadeMaxima()) {
                                produto.getSelecionado().remove(produto.getSelecionado().size() - 1);
                                produto.getSelecionado().add(button.getText());
                            } else {
                                produto.getSelecionado().add(button.getText());

                            }

                            buttonColor.atualizaCorDoBotoes(PRODUTOS);

                            //imprimeSelecionados(produtos);
                        }
                    });

                    if (painel.getSize().getWidth() > (largura + contador) + (contador * 1)) {

                        button.setBounds(x, y, largura, altura);

                        painel.add(button);
                        x = 100 + 0 + 0;
                        contador++;
                        this.repaint();

                    } else {
                        if (painel.getSize().getHeight() > (altura * aux) + aux * 1) {
                            contador = 2;
                            x = 15;
                            y = y + altura + 5;

                            button.setBounds(x, y, largura, altura);
                            painel.setLocation(X, Y);
                            painel.add(button);

                            x = 100 + 0 + 0;
                            aux++;
                            this.repaint();
                        }
                    }
                    produto.getBotoes().add(button);

                }

                painel.setBorder(javax.swing.BorderFactory.createTitledBorder(produto.getDescricao()));

                int tamanhodatela = produto.getBotoes().size();

                for (int i = 1; tamanhodatela < 7; i++) {

                    JLabel a = new JLabel();

                    painel.add(a);
                    tamanhodatela++;
                }
                panel.add(painel);

                Y = Y + 91;

            }

            JPanel painel2 = new JPanel();
            painel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
            painel2.setSize(1200, 70);
            painel2.setLocation(X, Y);
            painel2.setAlignmentX(LEFT_ALIGNMENT);

            painel2.setVisible(true);

            String enviar = "Enviar";

            final JButton buttonenv = new JButton(enviar);
            buttonenv.setFont(new Font("Tahoma", Font.BOLD, 14));
            buttonenv.setBackground(Color.gray);

            buttonenv.addActionListener(new ActionListener() {

                @Override

                public void actionPerformed(java.awt.event.ActionEvent e) {

                    montaImpressao(produtos);
                    clean.zeraProdutos(PRODUTOS);
                    buttonColor.atualizaCorDoBotoes(PRODUTOS);
                }

            });

            painel2.add(buttonenv);

            panel.add(painel2);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void Verificar(ArrayList<Produto> produtos) {

    }

    public void montaImpressao(ArrayList<Produto> produtos) {
		IMPRESSAO = NOME;

		System.out.println(IMPRESSAO);

		// IMPRESSAO.concat("QUENTINHA DE 10"+"\n");
		IMPRESSAO = IMPRESSAO + "\n";
		System.out.println(IMPRESSAO);
		// IMPRESSAO.concat("\n");

		// System.out.println("PRODUTOS SELECIONADOS");
//     for (String valor : SELECIONADOS) {
//         
//
//         System.out.println(valor);
//       
//     }
		for (Produto produto : produtos) {

			// System.out.println("DESCRIÃ‡ÃƒO DO PRODUTO: " + produto.descricao);
			// System.out.println(produto.descricao+":");
			// IMPRESSAO.concat(produto.descricao+":");
			System.out.println(IMPRESSAO);
			IMPRESSAO = IMPRESSAO + produto.getDescricao();
			IMPRESSAO = IMPRESSAO + ": ";
			IMPRESSAO = IMPRESSAO + "\n";

			for (String x : produto.getSelecionado()) {

				// System.out.println("OPÃ‡ÃƒO SELECIONADA: " + x);
				// System.out.println(x);
				// IMPRESSAO.concat(x);
				System.out.println(IMPRESSAO);
				IMPRESSAO = IMPRESSAO + "  ";
				IMPRESSAO = IMPRESSAO + x;
				IMPRESSAO = IMPRESSAO + "\n";
			}

		}

		System.out.println("IMPRESSAO....:\n");
		System.out.println(IMPRESSAO);
		ImprimeTexto IP = new ImprimeTexto();

		try {
			IP.imprimeDocumento(removeAcentos(IMPRESSAO), "RECIBO");
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(TelaSecundaria.class.getName()).log(Level.SEVERE, null, ex);
		} catch (PrintException ex) {
			Logger.getLogger(TelaSecundaria.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel.setMinimumSize(new java.awt.Dimension(1000, 500));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1361, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSecundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSecundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSecundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSecundaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaSecundaria dialog = new TelaSecundaria(new javax.swing.JFrame(), true, "QUETINHA");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
