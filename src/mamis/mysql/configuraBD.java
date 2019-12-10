package mamis.mysql;

import java.io.*;
import java.sql.SQLException;

public class configuraBD {
      
    public static void main( String args[] ) throws SQLException, ClassNotFoundException {
        Banco banco = new Banco();

        String[] sql;
        
        //CHAMA O ARQUIVO .TXT PARA CRIAR AS TABELAS E POPULAR O BANCO
        String filename = "C:\\Users\\PICHAU\\Documents\\NetBeansProjects\\JoaoPedro_RenanFrancisco\\src\\mamis\\mysql\\script.txt";
        
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader in = new BufferedReader (fr);
            String aux;
            String lido="";            
        while( (aux=in.readLine())!= null )
            lido += aux;        
        in.close ();  
        sql = lido.split("#");   
        //PERCORRE SQL E EXECUTA AS SQL NA CLASSE BANCO
        for (String sql1 : sql) {
            banco.executarSQL(sql1);
        }
        }catch( IOException e ) {
            System.out.println ("Erro na leitura do script.");
        }
    } 
}
