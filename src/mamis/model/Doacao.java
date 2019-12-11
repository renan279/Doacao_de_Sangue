package mamis.model;

import java.time.LocalDate;

//CLASSE DOACAO - IDENTICA AO TABLE DOACAO DO BANCO
public class Doacao {
    
    private int id_docao;
    private int fk_id_candidato;
    private LocalDate dia;
    private int sexo;

    //CONSTRUTOR SEM O ID     
    public Doacao(int fk_id_candidato, LocalDate dia, int sexo) {
        this.fk_id_candidato = fk_id_candidato;
        this.dia = dia;
        this.sexo = sexo;
    } 

    //CONSTRUTOR COM O ID PARA PESQUISA COMPLETA NO BANCO
    public Doacao(int id_docao, int fk_id_candidato, LocalDate dia, int sexo) {
        this.id_docao = id_docao;
        this.fk_id_candidato = fk_id_candidato;
        this.dia = dia;
        this.sexo = sexo;
    }

    public int getFk_id_candidato() {
        return fk_id_candidato;
    }

    public void setFk_id_candidato(int fk_id_candidato) {
        this.fk_id_candidato = fk_id_candidato;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }


    
    
    
    
}
