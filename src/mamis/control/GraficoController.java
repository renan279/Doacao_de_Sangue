package mamis.control;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import mamis.Mamis;
import mamis.mysql.Banco;

public class GraficoController implements Initializable {

    @FXML
    private PieChart grafico;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnGrafico;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            // CHAMA A FUNÇÃO PARA GERAR O GRAFICO ASSIM QUE A TELA É INICIALIZADA
            gerarGrafico();
        } catch (SQLException ex) {
            Logger.getLogger(GraficoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GraficoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    //ABRIR A TELA CORRESPONDENTE
    @FXML
    private void abrirTelaIndex(ActionEvent event) {
        Mamis.mudaTela("index");
    }

    @FXML
    private void gerarGrafico() throws SQLException, ClassNotFoundException {
        //CHAMA OS MÉTODOS NOS BANCOS PARA SABER QUANTOS HOMENS E MULHERES EXISTEM
        Banco banco = new Banco();
        int H = banco.getAllHomens();
        int M = banco.getAllMulheres();
            //POPULA O GRÁFICO
            ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Homenes", H),
                new PieChart.Data("Mulheres", M)
            );
            grafico.setTitle("Relação de doação por Gênero");
            grafico.setData(pieChartData);
            
    }
    
}
