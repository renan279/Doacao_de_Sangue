package mamis.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import mamis.Mamis;

public class IndexController implements Initializable {

    @FXML
    private Button btnCadastrarDoador;
    @FXML
    private Button btnHistorico;
    @FXML
    private Button btnAjuda;
    @FXML
    private Button btnSair;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnGrafico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void abrirTelaCadastro(ActionEvent event) {
        Mamis.mudaTela("doar");
    }

    @FXML
    private void abrirTelaHistorico(ActionEvent event) {
        Mamis.mudaTela("historico");
    }

    @FXML
    private void clickAjuda(ActionEvent event) {
        JOptionPane.showMessageDialog(null,
              "Bem vindo ao software da Fundação Pro-Sangue."
            + "\n\nNo botão 'Cadastrar Doador' pode-se cadastrar os dados do paciente e realizar a coleta de sangue após os exames."
            + "\nNo botão 'Gráfico' pode-se gerar gráficos dos dados armazenados de acordo com o genêro dos doadores."
            + "\nNo botão 'Histórico' pode-se visualizar todas as doações armazenadas no banco."
            + "\nO botão 'Sair' encerra o aplicativo."
            + "\n\nQualquer dúvida entre em contato com os desenvolvedores por meio do e-mail 'renanfrancisco.on@gmail.com' ou pelo telefone +55(42)998100170."
            + "\n\nObrigado por usar nosso programa."
            ); 
    }

    @FXML
    private void clickSair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void abrirTelaGrafico(ActionEvent event) {
        Mamis.mudaTela("grafico");
    }
    
}
