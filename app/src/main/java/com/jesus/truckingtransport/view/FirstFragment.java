package com.jesus.truckingtransport.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;


import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private TextView tv1;
    private ViewModel viewModel;
    private Button btIT, btEDT, btIP, btEDP;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btIT = view.findViewById(R.id.btIT);
        btEDT = view.findViewById(R.id.btEDT);
        btIP = view.findViewById(R.id.btIP);
        btEDP = view.findViewById(R.id.btEDP);



        btIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_InsertFragment);
            }
        });

        btEDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setRecycler(true);
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_recyclerFragment);
            }
        });

        btIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_insertPackageFragment);
            }
        });

        btEDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setRecycler(false);
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_recyclerFragment);
            }
        });


        //tv1 = view.findViewById(R.id.tv1);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        //viewModel.getCamionero(1);

        //Camionero camionero = new Camionero("https://static2.elcomercio.es/www/multimedia/202004/02/media/cortadas/54289045--624x415.JPG", "Pepillo", "777888999", 400, "Pinos Puente");

        //List<Camionero> list = new ArrayList<>();


        //viewModel.insertCamionero(camionero);

        //viewModel.deleteCamionero(4);

//        viewModel.getListaCamioneros().observe(getActivity(), new Observer<List<Camionero>>() {
//            @Override
//            public void onChanged(List<Camionero> camioneros) {
//                Log.v("XXXXX", camioneros.toString());
//                list.clear();
//                list.addAll(camioneros);
//                //tv1.setText(list.get(1).getNombre().toString());
//            }
//        });



    }




}