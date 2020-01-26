package com.example.ud8_ejemplo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ud8_ejemplo1.basedatos.Trabajador;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrabajadorAdapter extends RecyclerView.Adapter<TrabajadorAdapter.TrabajadorViewHolder>{

    private Context context;
    List<Trabajador> lista;
    private View.OnClickListener onClickListener; // Atributo para el evento

    public TrabajadorAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrabajadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.elementos_lista,parent,false);

        view.setOnClickListener(this.onClickListener);

        TrabajadorViewHolder miViewHolder=new TrabajadorViewHolder(view);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajadorViewHolder holder, int position) {
        Trabajador t = lista.get(position);

        holder.idtextView.setText(String.valueOf(t.getId()));
        holder.nombretextView.setText(t.getNombre());
    }

    @Override
    public int getItemCount() {
        if(lista == null)
            return 0;
        else
            return lista.size();
    }
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    // Método para añadir una lista de trabajadores al RecyclerView.
    public void anyadirALista(List<Trabajador> lista){
        this.lista = lista;
        notifyDataSetChanged(); // Actualizamos el recyclerView
    }

    class TrabajadorViewHolder extends RecyclerView.ViewHolder {

        TextView idtextView;
        TextView nombretextView;

        public TrabajadorViewHolder(View itemView) {
            super(itemView);

            idtextView = itemView.findViewById(R.id.idtextView);
            nombretextView = itemView.findViewById(R.id.nombretextView);
        }
    }
}

