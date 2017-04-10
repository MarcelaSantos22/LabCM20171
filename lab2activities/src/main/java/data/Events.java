package data;

import android.content.ContentValues;

/**
 * Created by admin on 9/03/2017.
 */

public class Events {
    private String nombre;
    private String descripcion;
    private float puntuacion;
    private String responsable;
    private String fecha;
    private String ubicacion;
    private String infoGeneral;

   /* public Events(String nombre, String descripcion, float puntuacion, String responsable, String fecha, String ubicacion, String infoGeneral, String foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.responsable = responsable;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.infoGeneral = infoGeneral;
        this.foto = foto;
    }*/

    private String foto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre= nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getInfoGeneral() {
        return infoGeneral;
    }

    public void setInfoGeneral(String infoGeneral) {
        this.infoGeneral = infoGeneral;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
