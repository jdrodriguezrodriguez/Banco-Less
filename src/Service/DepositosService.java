package Service;

import DAO.TransaccionDao;
import Model.Transaccion;
import Model.UsuarioActivo;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepositosService {

    private TransaccionDao transaccionDao;

    public DepositosService(TransaccionDao transaccionDao) {
        this.transaccionDao = transaccionDao;
    }

    public boolean TransferenciaMonto(int valor, String Descripcion, int CuentaDestino) {
        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        GenerarFechaActual();

        if (obtenerSaldo() < valor) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la transacción.");
            return false;
        }

        Transaccion transaccion = new Transaccion(0, usuarioActivo.getIdCuenta(), CuentaDestino, valor, "TRANSFERENCIA", Descripcion, GenerarFechaActual() );
        if (!transaccionDao.Transferir(transaccion)){
            return false;
        }
        return true;
    }

    public boolean DepositarMonto(int valor, String Descripcion) {

        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        GenerarFechaActual();

        Transaccion transaccion = new Transaccion(0, usuarioActivo.getIdCuenta(), 0, valor, "DEPOSITO", Descripcion, GenerarFechaActual() );

        if (!transaccionDao.Depositar(transaccion)){
            return false;
        }
        return true;
    }

    public Integer obtenerSaldo(){
        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();

        int num_cuenta = usuarioActivo.getIdCuenta();
        Integer saldo = transaccionDao.ConsultarSaldo(num_cuenta);

        if(saldo == 0){
          return null;
        }
        return saldo;
    }

    public static boolean CamposDeposito(int valor){
        return valor > 0;
    }

    public static boolean CamposTransferencia(int valor, int CuentaDestino){
        if (String.valueOf(CuentaDestino).length() == 10){
            if (valor > 0){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor mayor a $0");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Cuenta debe tener 10 digitos");
            return false;
        }
    }

    public static String GenerarFechaActual(){
        Date current = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaActual = formatter.format(current);

        return FechaActual;
    }

}
