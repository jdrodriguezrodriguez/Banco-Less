package Service;

import DAO.*;
import Model.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Random;

public class UsuariosService {

    private PersonaDao personaDao;
    private UsuarioDao usuarioDao;
    private CuentaDao cuentaDao;

    public UsuariosService(PersonaDao personaDao, UsuarioDao usuarioDao, CuentaDao cuentaDao) {
        this.personaDao = personaDao;
        this.usuarioDao = usuarioDao;
        this.cuentaDao = cuentaDao;
    }

    //AGREGAR NUEVA PERSONA
    public boolean RegistrarNuevaPersona(String nombre, String apellido, String documento, String nacimiento, String password) throws SQLException {
        try{
            if (personaDao.DocumentoExistente(documento)){
                JOptionPane.showMessageDialog(null, "Documento ya existe");
                return false;
            }

            if (!ValidarPassword(password)) {
                JOptionPane.showMessageDialog(null, "Su contraseña debe tener entre 4 y 8 caracteres.");
                return false;
            }

            Persona persona = new Persona(0, nombre, apellido, documento, nacimiento);

            if (!personaDao.InsertarPersona(persona)) {
                JOptionPane.showMessageDialog(null, "Fallo registro a la persona, llame a soporte");
                return false;
            }

            String username = GenerarUsername(nombre, documento);
            Usuario usuario = new Usuario(0, persona.getId(), username, password, "CLIENTE");

            if (!usuarioDao.CrearUsuario(usuario)) {
                return false;
            }

            int numeroCuenta = GenerarNumCuenta();

            Cuenta cuenta = new Cuenta(numeroCuenta, usuario.getIdUsuario(), 0, "ACTIVA");

            return cuentaDao.InsertarCuenta(cuenta);
        }catch (Exception ex){
            System.out.println("Error al registrar nueva persona" + ex.getMessage());
        }
        return false;
    }

    //INICIAR SESION
    public boolean LoginUsuario(String username, String password){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);

        if (usuarioDao.LoginUsuario(usuario)){

            UsuarioActivo usuarioActivo = usuarioDao.IdentificarUsuario(username);

            if (usuarioActivo != null){
                UsuarioActivo.iniciarSesion(
                        usuarioActivo.getIdUsuario(),
                        usuarioActivo.getIdCuenta(),
                        usuarioActivo.getUsername()
                );
                return true;
            }else{
                System.err.println("Error: Usuario autenticado pero sin cuenta asociada.");
            }
        }
        return false;
    }

    public static String GenerarUsername(String nombre, String documento) {
        return nombre.substring(0,Math.min(4, nombre.length())) + documento.substring(0,Math.min(4, documento.length()));
    }

    public static boolean ValidarPassword(String password) {
        return password.length() >= 4 && password.length() <= 8;
    }

    public static boolean ValidarCamposRegistro(String nombre, String apellido, String documento, String nacimiento, String password) {
        return !nombre.isEmpty() && !apellido.isEmpty() && !documento.isEmpty() && !nacimiento.isEmpty() && !password.isEmpty();
    }

    public static boolean ValidarCaposLogin(String usuario, String password) {
        return !usuario.isEmpty() && !password.isEmpty();
    }

    public static int GenerarNumCuenta() {
        Random random = new Random();
        int numeroCuenta = 1000000000 + random.nextInt(900000000);
        return numeroCuenta;
    }
}
