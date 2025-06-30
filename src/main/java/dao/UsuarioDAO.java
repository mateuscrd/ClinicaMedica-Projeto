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
import entity.Usuario;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public void create(Usuario usuario) {
        String sql = "INSERT INTO usuarios (Identificacao_Usuario, Senha_Acesso, Codigo_Funcionario_FK, " +
                     "Cadastro_Funcionario, Cadastro_Usuario, Cadastro_Especialidade, Cadastro_Medico, Cadastro_Convenio, " +
                     "Agendamento_Consulta, Cancelamento_Consulta, Modulo_Administrativo, Modulo_Agendamento, Modulo_Atendimento) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha()); // Idealmente, armazenar um hash da senha
            stmt.setInt(3, usuario.getFuncionario().getId());
            stmt.setBoolean(4, usuario.isPermissaoCadFuncionario());
            stmt.setBoolean(5, usuario.isPermissaoCadUsuario());
            stmt.setBoolean(6, usuario.isPermissaoCadEspecialidade());
            stmt.setBoolean(7, usuario.isPermissaoCadMedico());
            stmt.setBoolean(8, usuario.isPermissaoCadConvenio());
            stmt.setBoolean(9, usuario.isPermissaoAgendamento());
            stmt.setBoolean(10, usuario.isPermissaoCancelamento());
            stmt.setBoolean(11, usuario.isAcessoModuloAdmin());
            stmt.setBoolean(12, usuario.isAcessoModuloAgendamento());
            stmt.setBoolean(13, usuario.isAcessoModuloAtendimento());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar usuário: " + ex.getMessage());
        }
    }
    
    public List<Usuario> read() {
        String sql = "SELECT u.*, f.Nome_Completo FROM usuarios u INNER JOIN funcionarios f ON u.Codigo_Funcionario_FK = f.Codigo_Funcionario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("Codigo_Funcionario_FK"));
                func.setNome(rs.getString("Nome_Completo"));

                Usuario user = new Usuario();
                user.setId(rs.getInt("Codigo_Usuario"));
                user.setLogin(rs.getString("Identificacao_Usuario"));
                user.setFuncionario(func);
                user.setAcessoModuloAdmin(rs.getBoolean("Modulo_Administrativo"));
                user.setAcessoModuloAgendamento(rs.getBoolean("Modulo_Agendamento"));
                user.setAcessoModuloAtendimento(rs.getBoolean("Modulo_Atendimento"));
                // Carregar demais permissões...
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários: " + ex.getMessage());
        }
        return usuarios;
    }

    public void update(Usuario usuario) {
        // Implementar a lógica de UPDATE similar ao CREATE, mas com WHERE Codigo_Usuario = ?
    }
    
    public void delete(Usuario usuario) {
        // Implementar a lógica de DELETE FROM usuarios WHERE Codigo_Usuario = ?
    }
    
    public Usuario checkLogin(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE Identificacao_Usuario = ? AND Senha_Acesso = ?";
        Usuario usuario = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("Codigo_Usuario"));
                    usuario.setLogin(rs.getString("Identificacao_Usuario"));
                    usuario.setAcessoModuloAdmin(rs.getBoolean("Modulo_Administrativo"));
                    usuario.setAcessoModuloAgendamento(rs.getBoolean("Modulo_Agendamento"));
                    usuario.setAcessoModuloAtendimento(rs.getBoolean("Modulo_Atendimento"));
                    // Carregar outras permissões...
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar login: " + ex.getMessage());
        }
        return usuario;
    }
}
