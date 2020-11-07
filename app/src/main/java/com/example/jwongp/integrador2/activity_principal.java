package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CalendarView;

public class activity_principal extends AppCompatActivity {

    private TextView textView2;
    private ImageView imv_mis_reservas;
    private ImageView imv_reservar;
    private ImageView imv_about;
    private String usuario_id,nombre_completo,usuario_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        textView2 = (TextView) findViewById(R.id.textView2);

        imv_mis_reservas = (ImageView) findViewById(R.id.imv_mis_reservas);
        imv_reservar = (ImageView) findViewById(R.id.imv_reservar);
        imv_about = (ImageView) findViewById(R.id.imv_about);


        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);
        usuario_id = prefs.getString("id", "");
        nombre_completo ="\tBienvenido \n\t"+ prefs.getString("nombre", "");
        usuario_dni = prefs.getString("ndoc", "");

        textView2.setText(nombre_completo);
       // SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
      //  editor.putString("id", p1.toString());
      //  editor.putString("ndoc", p2.toString());
      //  editor.putString("nombre", p4.toString());
      //  editor.apply();

        imv_mis_reservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SharedPreferences.Editor editor5 = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
               editor5.putString("reservas_vista", "usu");
               editor5.apply();

              Intent intent2 = new Intent( activity_principal.this,  usu_reservas.class );
                startActivity(intent2);
             // finish();
              }
        });
        imv_reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                editor.putString("accion", "alquilar");
                editor.putString("presentacion", "horizontal");
                editor.apply();


                //  Intent intent2 = new Intent( activity_principal.this,  admin_vehiculos.class );
             Intent intent2 = new Intent( activity_principal.this,  nueva_reserva.class );
                startActivity(intent2);
               // finish();
            }
        });
        imv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(activity_principal.this,   about_me.class );
                startActivity(intent);

             //   finish();

            }
        });
    }
}
