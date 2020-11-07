package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jwongp.integrador2.utils.Config;
import com.example.jwongp.integrador2.utils.InternetConnection;

import java.util.regex.Pattern;

public class admin_usu_edit extends AppCompatActivity {

    private TextView id_usuario,  user_tipo;
    private EditText  user_dni, user_ape, user_pass, user_nom, user_mail;
    private Button btn_cancelar,btn_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usu_edit);

        SharedPreferences prefs = getSharedPreferences("UsuarioActual", MODE_PRIVATE);
        final String idUsuario = prefs.getString("idUsuario", "");
        String userDni = prefs.getString("userDni", "");
        String userApe = prefs.getString("userApe", "");
        String userPass = prefs.getString("userPass", "");
        String userStatus = prefs.getString("userStatus", "");
        String userTipo = prefs.getString("userTipo", "");
        if (userTipo.equals("1")){
            userTipo="USUARIO";
        }else {
            userTipo="ADMINISTRADOR";
        }
        String userMail = prefs.getString("userMail", "");
        String userNom = prefs.getString("userNom", "");

        id_usuario=(TextView) findViewById(R.id.id_usuario);
        user_dni=(EditText) findViewById(R.id.user_dni);
        user_ape=(EditText) findViewById(R.id.user_ape);
        user_pass=(EditText) findViewById(R.id.user_pass);
        user_tipo=(TextView) findViewById(R.id.user_tipo);
        user_nom=(EditText) findViewById(R.id.user_nom);
        user_mail=(EditText) findViewById(R.id.user_mail);

        id_usuario.setText(idUsuario);
        user_dni.setText(userDni);
        user_ape.setText(userApe);
        user_pass.setText(userPass);
        user_tipo.setText(userTipo);
        user_nom.setText(userNom);
        user_mail.setText(userMail);



        btn_cancelar=(Button) findViewById(R.id.btn_cancelar);
        btn_guardar=(Button) findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetConnection.checkConnection(getApplicationContext())) {

                    String dni=   user_dni.getText().toString().trim();
                    String pss=   user_pass.getText().toString().trim();
                    String ape=   user_ape.getText().toString().trim();
                    String nom=   user_nom.getText().toString().trim();
                    String mail=   user_mail.getText().toString().trim();


                    if(dni.length()!=8 || dni.isEmpty()){
                        //Toast.makeText(getApplicationContext(), "DNI INVALIDO", Toast.LENGTH_LONG).show();
                        Config.mensaje(getApplicationContext(), "DNI INVALIDO",1,"e");
                        return ;
                    }
                    if(pss.length()<3 || pss.isEmpty()){
                        //  Toast.makeText(getApplicationContext(), "PASSWORD INVALIDO", Toast.LENGTH_LONG).show();
                        Config.mensaje(getApplicationContext(),  "PASSWORD INVALIDO",1,"e");
                        return ;
                    }
                    if(mail.length()<3 || mail.isEmpty()){
                        //  Toast.makeText(getApplicationContext(), "PASSWORD INVALIDO", Toast.LENGTH_LONG).show();
                        Config.mensaje(getApplicationContext(),  "EMAIL INVALIDO",1,"e");
                        return ;
                    }

                    if( mail.indexOf("@")>0){
                        // todo bien
                    }else{
                        Config.mensaje(getApplicationContext(),  "FORMATO DE EMAIL INVALIDO",1,"e");
                        return ;
                    }
                    if(ape.length()<2 || ape.isEmpty() || nom.length()<2 || nom.isEmpty()){
                        // Toast.makeText(getApplicationContext(), "Nombre o apellidos no validos", Toast.LENGTH_LONG).show();
                        Config.mensaje(getApplicationContext(), "Nombre o apellidos no validos",1,"e");
                        return ;
                    }



                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url = Config.URL_JSON + Config.URL_USUARIOS_EDIT+
                            "?sk=4LQU1L3RV3H1CUL0S&id_usuario=" + idUsuario +
                            "&user_dni="+ dni +
                            "&user_ape="+ ape +
                            "&user_pass="+ pss +
                            "&user_nom="+ nom +
                            "&user_mail=" + mail ;//

                    Config.mensaje(getApplicationContext(),"\nURL UPDATE :\n"+ url,2,"u");

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    procesarResultado(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Config.mensaje(getApplicationContext(),  error.toString(),2 ,"e");
                            if(error.toString().indexOf("TimeoutError")>0){
                                Config.mensaje(getApplicationContext(),  "TIMEPO MAXIMO DE RESPUESTA SUPERADO\nVUELVA A INTENTARLO EN UNOS MINUTOS...",1 ,"e");
                            }
                        }
                    });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                }else {
                    // Toast.makeText(getApplicationContext(), "Verifique su conexion a internet!", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(),  "Verifique su conexion a internet!",1,"e");
                    return ;
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( getApplicationContext(),admin_usuarios.class );
                startActivity(intent2);
            }
        });

    }
    private void procesarResultado(String Resultados){
        String[] responseAPI;
        responseAPI = Resultados.toString().split(Pattern.quote("|"));
        //Log.i("info: result : ", result);
        Config.mensaje(getApplicationContext(),Resultados,2,"R");

        String rs = String.valueOf(responseAPI[0]).trim(); //resultado
        String p1 = String.valueOf(responseAPI[1]).trim(); // ID | ERROR


        if (rs.equals("OK")){
            Config.mensaje(getApplicationContext(),"DATOS ACTUALIZADOS CORRECTAMENTE",1,"R");
/*
            SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
            String tipo_usuario = prefs.getString("tipo_usuario", "");
             tipo_usuario*/
            Intent intent2 = new Intent( getApplicationContext(),admin.class );
            startActivity(intent2);
            finish();

        }else {
            Config.mensaje(getApplicationContext(),"ERROR \n"+ p1,1,"R");
        }
    }
}