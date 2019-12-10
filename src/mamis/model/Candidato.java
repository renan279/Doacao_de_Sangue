package mamis.model;

import java.time.LocalDate;

//CLASSE CANDIDATO - IDENTICA AO TABLE CANDIDADO NO BANCO
public class Candidato {
    
    private int id_candidato;
    private String nome;
    private String endereco;
    private LocalDate nascimento;
    private String pai;
    private String mae;

    //CONSTRUTOR COM O ID PARA PESQUISA COMPLETA NO BANCO
    public Candidato(int id_candidato, String nome, String endereco, LocalDate nascimento, String pai, String mae) {
        this.id_candidato = id_candidato;
        this.nome = nome;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.pai = pai;
        this.mae = mae;
    }

    //CONSTRUTOR SEM O ID 
    public Candidato(String nome, String endereco, LocalDate nascimento, String pai, String mae) {
        this.nome = nome;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.pai = pai;
        this.mae = mae;
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
    
    
    
    
}
