/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Login;
import entity.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import service.PacienteService;

/**
 *
 * @author Breno
 */
public class PacienteDao {
    
     private Connection connection;
    
    public PacienteDao() throws SQLException {
        connection = new Conexao().getConexao(); 
    }
    
    
     
    public boolean cadastrar(Paciente paciente) throws SQLException{
        String sql = "INSERT INTO PACIENTE(NOME, IDADE, CPF) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, paciente.getNome());
        preparedStatement.setInt(2, paciente.getIdade());
        preparedStatement.setString(3, paciente.getCpf());
        int linhasAlteradas = preparedStatement.executeUpdate();
        
        return linhasAlteradas==1; //se for igual a 1, um registro salvo
    }
    
}
