package com.jesus.truckingtransport.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.viewmodel.ViewModel;


public class EditFragment extends Fragment {

    private ViewModel viewModel;
    private EditText eturlEdit, etNameEdit, etPhone2, etSalary2, etPopulation2;
    private Camionero camionero = new Camionero();
    private ImageView image;
    private Button btEdit, btDelete;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eturlEdit = view.findViewById(R.id.eturlEdit);
        etNameEdit = view.findViewById(R.id.etNameEdit);
        etPhone2 = view.findViewById(R.id.etPhone2);
        etSalary2 = view.findViewById(R.id.etSalary2);
        etPopulation2 = view.findViewById(R.id.etPopulation2);
        image = view.findViewById(R.id.imageTruckDriver);
        btEdit = view.findViewById(R.id.btEdit);
        btDelete = view.findViewById(R.id.btDelete);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        camionero = viewModel.getCamionero();
        eturlEdit.setText(camionero.getFoto());
        etNameEdit.setText(camionero.getNombre());
        etPhone2.setText(camionero.getTelefono());
        etSalary2.setText(camionero.getSalario()+"");
        etPopulation2.setText(camionero.getPoblacion());
        Glide.with(getContext()).load(camionero.getFoto()).into(image);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camionero driver = new Camionero();
                driver.setFoto(eturlEdit.getText().toString());
                driver.setNombre(etNameEdit.getText().toString());
                driver.setTelefono(etPhone2.getText().toString());
                driver.setSalario(Float.parseFloat(etSalary2.getText().toString()));
                driver.setPoblacion(etPopulation2.getText().toString());
                viewModel.deleteTruckDriver(camionero);
                viewModel.updateTruckDriver(driver);
                Toast.makeText(getActivity(), "Update correctly", Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(EditFragment.this).navigate(R.id.action_editFragment_to_FirstFragment);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getContext());
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Are you sure?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       viewModel.deleteTruckDriver(camionero);
                        Toast.makeText(getActivity(), "Delete correctly", Toast.LENGTH_LONG).show();
                        NavHostFragment.findNavController(EditFragment.this).navigate(R.id.action_editFragment_to_FirstFragment);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        });







    }
}