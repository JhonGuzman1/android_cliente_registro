package com.guzman.appevaluacion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.guzman.appevaluacion.R;
import com.guzman.appevaluacion.adapter.ClienteAdapter;
import com.guzman.appevaluacion.components.Parametros;
import com.guzman.appevaluacion.models.ClienteModel;
import com.guzman.appevaluacion.rest.Api;
import com.guzman.appevaluacion.rest.Cliente;
import com.guzman.appevaluacion.rest.RespuestaApi;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    ClienteAdapter clienteAdapter;
    RecyclerView rvCliente;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Iniciar();
    }


    private void Iniciar(){

        rvCliente = findViewById(R.id.rv_cliente_menu);
        rvCliente.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnRegistro = findViewById(R.id.bt_registro_menu);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

        Listar();

    }

    private void Listar(){


        final Dialog dialogcargando= new SpotsDialog.Builder()
                .setContext(MenuActivity.this)
                .setMessage("Cargando...")
                .setCancelable(true).build();
        dialogcargando.show();

        Api api = Cliente.getClient();
        Call<RespuestaApi<List<ClienteModel>>> call = api.ClienteListar();
        call.enqueue(new Callback<RespuestaApi<List<ClienteModel>>>() {
            @Override
            public void onResponse(Call<RespuestaApi<List<ClienteModel>>> call, Response<RespuestaApi<List<ClienteModel>>> response) {
               dialogcargando.dismiss();
                if(response.isSuccessful()){
                    RespuestaApi<List<ClienteModel>> respuesta = response.body();
                    if(respuesta.RespuestaExitosa()){

                        List<ClienteModel> list = respuesta.getRespuesta();
                        if(list.isEmpty()) {
                            list = new ArrayList<>();
                        }

                        clienteAdapter = new ClienteAdapter(MenuActivity.this,list);
                        rvCliente.setAdapter(clienteAdapter);
                        clienteAdapter.notifyDataSetChanged();


                    }else{
                        Toast.makeText(MenuActivity.this,respuesta.getMensaje(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaApi<List<ClienteModel>>> call, Throwable t) {
                dialogcargando.dismiss();
                Toast.makeText(MenuActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }









}