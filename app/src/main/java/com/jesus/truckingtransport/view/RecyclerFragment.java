package com.jesus.truckingtransport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.model.pojo.Paquete;
import com.jesus.truckingtransport.view.adapter.RecyclerAdapter;
import com.jesus.truckingtransport.view.adapter.RecyclerAdapterPackage;
import com.jesus.truckingtransport.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private ViewModel viewModel;
    List<Camionero> listaTrucksDriver = new ArrayList<>();
    List<Paquete> listaPackages = new ArrayList<>();

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        final NavController navC = Navigation.findNavController(view);

        if(viewModel.isRecycler() == true){

            viewModel.getTruckDrivers().observe(getActivity(), new Observer<List<Camionero>>() {
                @Override
                public void onChanged(List<Camionero> camioneros) {
                    Log.v("XXXXX", camioneros.toString());
                    listaTrucksDriver.clear();
                    listaTrucksDriver.addAll(camioneros);
                    RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                    final RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), listaTrucksDriver, navC);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            });

        }else{

            viewModel.getListaPaquetes().observe(getActivity(), new Observer<List<Paquete>>() {
                @Override
                public void onChanged(List<Paquete> paquetes) {
                    Log.v("XXXXX", paquetes.toString());
                    listaPackages.clear();
                    listaPackages.addAll(paquetes);
                    RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                    final RecyclerAdapterPackage adapter = new RecyclerAdapterPackage(getActivity(), listaPackages, navC);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            });

        }


    }
}