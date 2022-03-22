package com.guzman.appevaluacion.rest;

import com.guzman.appevaluacion.models.ClienteModel;
import com.guzman.appevaluacion.peticiones.ClientePOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("cliente")
    Call<RespuestaApi<List<ClienteModel>>> ClienteListar();

    @POST("cliente/login")
    Call<RespuestaApi<String>> ClienteLogin(@Body ClientePOJO clientePOJO);

    @POST("cliente/registro")
    Call<RespuestaApi<String>> ClienteRegistro(@Body ClientePOJO clientePOJO);

    @POST("cliente/modificar")
    Call<RespuestaApi<String>> ClienteModificar(@Body ClientePOJO clientePOJO);

    @POST("cliente/eliminar")
    Call<RespuestaApi<String>> ClienteEliminar(@Body ClientePOJO clientePOJO);



}
