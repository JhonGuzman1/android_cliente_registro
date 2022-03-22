package com.guzman.appevaluacion.peticiones;

public class ClientePOJO {

    public long IdCliente;
    public String Nombre;
    public String Apellido;
    public int Edad;
    public String Correo;
    public String Contrasena;
    public String ConfirmarContrasena;
    public String DocumentoIdentidad;
    public String Nacionalidad;


    public ClientePOJO(long idCliente, String nombre, String apellido, int edad, String correo, String contrasena, String confirmarContrasena, String documentoIdentidad, String nacionalidad) {
        IdCliente = idCliente;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Correo = correo;
        Contrasena = contrasena;
        ConfirmarContrasena = confirmarContrasena;
        DocumentoIdentidad = documentoIdentidad;
        Nacionalidad = nacionalidad;
    }



}
