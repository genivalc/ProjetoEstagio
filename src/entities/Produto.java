/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author geniv
 */
public class Produto {

    private String descricao;
    private String nome;
    ArrayList<String> valorDaQuetinha = new ArrayList();
    ArrayList<String> opcoes = new ArrayList();
    ArrayList<String> selecionado = new ArrayList();
    ArrayList<JButton> botoes = new ArrayList<JButton>();
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;

    public Produto() {
       
    }
    
    public Produto(String descricao, String nome, Integer quantidadeMinima, Integer quantidadeMaxima) {
        this.descricao = descricao;
        this.nome = nome;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
    }
    
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getValorDaQuetinha() {
        return valorDaQuetinha;
    }

    public void setValorDaQuetinha(ArrayList<String> valorDaQuetinha) {
        this.valorDaQuetinha = valorDaQuetinha;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(ArrayList<String> opcoes) {
        this.opcoes = opcoes;
    }

    public ArrayList<String> getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(ArrayList<String> selecionado) {
        this.selecionado = selecionado;
    }

    public ArrayList<JButton> getBotoes() {
        return botoes;
    }

    public void setBotoes(ArrayList<JButton> botoes) {
        this.botoes = botoes;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    
    
    
}
