package com.example.jwongp.integrador2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jwongp.integrador2.R;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import com.example.jwongp.integrador2.modelos.vehiculo;
import com.example.jwongp.integrador2.interfaces.ItemListener;
import java.util.ArrayList;

import com.example.jwongp.integrador2.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

/* public     class vehiculoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{*/
public     class vehiculoAdapter extends RecyclerView.Adapter<vehiculoAdapter.MyViewHolder> implements View.OnClickListener{

    private Context mContext;
    private ArrayList<vehiculo> mLista;

    private ItemClickListener mClickListener;

    private  ItemListener itemListener;


    @Override
    public void onClick(View view) {
      //  if (mClickListener != null)
      //      mClickListener.onRVItemClick(view, getAdapterPosition());
    }

    public interface ItemClickListener {
        void onRVItemClick(View view, int position);
    }
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public String getItem(int position) {
        return mLista.get(position).toString();
    }
    public vehiculoAdapter(Context context, ArrayList<vehiculo> lista, ItemListener itemListener){
    //public vehiculoAdapter(Context context, ArrayList<vehiculo> lista){

        this.mContext = context;
        this.mLista = lista;
        this.itemListener = itemListener;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // implements View.OnClickListener
        public ImageView imgVehiculo;
        public TextView modelo,marca,precio,idvehiculos,descripcion,status;
        public MyViewHolder(View view,final ItemListener itemListener) {
            super(view);
            imgVehiculo =(ImageView)  view.findViewById( R.id.imgVehiculo);
            modelo =(TextView) view.findViewById(R.id.modeloVehiculo);
            marca =(TextView)  view.findViewById(R.id.MarcaVehiculo);
            precio =(TextView)  view.findViewById(R.id.prec_alquile);
            idvehiculos =(TextView)  view.findViewById(R.id.idvehiculos);
            descripcion =(TextView)  view.findViewById(R.id.descripcion);
            status =(TextView)  view.findViewById(R.id.status);

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition());
                }
            });
           // view.setOnClickListener(this);

        }
  /*      @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onRVItemClick(view, getAdapterPosition());
        }*/
    }
     @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //layout inflator-set view of each item of recyclerview
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehiculo_card , parent, false);
               // .inflate(R.layout.activity_admin_vehiculos , parent, false);



        return new MyViewHolder(v,itemListener);
    }

     @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get item at position
        vehiculo actual =mLista.get(position);
          String idvehiculos =actual.getIdvehiculos();
          String marca ="Marca : " + actual.getMarca();
          String modelo ="Modelo : " + actual.getModelo();
          String img =actual.getImgURL();
          String descripcion =actual.getDescripcion();
          String precAlquile ="Alquiler x dia : S/ " + actual.getPrecAlquile();// + "\n" + img;
          String status =actual.getStatus();

        Config.mensaje(mContext,"ruta de imagen : " + img,2,"r");

        holder.modelo.setText(modelo);
        holder.marca.setText(marca);
        holder.precio.setText(precAlquile);
        holder.idvehiculos.setText(idvehiculos);
        holder.descripcion.setText(descripcion);

        if(status.trim().equals("1")){
            status="(disponible)";
        }else {
            status="(alquilado)";
        }
        holder.status.setText(status);
        //Picasso.with(mContext).load(img).fit().centerInside().into(holder.imgVehiculo);
       // Picasso.with(mContext).load(img).fit().centerCrop().into(holder.imgVehiculo);
        Picasso.with(mContext).load(img).into(holder.imgVehiculo);
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
}
