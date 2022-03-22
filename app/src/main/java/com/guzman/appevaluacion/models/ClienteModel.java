package com.guzman.appevaluacion.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClienteModel {

    @SerializedName("IdCliente")
    @Expose
    public long IdCliente;

    @SerializedName("Nombre")
    @Expose
    public String Nombre;

    @SerializedName("Apellido")
    @Expose
    public String Apellido;

    @SerializedName("Edad")
    @Expose
    public int Edad;

    @SerializedName("Correo")
    @Expose
    public String Correo;

    @SerializedName("DocumentoIdentidad")
    @Expose
    public String DocumentoIdentidad;

    @SerializedName("Nacionalidad")
    @Expose
    public String Nacionalidad;


    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long idCliente) {
        IdCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDocumentoIdentidad() {
        return DocumentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        DocumentoIdentidad = documentoIdentidad;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }
}
