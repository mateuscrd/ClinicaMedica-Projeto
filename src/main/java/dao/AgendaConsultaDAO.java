/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Adauto
 */
import entity.Especialidade;
import java.sql.Connection;
import util.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AgendaConsultaDAO {

    public void create(Especialidade especialidade) {
        String sql = "INSERT INTO especialidades (Descricao_Especialidade) VALUES (?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, especialidade.getDescricao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Especialidade salva com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar especialidade: " + ex.getMessage());
        }
    }

    public List<Especialidade> read() {
        String sql = "SELECT * FROM especialidades";
        List<Especialidade> especialidades = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Especialidade esp = new Especialidade();
                esp.setId(rs.getInt("Codigo_Especialidade"));
                esp.setDescricao(rs.getString("Descricao_Especialidade"));
                especialidades.add(esp);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler especialidades: " + ex.getMessage());
        }
        return especialidades;
    }

    public void update(Especialidade especialidade) {
        String sql = "UPDATE especialidades SET Descricao_Especialidade = ? WHERE Codigo_Especialidade = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, especialidade.getDescricao());
            stmt.setInt(2, especialidade.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Especialidade atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar especialidade: " + ex.getMessage());
        }
    }

    public void delete(Especialidade especialidade) {
        String sql = "DELETE FROM especialidades WHERE Codigo_Especialidade = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, especialidade.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Especialidade excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir especialidade: " + ex.getMessage());
        }
    }
}
