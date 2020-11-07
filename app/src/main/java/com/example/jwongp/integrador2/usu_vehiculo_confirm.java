package com.example.jwongp.integrador2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import android.content.SharedPreferences;

import com.example.jwongp.integrador2.utils.Config;

public class usu_vehiculo_confirm extends AppCompatActivity {

    private String Idvehiculos;
    private ImageView imgVehiculo;
    private TextView  vehiculo,precio,fech_ini,fech_fin,dias,monto_total;
    private Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_vehiculo_confirm);

        imgVehiculo = (ImageView) findViewById(R.id.imgVehiculo);
        vehiculo = (TextView) findViewById(R.id.vehiculo);
        precio = (TextView) findViewById(R.id.precio);
        fech_ini = (TextView) findViewById(R.id.fech_ini);
        fech_fin = (TextView) findViewById(R.id.fech_fin);
        dias = (TextView) findViewById(R.id.dias);
        monto_total = (TextView) findViewById(R.id.monto_total);

        SharedPreferences prefs = getSharedPreferences("VehiculoSeleccionado", MODE_PRIVATE);
        Idvehiculos = prefs.getString("Idvehiculos", "");
        String url = prefs.getString("ImgURL", "");
        String fecha1 = prefs.getString("fecha1", "");
        String fecha2 = prefs.getString("fecha2", "");
        String dias_alquiler = prefs.getString("dias", "");
        String PrecAlquiler_ = prefs.getString("PrecAlquiler", "");
        String vh = prefs.getString("Modelo", "") + " - " + prefs.getString("Marca", "");

        vehiculo.setText(vh);
        fech_ini.setText("Fecha inicial : " + fecha1);
        fech_fin.setText("Fecha final : " + fecha2);
        dias.setText("Dias de alquiler : " + dias_alquiler);
        precio.setText("Alquiler por día : S/ " + PrecAlquiler_);


        try{
            Double monto_total_=0.0;
            monto_total_= Double.valueOf(PrecAlquiler_) * Double.valueOf(dias_alquiler) ;
            //      Double.valueOf(PrecAlquile) * Double.valueOf(dias_alquiler) ;

            monto_total.setText("Monto total : S/ " + monto_total_);

            SharedPreferences.Editor editor = getSharedPreferences("VehiculoSeleccionado", MODE_PRIVATE).edit();
            editor.putString("monto_total", monto_total_.toString());
            editor.apply();
        }catch(Exception ex){
            Config.mensaje(getApplicationContext(),ex.toString(),2,"e");
        }
       // Config.mensaje(getApplicationContext(),accion,2,"r");


        try{
            Picasso.with(getApplicationContext()).load(url).into(imgVehiculo);
        }catch(Exception ex){
            Config.mensaje(getApplicationContext(),ex.toString(),2,"e");
        }
        btnPagar=(Button) findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( usu_vehiculo_confirm.this,  usu_vehiculo_pago.class );
                startActivity(intent2);
            }});
    }
}