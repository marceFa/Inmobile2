package com.example.inmobile.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Contrato implements Serializable {

    private int idContrato;
    private String fechaInicio;
    private String fechaFin;
    private double montoAlquiler;
    private Inquilino inquilino;
    private Inmueble inmuebles;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, double montoAlquiler, Inquilino inquilino, Inmueble inmueble) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoAlquiler = montoAlquiler;
        this.inquilino = inquilino;
        this.inmuebles = inmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmuebles;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmuebles = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return idContrato == contrato.idContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrato);
    }
}
