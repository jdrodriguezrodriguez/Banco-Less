package Model;

public class Cuenta {

    private int num_cuenta, idUsuario, saldo;
    private String estado;

    public Cuenta(){
    }

    public Cuenta(int num_cuenta, int idUsuario, int saldo, String estado) {
        this.num_cuenta = num_cuenta;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
        this.estado = estado;
    }

    public int getNum_cuenta() {
        return num_cuenta;
    }
    public void setNum_cuenta(int num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
