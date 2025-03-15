package Controller;

import Model.Transaccion;
import Service.DepositosService;

import java.util.List;

public class DepositosValidatorController {

    private DepositosService depositosService;

    public DepositosValidatorController(DepositosService depositosService) {
        this.depositosService = depositosService;
    }

    public boolean Depositar(int valor, String Descripcion) {
        return depositosService.DepositarMonto(valor, Descripcion);
    }

    public Integer consultarSaldo() {
        return depositosService.obtenerSaldo();
    }

    public boolean TransferirSaldo(int valor, String Descripcion, int CuentaDestino) {
        return depositosService.TransferenciaMonto(valor, Descripcion, CuentaDestino);
    }

    public List<Transaccion> ConsultarHistorial() {
        return depositosService.ConsultarHistorial();
    }

}
