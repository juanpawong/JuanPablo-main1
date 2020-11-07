package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jwongp.integrador2.utils.InternetConnection;
import com.example.jwongp.integrador2.utils.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class recupera_ps extends AppCompatActivity {
    EditText txtusudni;
    Button btnps;
    Button btnregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_ps);

        txtusudni = (EditText) findViewById(R.id.txtusudni);

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        String usuario_dni = prefs.getString("ndoc", "");
        txtusudni.setText(usuario_dni);

        //limpiando datos locales

        SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
        editor.putString("id", "");
        editor.putString("ndoc", "");
        editor.putString("nombre", "");
        editor.apply();/**/

        //    Config.mensaje(getApplicationContext(), "DATOS GUARDADOS SATISFACTORIAMENTE" ,1,"r");
        //
        btnps = (Button) findViewById(R.id.btnps);
        btnregresar = (Button) findViewById(R.id.btnregresar);
        btnregresar.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         String dni=   txtusudni.getText().toString().trim();
                                         SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                                         editor.putString("ndoc", dni);
                                         editor.apply();

                                         Intent intent2 = new Intent( getApplicationContext(),MainActivity.class );
                                         startActivity(intent2);

                                         finish();
                                     }
                                 });
        btnps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dni=   txtusudni.getText().toString().trim();


                if(dni.length()!=8 || dni.isEmpty()){
                    //Toast.makeText(getApplicationContext(), "DNI INVALIDO", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(), "DNI INVALIDO",1,"e");
                    return ;
                }


                if (InternetConnection.checkConnection(getApplicationContext())) {

                    String auxUrl =Config.URL_JSON + Config.URL_RECUPERA_PASSWORD +
                            "?doc="+ txtusudni.getText().toString()  +
                            "&sk=" +  Config.API_SK;

                    Config.mensaje(getApplicationContext(),auxUrl,2,"u");

                    new recupera_ps.CargarDatos().execute( auxUrl );
                }else {
                    // Toast.makeText(getApplicationContext(), "Verifique su conexion a internet!", Toast.LENGTH_LONG).show();
                    Config.mensaje(getApplicationContext(),  "Verifique su conexion a internet!",1,"e");
                    return ;
                }

            }
        });


    }
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
            String rs = String.valueOf(responseAPI[0]).trim(); //resultado
            String p1 = String.valueOf(responseAPI[1]).trim(); // DESCRIPCION

            //OK|4|DNI|EXIST| NOMBRE APELLIDO
            //OK|4|DNI|NEW| NOMBRE APELLIDO

            if (rs.equals("OK")){

                Config.mensaje(getApplicationContext(), p1 ,1,"r");
/*
                Intent intreg= new Intent(  activity_recupera_ps.class,MainActivity.this);
                MainActivity.this.startActivity(intreg);
*/
                //Intent intent2 = new Intent( activity_recupera_ps.this,MainActivity.class );
            /*    Intent intent2 = new Intent( getApplicationContext(),MainActivity.class );
                startActivity(intent2);

                finish();
*/
            }else{
                //  Toast.makeText(getApplicationContext(),"ERROR REGISTRANDO \n" +p1, Toast.LENGTH_LONG).show();
                Config.mensaje(getApplicationContext(), "ERROR : \n" +p1,1,"e");
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


}