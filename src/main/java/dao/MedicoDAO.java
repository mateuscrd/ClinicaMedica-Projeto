/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Adauto
 */
import dao.ConexaoDAO;
import entity.Medico;
import entity.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicoDAO {

    private Connection conn;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
    public MedicoDAO() {
        this.conn = new ConexaoDAO().conectaBD();
    }

    /**
     * Cadastra um novo médico no banco de dados.
     * @param obj O objeto Medico contendo os dados a serem cadastrados.
     */
    public void cadastrarMedico(Medico obj) {
        String sql = "INSERT INTO medicos (Nome_Medico, CRM, Codigo_Especialidade_FK) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCrm());
            stmt.setInt(3, obj.getEspecialidade().getId()); // Pega o ID do objeto Especialidade aninhado
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico: " + e.getMessage());
        }
    }

    /**
     * Lista todos os médicos cadastrados, juntando os dados da especialidade.
     * @return Uma lista de objetos Medico.
     */
    public List<Medico> listarMedicos() {
        List<Medico> lista = new ArrayList<>();
        // O SQL faz a junção (JOIN) para buscar a descrição da especialidade junto com os dados do médico
        String sql = "SELECT m.Codigo_Medico, m.Nome_Medico, m.CRM, " +
                     "e.Codigo_Especialidade, e.Descricao_Especialidade " +
                     "FROM medicos AS m INNER JOIN especialidades AS e " +
                     "ON m.Codigo_Especialidade_FK = e.Codigo_Especialidade";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // 1. Cria e preenche o objeto Especialidade
                Especialidade esp = new Especialidade();
                esp.setId(rs.getInt("Codigo_Especialidade"));
                esp.setDescricao(rs.getString("Descricao_Especialidade"));
                
                // 2. Cria e preenche o objeto Medico
                Medico medico = new Medico();
                medico.setId(rs.getInt("Codigo_Medico"));
                medico.setNome(rs.getString("Nome_Medico"));
                medico.setCrm(rs.getString("CRM"));
                
                // 3. Associa a especialidade ao médico
                medico.setEspecialidade(esp);
                
                // 4. Adiciona o médico completo à lista
                lista.add(medico);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar médicos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os dados de um médico existente no banco de dados.
     * @param obj O objeto Medico com os dados atualizados.
     */
    public void atualizarMedico(Medico obj) {
        String sql = "UPDATE medicos SET Nome_Medico = ?, CRM = ?, Codigo_Especialidade_FK = ? WHERE Codigo_Medico = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCrm());
            stmt.setInt(3, obj.getEspecialidade().getId());
            stmt.setInt(4, obj.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar médico: " + e.getMessage());
        }
    }

    /**
     * Exclui um médico do banco de dados.
     * @param obj O objeto Medico contendo o ID a ser excluído.
     */
    public void excluirMedico(Medico obj) {
        String sql = "DELETE FROM medicos WHERE Codigo_Medico = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Médico excluído com sucesso!");

        } catch (SQLException e) {
            // Este erro pode ocorrer se o médico estiver vinculado a consultas existentes
            JOptionPane.showMessageDialog(null, "Erro ao excluir médico: " + e.getMessage());
        }
    }
}
