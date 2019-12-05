/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Model.Candidato;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CandidatoDAO {

    private Connection connection;
    int id;
    String nome;
    String endereco;
    //LocalDate nascimento;
    String pai;
    String mae;

    public CandidatoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void salvar(Candidato objUsuario) {
        try {
            String sql;
            if (String.valueOf(objUsuario.getId_candidato()).isEmpty()) {
                sql = "INSERT INTO candidato(nome,endereco,pai,mae) VALUES(?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, objUsuario.getNome());
                stmt.setString(2, objUsuario.getEndereco());
                //
                stmt.setString(4, objUsuario.getPai());
                stmt.setString(5, objUsuario.getMae());
                stmt.execute();
                stmt.close();

            } else {
                sql = "UPDATE candidato SET nome = ?, endereco = ?, pai = ?, mae = ? WHERE candidato.id_candidato = ?";

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setInt(0, objUsuario.getId_candidato());
                stmt.setString(1, objUsuario.getNome());
                stmt.setString(2, objUsuario.getEndereco());
                stmt.setString(4, objUsuario.getPai());
                stmt.setString(5, objUsuario.getMae());
                stmt.execute();
                stmt.close();

            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList buscar(Candidato objUsuario) {
        try {
            String sql = "";
            if (!objUsuario.getNome().isEmpty()) {
                sql = "SELECT * FROM candidato WHERE nome LIKE '%" + objUsuario.getNome() + "%' ";

            }

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getInt("id_candidato"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("pai"),
                    rs.getString("mae")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }

    }
    
    
    public ArrayList letra(String texto) {
        try {
            String sql = "";
            if (!texto.isEmpty()) {
                sql = "SELECT * FROM candidato WHERE nome LIKE '%" + texto.toString() + "%' ";
            }

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getInt("id_candidato"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("pai"),
                    rs.getString("mae")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro preencher o ArrayList");
            return null;
        }

    }
/*
    public void deletar(Candidato objUsuario) {
        try {
            String sql;
            if (!String.valueOf(objUsuario.getId()).isEmpty()) {
                sql = "DELETE FROM candidato WHERE candidato.id_candidato = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, objUsuario.getId());
                stmt.execute();
                stmt.close();

            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
*/
    public ArrayList listarTodos() {
        try {

            ArrayList dado = new ArrayList();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM candidato");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dado.add(new Object[]{
                    rs.getInt("id_candidato"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("pai"),
                    rs.getString("mae")
                });

            }
            ps.close();
            rs.close();
            connection.close();

            return dado;
        } catch (SQLException e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList");
            return null;
        }
    }

    public static void testarConexao() throws SQLException {
        try (Connection objConnection = new ConnectionFactory().getConnection()) {
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso! ");
        }
    }

}
