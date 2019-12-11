package mamis;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Mamis extends Application {
    private static Stage stage;
    private static Scene indexTela;
    private static Scene doarTela;
    private static Scene historicoTela;
    private static Scene testeTela;
    private static Scene graficoTela;

    //CLASSE PRINCIPAL
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Fundação Pro-Sangue");
        
        //TELA DO INDEX
        Parent index = FXMLLoader.load(getClass().getResource("view/index.fxml"));
        indexTela = new Scene(index);
        //TELA DO DOADOR
        Parent doar = FXMLLoader.load(getClass().getResource("view/doar.fxml"));
        doarTela = new Scene(doar);
        //TELA DOS HISTÓRICOS
        Parent historico = FXMLLoader.load(getClass().getResource("view/historico.fxml"));
        historicoTela = new Scene(historico);
        //TELA DE TESTES
        Parent teste = FXMLLoader.load(getClass().getResource("view/teste.fxml"));
        testeTela = new Scene(teste);
        //TELA DE GRÁFICO
        Parent grafico = FXMLLoader.load(getClass().getResource("view/grafico.fxml"));
        graficoTela = new Scene(grafico);
        //TELA DO INDEX COMO PRIMÁRIA
        primaryStage.setScene(indexTela);
        primaryStage.show();
        
    /**
     *
     */
     
    }
     public static void mudaTela(String tela){
         //SELECIONA TELA QUE SERÁ EXIBIDA
         switch(tela){
            case "doar":
                stage.setScene(doarTela);
                break;
            case "historico":
                stage.setScene(historicoTela);
                break;
            case "teste":
                stage.setScene(testeTela);
                break;
            case "index":
                stage.setScene(indexTela);
                break;
            case "grafico":
                stage.setScene(graficoTela);
                break;
        }     
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
