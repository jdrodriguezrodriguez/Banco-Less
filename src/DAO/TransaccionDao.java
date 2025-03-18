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

            if (!DepositarSaldo(cn, transaccion.getNum_cuenta(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!RegistrarTransaccion(cn, transaccion.getNum_cuenta(), transaccion.getCuentaDestino(), "DEPOSITO", transaccion)) {
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

            if (!RetirarSaldo(cn, transaccion.getNum_cuenta(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!DepositarSaldo(cn, transaccion.getCuentaDestino(), transaccion.getMonto())){
                cn.rollback();
                return false;
            }

            if (!RegistrarTransaccion(cn, transaccion.getNum_cuenta(), transaccion.getCuentaDestino(), "TRANSFERENCIA", transaccion)) {
                cn.rollback();
                return false;
            }

            if (!RegistrarTransaccion(cn, transaccion.getCuentaDestino(), transaccion.getNum_cuenta(), "TRANSFERENCIA", transaccion)) {
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

    //CONSULTAR CUENTA TRASPASO
    public boolean ConsultarCuenta(int cuenta_destino) {
        String sql = "select num_cuenta from cuenta where num_cuenta = ?";

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setInt(1, cuenta_destino);

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()){
                    return true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al mostrar consultar cuenta transpaso: " + ex.getMessage());
        }
        return false;
    }

    //HISTORIAL DE TRASPASOS
    public List<Transaccion> ConsultarHistorial(int num_cuenta) {
        String sql = "select id_transaccion, num_cuenta, cuenta_destino, tipo, monto, fecha, descripcion from transaccion where num_cuenta = ?";
        List<Transaccion> historial = new ArrayList<>();

        try(Connection cn = ConexionBD.conectar();
        PreparedStatement pst = cn.prepareStatement(sql)){

            pst.setInt(1, num_cuenta);

            try(ResultSet rs = pst.executeQuery()){

                while(rs.next()){
                    int id_transaccion = rs.getInt("id_transaccion");
                    int cuenta_destino = rs.getInt("cuenta_destino");
                    String tipo = rs.getString("tipo");
                    int monto = rs.getInt("monto");
                    String fecha = rs.getString("fecha");
                    String descripcion = rs.getString("descripcion");

                    historial.add(new Transaccion(id_transaccion, num_cuenta, cuenta_destino, monto, tipo, descripcion, fecha));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al mostrar historial: " + ex.getMessage());
        }
        return historial;
    }

    private boolean RetirarSaldo(Connection cn, int numCuenta, int monto) throws SQLException{
        String sql = "update cuenta set saldo = saldo - ? where num_cuenta = ?";
        try(PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, monto);
            pst.setInt(2, numCuenta);
            return pst.executeUpdate() > 0;
        }
    }

    private boolean DepositarSaldo(Connection cn, int numCuenta, int monto) throws SQLException{
        String sql = "update cuenta set saldo = saldo + ? where num_cuenta = ?";
        try(PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, monto);
            pst.setInt(2, numCuenta);
            return pst.executeUpdate() > 0;
        }
    }

    private boolean RegistrarTransaccion(Connection cn, int cuentaOrige, int cuentaDestino, String tipo, Transaccion transaccion) throws SQLException{
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
