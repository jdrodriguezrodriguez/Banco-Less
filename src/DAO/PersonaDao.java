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

    //VALIDAR DOCUMENTO EXISTENTE
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

    //CONSULTAR DATOS DEL USUARIO
    public String[] Datosusuario(int idUsuario){
        String sql = "select p.nombre, p.apellido, p.documento, p.nacimiento from persona p join usuario u on p.id_persona = u.id_persona where u.id_usuario = ?";

        String [] datos = new String[4];

        try (Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setInt(1, idUsuario);

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){
                    datos[0] =  rs.getString("nombre");
                    datos[1] =  rs.getString("apellido");
                    datos[2] = rs.getString("documento");
                    datos[3] =  rs.getString("nacimiento");
                    //datos[4] = rs.getString("correo");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar datos del usuario" + e);
        }
        return datos;
    }

    //CAMBIAR CONTRASEÑA USUARIO
    public boolean ActualizarContraseña(int idUsuario, String pass){
        String sql = "update usuario set password = ? where id_usuario = ?";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){
            cn.setAutoCommit(false);

            pst.setString(1, pass);
            pst.setInt(2, idUsuario);

            if (pst.executeUpdate() == 0){
                cn.rollback();
                return false;
            }

            cn.commit();
            return true;

        }catch (SQLException e){
            System.err.println("Error al actualizar la contraseña: " + e.getMessage());
        }
        return false;
    }

    //CAMBIAR DATOS DEL USUARIO
    public boolean ActualizarDatosPersonales(int idUsuario, String nombre, String apellido, String nacimiento){
        String sqlBuscarID = "select id_persona from usuario where  id_usuario = ?";
        String sqlActualizarDatos = "update persona set nombre = ?, apellido = ?, nacimiento = ? where id_persona = ?";
        try (Connection cn = ConexionBD.conectar();
        PreparedStatement pstBuscar = cn.prepareStatement(sqlBuscarID)){

            pstBuscar.setInt(1, idUsuario);

            try (ResultSet rs = pstBuscar.executeQuery()){
                if (rs.next()){
                    int idPersona = rs.getInt("id_persona");

                    try (PreparedStatement pstActualizar = cn.prepareStatement(sqlActualizarDatos)){
                        pstActualizar.setString(1,nombre );
                        pstActualizar.setString(2, apellido);
                        pstActualizar.setString(3, nacimiento);
                        pstActualizar.setInt(4, idPersona );

                        return pstActualizar.executeUpdate() > 0;
                    }
                }
            }
        }catch (SQLException ex){
            System.err.println("Error al actualizar los datos del usuario: " + ex.getMessage());
        }
        return false;
    }
}
