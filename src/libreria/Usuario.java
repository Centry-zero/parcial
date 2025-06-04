package libreria;

public class Usuario {
    private String cargo;
    private String nombreCompleto;
    private String usuario;
    private String contrasena;

    public Usuario(String cargo, String nombreCompleto, String usuario, String contrasena) {
        this.cargo = cargo;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contrasena;
    }
}
