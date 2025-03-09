package Model;

public class UsuarioActivo {

    private static UsuarioActivo linea;
    private int idUsuario, idCuenta;
    private String username;

    public UsuarioActivo(){}

    public UsuarioActivo(int idUsuario, int idCuenta, String username) {
        this.idUsuario = idUsuario;
        this.idCuenta = idCuenta;
        this.username = username;
    }

    public static UsuarioActivo iniciarSesion(int idUsuario, int idCuenta, String username) {
        if (linea == null) {
            linea = new UsuarioActivo(idUsuario, idCuenta, username);
        }
        return linea;
    }

    public static UsuarioActivo getLinea() {
        return linea;
    }

    public static void cerrarSesion(){
        linea = null;
    }


    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
