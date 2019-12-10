package mamis.mysql;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mamis.model.Doacao;

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
     
    //CADASTRAR CANDIDATO 
     public boolean cadastroCandidato(String nome, String endereco, LocalDate nascimento, String pai, String mae) throws SQLException, ClassNotFoundException{
         String sql = "INSERT INTO candidato (nome, endereco, nascimento, pai, mae)"
                + "VALUES ('"+nome+"','"+endereco+"','"+nascimento+"','"+pai+"','"+mae+"');";
         return this.executarSQL(sql);
     }
     
     //CADASTRAR DOAÇÃO
     public boolean cadastroDoacao(int fk_id_candidato, LocalDate dia, int sexo) throws SQLException, ClassNotFoundException{
         String sql = "INSERT INTO doacao (fk_id_candidato, dia, sexo)"
                + " VALUES ('"+fk_id_candidato+"','"+dia+"','"+sexo+"');";
         return this.executarSQL(sql);
     }
     
     //PEGA O ULTIMO ID DO USUÁRIO 
     public int getLastIDCandidato() throws SQLException, ClassNotFoundException{
        String sql = "SELECT MAX(id_candidato) AS id FROM candidato;";
        int lastIDCandidato = 0;
        ResultSet response;
        if (getConexao()== null) {           
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        if(response.next()) {
            lastIDCandidato = response.getInt("id");
        }
        return lastIDCandidato;
    }
     
     //PEGA TODAS AS DOAÇÕES
     public List<Doacao> getAllDoacoes() throws SQLException, ClassNotFoundException{
        String data;
        
        DateTimeFormatter formatter_1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        ResultSet response = null;
        String sql = "SELECT id_doacao, fk_id_candidato, dia, sexo FROM doacao;";
        if (getConexao()== null) {
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        List<Doacao> doacoes = new ArrayList<>();
        while(response.next()){            
            data = response.getString("dia");
            LocalDate localDate_1= LocalDate.parse(data,formatter_1);            
            Doacao doacao = new Doacao(response.getInt("id_doacao"), response.getInt("fk_id_candidato"), localDate_1, response.getInt("sexo"));
            doacoes.add(doacao);
        }
        return doacoes;
        
    }
    
    //RETORNA TODOS OS HOMENS DOADORES 
    public int getAllHomens() throws SQLException, ClassNotFoundException{
        int count = 0;
        ResultSet response = null;
        String sql = "SELECT sexo FROM doacao WHERE sexo = 1;";
        if (getConexao()== null) {
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        while(response.next()){
            count++;
        }
        return count;        
    }     
     
    //RETORNAR TODAS AS MULHERES DOADORAS
     public int getAllMulheres() throws SQLException, ClassNotFoundException{
        int count = 0;
        ResultSet response = null;
        String sql = "SELECT sexo FROM doacao WHERE sexo = 0;";
        if (getConexao()== null) {
            conectar();
        }
        Statement sessao = getConexao().createStatement();
        response = sessao.executeQuery(sql);
        while(response.next()){
            count++;
        }
        return count;        
    }
 
     
     //GETTERS & SETTERS
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
