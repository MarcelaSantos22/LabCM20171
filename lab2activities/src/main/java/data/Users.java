package data;

import java.util.UUID;

/**
 * Created by admin on 1/03/2017.
 */

public class Users {
    private String usuario;
    private String contraseña;
    private String correo;
    private String edad;
    private String foto;

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEdad() {
        return edad;
    }

    public String getFoto() {
        return foto;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
