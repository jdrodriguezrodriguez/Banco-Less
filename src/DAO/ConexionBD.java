package DAO;

import java.sql.*;
import javax.swing.*;


public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost/bancoless";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection conectar() {

        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (SQLException e) {
            System.out.println("Error conexion BD");
            JOptionPane.showMessageDialog(null, "Error conexion BD" + e.getMessage());
            return null;
        }

    }
}
