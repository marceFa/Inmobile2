package com.example.inmobile.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private int numero;
    private Contrato contratos;
    private double importe;
    private String fechaDePago;

    public Pago() {}

    public Pago(int idPago, int numero, Contrato contratos, double importe, String fechaDePago) {
        this.idPago = idPago;
        this.numero = numero;
        this.contratos = contratos;
        this.importe = importe;
        this.fechaDePago = fechaDePago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Contrato getContrato() {
        return contratos;
    }

    public void setContrato(Contrato contratos) {
        this.contratos = contratos;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaDePago = fechaDePago;
    }
}
