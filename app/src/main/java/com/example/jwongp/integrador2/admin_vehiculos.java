package com.example.jwongp.integrador2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jwongp.integrador2.utils.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
import com.example.jwongp.integrador2.modelos.vehiculo;
import com.example.jwongp.integrador2.adapters.vehiculoAdapter;
import com.example.jwongp.integrador2.interfaces.ItemListener;
import com.example.jwongp.integrador2.utils.InternetConnection;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class admin_vehiculos extends AppCompatActivity implements ItemListener {

    private RecyclerView recyclerVista;
    private vehiculo miVehiculo;
    private ArrayList<vehiculo> lista;
    private GridLayoutManager glm;
    private vehiculoAdapter  miAdapter;
    private String accion;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vehiculos);

        titulo =(TextView) findViewById(R.id.textView5);

        recyclerVista =(RecyclerView) findViewById(R.id.recyclerVista);
        recyclerVista.setHasFixedSize(true);
        glm = new GridLayoutManager(this, 1);
        recyclerVista.setLayoutManager( glm);

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        String presentacion = prefs.getString("presentacion", "");
        accion = prefs.getString("accion" , "");
       /*  RecyclerView.LayoutManager mLayoutManager = new  LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerVista.setLayoutManager(mLayoutManager);

        if (presentacion.equals("horizontal")){
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

           // RecyclerView myList = (RecyclerView) findViewById(R.id.my_recycler_view);
            recyclerVista.setLayoutManager(layoutManager);
        }else{
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
*/

        lista=new ArrayList<>();

        String AuxapisURL = Config.URL_JSON + Config.URL_GET_VEHICULOS_TODOS+
                "?sk=4LQU1L3RV3H1CUL0S";

        if(accion.equals("alquilar")){
            AuxapisURL = Config.URL_JSON + Config.URL_GET_VEHICULOS_DISPONIBLES+
                    "?sk=4LQU1L3RV3H1CUL0S";
            titulo.setText("SELECCIONE EL VEHICULO :");
        }

        Config.mensaje(getApplicationContext(),AuxapisURL,2,"U");
       // new  admin_vehiculos.CargarDatos().execute( AuxapisURL );
        if (InternetConnection.checkConnection(getApplicationContext())) {


            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url =AuxapisURL;//"http://www.google.com";

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
    @Override
    public void onClick(int posicion){
        Config.mensaje(getApplicationContext(),"posicion : " + posicion ,2,"r");
        Config.mensaje(getApplicationContext(),"lista : " + this.lista.get(posicion).getMarca().toString(),2,"r");
        if(accion.equals("alquilar")){
            //alquilar

          //   marca_ , modelo_ , id_ , imgUrl_ , precAlquiler_ ;
            final String  marca_=this.lista.get(posicion).getMarca().toString();
            final String   modelo_=this.lista.get(posicion).getModelo().toString();
            final String   id_=this.lista.get(posicion).getIdvehiculos().toString();
            final String   imgUrl_=this.lista.get(posicion).getImgURL().toString();
            final String   precAlquiler_=this.lista.get(posicion).getPrecAlquile().toString();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("VEHICULO SELECCIONADO");
            builder.setMessage("Está seguro de alquilar el :\n" + marca_ + " - " + modelo_);

            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog


                    SharedPreferences.Editor editor = getSharedPreferences("VehiculoSeleccionado", MODE_PRIVATE).edit();

                    editor.putString("Idvehiculos", id_);
                    editor.putString("ImgURL", imgUrl_);
                    editor.putString("Modelo", modelo_);
                    editor.putString("Marca",marca_);
                    editor.putString("PrecAlquiler", precAlquiler_);

                    editor.putString("elegir_vehiculo", accion);
                    editor.apply();

                    Intent intent2 = new Intent( admin_vehiculos.this,  usu_vehiculo_confirm.class );
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



        }else {
            //editar el vehiculo
        }
    }
    private void procesarResultado(String result){
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
                   // List<String> mApps1 = new ArrayList<>();



                    for (int i = 0; i < dataArray.length(); i++) {

                        // Creo la variable 'datosModelo' y le paso mi modelo 'MyAppModel'
                        vehiculo datosModelo = new vehiculo();

                        // Creo la  variable 'objetos' y recupero los valores
                        JSONObject objetos = dataArray.getJSONObject(i);

                        // Selecciono dato por dato
                        datosModelo.setIdvehiculos(objetos.getString("idvehiculos"));
                        datosModelo.setMarca(objetos.getString("marca"));
                        datosModelo.setModelo(objetos.getString("modelo"));
                        datosModelo.setImg(objetos.getString("img"));
                        datosModelo.setDescripcion(objetos.getString("descripcion"));
                        datosModelo.setPrecAlquile(objetos.getString("prec_alquile"));
                        datosModelo.setStatus(objetos.getString("status"));


                        // Meto los datos en el array que definí más arriba 'listaArray'
                        lista.add(datosModelo);

                    }
                    //   vehiculoAdapter  adapter =    new vehiculoAdapter (getApplicationContext(),lista );
                    //    recyclerVista.setAdapter(adapter);
                    miAdapter =    new vehiculoAdapter (getApplicationContext(),lista,this );
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

    /////////////////// VEHICULOS-INICIO

    /*
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



                       for (int i = 0; i < dataArray.length(); i++) {

                            // Creo la variable 'datosModelo' y le paso mi modelo 'MyAppModel'
                           vehiculo datosModelo = new vehiculo();

                            // Creo la  variable 'objetos' y recupero los valores
                            JSONObject objetos = dataArray.getJSONObject(i);

                            // Selecciono dato por dato
                            datosModelo.setIdvehiculos(objetos.getString("idvehiculos"));
                            datosModelo.setMarca(objetos.getString("marca"));
                            datosModelo.setModelo(objetos.getString("modelo"));
                            datosModelo.setImg(objetos.getString("img"));
                            datosModelo.setDescripcion(objetos.getString("descripcion"));
                            datosModelo.setPrecAlquile(objetos.getString("prec_alquile"));
                            datosModelo.setStatus(objetos.getString("status"));


                            // Meto los datos en el array que definí más arriba 'listaArray'
                           lista.add(datosModelo);

                        }
                     //   vehiculoAdapter  adapter =    new vehiculoAdapter (getApplicationContext(),lista );
                    //    recyclerVista.setAdapter(adapter);
                        miAdapter =    new vehiculoAdapter (getApplicationContext(),lista );
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
    /////////////////// VEHICULOS-FIN


}