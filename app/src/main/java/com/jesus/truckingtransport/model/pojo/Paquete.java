package com.jesus.truckingtransport.model.pojo;

public class Paquete {

    private String nomCamionero;
    private String descripcion;
    private float precio;

    public Paquete() {
    }

    public Paquete(String nomCamionero, String descripcion, float precio) {
        this.nomCamionero = nomCamionero;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getNomCamionero() {
        return nomCamionero;
    }

    public void setNomCamionero(String nomCamionero) {
        this.nomCamionero = nomCamionero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                ", idcamionero=" + nomCamionero +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
