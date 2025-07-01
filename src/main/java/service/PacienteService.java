/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PacienteDao;
import entity.Login;
import entity.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Breno
 */
public class PacienteService {
    
    public void cadastrar(Paciente paciente) throws SQLException{
        PacienteDao dao = new PacienteDao();
        dao.cadastrarPaciente(paciente);
    }
   
    
}
