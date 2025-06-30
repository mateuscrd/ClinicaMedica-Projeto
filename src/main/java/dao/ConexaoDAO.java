/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Adauto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/db_clinica_medica";
    private static final String USER = "root"; // Altere para seu usuário
    private static final String PASSWORD = "@Mateus050905"; // Altere para sua senha

    public Connection conectaBD() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ConexaoDAO: " + e.getMessage());
        }
        return conn;
    }
}
