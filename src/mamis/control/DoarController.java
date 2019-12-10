package mamis.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import mamis.Mamis;
import mamis.model.Candidato;
import mamis.mysql.Banco;

public class DoarController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtPai;
    @FXML
    private TextField txtMae;
    @FXML
    private DatePicker txtNascimento;
    @FXML
    private Button btnDoador;
    @FXML
    private Button btnVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void cadastrarDoador(ActionEvent event) {
        //VALIDAÇÃO DOS CAMPOS 
        if("".equals(txtNome.getText()) || "".equals(txtEndereco.getText()) || "".equals(txtNascimento.getValue()) || 
            "".equals(txtPai.getText()) || "".equals(txtMae.getText())){
             JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        }  
        else{            
            Banco banco = new Banco();
            try {
                Candidato c = new Candidato(txtNome.getText(), txtEndereco.getText(), txtNascimento.getValue(), txtPai.getText(), txtMae.getText());
                banco.cadastroCandidato(c.getNome(), c.getEndereco(), c.getNascimento(), c.getPai(), c.getMae());
                JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso! ");
                Mamis.mudaTela("teste");
            }
            catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao criar candidato.");
            } 
        }  
    }

    @FXML
    private void abrirIndex(ActionEvent event) {
        Mamis.mudaTela("index");
    }
    
}
