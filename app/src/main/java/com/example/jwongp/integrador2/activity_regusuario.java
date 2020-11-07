package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.ResponseCache;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import com.example.jwongp.integrador2.utils.InternetConnection;
import com.example.jwongp.integrador2.utils.Config;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

public class activity_regusuario extends AppCompatActivity {
    EditText txtusudni;
    EditText txtusunom;
    EditText txtusuape;
    EditText txtusermail;
    EditText txtpassword;
    Button btnusureg;
    TextView tv_regresar1;
  //  const String apisURL="https://www.tuguiadecomprasalamanca.com/x.sSc.R90$o0/apis/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regusuario);

        txtusudni = (EditText) findViewById(R.id.txtusudni);
        txtusunom = (EditText) findViewById(R.id.txtusunom);
        txtusuape = (EditText) findViewById(R.id.txtusuape);
        txtusermail = (EditText) findViewById(R.id.txtusermail);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        btnusureg = (Button) findViewById(R.id.btnusureg);
        tv_regresar1 = (TextView) findViewById(R.id.tv_regresar1);

        btnusureg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String dni=   txtusudni.getText().toString().trim();
             String pss=   txtpassword.getText().toString().trim();
             String ape=   txtusuape.getText().toString().trim();
             String nom=   txtusunom.getText().toString().trim();
             String mail=   txtusermail.getText().toString().trim();


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
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    //new CargarDatos().execute("https://192.168.1.19:4433/integrador2/register.php?user_dni="+ txtusudni.getText().toString()+"&user_nom="+ txtusunom.getText().toString() + "&user_ape=" + txtusuape.getText().toString() +  "&user_name=" + txtusername.getText().toString() + "&user_pass=" + txtpassword.getText().toString());

                    //public String apisURL="https://proyectoint01.000webhostapp.com//x.sSc.R90$o0/apis/";
                    String auxUrl =Config.URL_JSON + Config.URL_REGISTRO +
                            "?user_dni="+ txtusudni.getText().toString()+
                            "&user_nom="+ txtusunom.getText().toString() +
                            "&user_ape=" + txtusuape.getText().toString() +
                            "&user_mail=" + txtusermail.getText().toString() +
                            "&user_pass=" + txtpassword.getText().toString();

                    Config.mensaje(getApplicationContext(),auxUrl,2,"u");
                  //  new CargarDatos().execute();

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url =auxUrl;//"http://www.google.com";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                  //  textView.setText("Response is: "+ response.substring(0,500));
                                    procesarResultado(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //textView.setText("That didn't work!");
                            Config.mensaje(getApplicationContext(),  error.toString(),2 ,"e");
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

        tv_regresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(activity_regusuario.this,MainActivity.class);
                startActivity(intent3);
            }
        });

    }
    private void procesarResultado(String result){
        result=result+"| | | | | | | | ";
        Config.mensaje(getApplicationContext(), result,2,"r");
        String[] responseAPI;
        responseAPI = result.toString().split(Pattern.quote("|"));
        String rs = String.valueOf(responseAPI[0]).trim(); //resultado
        String p1 = String.valueOf(responseAPI[1]).trim(); // ID | ERROR
        String p2 = String.valueOf(responseAPI[2]).trim(); // DOC | ERROR
        String p3 = String.valueOf(responseAPI[3]).trim(); //estado
        String p4 = String.valueOf(responseAPI[4]).trim(); // NOMBRE COMPLETO
        //OK|4|DNI|EXIST| NOMBRE APELLIDO
        //OK|4|DNI|NEW| NOMBRE APELLIDO

        if (rs.equals("OK")){
            if (p3.equals("EXIST")){
                //usuario ya registrado
                Toast.makeText(getApplicationContext(),"DNI YA REGISTRADO, SI HA PERDIDO SU CONTRASEÑA \nNOTIFICAR AL ADMINISTRADOR" , Toast.LENGTH_LONG).show();
            }else {
                //almacenando la informacion en el internal storage
                SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                editor.putString("id", p1.toString());
                editor.putString("ndoc", p2.toString());
                editor.putString("nombre", p4.toString());
                editor.apply();/**/

                // Toast.makeText(getApplicationContext(),"DATOS GUARDADOS SATISFACTORIAMENTE"  , Toast.LENGTH_LONG).show();
                Config.mensaje(getApplicationContext(), "DATOS GUARDADOS SATISFACTORIAMENTE" ,1,"r");
                Intent intent2 = new Intent( activity_regusuario.this,activity_principal.class );
                //  startActivity(intent2);
                //  finish();
            }

        }else{
            Toast.makeText(getApplicationContext(),"ERROR REGISTRANDO \n" +p1, Toast.LENGTH_LONG).show();
        }
    }

}
