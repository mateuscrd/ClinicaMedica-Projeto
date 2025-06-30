/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.ConexaoDAO;
import entity.Convenio;
import entity.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Breno
 */
public class PacienteDao {
    
    private final Connection conn;

    public PacienteDao() {
        this.conn = new ConexaoDAO().conectaBD();
    }

    public void cadastrarPaciente(Paciente obj) {
        String sql = "INSERT INTO pacientes (Nome_Paciente, Numero_RG, Orgao_Emissor, Numero_CPF, Endereco, Numero, " +
                     "Complemento, Bairro, Cidade, Estado, Telefone, Celular, Data_Nascimento, Sexo, " +
                     "Possui_Convenio, Codigo_Convenio_FK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getNomeCompleto());
            stmt.setString(2, obj.getNumeroRg());
            stmt.setString(3, obj.getOrgaoEmissor());
            stmt.setString(4, obj.getNumeroCpf());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getNumero());
            stmt.setString(7, obj.getComplemento());
            stmt.setString(8, obj.getBairro());
            stmt.setString(9, obj.getCidade());
            stmt.setString(10, obj.getEstado());
            stmt.setString(11, obj.getTelefone());
            stmt.setString(12, obj.getCelular());
            stmt.setDate(13, Date.valueOf(obj.getDataNascimento()));
            stmt.setString(14, obj.getSexo());
            stmt.setBoolean(15, obj.isPossuiConvenio());

            if (obj.isPossuiConvenio() && obj.getConvenio() != null) {
                stmt.setInt(16, obj.getConvenio().getId());
            } else {
                stmt.setNull(16, Types.INTEGER);
            }
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.Empresa_Convenio FROM pacientes as p LEFT JOIN convenios as c ON p.Codigo_Convenio_FK = c.Codigo_Convenio";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Paciente obj = new Paciente();
                obj.setId(rs.getInt("Codigo_Paciente"));
                obj.setNomeCompleto(rs.getString("Nome_Paciente"));
                // Preencher outros campos...
                
                obj.setPossuiConvenio(rs.getBoolean("Possui_Convenio"));
                if (obj.isPossuiConvenio()) {
                    Convenio conv = new Convenio();
                    conv.setId(rs.getInt("Codigo_Convenio_FK"));
                    conv.setNomeEmpresa(rs.getString("Empresa_Convenio"));
                    obj.setConvenio(conv);
                }
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }
}
