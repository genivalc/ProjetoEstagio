/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.excepion;

import entities.Produto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geniv
 */
public class leitorProduto {

    public ArrayList<Produto> lerTexto(String caminho, String nome) throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(caminho);
        BufferedReader reader = new BufferedReader(fileReader);
        String linha = null;
        ArrayList<Produto> produtos = new ArrayList();
        Produto p = null;
        String NOME = nome;
        boolean quentinha = false;

        while ((linha = reader.readLine()) != null) {

            while (!linha.isEmpty()) {
                String x = "";
                StringTokenizer st = new StringTokenizer(linha, "|");
                String palavra = st.nextToken();
                System.out.println(palavra);

                if (palavra.equals("VALORDAQUENTINHA")) {
                    String xx = "";
                    StringTokenizer st5 = new StringTokenizer(linha, "|");
                    String cabecario = st5.nextToken();
                    String nome_quentinha = st5.nextToken();
                    System.out.println("NOME QUENTINHA: " + nome_quentinha);
                    System.out.println("NOME QUENTINHA RECEBIDA: " + NOME);
                    if (nome_quentinha.equals(NOME)) {

                        quentinha = true;

                    } else {
                        quentinha = false;
                    }

                }

                if (palavra.equals("PRODUTO") && quentinha) {

                    if (p != null) {
                        produtos.add(p);
                    }

                    p = new Produto();
                    criaProduto(linha);
                    String xx = "";
                    StringTokenizer st2 = new StringTokenizer(linha, "|");
                    String cabecario = st2.nextToken();
                    System.out.println("TIPO: " + cabecario);
                    String descricao = st2.nextToken();
                    System.out.println("DESCRIÃ‡ÃƒO: " + descricao);
                    String min = st2.nextToken();
                    System.out.println("MINIMO: " + min);
                    String max = st2.nextToken();
                    System.out.println("MAX: " + max);

                    p.setDescricao(descricao);
                    p.setQuantidadeMinima(Integer.parseInt(min));
                    p.setQuantidadeMaxima(Integer.parseInt(max));

                }

                if (palavra.equals("OPCAO") && quentinha) {
                    // aqui devo pegar os dados dessa linha e adicionar este item de pedido no ultimo pedido aberto acima e zerar a linha para passar para proxima
                    criaOpcao(linha);
                    String xx = "";
                    StringTokenizer st3 = new StringTokenizer(linha, "|");
                    String cabecario = st3.nextToken();
                    String opcao = st3.nextToken();
                    // desprezado cabeÃ§ario
                    p.getOpcoes().add(opcao);

                }
                // aqui tenho que diminuir a string apartir do comeÃ§o
                if (linha.length() > palavra.length()) {
                    x = linha.substring(palavra.length() + 1);
                } else {
                    x = linha.substring(palavra.length());
                }
                linha = x;
            }
        }
        fileReader.close();
        reader.close();
        produtos.add(p);

        return produtos;

    }

    public ArrayList<Produto> lerProdutos(String caminho) throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(caminho);
        BufferedReader reader = new BufferedReader(fileReader);
        String linha = null;
        ArrayList<Produto> produtos = new ArrayList();
        Produto p = null;
        int cont = 0;

        while ((linha = reader.readLine()) != null) {

            while (!linha.isEmpty()) {
                String x = "";
                StringTokenizer st = new StringTokenizer(linha, "|");
                String palavra = st.nextToken();
                System.out.println(palavra);

                if (palavra.equals("VALORDAQUENTINHA")) {
                    String xx = "";
                    StringTokenizer st5 = new StringTokenizer(linha, "|");
                    String cabecario = st5.nextToken();
                    System.out.println("CABECALHO: " + cabecario);
                    String nome_quentinha = st5.nextToken();
                    System.out.println("NOME QUENTINHA: " + nome_quentinha);

                    p = new Produto();

                    p.setNome(nome_quentinha);
                    produtos.add(p);
                }
                if (linha.length() > palavra.length()) {
                    x = linha.substring(palavra.length() + 1);
                } else {
                    x = linha.substring(palavra.length());
                }
                linha = x;
            }
        }
        fileReader.close();
        reader.close();

        return produtos;

    }

    public void criaProduto(String linha) {

        String xx = "";
        StringTokenizer st = new StringTokenizer(linha, "|");
        String cabecario = st.nextToken();
        // desprezado cabeÃ§ario
        System.out.println("TIPO: " + cabecario);
        String descricao = st.nextToken();
        // desprezado cod pedido antigo
        System.out.println("DESCRIÃ‡ÃƒO: " + descricao);
        String min = st.nextToken();
        System.out.println("MINIMO: " + min);
        String max = st.nextToken();
        System.out.println("MAX: " + max);

    }

    public void criaOpcao(String linha) {

        String xx = "";
        StringTokenizer st = new StringTokenizer(linha, "|");
        String cabecario = st.nextToken();
        String opcao = st.nextToken();
        // desprezado cabeÃ§ario
        System.out.println("" + opcao);

    }

    public static void main(String[] args) {


    }

}
