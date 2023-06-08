package com.example.flexfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.MyViewHolder>{
    Context context;
    ArrayList<ClaseEjercicio> arrayEjercicios;

    public ReciclerViewAdaptador(Context context, ArrayList<ClaseEjercicio> arrayEjercicios){
        this.context = context;
        this.arrayEjercicios = arrayEjercicios;
    }

    @NonNull
    @Override
    public ReciclerViewAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recicler_view_row,parent,false);

        return new ReciclerViewAdaptador.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciclerViewAdaptador.MyViewHolder holder, int position) {
        holder.txtTitulo.setText(arrayEjercicios.get(position).getTitulo());
        holder.txtDesc.setText(arrayEjercicios.get(position).getDesc());
    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return arrayEjercicios.size();
    }

    //Constructor del ReciclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitulo, txtDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDesc = itemView.findViewById(R.id.txtDescEjercicio);
            txtTitulo = itemView.findViewById(R.id.txtTituloEjercicio);
        }
    }//MyViewHolder

}//ReciclerViewAdaptador
