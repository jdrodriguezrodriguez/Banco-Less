package Model;

public class Persona {

    private int id;
    private String nombre, apellido, documento, nacimiento, correo, password;


    public Persona(){
    }

    public Persona(int id, String nombre, String apellido, String documento, String nacimiento, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.correo = correo;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getDocumento(){
        return documento;
    }
    public void setDocumento(String documento){
        this.documento = documento;
    }

    public String getNacimiento(){
        return nacimiento;
    }
    public void setNacimiento(String nacimiento){
        this.nacimiento = nacimiento;
    }

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
}
