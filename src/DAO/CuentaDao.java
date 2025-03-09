package DAO;

import Model.Cuenta;

import javax.swing.*;
import java.sql.*;

public class CuentaDao {

    public boolean InsertarCuenta(Cuenta cuenta){

        String sql = "insert into cuenta (num_cuenta, id_usuario, saldo, estado)values(?,?,?,?)";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pst.setInt(1 , cuenta.getNum_cuenta());
            pst.setInt(2, cuenta.getIdUsuario());
            pst.setInt(3, cuenta.getSaldo());
            pst.setString(4, cuenta.getEstado());

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0){
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()){
                        cuenta.setNum_cuenta(rs.getInt(1));
                    }
                }
                return true;
            }
            JOptionPane.showMessageDialog(null, "Fallo id cuenta");
            return false;

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Fallo registro cuenta, llame a soporte" + e);
            return false;
        }
    }
}
