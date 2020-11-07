package com.example.jwongp.integrador2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jwongp.integrador2.adapters.UsuarioAdapter;

import com.example.jwongp.integrador2.adapters.reservasAdapter;
import com.example.jwongp.integrador2.interfaces.ItemListener;
import com.example.jwongp.integrador2.modelos.Reservas;
import com.example.jwongp.integrador2.utils.Config;
import com.example.jwongp.integrador2.modelos.usuarioData;
import com.example.jwongp.integrador2.utils.InternetConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;

import java.net.MalformedURLException;
import android.os.Environment;
import android.os.StrictMode;
import android.content.Context;
import android.content.ActivityNotFoundException;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class admin_usuarios extends AppCompatActivity    implements ItemListener {

    private RecyclerView recyclerVista;
    //private Reservas misReservas;
    private ArrayList<usuarioData> lista;
    private GridLayoutManager glm;
    private UsuarioAdapter miAdapter;
    private TextView titulo;



    File elarchivo;
    android.widget.ListView lst_content;
    private ImageView imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios);

        recyclerVista =(RecyclerView) findViewById(R.id.recyclerVista);
        recyclerVista.setHasFixedSize(true);
        glm = new GridLayoutManager(this, 1);
        recyclerVista.setLayoutManager( glm);

        lista=new ArrayList<>();

        imageView3 =(ImageView)  findViewById(R.id.imageView3);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  sURL="";
                 sURL = Config.URL_JSON + Config.URL_USUARIOS_EXCEL +
                        "?sk=4LQU1L3RV3H1CUL0S";

                if (checkStoragePermission()){
                    Download_Archivo(sURL, "usuarios.xls");
                }else {
                    Config.mensaje(getApplicationContext(),"NO TIENE PERMISOS PARA USAR ALMACENAMIENTO INTERNO"  ,1,"i");
                }
            }
        });

        if (InternetConnection.checkConnection(getApplicationContext())) {


            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url =Config.URL_JSON + Config.URL_ADMIN_USUARIOS_LIST+
                    "?sk=4LQU1L3RV3H1CUL0S";//

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

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
        /*
        lst_content =  findViewById(R.id.lst_content);
       // lst_content = (Listview)findViewById(R.id.lst_content);
        //ListView
        String AuxapisURL="";
         AuxapisURL = Config.URL_JSON + Config.URL_ADMIN_USUARIOS_LIST+
                "?sk=4LQU1L3RV3H1CUL0S";
        Config.mensaje(getApplicationContext(),AuxapisURL,2,"U");
        new  admin_usuarios.CargarDatos().execute( AuxapisURL );
        */

    }
    @Override
    public void onClick(int posicion){
        Config.mensaje(getApplicationContext(),"posicion : " + posicion ,2,"r");
        Config.mensaje(getApplicationContext(),"lista : " + this.lista.get(posicion).getUserNom().toString(),2,"r");

        //   marca_ , modelo_ , id_ , imgUrl_ , precAlquiler_ ;
        final String  nm_=this.lista.get(posicion).getUserNom().toString();
        final String  ap_=this.lista.get(posicion).getUserApe().toString();
        final String  id_=this.lista.get(posicion).getIDUsuario().toString();
        final String  dn_=this.lista.get(posicion).getUserDni().toString();
        final String  ml_=this.lista.get(posicion).getUserMail().toString();
        final String  ps_=this.lista.get(posicion).getUserPass().toString();
        final String  tp_=this.lista.get(posicion).getUserTipo().toString();
        final String  st_=this.lista.get(posicion).getUserStatus().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("USUARIO SELECCIONADO");
        builder.setMessage(this.lista.get(posicion).getUserApe().toString() + " " +  this.lista.get(posicion).getUserNom().toString());

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                SharedPreferences.Editor editor = getSharedPreferences("UsuarioActual", MODE_PRIVATE).edit();
                editor.putString("idUsuario",  id_  );
                //  editor.putString("fechaDef",    );
                editor.putString("userDni",  dn_  );
                editor.putString("userApe",  ap_  );
                editor.putString("userPass",   ps_ );
                editor.putString("userStatus",   st_ );
                editor.putString("userTipo",  tp_  );
                editor.putString("userNom",  nm_  );
                editor.putString("userMail",  ml_  );


                editor.apply();

                Intent intent2 = new Intent( admin_usuarios.this,  admin_usu_edit.class );
                startActivity(intent2);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    private void procesarResultado(String result){

        Config.mensaje(getApplicationContext(),result.toString(),2,"R");



        if (result.indexOf("OK" )>0 && result.indexOf("success" )>0){
            try {
                JSONObject jsonObject = new JSONObject(result);

                if (jsonObject.getString("resultado").toString().equals("OK")) {
                    //  Config.mensaje(getApplicationContext(),"DATOS RECIBIDOS : \n"+ jsonObject.getString("data").toString(),1,"R");

                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    Config.mensaje(getApplicationContext(),"dataArray ->" + dataArray.toString(),2,"R");
                    // List<String> mApps1 = new ArrayList<>();



                    for (int i = 0; i < dataArray.length(); i++) {

                        // Creo la variable 'datosModelo' y le paso mi modelo 'MyAppModel'
                        usuarioData datosModelo = new usuarioData();

                        // Creo la  variable 'objetos' y recupero los valores
                        JSONObject objetos = dataArray.getJSONObject(i);

                        // Selecciono dato por dato
                        datosModelo.setIDUsuario(objetos.getString("id_usuario"));
                        datosModelo.setFechaDef(objetos.getString("fecha_def"));
                        datosModelo.setUserDni(objetos.getString("user_dni"));
                        datosModelo.setUserApe(objetos.getString("user_ape"));
                        datosModelo.setUserPass(objetos.getString("user_pass"));
                        datosModelo.setUserStatus(objetos.getString("user_status"));
                        datosModelo.setUserTipo(objetos.getString("user_tipo"));
                        datosModelo.setUserNom(objetos.getString("user_nom"));
                        datosModelo.setUserMail(objetos.getString("user_mail"));


                        // Meto los datos en el array que definí más arriba 'listaArray'
                        lista.add(datosModelo);

                    }
                    //   vehiculoAdapter  adapter =    new vehiculoAdapter (getApplicationContext(),lista );
                    //    recyclerVista.setAdapter(adapter);
                    miAdapter =    new UsuarioAdapter(getApplicationContext(),lista,this );
                    //  miAdapter.setClickListener(admin_vehiculos.this );
                    recyclerVista.setAdapter(miAdapter);

                }else{
                    Config.mensaje(getApplicationContext(),"ERROR \n"+ jsonObject.getString("mensaje").toString(),1,"R");
                }


            } catch (JSONException ex) {
                //some exception handler code.
                Config.mensaje(getApplicationContext(),ex.toString(),2,"e");
            } catch(Exception exx) {
                Config.mensaje(getApplicationContext(),exx.toString(),2,"e");
            }

        }else{
            Config.mensaje(getApplicationContext(),"ERROR AL CARGAR LA DATA DE USUARIOS",1,"R");
        }

    }
    ////// resultados + clic - FIN
    //////// DESCARGA USUARIO - INICIO

   private void Download_Archivo(String url_completa, String Nombre_Archivo){
       Config.mensaje(getApplicationContext(),url_completa  ,2,"U");
       Config.mensaje(getApplicationContext(),"ESPERE UN MOMENTO MIENTRAS SE DESCARGA EL ARCHIVO"  ,1,"i");
       File dir = new File(Environment.getExternalStorageDirectory() + "/");

       if (!dir.exists()) {
           dir.mkdirs();
       }

       elarchivo = new File(dir, Nombre_Archivo);

       int SDK_INT = android.os.Build.VERSION.SDK_INT;
       if (SDK_INT > 8) {
           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                   .permitAll().build();
           StrictMode.setThreadPolicy(policy);

           try {
               //primero especificaremos el origen de nuestro archivo a descargar utilizando
               //la ruta completa
               URL url = new URL(url_completa);

               //establecemos la conexión con el destino
               HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

               //establecemos el método jet para nuestra conexión
               //el método setdooutput es necesario para este tipo de conexiones
               urlConnection.setRequestMethod("GET");
               urlConnection.setDoOutput(true);

               //por último establecemos nuestra conexión y cruzamos los dedos
               urlConnection.connect();

               //vamos a establecer la ruta de destino para nuestra descarga
               //para hacerlo sencillo en este ejemplo he decidido descargar en
               //la raíz de la tarjeta SD

               //vamos a crear un objeto del tipo de fichero
               //donde descargaremos nuestro fichero, debemos darle el nombre que
               //queramos, si quisieramos hacer esto mas completo
               //cogeríamos el nombre del origen

               //utilizaremos un objeto del tipo fileoutputstream
               //para escribir el archivo que descargamos en el nuevo

               FileOutputStream fileOutput = new FileOutputStream(elarchivo);

               //leemos los datos desde la url
               InputStream inputStream = urlConnection.getInputStream();

               //creamos un buffer y una variable para ir almacenando el
               //tamaño temporal de este
               byte[] buffer = new byte[1024];
               int bufferLength = 0;

               int downloadedSize=0;
               //ahora iremos recorriendo el buffer para escribir el archivo de destino
               while ((bufferLength = inputStream.read(buffer)) > 0) {
                   downloadedSize += bufferLength;
                   Config.mensaje(getApplicationContext(),"DESCARGANDO..." +downloadedSize   ,2,"i");
                   fileOutput.write(buffer, 0, bufferLength);

               }
               //cerramos
               fileOutput.close();
               Config.mensaje(getApplicationContext(),"FINALIZADO  \n" + Environment.getExternalStorageDirectory() + "/"   ,1,"i");
               abrirArchivo(getApplicationContext(), Nombre_Archivo);
               //y gestionamos errores
           } catch (MalformedURLException e) {
               e.printStackTrace();
               Config.mensaje(getApplicationContext(),"MalformedURLException - " + e.toString(),2,"e");
               Config.mensaje(getApplicationContext(),"ERROR DE FORMATO  \n"  + e.toString() ,1,"i");
           } catch (IOException e) {
               e.printStackTrace();
               Config.mensaje(getApplicationContext(),"IOException - " + e.toString(),2,"e");
               Config.mensaje(getApplicationContext(),"ERROR DE ARCHIVO\n"  + e.toString() ,1,"i");
           }catch(Exception e) {
               Config.mensaje(getApplicationContext(),"Exception - " + e.toString(),2,"e");
               Config.mensaje(getApplicationContext(),"ERROR  \n"  + e.toString() ,1,"i");
           }
       }


    }
    private void abrirArchivo(Context context, String archivo) {
        try {
            // String dir = Environment.getExternalStorageDirectory() + "/PDFS/" + archivo + ".pdf";
            String dir = Environment.getExternalStorageDirectory() + "/" + archivo;

            Config.mensaje(getApplicationContext(),"ruta:  " +  dir ,1,"i");
            File f = new File(dir);
            Uri uri = Uri.fromFile(f);
            Intent intentUrl = new Intent(Intent.ACTION_VIEW);
            //obtenemos la extension del archivo y su mime type para abrirlo
            String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            String mimetype = "";

            if (extension != null && extension.length() > 0)
                mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
            else {
                //mimetype = "*/*";
                mimetype =   URLConnection.guessContentTypeFromName(archivo );//+ "pdf"

            }

            //    Log.i(tag, "uri: " + uri.toString() + "extension: "+ extension + " mimetype: " + mimetype);

            intentUrl.setDataAndType(uri, mimetype );
            //             intentUrl.setData(uri);
            intentUrl.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentUrl);
        } catch (  ActivityNotFoundException e) {
             Config.mensaje(getApplicationContext(),"No app instalada\n" + e.toString() ,1,"i");
        } catch (Exception e) {

            Config.mensaje(getApplicationContext(),"error abriendo el archivo\n" + e.toString() ,1,"i");
        }
    }
    public boolean checkStoragePermission(){
      /*  if (    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }*/
        return true;
    }
    //////// DESCARGA USUARIO - FIN
    /////////////////// USUARIOS-INICIO
  /*  private class CargarDatos extends AsyncTask<String, Void, String> {

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
            //OK|ID|DNI|EXIST|NOMBRE_PRUEBA APE_PRUEBA
            String[] responseAPI;
         //   InputStreamReader reader = new InputStreamReader(stream);
         //   BufferedReader br = new BufferedReader(reader);
         //   StringBuilder sb = new StringBuilder();

            Config.mensaje(getApplicationContext(),result.toString(),2,"r");

            if (result.indexOf("OK" )>0 && result.indexOf("success" )>0){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    //    JSONObject jsonObj = new JSONObject("{\"count\":3939,\"has_more\":true,\"map_location\":{\"lat\":0.60996950000000183,\"lon\":-27.568517000000003,\"panoramio_zoom\":16},\"photos\":[{\"height\":375,}]}");
                 //   System.out.println(jsonObject);
                   // Config.mensaje(getApplicationContext(),"ERROR \n"+ jsonObject ,1,"R");
                    if (jsonObject.getString("resultado").toString().equals("OK")) {
                      //  Config.mensaje(getApplicationContext(),"DATOS RECIBIDOS : \n"+ jsonObject.getString("data").toString(),1,"R");

                        JSONArray dataArray = jsonObject.getJSONArray("data");
                        Config.mensaje(getApplicationContext(),"dataArray ->" + dataArray.toString(),2,"R");
                        List<String> mApps1 = new ArrayList<>();

                      //  String[] mApps1 = {"Instagram","Pinterest","Pocket","Twitter" };

                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject dataobj = dataArray.getJSONObject(i);
                         //   publicFirstName = dataobj.getString("name");
                        //    publicHobby = dataobj.getString("hobby");
                            mApps1.add(   dataobj.getString("user_ape").toString() + " " +  dataobj.getString("user_nom").toString());
                        }


                    //    ArrayAdapter <String>  adapter =  new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,mApps1);
                        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(
                                admin_usuarios.this,
                                android.R.layout.simple_list_item_1,
                                mApps1 );
                        //dataArray("user_nom"));
                        lst_content.setAdapter(adapter);


                    }else{
                        Config.mensaje(getApplicationContext(),"ERROR \n"+ jsonObject.getString("mensaje").toString(),1,"R");
                    }


                } catch (JSONException ex) {
                    //some exception handler code.
                    Config.mensaje(getApplicationContext(),ex.toString(),2,"e");
                } catch(Exception exx) {
                    Config.mensaje(getApplicationContext(),exx.toString(),2,"e");
                }



            }else{
                Config.mensaje(getApplicationContext(),"ERROR AL CARGAR LA DATA DE USUARIOS",1,"R");
            }



        }
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        String contentAsString="";
        StringBuilder response = new StringBuilder();
        int len= 1500;
        //HttpsURLConnection connection = null;
        //String result = null;
        try {
            URL url = new URL(myurl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-type", "application/json");

            conn.connect();
            int responseCode = conn.getResponseCode();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
            //    Config.mensaje(getApplicationContext(),line.toString(),2,"r");
                response.append(line);
            }
            
           // Config.mensaje(getApplicationContext(),response.toString(),2,"r");

            conn.disconnect();
            contentAsString= response.toString();
            return contentAsString;



        } catch (Exception ex) {
            Log.e("error>> ",ex.toString() );

            Config.mensaje(getApplicationContext(),ex.toString(),2,"e");

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
*/
    /////////////////// USUARIOS-FIN
}
