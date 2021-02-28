package com.jesus.truckingtransport.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;


import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.viewmodel.ViewModel;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context contexto;
    private List<Camionero> camioneros;
    private NavController navCAux;
    private ViewModel viewModel;

    public RecyclerAdapter(Context contexto, List<Camionero> camioneros, NavController navCAux) {
        this.contexto = contexto;
        this.camioneros = camioneros;
        this.navCAux = navCAux;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) contexto).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //pasarle el numero de llamadas tambien

        holder.elnombre.setText(camioneros.get(position).getNombre()); //camioneros.get(position0) -> tengo el objeto camionero
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setCamionero(camioneros.get(position));
                navCAux.navigate(R.id.action_recyclerFragment_to_editFragment);
            }
        });

    }


    @Override
    public int getItemCount() {
        return camioneros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView elnombre;
        ConstraintLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            elnombre = itemView.findViewById(R.id.tvNomDriver);
            parentlayout = itemView.findViewById(R.id.parentlayout);
        }


    }
}
