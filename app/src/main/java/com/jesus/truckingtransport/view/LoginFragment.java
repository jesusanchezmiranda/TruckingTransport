package com.jesus.truckingtransport.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jesus.truckingtransport.R;
import com.jesus.truckingtransport.viewmodel.ViewModel;

public class LoginFragment extends Fragment {

    private TextInputEditText tietUser, tietPass;
    private TextInputLayout tilUser, tilPass;
    private ViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        tietUser = view.findViewById(R.id.tietUser);
        tietPass = view.findViewById(R.id.tietPass);
        tilUser = view.findViewById(R.id.tfUser);
        tilPass = view.findViewById(R.id.tfPass);


        view.findViewById(R.id.btSigIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = tietUser.getText().toString();
                String pass = tietPass.getText().toString();

                viewModel.isloggedin(user, pass).observe(getActivity(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_LoginFragment_to_firstFragment);
                            Toast.makeText(getActivity(), "Sign In correctly", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Error, ", Toast.LENGTH_SHORT).show();
                            tilPass.setError("Fill correctly the fields");
                            tilUser.setError("Fill correctly the fields");
                        }
                    }
                });
                
            }
        });
    }


}