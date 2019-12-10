package mamis.control;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mamis.Mamis;
import mamis.model.Doacao;
import mamis.mysql.Banco;

public class HistoricoController implements Initializable {

    @FXML
    private TableView<Doacao> tableview;
    @FXML
    private TableColumn<Doacao, String> colIdDoacao;
    @FXML
    private TableColumn<Doacao, String> colDataDoacao;
    @FXML
    private Button btnHistAtt;
    @FXML
    private Button btnHistVoltar;
    @FXML
    private TableColumn<Doacao, String> colIdDoador;
    @FXML
    private TableColumn<Doacao, String> colSexo;
    
//PARA RECEBER O RESULTADO DA FUNÇÃO DO BANCO    
    List<Doacao> list = new ArrayList<>();
    ObservableList<Doacao> doacoes = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // PARA IDENTIFICAR AS COLUNAS COM OS TIPOS DAS VARIÁVEIS DO OBJETO DA CLASSE DOAÇÃO

        colIdDoador.setCellValueFactory(
                new PropertyValueFactory<>("fk_id_candidato"));
        colDataDoacao.setCellValueFactory(
                new PropertyValueFactory<>("dia"));
        colSexo.setCellValueFactory(
                new PropertyValueFactory<>("sexo"));

//CHAMA A FUNÇÃO PRA ATUALIZAR TABELA        
        try {
            atualizarHistorico();
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoricoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    //ATUALIZA A TABELA
    @FXML
    private void atualizarHistorico() throws SQLException, ClassNotFoundException {
        Banco banco = new Banco();
        list.clear();
        list = banco.getAllDoacoes();
        tableview.setItems(FXCollections.observableArrayList(list)); 
    }

    @FXML
    private void abrirHome(ActionEvent event) {
        Mamis.mudaTela("index");
    }
    
}
