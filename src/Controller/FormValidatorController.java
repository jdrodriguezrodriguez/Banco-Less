package Controller;

import Service.DepositosService;
import Service.UsuariosService;

import java.sql.SQLException;

public class FormValidatorController {

    private UsuariosService usuariosService;

    public FormValidatorController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    public boolean registrarNuevaPersona(String nombre, String apellido, String documento, String nacimiento, String correo, String password) throws SQLException {
        return usuariosService.RegistrarNuevaPersona(nombre, apellido, documento, nacimiento, correo, password);
    }

    public boolean LoginUsuario(String username, String password) {
        return usuariosService.LoginUsuario(username, password);
    }

    public String[] ConsultarDatos(){
        return usuariosService.ConsultarDatos();
    }

    public boolean ActualizarDatos(String nombre, String apellido, String nacimiento, String correo){
        return usuariosService.ActualizarDatos(nombre, apellido, nacimiento, correo);
    }

    public boolean ActualizarContraseña(String pass){
        return usuariosService.ActualizarContraseña(pass);
    }

}
