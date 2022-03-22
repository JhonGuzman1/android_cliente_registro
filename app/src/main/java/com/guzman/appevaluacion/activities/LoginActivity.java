package com.guzman.appevaluacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guzman.appevaluacion.R;
import com.guzman.appevaluacion.components.Session;
import com.guzman.appevaluacion.peticiones.ClientePOJO;
import com.guzman.appevaluacion.rest.Api;
import com.guzman.appevaluacion.rest.Cliente;
import com.guzman.appevaluacion.rest.RespuestaApi;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etCorreo, etContrasena;
    private Button btnIngresar,btnAceptarDialog;
    private TextView tvRegistro, tvMensajeDialog;
    Session session;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Iniciar();
    }


    private void Iniciar(){

        session = new Session(this);
        etCorreo = findViewById(R.id.et_correo_login);
        etContrasena = findViewById(R.id.et_contrasena_login);
        btnIngresar = findViewById(R.id.bt_ingresar_login);
        tvRegistro = findViewById(R.id.tv_registro_login);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Login(){

        if(!Validar()){
            Toast.makeText(this,"Ingrese los datos",Toast.LENGTH_LONG).show();
        }

        final Dialog dialogcargando= new SpotsDialog.Builder()
                .setContext(LoginActivity.this)
                .setMessage("Cargando...")
                .setCancelable(true).build();
        dialogcargando.show();

        ClientePOJO clientePOJO = new ClientePOJO(0,"","",0,etCorreo.getText().toString(),etContrasena.getText().toString(),"","","");
        Api api = Cliente.getClient();
        Call<RespuestaApi<String>> call = api.ClienteLogin(clientePOJO);
        call.enqueue(new Callback<RespuestaApi<String>>() {
            @Override
            public void onResponse(Call<RespuestaApi<String>> call, Response<RespuestaApi<String>> response) {
                dialogcargando.dismiss();
                if(response.isSuccessful()){
                    RespuestaApi<String> respuesta = response.body();
                    if(respuesta.RespuestaExitosa()){

                        SharedPreferences sharedPreferences = getSharedPreferences("Session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("login", true);

                        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,respuesta.getMensaje(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaApi<String>> call, Throwable t) {
                dialogcargando.dismiss();

                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    public boolean Validar(){

        if (etCorreo.getText().toString().equals("")){
            etCorreo.setError("Ingresa tu correo");
            etCorreo.requestFocus();
            return false;
        }
        if (!etCorreo.getText().toString().contains("@")){
            etCorreo.setError("Ingresa un correo válido");
            etCorreo.requestFocus();
            return false;
        }

        if (etContrasena.getText().toString().equals("")){
            etContrasena.setError("Ingresa tu contraseña");
            etContrasena.requestFocus();
            return false;
        }
        return true;
    }

    private void  Mensaje(String mensaje){
        dialog = new Dialog(this,R.style.SlideTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_mensaje);

        tvMensajeDialog = dialog.findViewById(R.id.tv_mensaje_dialog_mensaje);
        btnAceptarDialog = dialog.findViewById(R.id.btn_aceptar_dialog_mensaje);

        tvMensajeDialog.setText(mensaje);
        btnAceptarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }






}