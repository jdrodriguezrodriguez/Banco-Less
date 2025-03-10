package Controller;

import Service.DepositosService;

public class DepositosValidatorController {

    private DepositosService depositosService;

    public DepositosValidatorController(DepositosService depositosService) {
        this.depositosService = depositosService;
    }

    public boolean Depositar(int valor, String Descripcion) {
        return depositosService.Depositar(valor, Descripcion);
    }

    public Integer consultarSaldo() {
        return depositosService.obtenerSaldo();
    }

    public boolean TransferirSaldo(int valor, String Descripcion, int CuentaDestino) {
        return depositosService.TransferenciaMonto(valor, Descripcion, CuentaDestino);
    }

}
