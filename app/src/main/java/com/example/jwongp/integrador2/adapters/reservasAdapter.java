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
import com.example.jwongp.integrador2.modelos.Reservas;
import com.example.jwongp.integrador2.interfaces.ItemListener;
import java.util.ArrayList;

import com.example.jwongp.integrador2.modelos.vehiculo;
import com.example.jwongp.integrador2.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class reservasAdapter extends RecyclerView.Adapter<reservasAdapter.MyViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Reservas> mLista;

   private  ItemClickListener mClickListener;

    private  ItemListener itemListener;
/*
    void setClickListener(reservasAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }*/
    public String getItem(int position) {
        return mLista.get(position).toString();
    }
    @Override
    public void onClick(View view) {
        //  if (mClickListener != null)
        //      mClickListener.onRVItemClick(view, getAdapterPosition());
    }

    public interface ItemClickListener {
        void onRVItemClick(View view, int position);
    }
    void setClickListener(reservasAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public reservasAdapter(Context context, ArrayList<Reservas> lista, ItemListener itemListener){
        //public vehiculoAdapter(Context context, ArrayList<vehiculo> lista){

        this.mContext = context;
        this.mLista = lista;
        this.itemListener = itemListener;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // implements View.OnClickListener
        public ImageView img;
        public TextView idvehiculos,marca,modelo,precAlquile,status,monto,fechaInicio,fechaFin,diasAlquiler,cliente;


        public MyViewHolder(View view,final ItemListener itemListener) {
            super(view);
            img =(ImageView)  view.findViewById( R.id.img);
            idvehiculos =(TextView) view.findViewById(R.id.idvehiculos);
            marca =(TextView)  view.findViewById(R.id.marca);
            modelo =(TextView)  view.findViewById(R.id.modelo);
            precAlquile =(TextView)  view.findViewById(R.id.precAlquile);
            status =(TextView)  view.findViewById(R.id.status);
            monto =(TextView)  view.findViewById(R.id.monto);
            fechaFin =(TextView)  view.findViewById(R.id.fechaFin);
            fechaInicio =(TextView)  view.findViewById(R.id.fechaInicio);
            diasAlquiler =(TextView)  view.findViewById(R.id.diasAlquiler);
            cliente =(TextView)  view.findViewById(R.id.cliente);

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition());
                }
            });

        }

    }
    @Override
    public reservasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //layout inflator-set view of each item of recyclerview
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservas_card , parent, false);
        // .inflate(R.layout.activity_admin_vehiculos , parent, false);

         return new reservasAdapter.MyViewHolder(v,itemListener);
    }

    @Override
    public void onBindViewHolder(reservasAdapter.MyViewHolder holder, int position) {
        //get item at position

        String idvehiculos_,marca_,modelo_,precAlquile_,status_,monto_,fechaInicio_,fechaFin_,diasAlquiler_,img_,cliente_;
        Reservas actual =mLista.get(position);
        idvehiculos_ =actual.getIdvehiculos();
        marca_ ="Marca : " + actual.getMarca();
        modelo_ ="Modelo : " + actual.getModelo();
        img_ =actual.getImgURL();
        precAlquile_ ="Alquiler por dia : S/ " + actual.getPrecAlquile();// + "\n" + img;
        status_ =actual.getStatus();
        monto_ ="Monto total : S/ " + actual.getMonto();
        fechaInicio_ ="Fecha inicial : " +actual.getFechaInicio();
        fechaFin_ ="Fecha final : " + actual.getFechaFin();
        diasAlquiler_ ="Dias alquilado : " +actual.getDiasAlquiler();
        cliente_ ="Cliente : " +actual.getCliente();

        Config.mensaje(mContext,"ruta de imagen : " + img_,2,"r");




        holder.idvehiculos.setText(idvehiculos_);
        holder.marca.setText(marca_);
        holder.modelo.setText(modelo_);
        holder.precAlquile.setText(precAlquile_);

        holder.monto.setText(monto_);
        holder.fechaInicio.setText(fechaInicio_);
        holder.fechaFin.setText(fechaFin_);
        holder.diasAlquiler.setText(diasAlquiler_);
        holder.cliente.setText(cliente_);


        if(status_.trim().equals("0")){
            status_="ESTADO : ALQUILADO";
        }else {
            status_="ESTADO : FINALIZADO";
        }
        holder.status.setText(status_);
        //Picasso.with(mContext).load(img).fit().centerInside().into(holder.imgVehiculo);
        // Picasso.with(mContext).load(img).fit().centerCrop().into(holder.imgVehiculo);
        Picasso.with(mContext).load(img_).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
}
