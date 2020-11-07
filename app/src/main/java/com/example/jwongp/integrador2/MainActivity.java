package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import com.example.jwongp.integrador2.utils.InternetConnection;
import com.example.jwongp.integrador2.utils.Config;

import android.app.AlertDialog;
import android.content.DialogInterface;



public class MainActivity extends AppCompatActivity {
    TextView tv_regus;
    TextView tv_recuperar;
    TextView etpass;
    TextView etuser;
    ImageView img_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_regus= findViewById(R.id.tv_regus);
        tv_recuperar= findViewById(R.id.tv_recuperar);
        img_ingresar = (ImageView) findViewById(R.id.img_ingresar);
        etpass = (TextView) findViewById(R.id.etpass);
        etuser = (TextView) findViewById(R.id.etuser);

        //iniciando con la informacion previamente guardada
        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        String usuario_dni = prefs.getString("ndoc", "");
        etuser.setText(usuario_dni);
        //fin-iniciando con la informacion previamente guardada

        tv_regus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intreg= new Intent(MainActivity.this,activity_regusuario.class);
                MainActivity.this.startActivity(intreg);
            }
        });
        tv_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = etuser.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                 editor.putString("ndoc", dni);

                editor.apply();


                Intent intent3 = new Intent( MainActivity.this, com.example.jwongp.integrador2.recupera_ps.class );
                startActivity(intent3);


                finish();
            }
        });

        //Login de aplicación
        img_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nom = etuser.getText().toString().trim();
                String Pas = etpass.getText().toString().trim();

                if(Nom.length()!=8 || Nom.isEmpty()){
                    //Toast.makeText(getApplicationContext(), "DNI INVALIDO", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(), "DNI INVALIDO",1,"E");
                        return ;
                }
                if(Pas.length()<5 || Pas.isEmpty()){
                   // Toast.makeText(getApplicationContext(), "PASSWORD INVALIDO", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(), "PASSWORD INVALIDO",1,"E");
                    return ;
                }

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    //Recupera parámetros de campos de texto y almacena en auxiliar
                     //"https://proyectoint01.000webhostapp.com//x.sSc.R90$o0/apis/";

                    String AuxapisURL = Config.URL_JSON + Config.URL_LOGIN+
                            "?doc="+ Nom.toString()+
                            "&ps="+ Pas.toString() +
                            "&sk=4LQU1L3RV3H1CUL0S";

                   // Log.i("info: ", AuxapisURL);
                    Config.mensaje(getApplicationContext(),AuxapisURL,2,"U");
                    new MainActivity.CargarDatos().execute( AuxapisURL );
                }else {
                   // Toast.makeText(getApplicationContext(), "Verifique su conexion a internet!", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(),"Verifique su conexion a internet!",1,"0");
                    return ;
                }


            }
        });


    }
    /////////////////// LOGIN-INICIO
    private class CargarDatos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try{
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "Unable to retrieve web page, URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result){
            String[] responseAPI;
            responseAPI = result.toString().split(Pattern.quote("|"));
            Config.mensaje(getApplicationContext(),result,2,"R");

            String rs = String.valueOf(responseAPI[0]).trim(); //resultado
            String p1 = String.valueOf(responseAPI[1]).trim(); // ID | ERROR
            String p2 = String.valueOf(responseAPI[2]).trim(); // DOC | ERROR
            String p3 = String.valueOf(responseAPI[3]).trim(); //EXIST
            String p4 = String.valueOf(responseAPI[4]).trim(); // NOMBRE COMPLETO
            String p5 = String.valueOf(responseAPI[5]).trim(); // TIPO DE USUARIO


            if (rs.equals("OK")){

                SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                editor.putString("id", p1.toString());
                editor.putString("ndoc", p2.toString());
                editor.putString("nombre", p4.toString());
                editor.putString("tipo_usuario", p5.toString());
                editor.apply();
               // Toast.makeText(getApplicationContext(),"REGISTRO VALIDADO"  , Toast.LENGTH_LONG).show();
                Config.mensaje(getApplicationContext(),"REGISTRO VALIDADO",1,"r");

                if(p5.equals("1"))
                {
                    Intent intent2 = new Intent( MainActivity.this,activity_principal.class );
                    startActivity(intent2);
                }else {
                    Intent intent2 = new Intent( MainActivity.this,selector.class );
                    startActivity(intent2);
                }

                finish();


            }else{
                Config.mensaje(getApplicationContext(),"ERROR : \n" +p1.toString(),1,"E");
            }
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        String contentAsString="";
        int len=500;
        //HttpsURLConnection connection = null;
        //String result = null;
        try {
            URL url = new URL(myurl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta","The response is:"+response);
            is = conn.getInputStream();

            contentAsString = readIt(is,len);
            return contentAsString;



        } catch (Exception ex) {
            Log.e("error ",ex.toString() );
            if (is != null) {
                is.close();

            }
            return contentAsString;
        }  finally {

            if (is != null) {
                is.close();

            }
            return contentAsString;
        }
    }

    public String readIt(InputStream stream, int len)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);


    }

    /////////////////// LOGIN-FIN
}
