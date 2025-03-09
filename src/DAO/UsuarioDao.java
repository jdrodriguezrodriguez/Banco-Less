package DAO;

import Model.Usuario;
import Model.UsuarioActivo;
import UI.Roles.Admin;
import UI.Roles.Tecnico;
import UI.MenuPrincipal;
import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;

public class UsuarioDao {

    public boolean LoginUsuario(Usuario usuario){
        String sql = "select rol from usuario where username = ? and password = ?";

        try(Connection cn = ConexionBD.conectar();
            PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){

                    String rol = rs.getString("rol");
                    switch (rol){
                        case "CLIENTE":
                            new MenuPrincipal().setVisible(true);
                            break;
                        case "ADMIN":
                            new Admin().setVisible(true);
                            break;
                        case "EMPLEADO":
                            new Tecnico().setVisible(true);
                            break;
                    }
                    return true;
                }
                return false;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Fallo login por conexion, llame a soporte");
            return false;
        }
    }

    public boolean CrearUsuario(Usuario usuario){
        String sql = "insert into usuario (id_persona, username, password, rol) values(?,?,?,?)";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pst.setInt(1, usuario.getIdPersona());
            pst.setString(2, usuario.getUsername());
            pst.setString(3, usuario.getPassword());
            pst.setString(4, usuario.getRol());

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0){
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()){
                        usuario.setIdUsuario(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Fallo registro usuario, llame a soporte" + e);
            System.out.println("Fallo registro usuario, llame a soporte" + e);
            return false;
        }
    }

    public UsuarioActivo IdentificarUsuario(String username){
        String sql = "select u.id_usuario, c.num_cuenta from usuario u join cuenta c on c.id_usuario = u.id_usuario where u.username = ?";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setString(1, username);

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){
                    int idUsuario = rs.getInt("id_usuario");
                    int idCuenta = rs.getInt("num_cuenta");
                    return new UsuarioActivo(idUsuario, idCuenta, username);
                }
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al identificar usuario, llame a soporte");
            System.out.println("Error al identificar usuario, llame a soporte" + e.getMessage());

        }
        return null;
    }
}
