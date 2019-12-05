/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Model.Candidato;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class Banco {

    private Connection conexao;
    private String driver;
    private String url;
    private String user;
    private String senha;
    
    public Banco(String driver, String url, String user, String senha){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.senha = senha;
    }

    public Banco() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/prosangue";
        this.user = "root";
        this.senha = "";
    }
    
    public void conectar() throws SQLException, ClassNotFoundException{
        Class.forName(getDriver());
        setConexao(DriverManager.getConnection(getUrl(), getUser(), getSenha()));
    }
    
    public void fecharConexao() throws SQLException{
        getConexao().close();
    }

    public boolean executarSQL(String sql) throws SQLException, ClassNotFoundException {
        if (getConexao()== null) {
           
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        sessao.executeUpdate(sql);
        return true;
    }
    
    // EXECUTAR SQL COM RETORNO
     public ResultSet getSQL(String sql) throws SQLException, ClassNotFoundException {
        ResultSet response = null;
        if (getConexao()== null) {
           
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        return response;
    } 
     
     public boolean cadastroCandidato(String nome, String endereco, LocalDate nascimento, String pai, String mae) throws SQLException, ClassNotFoundException{
         String sql = "INSERT INTO candidato (nome, endereco, nascimento, pai, mae)"
                + "VALUES ('"+nome+"','"+endereco+"','"+nascimento+"','"+pai+"','"+mae+"');";
         return this.executarSQL(sql);
     }
     
     public boolean updateCandidato(String nome, String endereco, LocalDate nascimento, String pai, String mae) throws SQLException, ClassNotFoundException{
        String sql =  "UPDATE candidato SET endereco = '"+endereco+"', nascimento = '"+nascimento+"', pai = '"+pai+"', mae = '"+mae+"' WHERE nome = '"+nome+"';";
        return this.executarSQL(sql);
          
     }
     
     public void consulta() throws SQLException{
         Statement sessao = conexao.createStatement();
         String sql = "Select * from candidato";
         ResultSet dados = sessao.executeQuery(sql);
         ResultSetMetaData campos = dados.getMetaData();
         System.out.println(campos.getColumnName(1)+"\t"+campos.getColumnName(2));
         while(dados.next()){
             System.out.println(dados.getInt(1)+"\t"+dados.getString(2));
         }
     }
     
     
     public List<Candidato> buscarCandidato() throws SQLException, ClassNotFoundException{
         
        ResultSet response = null;
        String sql = "SELECT * FROM candidato";
        if (getConexao()== null) {           
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        List<Candidato> candidatos = new ArrayList<>();
        while(response.next()){
            Candidato candidato = new Candidato(
                    /*response.getInt("id_candidato"),*/ response.getString("nome"),
                    response.getString("endereco"), /*response.getTime("nascimento", Calendar.DATE),*/
                    response.getString("pai"), response.getString("mae"));
            candidatos.add(candidato);
            System.out.println(candidato.getNome());
        }
        return candidatos;

        
     }

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    /**
     * @return the conexao
     */
    public Connection getConexao() {
        return conexao;
    }

    /**
     * @param conexao the conexao to set
     */
    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
