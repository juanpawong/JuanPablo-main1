package com.example.jwongp.integrador2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.jwongp.integrador2.utils.Config;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jwongp.integrador2.utils.Config;
import com.example.jwongp.integrador2.utils.InternetConnection;

import java.util.regex.Pattern;

public class usu_vehiculo_pago extends AppCompatActivity {
    private TextView monto_total;
    private EditText tarjeta,tarjeta_fecha,cod_validacion,titular,email;
    private Button btnPagar;
    private String tipo_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_vehiculo_pago);

        SharedPreferences prefs1 = getSharedPreferences("VehiculoSeleccionado", MODE_PRIVATE);

        monto_total=(TextView) findViewById(R.id.monto_total);

        final String monto = prefs1.getString("monto_total" , "");
        monto_total.setText("Total a pagar S/" +monto);

       final String vehiculoId =prefs1.getString("Idvehiculos" , "");
        final String dias_alquiler =prefs1.getString("dias" , "");
        final String fecha_inicio =prefs1.getString("fecha1" , "");
        final String fecha_fin =prefs1.getString("fecha2" , "");

        tarjeta=(EditText) findViewById(R.id.tarjeta);
        tarjeta_fecha=(EditText) findViewById(R.id.tarjeta_fecha);
        cod_validacion=(EditText) findViewById(R.id.cod_validacion);
        titular=(EditText) findViewById(R.id.titular);
        email=(EditText) findViewById(R.id.email);

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        final String nombre =prefs.getString("nombre","" );

        tipo_usuario = prefs.getString("tipo_usuario" , "");

        final String dni = prefs.getString("ndoc" , "");

        titular.setText(nombre);

        btnPagar=(Button) findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (InternetConnection.checkConnection(getApplicationContext())) {

                    //vehiculoId $dni tarjeta,tarjeta_fecha,cod_validacion,titular,email

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    String AuxapisURL = Config.URL_JSON + Config.URL_REGISTRO_PAGO+
                            "?sk=4LQU1L3RV3H1CUL0S"+
                            "&tarjeta="+tarjeta.getText() +"&tarjeta_fecha="+tarjeta_fecha.getText() +
                            "&dni="+dni +"&vehiculoId=" +vehiculoId +
                            "&monto="+monto +"&fecha_inicio="+fecha_inicio +
                            "&fecha_fin="+fecha_fin +"&dias_alquiler=" +dias_alquiler+
                            "&cod_validacion="+cod_validacion.getText() +"&titular="+titular.getText() +
                            "&email="+email.getText() +
                            "&fecha_caducidad="+tarjeta_fecha.getText() ;

                    String url =AuxapisURL;//"http://www.google.com";
                    Config.mensaje(getApplicationContext(),  AuxapisURL,2 ,"u");
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


            }});
    }
    private void procesarResultado(String result){

        Config.mensaje(getApplicationContext(),result,2,"R");

        if(result.indexOf("|")>0){

        }else {
            return ;
        }
        String[] responseAPI;
        responseAPI = result.toString().split(Pattern.quote("|"));
        //Log.i("info: result : ", result);

        String rs = String.valueOf(responseAPI[0]).trim(); //resultado
        String p1 = String.valueOf(responseAPI[1]).trim(); // mensaje

        if (rs.equals("OK")){
            Config.mensaje(getApplicationContext(),"Pago registrado exitosamente!",1,"e");
            /**/
                if(tipo_usuario.equals("0")){
                    //admin
                    Intent intent2 = new Intent( usu_vehiculo_pago.this,  selector.class );
                    startActivity(intent2);
                }else{
                    //usuario
                    Intent intent2 = new Intent( usu_vehiculo_pago.this,  activity_principal.class );
                    startActivity(intent2);
                }

        } else{
            Config.mensaje(getApplicationContext(),p1,1,"e");
        }
    }
}