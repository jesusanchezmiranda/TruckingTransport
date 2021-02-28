package com.jesus.truckingtransport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.model.pojo.Paquete;
import com.jesus.truckingtransport.view.adapter.RecyclerDrivers;
import com.jesus.truckingtransport.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class InsertPackageFragment extends Fragment {
    private ViewModel viewModel;
    private EditText etIdCam, etDescription, etPrize;
    private Button btInsertP;
    private List<Camionero> listaTrucksDriver = new ArrayList<>();

    public InsertPackageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_package, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etIdCam = view.findViewById(R.id.etIdCam);
        etDescription = view.findViewById(R.id.etDescription);
        etPrize = view.findViewById(R.id.etPrize);
        btInsertP = view.findViewById(R.id.btInsertP);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        Paquete paquete = new Paquete();


        viewModel.getTruckDrivers().observe(getActivity(), new Observer<List<Camionero>>() {
            @Override
            public void onChanged(List<Camionero> camioneros) {
                Log.v("XXXXX", camioneros.toString());
                listaTrucksDriver.clear();
                listaTrucksDriver.addAll(camioneros);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerDrivers);
                final RecyclerDrivers adapter = new RecyclerDrivers(getActivity(), listaTrucksDriver);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });

        btInsertP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etIdCam.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty() || etPrize.getText().toString().isEmpty() ){
                    Toast.makeText(getActivity(), "Fill all the gaps", Toast.LENGTH_LONG).show();
                }else{
                    paquete.setNomCamionero(etIdCam.getText().toString());
                    paquete.setDescripcion(etDescription.getText().toString());
                    paquete.setPrecio(Float.parseFloat(etPrize.getText().toString()));
                    viewModel.insertaPaquete(paquete);
                    Toast.makeText(getActivity(), "Insert correctly", Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(InsertPackageFragment.this).navigate(R.id.action_insertPackageFragment_to_firstFragment);
                }

            }
        });

    }
}