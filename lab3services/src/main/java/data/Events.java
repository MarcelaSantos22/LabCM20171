package data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 9/03/2017.
 */

public class Events implements Parcelable {
    public int id;
    public String nombre;
    public String descripcion;
    public float puntuacion;
    public String responsable;
    public String fecha;
    public String ubicacion;
    public String infoGeneral;
    public String foto;

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

    public Events (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    protected Events(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        puntuacion = in.readFloat();
        responsable = in.readString();
        fecha = in.readString();
        ubicacion = in.readString();
        infoGeneral = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeFloat(puntuacion);
        dest.writeString(responsable);
        dest.writeString(fecha);
        dest.writeString(ubicacion);
        dest.writeString(infoGeneral);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };
}
