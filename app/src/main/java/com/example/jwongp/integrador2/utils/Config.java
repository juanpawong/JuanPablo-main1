package com.example.jwongp.integrador2.utils;

import android.content.SharedPreferences;
import android.widget.Toast;
import android.content.Context;
import android.util.Log;
import android.content.SharedPreferences.Editor ;

import java.util.regex.*;

public class Config {
     /*
    public static final String URL_JSON                             =  "https://proyectoint01.000webhostapp.com/x.sSc.R90$o0/apis/";
    public static final String URL_IMG                              =  "https://proyectoint01.000webhostapp.com/img/vehiculos/";
    */
    public static final String URL_JSON                             =  "https://www.tuguiadecomprasalamanca.com/x.sSc.R90$o0/apis/";
    public static final String URL_IMG                              =  "https://www.tuguiadecomprasalamanca.com/img/vehiculos/";


    public static final String URL_LOGIN                            =   "login.php";
    public static final String URL_RESERVAS                         =   "reservas.php";
    public static final String URL_RESERVAS_ALL                     =   "reservas_all.php";
    public static final String URL_GET_VEHICULOS_DISPONIBLES        =   "vehiculos.php";
    public static final String URL_REGISTRO_PAGO                    =   "pago.php";
    public static final String URL_GET_VEHICULOS_TODOS              =   "vehiculos_full.php";
    public static final String URL_REGISTRO                         =   "register_mail.php";
    public static final String URL_RECUPERA_PASSWORD                =   "recuperar_ps.php";
    public static final String URL_ADMIN_USUARIOS_LIST              =   "admin_usuarios.php";
    public static final String URL_USUARIOS_EXCEL                   =   "admin_usuarios_excel.php";
    public static final String URL_USUARIOS_EDIT                    =   "admin_usuarios_edit.php";
    public static final String API_SK                               =   "4LQU1L3RV3H1CUL0S";

   // public static String TAG_MENSAJE  ="ALERTA>";
   // public static String TAG_SUCCESS  ="SUCCESS>";

    public static void SetConfig( String nodo_,  String key_ , String value_) {

       // SharedPreferences.Editor editor = getSharedPreferences(nodo_, MODE_PRIVATE).edit();
     /*   SharedPreferences.Editor editor = getSharedPreferences(nodo_, 0).edit();
        editor.putString(key_, value_);

        editor.apply();*/
    }
    public static void mensaje(Context context, String mensaje, int toast, String TAG ) {

        try {
            if (toast==1){
                Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();

            }

            if(TAG.length()==0 || TAG.equals("R")  || TAG.equals("r")){
                TAG= "RESULTADOS>>";
              //  Log.i(TAG,   mensaje);
            }else if(TAG.toUpperCase().equals("E")){
                TAG= "ERROR>>";
               // Log.e(TAG,   mensaje);
            }else if(TAG.toUpperCase().equals("U")){
                TAG= "URL>>";
               // Log.i(TAG,   mensaje);
            }else if(TAG.toUpperCase().equals("A")){
                TAG= "ALERTA>>";
               // Log.i(TAG,   mensaje);
            }else if(TAG.toUpperCase().equals("I")){
                TAG= "INFORMACION>>";
               // Log.i(TAG,   mensaje);
            }else {
                TAG= "OTROS>>";
            }
            Log.d(TAG,  "\n"+ mensaje);
        }
        catch(Exception ex) {
            Log.d(TAG, ex.toString());
          //  ex.printStackTrace();
        }
    }
    public static boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
