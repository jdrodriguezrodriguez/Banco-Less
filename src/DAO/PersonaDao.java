package DAO;

import Model.Persona;
import java.sql.*;

import javax.swing.*;

public class PersonaDao {

    //REGISTRAR PERSONA
    public boolean InsertarPersona(Persona persona) throws SQLException{

        String sql = "insert into persona (nombre, apellido, documento, nacimiento) values(?,?,?,?)";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){  //OBTENER CLAVES GENERADAS

            pst.setString(1, persona.getNombre());
            pst.setString(2, persona.getApellido());
            pst.setString(3, persona.getDocumento());
            pst.setString(4, persona.getNacimiento());

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                try(ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        persona.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;

        }catch (SQLException ex){
            throw new SQLException("Error al insertar persona", ex);
        }
    }

    public boolean DocumentoExistente(String documento) throws SQLException{
        String sql = "select documento from persona where documento = ?";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setString(1, documento);

            try(ResultSet rs = pst.executeQuery()){
                return rs.next();
            }

        }catch (SQLException ex){
            throw new SQLException("Error al validar documento personal", ex);
        }
    }
}
