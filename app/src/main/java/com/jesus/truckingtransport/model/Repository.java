package com.jesus.truckingtransport.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.model.pojo.Paquete;

import java.util.ArrayList;
import java.util.List;


public class Repository {

    private Context context;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private FirebaseAuth firebaseAuth;
    public Repository(Context context) {
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }


    public MutableLiveData<Boolean> isloggedin (String correo, String clave){

        MutableLiveData<Boolean> resultado = new MutableLiveData<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(correo, clave)

                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.v("XXXXX", "success");
                        currentUser = firebaseAuth.getCurrentUser();
                        //Log.v("XXXXX", currentUser.getEmail() + "   " + currentUser.isEmailVerified());
                        //Log.v("XXXXX", currentUser.toString());

                        resultado.setValue(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("XXXXX", "failure " + e.getLocalizedMessage());
                        currentUser = null; //firebaseAuth.getCurrentUser();
                        //Log.v("XXXXX", "failure" + currentUser.toString());
                        resultado.setValue(false);
                    }
                });


        return resultado;

    }

    public void insertTruckDriver(Camionero camionero){

        DocumentReference reference = db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre());
        reference.set(camionero);
    }

    public void insertaPaquete(Paquete paquete){
        DocumentReference reference = db.collection("user/"+ currentUser.getUid()+"/paquete").document(paquete.getDescripcion());
        reference.set(paquete);

    }

    public MutableLiveData<List<Camionero>> getTruckDrivers(){

        MutableLiveData<List<Camionero>> camioneros = new MutableLiveData<>();

            db.collection("user/"+ currentUser.getUid()+"/camionero") // obtengo todos los documentos
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        camioneros.setValue(task.getResult().toObjects(Camionero.class));
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.v("XXXXXX", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.v("XXXXX", task.getException().toString());
                    }
                }
            });


            return camioneros;

    }

    public MutableLiveData<List<Paquete>> getListaPaquetes(){

        MutableLiveData<List<Paquete>> paquetes = new MutableLiveData<>();

        db.collection("user/"+ currentUser.getUid()+"/paquete") // obtengo todos los documentos
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            paquetes.setValue(task.getResult().toObjects(Paquete.class));
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.v("XXXXXX", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.v("XXXXX", task.getException().toString());
                        }
                    }
                });


        return paquetes;

    }

    public void updateTruckDriver(Camionero camionero){
        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).set(camionero, SetOptions.merge());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).update("foto", camionero.getFoto());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).update( "nombre", camionero.getNombre());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).update("telefono", camionero.getTelefono());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).update("salario", camionero.getSalario());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).update("poblacion", camionero.getPoblacion());
    }


    public void updatePaquete(Paquete paquete){
        db.collection("user/"+ currentUser.getUid()+"/paquete").document(paquete.getDescripcion()).set(paquete, SetOptions.merge());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(paquete.getDescripcion()).update("descripcion", paquete.getDescripcion());
//        db.collection("user/"+ currentUser.getUid()+"/camionero").document(paquete.getDescripcion()).update( "precio", paquete.getPrecio());
    }


    public void deleteTruckDriver(Camionero camionero){
        db.collection("user/"+ currentUser.getUid()+"/camionero").document(camionero.getNombre()).delete();

    }

    public void deletePaquete(Paquete paquete){
        db.collection("user/"+ currentUser.getUid()+"/paquete").document(paquete.getDescripcion()).delete();

    }






}
