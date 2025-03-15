package Service;

import DAO.TransaccionDao;
import Model.Transaccion;
import Model.UsuarioActivo;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DepositosService {

    private String fechaActual;
    private TransaccionDao transaccionDao;

    public DepositosService(TransaccionDao transaccionDao) {
        this.fechaActual = GenerarFechaActual();
        this.transaccionDao = transaccionDao;
    }

    //CONSULTAR HISTORIAL
    public List<Transaccion> ConsultarHistorial() {
        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        int num_cuenta = usuarioActivo.getIdCuenta();

        return transaccionDao.ConsultarHistorial(num_cuenta);
    }

    //TRANSFERIR SALDO
    public boolean TransferenciaMonto(int valor, String Descripcion, int CuentaDestino) {

        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        if (ConsultarSaldo() < valor) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la transacción.");
            return false;
        }

        Transaccion transaccion = new Transaccion(0, usuarioActivo.getIdCuenta(), CuentaDestino, valor, "TRANSFERENCIA", Descripcion, fechaActual);
        if (!transaccionDao.Transferir(transaccion)){
            return false;
        }
        return true;
    }

    //DEPOSITAR SALDO
    public boolean DepositarSaldo(int valor, String Descripcion) {

        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        Transaccion transaccion = new Transaccion(0, usuarioActivo.getIdCuenta(), 0, valor, "DEPOSITO", Descripcion, fechaActual);

        if (!transaccionDao.Depositar(transaccion)){
            return false;
        }
        return true;
    }

    //CONSULTAR SALDO
    public Integer ConsultarSaldo(){
        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();

        int num_cuenta = usuarioActivo.getIdCuenta();
        Integer saldo = transaccionDao.ConsultarSaldo(num_cuenta);

        if(saldo == 0){
          return null;
        }
        return saldo;
    }

    //VERIFICAR CAMPOS UI DEPOSITO
    public static boolean VerificarCamposDeposito(int valor){
        return valor > 0;
    }

    //VERIFICAR CAMPOS UI TRANSFERENCIA
    public static boolean VerificarCamposTransferencia(int valor, int CuentaDestino){
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

    //GENERAR FECHA ACTUAL
    public static String GenerarFechaActual(){
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String FechaActual = formatter.format(current);
        return FechaActual;
    }

}
