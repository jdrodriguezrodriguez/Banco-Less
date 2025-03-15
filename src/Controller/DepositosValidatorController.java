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
        return depositosService.DepositarSaldo(valor, Descripcion);
    }

    public Integer consultarSaldo() {
        return depositosService.ConsultarSaldo();
    }

    public boolean TransferirSaldo(int valor, String Descripcion, int CuentaDestino) {
        return depositosService.TransferenciaMonto(valor, Descripcion, CuentaDestino);
    }

    public List<Transaccion> ConsultarHistorial() {
        return depositosService.ConsultarHistorial();
    }

}
