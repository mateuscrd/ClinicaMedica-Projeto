/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Breno
 */
public class LoginDao {
    
    private Connection connection;
    
    public LoginDao() throws SQLException {
        connection = new Conexao().getConexao(); 
    }
        
    public Login buscarUsuario(String usuario, String senha) throws SQLException {
        String sql = "select * from login where usuario = ? and senha = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, usuario);
        preparedStatement.setString(2, senha);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next() == true) {
            Login login = new Login();
            login.setUsuario(resultSet.getString("usuario"));
            login.setSenha(resultSet.getString("senha"));
            return login;
        }
        return null;
    }
    
}
