/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Adauto
 */
import entity.Convenio;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

       
public class ConvenioDAO {

    public void create(Convenio convenio) {
        String sql = "INSERT INTO convenios (Empresa_Convenio, CNPJ, Telefone) VALUES (?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, convenio.getNomeEmpresa());
            stmt.setString(2, convenio.getCnpj());
            stmt.setString(3, convenio.getTelefone());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Convênio salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar convênio: " + ex.getMessage());
        }
    }

    public List<Convenio> read() {
        String sql = "SELECT * FROM convenios";
        List<Convenio> convenios = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Convenio conv = new Convenio();
                conv.setId(rs.getInt("Codigo_Convenio"));
                conv.setNomeEmpresa(rs.getString("Empresa_Convenio"));
                conv.setCnpj(rs.getString("CNPJ"));
                conv.setTelefone(rs.getString("Telefone"));
                convenios.add(conv);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler convênios: " + ex.getMessage());
        }
        return convenios;
    }

    public void update(Convenio convenio) {
        String sql = "UPDATE convenios SET Empresa_Convenio = ?, CNPJ = ?, Telefone = ? WHERE Codigo_Convenio = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, convenio.getNomeEmpresa());
            stmt.setString(2, convenio.getCnpj());
            stmt.setString(3, convenio.getTelefone());
            stmt.setInt(4, convenio.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Convênio atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar convênio: " + ex.getMessage());
        }
    }

    public void delete(Convenio convenio) {
        String sql = "DELETE FROM convenios WHERE Codigo_Convenio = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, convenio.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Convênio excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir convênio: " + ex.getMessage());
        }
    }
}
