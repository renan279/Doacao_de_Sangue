package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 */

public class Index extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {

        FXMLLoader teste = new FXMLLoader();
        Pane root = teste.load(new FileInputStream("C:\\Users\\PICHAU\\Documents\\NetBeansProjects\\JoaoPedro_RenanFrancisco\\src\\View\\Index.fxml"));
                
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Fundação Pro-Sangue");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
