package Model;

public class Transaccion {

    private int idTransaccion, num_cuenta, cuentaDestino, monto;
    private String tipo_entrega, descripcion, fecha;

    public Transaccion(int idTransaccion, int num_cuenta, int cuentaDestino, int monto, String tipo_entrega, String descripcion, String fecha) {
        this.idTransaccion = idTransaccion;
        this.num_cuenta = num_cuenta;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.tipo_entrega = tipo_entrega;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Transaccion() {
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getNum_cuenta() {
        return num_cuenta;
    }
    public void setNum_cuenta(int num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }
    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }
    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
