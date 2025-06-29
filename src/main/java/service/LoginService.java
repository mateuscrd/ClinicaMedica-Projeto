/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.LoginDao;
import entity.Login;
import java.sql.SQLException;

/**
 *
 * @author Breno
 */
public class LoginService {
    
    public Login loginExiste(String usuario, String senha) throws SQLException{
        LoginDao dao = new LoginDao();
        return dao.buscarUsuario(usuario, senha);
    }
     
}
