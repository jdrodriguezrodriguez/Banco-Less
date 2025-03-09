package DAO;

import Model.Transaccion;
import Model.UsuarioActivo;

import javax.swing.*;
import java.sql.*;

public class TransaccionDao {

    public boolean Depositar(Transaccion transaccion) {

        String sqlTransaccion  = "insert into transaccion (num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion) values (?,?,?,?,?,?)";
        String sqlActualizarSaldo  = "update cuenta set saldo = saldo + ? where num_cuenta = ?";

        try(Connection cn = ConexionBD.conectar()) {

            cn.setAutoCommit(false);

            try(PreparedStatement pstTransaccion = cn.prepareStatement(sqlTransaccion);
                PreparedStatement pstSaldo = cn.prepareStatement(sqlActualizarSaldo)){

                pstTransaccion.setInt(1, transaccion.getNum_cuenta());
                pstTransaccion.setObject(2, transaccion.getCuentaDestino() == 0 ? null : transaccion.getCuentaDestino(), java.sql.Types.INTEGER);
                pstTransaccion.setString(3, transaccion.getTipo_entrega());
                pstTransaccion.setInt(4, transaccion.getMonto());
                pstTransaccion.setString(5, transaccion.getFecha());
                pstTransaccion.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);

                int filasAfectadas = pstTransaccion.executeUpdate();

                if (filasAfectadas > 0) {

                    pstSaldo.setInt(1, transaccion.getMonto());
                    pstSaldo.setInt(2, transaccion.getNum_cuenta());

                    int saldoActualizado = pstSaldo.executeUpdate();

                    if (saldoActualizado > 0) {
                        cn.commit();
                        return true;
                    }
                }
            }

            cn.rollback();
            return false;

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Fallo de transaccion, llame a soporte" + ex);
            System.out.println("Error en la transacción: " + ex.getMessage());
        }
        return false;
    }

    public Integer ConsultarSaldo(int num_cuenta) {
        String sqlConsultar = "select saldo from cuenta where num_cuenta = ?";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sqlConsultar)){

            pst.setInt(1, num_cuenta);

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){
                    return rs.getInt("saldo");
                }
            }

        }catch (SQLException ex){
            System.out.println("Error al consultar saldo " + ex.getMessage());
        }
        return null;
    }

    public boolean Transferir(Transaccion transaccion){
        String sqlTransaccion  = "insert into transaccion (num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion) values (?,?,?,?,?,?)";
        String sqlRetiroMonto = "update cuenta set saldo = saldo - ? where num_cuenta = ?";
        String sqlCuentaTransferir  = "update cuenta set saldo = saldo + ? where num_cuenta = ?";

        try(Connection cn = ConexionBD.conectar()) {

            cn.setAutoCommit(false);

            try(PreparedStatement pstTransaccionEnvio = cn.prepareStatement(sqlTransaccion);
                PreparedStatement pstTransaccionDestino = cn.prepareStatement(sqlTransaccion);
                PreparedStatement pstInsertar = cn.prepareStatement(sqlCuentaTransferir);
                PreparedStatement pstRetirar = cn.prepareStatement(sqlRetiroMonto)){

                pstTransaccionEnvio.setInt(1, transaccion.getNum_cuenta());
                pstTransaccionEnvio.setObject(2, transaccion.getCuentaDestino() == 0 ? null : transaccion.getCuentaDestino(), java.sql.Types.INTEGER);
                pstTransaccionEnvio.setString(3, transaccion.getTipo_entrega());
                pstTransaccionEnvio.setInt(4, transaccion.getMonto());
                pstTransaccionEnvio.setString(5, transaccion.getFecha());
                pstTransaccionEnvio.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);

                int Envio = pstTransaccionEnvio.executeUpdate();

                if (Envio > 0) {

                    pstInsertar.setInt(1, transaccion.getMonto());
                    pstInsertar.setInt(2, transaccion.getCuentaDestino());

                    int saldoActualizado = pstInsertar.executeUpdate();

                    if (saldoActualizado > 0) {
                        pstRetirar.setInt(1, transaccion.getMonto());
                        pstRetirar.setInt(2, transaccion.getNum_cuenta());

                        int saldoRetirado = pstRetirar.executeUpdate();

                        if (saldoRetirado > 0) {
                            pstTransaccionDestino.setInt(1, transaccion.getCuentaDestino());
                            pstTransaccionDestino.setObject(2, transaccion.getNum_cuenta() == 0 ? null : transaccion.getNum_cuenta(), java.sql.Types.INTEGER);
                            pstTransaccionDestino.setString(3, transaccion.getTipo_entrega());
                            pstTransaccionDestino.setInt(4, transaccion.getMonto());
                            pstTransaccionDestino.setString(5, transaccion.getFecha());
                            pstTransaccionDestino.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);

                            int Recibido = pstTransaccionDestino.executeUpdate();

                            if (Recibido > 0){
                                cn.commit();
                                return true;
                            }
                        }
                    }
                }
            }

            cn.rollback();
            return false;

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Fallo de transaccion");
            System.out.println("Error en la transacción: " + ex.getMessage());
        }
        return false;
    }
}
