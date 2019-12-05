/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author PICHAU
 */
public class Candidato implements Serializable {
    private int id_candidato;
    private String nome;
    private String endereco;
    private LocalDate nascimento;
    private String pai;
    private String mae;
    
    private String[] colunas = new String[]{"ID_CANDIDATO", "NOME", "ENDERECO", "PAI", "MAE"};

    public Candidato() {
    }

    
    public Candidato(int id_candidato, String nome, String endereco, LocalDate nascimento, String pai, String mae) {
        this.nome = nome;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.pai = pai;
        this.mae = mae;
    }
    
    //id vai como serial
    public Candidato(String nome, String endereco, LocalDate nascimento, String pai, String mae) {
        this.nome = nome;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.pai = pai;
        this.mae = mae;
    }

    public Candidato(String string, String string0, String string1, String string2) {
        throw new UnsupportedOperationException("É ESSE?"); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(int id_candidato) {
        this.id_candidato = id_candidato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    
    
    
    
    
    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] Colunas) {
        this.colunas = Colunas;
    }
    
    
    

    
}
