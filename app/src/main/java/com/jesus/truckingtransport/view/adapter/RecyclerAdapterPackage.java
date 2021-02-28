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
import com.jesus.truckingtransport.model.pojo.Paquete;
import com.jesus.truckingtransport.viewmodel.ViewModel;

import java.util.List;

public class RecyclerAdapterPackage extends RecyclerView.Adapter<RecyclerAdapterPackage.ViewHolder> {

    private Context contexto;
    private List<Paquete> paquetes;
    private NavController navCAux;
    private ViewModel viewModel;

    public RecyclerAdapterPackage(Context contexto, List<Paquete> paquetes,  NavController navCAux) {
        this.contexto = contexto;
        this.paquetes = paquetes;
        this.navCAux = navCAux;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) contexto).get(ViewModel.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //pasarle el numero de llamadas tambien

        holder.elnombre.setText(paquetes.get(position).getDescripcion()); //camioneros.get(position0) -> tengo el objeto camionero
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setPaquete(paquetes.get(position));
                navCAux.navigate(R.id.action_recyclerFragment_to_editPackageFragment);
            }
        });

    }


    @Override
    public int getItemCount() {
        return paquetes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView elnombre;
        ConstraintLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            elnombre = itemView.findViewById(R.id.tvNomDriver);
            parentlayout = itemView.findViewById(R.id.parentlayout2);
        }


    }
}
