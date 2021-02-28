package com.jesus.truckingtransport.model.pojo;

public class Camionero {

    private String foto;
    private String nombre;
    private String telefono;
    private float salario;
    private String poblacion;

    public Camionero() {
    }

    public Camionero(String foto, String nombre, String telefono, float salario, String poblacion) {
        this.foto = foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario = salario;
        this.poblacion = poblacion;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }


    @Override
    public String toString() {
        return "Camionero{" +
                ", foto='" + foto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", salario=" + salario +
                ", poblacion='" + poblacion + '\'' +
                '}';
    }
}
