package com.example.jwongp.integrador2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import com.example.jwongp.integrador2.adapters.reservasAdapter;
import com.example.jwongp.integrador2.adapters.vehiculoAdapter;
import com.example.jwongp.integrador2.modelos.Reservas;
import com.example.jwongp.integrador2.modelos.vehiculo;
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
import com.example.jwongp.integrador2.modelos.Reservas;
import com.example.jwongp.integrador2.adapters.reservasAdapter;
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



public class usu_reservas extends AppCompatActivity  implements ItemListener  {
    private RecyclerView recyclerVista;
    private Reservas misReservas;
    private ArrayList<Reservas> lista;
    private GridLayoutManager glm;
    private reservasAdapter miAdapter;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_reservas);

        titulo=(TextView) findViewById(R.id.titulo);

        recyclerVista =(RecyclerView) findViewById(R.id.recyclerVista);
        recyclerVista.setHasFixedSize(true);
        glm = new GridLayoutManager(this, 1);
        recyclerVista.setLayoutManager( glm);

        lista=new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        String usuario_dni = prefs.getString("ndoc", "");
        String reservas_vista = prefs.getString("reservas_vista",  "");

        String AuxapisURL ="";

        if (reservas_vista.equals("all")){
            AuxapisURL =Config.URL_JSON + Config.URL_RESERVAS_ALL +
                    "?sk=4LQU1L3RV3H1CUL0S" ;
            titulo.setText("RESERVAS COMPLETAS");
        }else{
            AuxapisURL =Config.URL_JSON + Config.URL_RESERVAS+
                    "?sk=4LQU1L3RV3H1CUL0S&dni="+usuario_dni;
        }

        Config.mensaje(getApplicationContext(),AuxapisURL,2,"U");
        if (InternetConnection.checkConnection(getApplicationContext())) {


            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url =AuxapisURL;//

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
    @Override
    public void onClick(int posicion){
        Config.mensaje(getApplicationContext(),"posicion : " + posicion ,2,"r");
        Config.mensaje(getApplicationContext(),"lista : " + this.lista.get(posicion).getMarca().toString(),2,"r");

    }

    private void procesarResultado(String result){
        Config.mensaje(getApplicationContext(),result.toString(),2,"R");

        if (result.indexOf("OK" )>0 && result.indexOf("success" )>0){
            try {
                JSONObject jsonObject = new JSONObject(result);
                //    JSONObject jsonObj = new JSONObject("{\"count\":3939,\"has_more\":true,\"map_location\":{\"lat\":0.60996950000000183,\"lon\":-27.568517000000003,\"panoramio_zoom\":16},\"photos\":[{\"height\":375,}]}");
                if (jsonObject.getString("resultado").toString().equals("OK")) {

                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    Config.mensaje(getApplicationContext(),"dataArray ->" + dataArray.toString(),2,"R");

                    for (int i = 0; i < dataArray.length(); i++) {

                        // Creo la variable 'datosModelo' y le paso mi modelo 'MyAppModel'
                        Reservas datosModelo = new Reservas();

                        // Creo la  variable 'objetos' y recupero los valores
                        JSONObject objetos = dataArray.getJSONObject(i);

                        // Selecciono dato por dato
                        datosModelo.setIdvehiculos(objetos.getString("idvehiculos"));
                        datosModelo.setMarca(objetos.getString("marca"));
                        datosModelo.setModelo(objetos.getString("modelo"));
                        datosModelo.setImg(objetos.getString("img"));
                        datosModelo.setPrecAlquile(objetos.getString("prec_alquile"));
                        datosModelo.setStatus(objetos.getString("status"));
                        datosModelo.setMonto(objetos.getString("monto"));
                        datosModelo.setFechaInicio(objetos.getString("fecha_inicio"));
                        datosModelo.setFechaFin(objetos.getString("fecha_fin"));
                        datosModelo.setDiasAlquiler(objetos.getString("dias_alquiler"));
                        datosModelo.setCliente(objetos.getString("cliente_nombre"));

                        // Agrego los datos en el array definido 'listaArray'
                        lista.add(datosModelo);
                    }
                    miAdapter =    new reservasAdapter(getApplicationContext(),lista,this );
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