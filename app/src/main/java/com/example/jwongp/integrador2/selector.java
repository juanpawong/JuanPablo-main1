package com.example.jwongp.integrador2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class selector extends AppCompatActivity {
    Button btnUsuario;
    Button btnAdmin;


    private String usuario_id,nombre_completo,usuario_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);



        Button btnUsuario= (Button) findViewById(R.id.btnUsuario);
        Button btnAdmin= (Button) findViewById(R.id.btnAdmin);

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent2 = new Intent( selector.this,  activity_principal.class );
                startActivity(intent2);
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intreg= new Intent(selector.this,admin.class);
                startActivity(intreg);
            }
        });
    }
}