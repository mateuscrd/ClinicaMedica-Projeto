/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Adauto
 */
import entity.Funcionario;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    public void create(Funcionario func) {
        String sql = "INSERT INTO funcionarios (Nome_Completo, Numero_RG, Numero_CPF, Endereco, Numero, Complemento, Bairro, Cidade, Estado, CEP, Telefone, Celular, Numero_CTPS, Numero_PIS, Data_Nascimento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getRg());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getEndereco());
            stmt.setString(5, func.getNumero());
            stmt.setString(6, func.getComplemento());
            stmt.setString(7, func.getBairro());
            stmt.setString(8, func.getCidade());
            stmt.setString(9, func.getEstado());
            stmt.setString(10, func.getCep());
            stmt.setString(11, func.getTelefoneFixo());
            stmt.setString(12, func.getCelular());
            stmt.setString(13, func.getCtps());
            stmt.setString(14, func.getPis());
            stmt.setDate(15, new Date(func.getDataNascimento().getTime()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar funcionário: " + ex.getMessage());
        }
    }

    public List<Funcionario> read() {
        String sql = "SELECT * FROM funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("Codigo_Funcionario"));
                func.setNome(rs.getString("Nome_Completo"));
                func.setRg(rs.getString("Numero_RG"));
                func.setCpf(rs.getString("Numero_CPF"));
                func.setEndereco(rs.getString("Endereco"));
                func.setNumero(rs.getString("Numero"));
                func.setComplemento(rs.getString("Complemento"));
                func.setBairro(rs.getString("Bairro"));
                func.setCidade(rs.getString("Cidade"));
                func.setEstado(rs.getString("Estado"));
                func.setCep(rs.getString("CEP"));
                func.setTelefoneFixo(rs.getString("Telefone"));
                func.setCelular(rs.getString("Celular"));
                func.setCtps(rs.getString("Numero_CTPS"));
                func.setPis(rs.getString("Numero_PIS"));
                func.setDataNascimento(rs.getDate("Data_Nascimento"));
                funcionarios.add(func);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler funcionários: " + ex.getMessage());
        }
        return funcionarios;
    }

    public void update(Funcionario func) {
        String sql = "UPDATE funcionarios SET Nome_Completo = ?, Numero_RG = ?, Numero_CPF = ?, Endereco = ?, Numero = ?, Complemento = ?, Bairro = ?, Cidade = ?, Estado = ?, CEP = ?, Telefone = ?, Celular = ?, Numero_CTPS = ?, Numero_PIS = ?, Data_Nascimento = ? WHERE Codigo_Funcionario = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getRg());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getEndereco());
            stmt.setString(5, func.getNumero());
            stmt.setString(6, func.getComplemento());
            stmt.setString(7, func.getBairro());
            stmt.setString(8, func.getCidade());
            stmt.setString(9, func.getEstado());
            stmt.setString(10, func.getCep());
            stmt.setString(11, func.getTelefoneFixo());
            stmt.setString(12, func.getCelular());
            stmt.setString(13, func.getCtps());
            stmt.setString(14, func.getPis());
            stmt.setDate(15, new Date(func.getDataNascimento().getTime()));
            stmt.setInt(16, func.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + ex.getMessage());
        }
    }

    public void delete(Funcionario func) {
        String sql = "DELETE FROM funcionarios WHERE Codigo_Funcionario = ?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, func.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário: " + ex.getMessage());
        }
    }
}
