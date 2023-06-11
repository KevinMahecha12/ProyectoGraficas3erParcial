package aplicacionfitness.directoriofitness.flexfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flexfitness.R;

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

        String vid = this.arrayEjercicios.get(position).getVideo();
        int resourceId = this.context.getResources().getIdentifier(vid, "raw", this.context.getPackageName());
        //Toast.makeText(context, "nombreVid: " + vid + " id: " + resourceId, Toast.LENGTH_SHORT).show();

        Glide.with(this.context).load(resourceId).into(holder.imgGif);

    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return arrayEjercicios.size();
    }

    //Constructor del ReciclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitulo, txtDesc;
        ImageView imgGif;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDesc = itemView.findViewById(R.id.txtDescEjercicio);
            txtTitulo = itemView.findViewById(R.id.txtTituloEjercicio);
            imgGif = itemView.findViewById(R.id.imgGif);
        }
    }//MyViewHolder

}//ReciclerViewAdaptador
