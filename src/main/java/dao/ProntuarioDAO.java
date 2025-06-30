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
import entity.Paciente;
import entity.Prontuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProntuarioDAO {
    
    private Connection conn;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
    public ProntuarioDAO() {
        this.conn = new ConexaoDAO().conectaBD();
    }

    /**
     * Registra um novo atendimento (prontuário) para um paciente.
     * @param obj O objeto ProntuarioPaciente com os dados do atendimento.
     */
    public void registrarAtendimento(Prontuario obj) {
        String sql = "INSERT INTO prontuario_paciente (Codigo_Paciente_FK, Historico_Medico, Receituario, Exames_Solicitados, Data_Registro) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getPaciente().getId());
            stmt.setString(2, obj.getHistorico());
            stmt.setString(3, obj.getReceituario());
            stmt.setString(4, obj.getExames());
            stmt.setTimestamp(5, Timestamp.valueOf(obj.getDataRegistro()));

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Registro de atendimento salvo com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar registro de atendimento: " + e.getMessage());
        }
    }
    
    /**
     * Lista todos os registros de prontuário de um paciente específico.
     * @param idPaciente O ID do paciente para buscar o histórico.
     * @return Uma lista de objetos ProntuarioPaciente.
     */
    public List<Prontuario> listarProntuariosPorPaciente(int idPaciente) {
        List<Prontuario> lista = new ArrayList<>();
        // Ordena os resultados pela data de registro, do mais recente para o mais antigo.
        String sql = "SELECT * FROM prontuario_paciente WHERE Codigo_Paciente_FK = ? ORDER BY Data_Registro DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prontuario prontuario = new Prontuario();
                    prontuario.setId(rs.getInt("Registro_Prontuario"));
                    prontuario.setHistorico(rs.getString("Historico_Medico"));
                    prontuario.setReceituario(rs.getString("Receituario"));
                    prontuario.setExames(rs.getString("Exames_Solicitados"));
                    prontuario.setDataRegistro(rs.getTimestamp("Data_Registro").toLocalDateTime());
                    
                    // Cria um objeto Paciente apenas com o ID para manter a referência
                    Paciente p = new Paciente();
                    p.setId(rs.getInt("Codigo_Paciente_FK"));
                    prontuario.setPaciente(p);
                    
                    lista.add(prontuario);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar o histórico do paciente: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza um registro de prontuário existente.
     * @param obj O objeto ProntuarioPaciente com os dados a serem atualizados.
     */
    public void atualizarProntuario(Prontuario obj) {
        String sql = "UPDATE prontuario_paciente SET Historico_Medico = ?, Receituario = ?, Exames_Solicitados = ? " +
                     "WHERE Registro_Prontuario = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getHistorico());
            stmt.setString(2, obj.getReceituario());
            stmt.setString(3, obj.getExames());
            stmt.setInt(4, obj.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Registro de prontuário atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o prontuário: " + e.getMessage());
        }
    }
    
    /**
     * Exclui um registro de prontuário do banco de dados.
     * @param obj O objeto ProntuarioPaciente contendo o ID a ser excluído.
     */
    public void excluirProntuario(Prontuario obj) {
        String sql = "DELETE FROM prontuario_paciente WHERE Registro_Prontuario = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Registro de prontuário excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o prontuário: " + e.getMessage());
        }
    }
}
