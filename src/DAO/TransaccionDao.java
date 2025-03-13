package DAO;

import Model.Transaccion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDao {

    //DEPOSITAR DINERO
    public boolean Depositar(Transaccion transaccion) {
        try(Connection cn = ConexionBD.conectar()) {
            cn.setAutoCommit(false);

            if (!depositarSaldo(cn, transaccion.getNum_cuenta(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!registrarTransaccion(cn, transaccion.getNum_cuenta(), transaccion.getCuentaDestino(), "DEPOSITO", transaccion)) {
                cn.rollback();
                return false;
            }

            cn.commit();
            return true;

        }catch (SQLException ex){
            System.err.println("Error en el deposito: " + ex.getMessage());
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
        try(Connection cn = ConexionBD.conectar()){
            cn.setAutoCommit(false);

            if (!retirarSaldo(cn, transaccion.getNum_cuenta(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!depositarSaldo(cn, transaccion.getCuentaDestino(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!registrarTransaccion(cn, transaccion.getNum_cuenta(), transaccion.getCuentaDestino(), "TRANSFERENCIA", transaccion)) {
                cn.rollback();
                return false;
            }

            if (!registrarTransaccion(cn, transaccion.getCuentaDestino(), transaccion.getNum_cuenta(), "TRANSFERENCIA", transaccion)) {
                cn.rollback();
                return false;
            }

            cn.commit();
            return true;

        }catch (SQLException ex){
            System.err.println("Error en la transacción: " + ex.getMessage());
        }
        return false;
    }

    //HISTORIAL DE TRASPASOS
    public List<Object[]> ConsultarHistorial(int num_cuenta) {
        String sql = "select id_transaccion, num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion from transaccion where num_cuenta = ?";
        List<Object[]> historial = new ArrayList<>();

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setInt(1, num_cuenta);

            try(ResultSet rs = pst.executeQuery()){
                while (rs.next()){

                    Object[] datos = new Object[7];

                    for (int i = 0; i < 7; i++){
                        datos[i] = rs.getObject(i + 1);
                    }
                    historial.add(datos);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar historial: " + ex.getMessage());
        }
        return historial;
    }

    private boolean retirarSaldo(Connection cn, int numCuenta, int monto) throws SQLException{
        String sql = "update cuenta set saldo = saldo - ? where num_cuenta = ?";
        try(PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, monto);
            pst.setInt(2, numCuenta);
            return pst.executeUpdate() > 0;
        }
    }

    private boolean depositarSaldo(Connection cn, int numCuenta, int monto) throws SQLException{
        String sql = "update cuenta set saldo = saldo + ? where num_cuenta = ?";
        try(PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, monto);
            pst.setInt(2, numCuenta);
            return pst.executeUpdate() > 0;
        }
    }

    private boolean registrarTransaccion(Connection cn, int cuentaOrige, int cuentaDestino, String tipo, Transaccion transaccion) throws SQLException{
        String sql  = "insert into transaccion (num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion) values (?,?,?,?,?,?)";
        try(PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, cuentaOrige);
            pst.setObject(2, cuentaDestino == 0 ? null : cuentaDestino, java.sql.Types.INTEGER);
            pst.setString(3, tipo);
            pst.setInt(4, transaccion.getMonto());
            pst.setString(5, transaccion.getFecha());
            pst.setObject(6, transaccion.getDescripcion() == null ? null : transaccion.getDescripcion(), java.sql.Types.VARCHAR);
            return pst.executeUpdate() > 0;
        }
    }
}
