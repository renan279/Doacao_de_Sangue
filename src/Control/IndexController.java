/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import MySQL.Banco;
import Model.Candidato;
import Model.ModelTable;
import MySQL.CandidatoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author a1428721
 */
public class IndexController implements Initializable {
    
    private Candidato objUsuario;
    private CandidatoDAO objCandidatoDAO;
    private final boolean buscar = false;
    
    
    @FXML
    private Pane painel;
    @FXML
    private MenuBar menuBarra;
    @FXML
    private Menu menuDoar;
    @FXML
    private MenuItem itemDoar;
    @FXML
    private Menu menuGrafico;
    @FXML
    private MenuItem itemGraficoCandidato;
    @FXML
    private MenuItem itemGraficoTeste;
    @FXML
    private MenuItem itemGraficoSangue;
    @FXML
    private Menu menuAjuda;
    @FXML
    private MenuItem itemAjuda;
    @FXML
    private MenuItem itemSair;
    private Pane tela;
    @FXML
    private Pane telaCadastro;
    @FXML
    private ImageView logo;
    @FXML
    private Button btnFecharTelaCadastro;
    @FXML
    private Pane telaGrafico;
    @FXML
    private Button btnVoltarTelaGrafico;
    @FXML
    private Pane telaTeste;
    @FXML
    private Button btnProximoTelaTeste;
    @FXML
    private ToggleGroup grupoRh;
    @FXML
    private ToggleGroup grupoPAI;
    @FXML
    private Pane telaHistorico;
    @FXML
    private Button btnVoltarTelaHistorico;
    @FXML
    private Button btnConfirmarTelaCadastro;
    @FXML
    private Button btnSangue;
    @FXML
    private Button btnSexo;
    @FXML
    private Button btnDoenca;
    @FXML
    private ComboBox<String> comboBoxSangue;
    @FXML
    private Button BtnBuscarTelaCadastro;
    @FXML
    private ToggleGroup grupoAnemia;
    @FXML
    private ToggleGroup grupoColeta;
    @FXML
    private DatePicker txtNascimento;
    @FXML
    private TextField txtPai;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtMae;
    @FXML
    private TextField txtBuscarNome;
    @FXML
    private TextField txtPressaoMa;
    @FXML
    private TextField txtPressaoMe;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextField txtPeso;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private ComboBox<Candidato> cbCliente;
    
    
    List<Candidato> listaCandidato = new ArrayList<>();    
    ObservableList<Candidato> candidatos = FXCollections.observableArrayList();    
    @FXML
    private TableView<Candidato> tableDoacao;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //logo.setVisible(true);        
        ObservableList<String> abo = FXCollections.observableArrayList();
        abo.addAll("A","B","AB","O");
        comboBoxSangue.itemsProperty().setValue(abo);    
    }    

    @FXML
    private void itemAjudaAction(ActionEvent event) {
        JOptionPane.showMessageDialog(null,
                  "Bem vindo ao software da Fundação Pro-Sangue."
                + "\n\nNo botão 'Doar' pode-se cadastrar os dados do paciente e realizar a coleta de sangue;"
                + "\nNo botão 'Gráfico' pode-se gerar gráficos dos dados armazenados de acordo com três parâmetros: por sexo, por tipo de doenças encontradas e por tipo sanguineo;"
                + "\nNo botão 'Ajuda' pode-se visualizar essa mensagem por meio do botão 'Sobre' enquanto o botão 'Sair' encerra o aplicativo."
                + "\n\nQualquer dúvida entre em contato com os desenvolvedores por meio do e-mail 'renanfrancisco.on@gmail.com' ou pelo telefone +55(42)998100170."
                + "\n\nObrigado por usar nosso programa."
                ); 
    }
    
    @FXML
    private void itemSairAction(ActionEvent event){
        Platform.exit();
    }

    @FXML
    private void itemDoarAction(ActionEvent event) {
        telaCadastro.setVisible(true);
        telaTeste.setVisible(false);
        telaHistorico.setVisible(false);
        telaGrafico.setVisible(false);
        logo.setVisible(false);
    }

    @FXML
    private void btnFechar(ActionEvent event) {
        telaCadastro.setVisible(false);
        telaGrafico.setVisible(false);
        telaHistorico.setVisible(false);
        telaTeste.setVisible(false);
        logo.setVisible(true);
    }

    @FXML
    private void btnConfirmarTelaCadastro1(ActionEvent event) {
        //VALIDAÇÃO DOS CAMPOS 
        if("".equals(txtNome.getText()) || "".equals(txtEndereco.getText()) || "".equals(txtNascimento.getValue()) || 
            "".equals(txtPai.getText()) || "".equals(txtMae.getText())){
             JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        }  
        else{
            
            telaCadastro.setVisible(false);
            telaTeste.setVisible(true);
            
            
            Banco banco = new Banco();
            try {
                Candidato c = new Candidato(txtNome.getText(), txtEndereco.getText(), txtNascimento.getValue(), txtPai.getText(), txtMae.getText());
                banco.cadastroCandidato(c.getNome(), c.getEndereco(), c.getNascimento(), c.getPai(), c.getMae());
                //banco.cadastroCandidato(c);
                
                JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso! ");   
            }
            catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao criar candidato.");
            }  
        }  
        
        
    }

    @FXML
    private void btnGraficoSexo(ActionEvent event) {
        logo.setVisible(false);
        telaCadastro.setVisible(false);
        telaHistorico.setVisible(false);
        telaTeste.setVisible(false);
        telaGrafico.setVisible(true);
    }

    @FXML
    private void btnGraficoDoenca(ActionEvent event) {
        logo.setVisible(false);
        telaCadastro.setVisible(false);
        telaHistorico.setVisible(false);
        telaTeste.setVisible(false);
        telaGrafico.setVisible(true);
    }

    @FXML
    private void btnGraficoSangue(ActionEvent event) {
        logo.setVisible(false);
        telaCadastro.setVisible(false);
        telaHistorico.setVisible(false);
        telaTeste.setVisible(false);
        telaGrafico.setVisible(true);
    }

    @FXML
    private void btnProximoTelaTeste1(ActionEvent event) {        
        telaTeste.setVisible(false);
        telaHistorico.setVisible(true);               
    }

    @FXML
    private void btnBuscar(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        objCandidatoDAO = new CandidatoDAO();
        ArrayList dados = new ArrayList();

        if (buscar) {
            dados = objCandidatoDAO.buscar(objUsuario);
            //dados = objDAO.buscar(objUsuario);
        } else {
            objUsuario = new Candidato();
            dados = objCandidatoDAO.listarTodos();
            txtBuscarNome.setText("FOI");
        }
        ObservableList<Candidato> oListCandidatos = FXCollections.observableArrayList(dados);
        cbCliente.itemsProperty().setValue(oListCandidatos);
        
        System.out.println(dados.toString());
        
        
    }

    @FXML
    private void triagemClinica(KeyEvent event) {
        Integer pM = Integer.parseInt(txtPressaoMa.getText()) ;
        Integer pN = Integer.parseInt(txtPressaoMe.getText()) ;
        Integer pressao = pM - pN;
        
        Float altura = Float.parseFloat(txtAltura.getText());
        Float peso = Float.parseFloat(txtPeso.getText());
        Float imc2 = peso / (altura * altura);
        int imc = (int) Math.round(imc2);
                
        if ( ((pressao >= 4) && (pressao <= 7)) && ((imc >= 17) && (imc < 40)) ){
            btnProximoTelaTeste.setDisable(false);
        }else{
            JOptionPane.showMessageDialog(null, "Triagem Clinica Recusada");
            btnProximoTelaTeste.setDisable(true);
        }  
    }

    public String toString() {
       return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    
}
