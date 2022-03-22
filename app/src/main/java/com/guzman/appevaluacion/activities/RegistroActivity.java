package com.guzman.appevaluacion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guzman.appevaluacion.R;
import com.guzman.appevaluacion.components.Parametros;
import com.guzman.appevaluacion.components.Session;
import com.guzman.appevaluacion.peticiones.ClientePOJO;
import com.guzman.appevaluacion.rest.Api;
import com.guzman.appevaluacion.rest.Cliente;
import com.guzman.appevaluacion.rest.RespuestaApi;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {


    private EditText etNombre,etApellido,etEdad,etDocumento,etNAcionalidad,etCorreo, etContrasena,etConfirmarContrasena;
    private Button btnGuardar;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Iniciar();
    }

    private void Iniciar(){

        session = new Session(this);
        etNombre = findViewById(R.id.et_nombre_registro);
        etApellido = findViewById(R.id.et_apellido_registro);
        etEdad = findViewById(R.id.et_edad_registro);
        etDocumento = findViewById(R.id.et_documento_registro);
        etNAcionalidad = findViewById(R.id.et_nacionalidad_registro);
        etConfirmarContrasena = findViewById(R.id.et_confirmar_contrasena_registro);
        etCorreo = findViewById(R.id.et_correo_registro);
        etContrasena = findViewById(R.id.et_contrasena_registro);
        btnGuardar = findViewById(R.id.bt_guardar_registro);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registro();
            }
        });

    }

    private void Registro(){

        if(!Validar()){
            Toast.makeText(this,"Ingrese los datos",Toast.LENGTH_LONG).show();
        }

        if(!etContrasena.getText().toString().equals(etConfirmarContrasena.getText().toString())){
            Toast.makeText(this,"Las contraseñas no son iguales",Toast.LENGTH_LONG).show();
        }

        final Dialog dialogcargando= new SpotsDialog.Builder()
                .setContext(RegistroActivity.this)
                .setMessage("Cargando...")
                .setCancelable(true).build();
        dialogcargando.show();

        ClientePOJO clientePOJO = new ClientePOJO(0,etNombre.getText().toString(),etApellido.getText().toString(),Integer.parseInt(etEdad.getText().toString()),etCorreo.getText().toString(),etContrasena.getText().toString(),etConfirmarContrasena.getText().toString(),etDocumento.getText().toString(),etNAcionalidad.getText().toString());
        Api api = Cliente.getClient();
        Call<RespuestaApi<String>> call = api.ClienteRegistro(clientePOJO);
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

                            Intent intent = new Intent(RegistroActivity.this,MenuActivity.class);
                            startActivity(intent);
                            finish();


                    }else{

                        Toast.makeText(RegistroActivity.this,respuesta.getMensaje(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaApi<String>> call, Throwable t) {
                dialogcargando.dismiss();
                Toast.makeText(RegistroActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    public boolean Validar(){


        if (etNombre.getText().toString().equals("")){
            etNombre.setError("Ingresa tu nombre");
            etNombre.requestFocus();
            return false;
        }

        if (etApellido.getText().toString().equals("")){
            etApellido.setError("Ingresa tu apellido");
            etApellido.requestFocus();
            return false;
        }

        if (etEdad.getText().toString().equals("")){
            etApellido.setError("Ingresa tu edad");
            etApellido.requestFocus();
            return false;
        }

        if (etDocumento.getText().toString().equals("")){
            etDocumento.setError("Ingresa tu documento");
            etDocumento.requestFocus();
            return false;
        }

        if (etNAcionalidad.getText().toString().equals("")){
            etNAcionalidad.setError("Ingresa tu nacionalidad");
            etNAcionalidad.requestFocus();
            return false;
        }

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

        if (etConfirmarContrasena.getText().toString().equals("")){
            etConfirmarContrasena.setError("Ingresa tu confirmacion de contraseña");
            etConfirmarContrasena.requestFocus();
            return false;
        }



        return true;
    }


}