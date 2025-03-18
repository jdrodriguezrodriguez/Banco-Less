package Controller;

import Service.DepositosService;
import Service.UsuariosService;

import java.sql.SQLException;

public class FormValidatorController {

    private UsuariosService usuariosService;

    public FormValidatorController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    public boolean registrarNuevaPersona(String nombre, String apellido, String documento, String nacimiento, String password) throws SQLException {
        return usuariosService.RegistrarNuevaPersona(nombre, apellido, documento, nacimiento, password);
    }

    public boolean LoginUsuario(String username, String password) {
        return usuariosService.LoginUsuario(username, password);
    }

}
