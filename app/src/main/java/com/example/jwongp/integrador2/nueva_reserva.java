package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Button;

import com.example.jwongp.integrador2.utils.Config;
import java.util.Date;
import java.text.SimpleDateFormat;

public class nueva_reserva extends AppCompatActivity {

    TextView tv_titulo;
    Button btnBuscar;
    DatePicker datePickerInicial;
    DatePicker        datePickerFinal;
    String accion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reserva);

        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
          accion = prefs.getString("accion" , "");
        Config.mensaje(getApplicationContext(),accion,2,"i");

        if (accion.equals("alquilar")){
            //reserva
            tv_titulo.setText("SELECCIONE RANGO \nDE CONSULTA");
            btnBuscar.setText("VER VEHICULOS DISPONIBLES");
        }else {
            //reservar - no modificamos nada
        }

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fecha inicial
                DatePicker datePickerInicial = (DatePicker) findViewById(R.id.datePickerInicial);

                int year = datePickerInicial.getYear();
                int month = datePickerInicial.getMonth();
                int day = datePickerInicial.getDayOfMonth();

                Date d = new Date(year, month, day);
                String  strDate1 =  day + "/" + month + "/"+year  ;

                //Fecha final
                DatePicker datePickerFinal = (DatePicker) findViewById(R.id.datePickerFinal);
                  year = datePickerFinal.getYear();
                  month = datePickerFinal.getMonth();
                  day = datePickerFinal.getDayOfMonth();

                Config.mensaje(getApplicationContext(),"fecha final : "+ year + "/" + month + "/"+ day,2,"i");

                Date d2 = new Date(year, month, day);
                String  strDate2 =  day + "/" + month + "/"+year  ;

                //Calcula dias
                int dias=(int) ((d2.getTime()-d.getTime())/86400000);

                if(dias<0){
                    Config.mensaje(getApplicationContext(),  "La fecha final debe ser superior o igual a la inicial!",1,"e");

                }

                String dias_ =String.valueOf(dias) ;

                SharedPreferences.Editor editor = getSharedPreferences("VehiculoSeleccionado", MODE_PRIVATE).edit();
                editor.putString("fecha1", strDate1);
                editor.putString("fecha2", strDate2);

              //  Config.mensaje(getApplicationContext(),"fecha inicial : "+ strDate1 + "\nfecha final : " + strDate2 + "\ndias : "+ dias,2,"i");

                if (strDate1.equals(strDate2)){
                    editor.putString("dias", "1");
                }else {
                    editor.putString("dias", dias_);
                }
               // editor.putString("accion","elegir_vehiculo" );
                editor.apply();

                Intent intent2 = new Intent( nueva_reserva.this,  admin_vehiculos.class );
                startActivity(intent2);

            }
        });
    }
}