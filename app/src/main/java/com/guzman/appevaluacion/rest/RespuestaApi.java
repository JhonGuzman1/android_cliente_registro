package com.guzman.appevaluacion.rest;

public class RespuestaApi<T> {

    public String Codigo;
    public String Mensaje;
    public T Respuesta;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public T getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(T respuesta) {
        Respuesta = respuesta;
    }

    public boolean RespuestaExitosa() {

        if (Codigo.equals("0")) {
            return true;
        } else if (Codigo.equals("1")) {
            return false;
        }
        return true;
    }


}
