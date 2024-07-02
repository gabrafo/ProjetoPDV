package dev.gabrafo.projetovendas.model;

import dev.gabrafo.projetovendas.model.enums.Status;

import java.sql.Timestamp;

public class Compra {
    private int id;
    private int clienteId;
    private Timestamp dataCompra;
    private double totalCompra;
    private int status;
    private String observacoes;

    public Compra(){}

    public Compra(int id, int clienteId, Timestamp dataCompra, double totalCompra, Status status, String observacoes) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataCompra = dataCompra;
        this.totalCompra = totalCompra;
        setStatus(status);
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Timestamp getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Timestamp dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public void setStatus(Status status) {
        if(status!=null) {
            this.status = status.getCodigo();
        }
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
