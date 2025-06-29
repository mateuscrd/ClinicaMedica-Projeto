/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Breno
 */
public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/clinica_medica?useSSL=false&allowPublicKeyRetrieval=true";
    private String user = "root";
    private String senha = "123456";

    private final Connection connection;
    
    public Conexao() throws SQLException {
        connection = DriverManager.getConnection(url, user, senha);
    }
    
    public Connection getConexao(){
        return this.connection;
    } 
}
