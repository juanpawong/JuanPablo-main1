package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.jwongp.integrador2.utils.Config;
import com.example.jwongp.integrador2.modelos.usuarioData;

public class admin extends AppCompatActivity {
    private TextView textView4;
    Button btnUsuarios;
    Button bnAdminVehiculos;
    Button btnReservas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        textView4 = (TextView) findViewById(R.id.textView4);

        SharedPreferences prefs = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE);

        String nombre_completo ="\tBienvenido \n\t"+ prefs.getString("nombre", "");
        textView4.setText(nombre_completo);




        btnUsuarios = (Button) findViewById(R.id.btnUsuarios);
        btnUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent( getApplicationContext(),admin_usuarios.class );
                startActivity(intent2);

                //finish();
            }
        });

        bnAdminVehiculos = (Button) findViewById(R.id.bnAdminVehiculos);
        bnAdminVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                editor.putString("accion", "listar");
                editor.putString("presentacion", "vertical");
                editor.apply();


                Intent intent2 = new Intent( getApplicationContext(),admin_vehiculos.class );
                startActivity(intent2);

              //  finish();
            }
        });

        btnReservas = (Button) findViewById(R.id.btnReservas);
        btnReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor editor5 = getSharedPreferences("alquilerVehiculos", MODE_PRIVATE).edit();
                editor5.putString("reservas_vista", "all");
                editor5.apply();

                Intent intent2 = new Intent( admin.this,  usu_reservas.class );
                startActivity(intent2);
              //  Intent intent2 = new Intent( getApplicationContext(),admin_reservas.class );
              //  startActivity(intent2);

              //  finish();
            }
        });
    }
}