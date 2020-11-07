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

import com.example.jwongp.integrador2.interfaces.ItemListener;
import java.util.ArrayList;

import com.example.jwongp.integrador2.modelos.usuarioData;
import com.example.jwongp.integrador2.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;


public   class UsuarioAdapter  extends  RecyclerView.Adapter<UsuarioAdapter.MyViewHolder> implements View.OnClickListener     {
    private Context mContext;
    private ArrayList<usuarioData> mLista;

    private ItemClickListener mClickListener;

    private  ItemListener itemListener;

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
    void setClickListener( UsuarioAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public  UsuarioAdapter(Context context, ArrayList<usuarioData> lista, ItemListener itemListener){
        //public vehiculoAdapter(Context context, ArrayList<vehiculo> lista){

        this.mContext = context;
        this.mLista = lista;
        this.itemListener = itemListener;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // implements View.OnClickListener

        public TextView id_usuario,   user_dni, user_ape, user_pass,  user_tipo, user_nom, user_mail;


        public MyViewHolder(View view,final ItemListener itemListener) {
            super(view);

            id_usuario =(TextView) view.findViewById(R.id.id_usuario);
            user_dni =(TextView)  view.findViewById(R.id.user_dni);
            user_ape =(TextView)  view.findViewById(R.id.user_ape);
            user_pass =(TextView)  view.findViewById(R.id.user_pass);
            user_tipo =(TextView)  view.findViewById(R.id.user_tipo);
            user_nom =(TextView)  view.findViewById(R.id.user_nom);
            user_mail =(TextView)  view.findViewById(R.id.user_mail);

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition());
                }
            });

        }

    }
    @Override
    public  UsuarioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //layout inflator-set view of each item of recyclerview
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_card , parent, false);
        // .inflate(R.layout.activity_admin_vehiculos , parent, false);

        return new  UsuarioAdapter.MyViewHolder(v,itemListener);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.MyViewHolder holder, int position) {
        //get item at position

        String idUsuario;
        String fechaDef;
        String userDni;
        String userApe;
        String userPass;
        String userStatus;
        String userTipo;
        String userNom;
        String userMail;

        usuarioData actual =mLista.get(position);
        idUsuario =actual.getIDUsuario();
        fechaDef =actual.getFechaDef();
        userDni =actual.getUserDni();
        userApe =actual.getUserApe();
        userPass =actual.getUserPass();
        userStatus =actual.getUserStatus();
        userTipo =actual.getUserTipo();
        userNom =actual.getUserNom();
        userMail =actual.getUserMail();



        holder.id_usuario.setText(idUsuario);
        holder.user_dni.setText(userDni);
        holder.user_ape.setText(userApe);
        holder.user_pass.setText(userPass);
        holder.user_nom.setText(userNom);
        holder.user_mail.setText(userMail);

        if(userTipo.trim().equals("0")){
            userTipo="ADMINISTRADOR";
        }else {
            userTipo="USUARIO";
        }
        holder.user_tipo.setText(userTipo);

    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
}