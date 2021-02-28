package com.jesus.truckingtransport.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.jesus.truckingtransport.model.Repository;
import com.jesus.truckingtransport.model.pojo.Camionero;
import com.jesus.truckingtransport.model.pojo.Paquete;

import java.util.List;


public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private boolean recycler;
    private Camionero camionero;
    private Paquete paquete;
    private MutableLiveData<String> nomCamionero = new MutableLiveData<>();

    public ViewModel(@NonNull Application application) {
        super(application);
        camionero = new Camionero();
        paquete = new Paquete();
        repository = new Repository(application);
    }

    public boolean isRecycler() {
        return recycler;
    }

    public void setRecycler(boolean recycler) {
        this.recycler = recycler;
    }

    public Camionero getCamionero() {
        return camionero;
    }

    public void setCamionero(Camionero camionero) {
        this.camionero = camionero;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public MutableLiveData<Boolean> isloggedin(String correo, String clave) {
        Log.v("XXXXX", repository.isloggedin(correo, clave)+ "");
        return repository.isloggedin(correo, clave);
    }

    public void insertTruckDriver(Camionero camionero) {
        repository.insertTruckDriver(camionero);
    }

    public void insertaPaquete(Paquete paquete) {
        repository.insertaPaquete(paquete);
    }

    public MutableLiveData<List<Camionero>> getTruckDrivers() {
        return repository.getTruckDrivers();
    }

    public MutableLiveData<List<Paquete>> getListaPaquetes() {
        return repository.getListaPaquetes();
    }

    public MutableLiveData<String> getNomCamionero() {
        return nomCamionero;
    }

    public void setNomCamionero(String nomCamionero) {
        this.nomCamionero.setValue(nomCamionero);
    }

    public void updateTruckDriver(Camionero camionero) {
        repository.updateTruckDriver(camionero);
    }

    public void updatePaquete(Paquete paquete) {
        repository.updatePaquete(paquete);
    }

    public void deleteTruckDriver(Camionero camionero) {
        repository.deleteTruckDriver(camionero);
    }

    public void deletePaquete(Paquete paquete) {
        repository.deletePaquete(paquete);
    }
}
