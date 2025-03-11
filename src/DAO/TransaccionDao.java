package DAO;

import Model.Transaccion;
import Model.UsuarioActivo;

import javax.swing.*;
import java.sql.*;

public class TransaccionDao {

    //DEPOSITAR DINERO
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

                if (filasAfectadas == 0) {
                    cn.rollback();
                    return false;
                }

                pstSaldo.setInt(1, transaccion.getMonto());
                pstSaldo.setInt(2, transaccion.getNum_cuenta());

                int saldoActualizado = pstSaldo.executeUpdate();

                if (saldoActualizado == 0) {
                    cn.rollback(); //RESTABLECER CAMBIOS
                    return false;
                }

                cn.commit(); //CONFIRMAR CAMBIOS
                return true;
            }catch(SQLException ex){
                cn.rollback();
                System.err.println("Error en el deposito: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Fallo en el deposito, llame a soporte.");
            }
        }catch (SQLException ex){
            System.err.println("Error de conexión: " + ex.getMessage());
        }
        return false;
    }

    //CONSULTAR DINERO
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
            return -1;
        }
        return -1;
    }

    //TRANFERIR DINERO
    public boolean Transferir(Transaccion transaccion){
        String sqlTransaccion  = "insert into transaccion (num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion) values (?,?,?,?,?,?)";
        String sqlRetiroMonto = "update cuenta set saldo = saldo - ? where num_cuenta = ?";
        String sqlCuentaTransferir  = "update cuenta set saldo = saldo + ? where num_cuenta = ?";

        try(Connection cn = ConexionBD.conectar()) {

            cn.setAutoCommit(false);

            try(PreparedStatement pstTransaccion = cn.prepareStatement(sqlTransaccion);
                PreparedStatement pstCuentaDestino = cn.prepareStatement(sqlCuentaTransferir);
                PreparedStatement pstCuentaEnvio = cn.prepareStatement(sqlRetiroMonto)){

                //RETIRAR DE CUENTA ORIGEN
                pstCuentaEnvio.setInt(1, transaccion.getMonto());
                pstCuentaEnvio.setInt(2, transaccion.getNum_cuenta());
                if (pstCuentaEnvio.executeUpdate() == 0) {
                    System.out.println("Fallo RETIRAR DE CUENTA ORIGEN");
                    cn.rollback();
                    return false;
                }

                //DEPOSITAR EN CUENTA DESTINO
                pstCuentaDestino.setInt(1, transaccion.getMonto());
                pstCuentaDestino.setInt(2, transaccion.getCuentaDestino());
                if (pstCuentaDestino.executeUpdate() == 0){
                    System.out.println("Fallo DEPOSITAR EN CUENTA DESTINO");
                    cn.rollback();
                    return false;
                }

                //REGISTRAR HISTORIAL TRANSACCION - ORIGEN
                pstTransaccion.setInt(1, transaccion.getNum_cuenta());
                pstTransaccion.setObject(2, transaccion.getCuentaDestino() == 0 ? null : transaccion.getCuentaDestino(), java.sql.Types.INTEGER);
                pstTransaccion.setString(3, transaccion.getTipo_entrega());
                pstTransaccion.setInt(4, transaccion.getMonto());
                pstTransaccion.setString(5, transaccion.getFecha());
                pstTransaccion.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);

                if (pstTransaccion.executeUpdate() == 0) {
                    System.out.println("Fallo REGISTRAR HISTORIAL TRANSACCION - ORIGEN");
                    cn.rollback();
                    return false;
                }

                // REGISTRAR TRANSACCIÓN - DESTINO
                pstTransaccion.setInt(1, transaccion.getCuentaDestino());
                pstTransaccion.setObject(2, transaccion.getNum_cuenta() == 0 ? null : transaccion.getNum_cuenta(), java.sql.Types.INTEGER);
                pstTransaccion.setString(3, transaccion.getTipo_entrega());
                pstTransaccion.setInt(4, transaccion.getMonto());
                pstTransaccion.setString(5, transaccion.getFecha());
                pstTransaccion.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);

                if (pstTransaccion.executeUpdate() == 0) {
                    System.out.println("fallo REGISTRAR TRANSACCIÓN - DESTINO");
                    cn.rollback();
                    return false;
                }

                cn.commit();
                return true;

            }catch (SQLException ex){
                cn.rollback();
                System.err.println("Error en la transacción: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Fallo de transacción, llame a soporte.");
            }
        }catch (SQLException ex){
            System.err.println("Error de conexión: " + ex.getMessage());
        }
        return false;
    }
}
