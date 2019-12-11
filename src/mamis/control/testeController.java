package mamis.control;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import mamis.Mamis;
import mamis.model.Candidato;
import mamis.model.Doacao;
import mamis.mysql.Banco;

public class testeController implements Initializable {

    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtAltura;
    @FXML
    private DatePicker txtDataDoacao;
    @FXML
    private Button btnDoar;
    @FXML
    private RadioButton rbH;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private RadioButton rbM;
    @FXML
    private ComboBox<String> comboBoxSangue;
    @FXML
    private ToggleButton anemia;
    @FXML
    private Button btnVoltar;
    @FXML
    private Text aviso;
    @FXML
    private Text triagem;
    @FXML
    private CheckBox hepaB;
    @FXML
    private CheckBox hepaC;
    @FXML
    private CheckBox chagas;
    @FXML
    private CheckBox aids;
    @FXML
    private CheckBox htlv;
    @FXML
    private CheckBox sifilis;
    @FXML
    private CheckBox anticorpos;
    @FXML
    private TextField txtPa;
    @FXML
    private TextField txtPe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // POPULA O COMBOBOX
        ObservableList<String> abo = FXCollections.observableArrayList();
        abo.addAll("A-","A+","B-","B+","AB-","AB+","O-","O+");
        comboBoxSangue.itemsProperty().setValue(abo);

    }    

    @FXML
    private void cadastrarDoacao(ActionEvent event) {
        int sexo = 0;
        int lastIDReclamacao;
        
        if( rbH.isSelected() ) sexo = 1;
        //VALIDA OS CAMPOS
        if( "".equals(txtDataDoacao.getValue()) || "".equals(txtAltura.getText()) || "".equals(txtPeso.getText()) || "".equals(txtPa.getText()) || "".equals(txtPe.getText()) ){
             JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        }else{            
            Banco banco = new Banco();
            try {
                lastIDReclamacao = banco.getLastIDCandidato();
                Doacao d = new Doacao(lastIDReclamacao, txtDataDoacao.getValue(), sexo);
                banco.cadastroDoacao(d.getFk_id_candidato(), d.getDia(), d.getSexo());
                JOptionPane.showMessageDialog(null, "Doação cadastrada com sucesso! ");
                Mamis.mudaTela("historico");
            }
            catch (HeadlessException | ClassNotFoundException | SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao cadastrar doação.");
            } 
        }  
        
    }

    @FXML
    private void bloquear(ActionEvent event) {
        //BLOQUEAR BOTÃO PARA AVANÇAR EM CASO DE RECUSAS NOS TESTES
        if (anemia.isSelected()==false || hepaB.isSelected() || hepaC.isSelected() || chagas.isSelected() || aids.isSelected() || htlv.isSelected() || sifilis.isSelected() || anticorpos.isSelected()){
            btnDoar.setDisable(true);
            aviso.setVisible(true);
        }
        if (anemia.isSelected() && hepaB.isSelected()==false && hepaC.isSelected()==false && chagas.isSelected()==false && aids.isSelected()==false && htlv.isSelected()==false && sifilis.isSelected()==false && anticorpos.isSelected()==false){
            btnDoar.setDisable(false);
            aviso.setVisible(false);
        }
        
    }

    @FXML
    private void abriTelaIndex(ActionEvent event) {
        Mamis.mudaTela("index");
    }

    @FXML
    private void triagem(ActionEvent event) {
        //TESTA PRESSAO
        int Pa = Integer.parseInt(txtPa.getText());
        int Pe = Integer.parseInt(txtPe.getText());
        int pressao = Pa - Pe;
        
        //TESTA IMC
        Float altura = Float.parseFloat(txtAltura.getText());
        Float peso = Float.parseFloat(txtPeso.getText());
        Float imc2 = peso / (altura * altura);
        
        int imc = (int) Math.round(imc2);
        //BLOQUEA BOTÃO CASO TRIAGEM CLINICA RECUSADA        
        if (    ((pressao > 3) && (pressao < 8))    &&    ((imc >= 17) && (imc < 40)) ){
            btnDoar.setDisable(false);
            triagem.setVisible(false);
        }else{
            btnDoar.setDisable(true);
            triagem.setVisible(true);
        } 

    }
    
}
