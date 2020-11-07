package com.example.jwongp.integrador2.modelos;

import com.example.jwongp.integrador2.utils.Config;

public class Reservas {
    private String idvehiculos;
    private String marca;
    private String modelo;
    private String img;
    private String precAlquile;
    private String status;
    private String monto;
    private String fechaInicio;
    private String fechaFin;
    private String diasAlquiler;
    private String cliente;

    //@JsonProperty("idvehiculos")
    public String getIdvehiculos() { return idvehiculos; }
    //@JsonProperty("idvehiculos")
    public void setIdvehiculos(String value) { this.idvehiculos = value; }

    //@JsonProperty("marca")
    public String getMarca() { return marca; }
    //@JsonProperty("marca")
    public void setMarca(String value) { this.marca = value; }

    //@JsonProperty("modelo")
    public String getModelo() { return modelo; }
    //@JsonProperty("modelo")
    public void setModelo(String value) { this.modelo = value; }

    //@JsonProperty("img")
    public String getImg() { return img; }
    public String getImgURL() {  return Config.URL_IMG + img; }
    //@JsonProperty("img")
    public void setImg(String value) { this.img = value; }

    //@JsonProperty("prec_alquile")
    public String getPrecAlquile() { return precAlquile; }
    //@JsonProperty("prec_alquile")
    public void setPrecAlquile(String value) { this.precAlquile = value; }

    //@JsonProperty("status")
    public String getStatus() { return status; }
    //@JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

    //@JsonProperty("monto")
    public String getMonto() { return monto; }
    //@JsonProperty("monto")
    public void setMonto(String value) { this.monto = value; }

    //@JsonProperty("fecha_inicio")
    public String getFechaInicio() { return fechaInicio; }
    //@JsonProperty("fecha_inicio")
    public void setFechaInicio(String value) { this.fechaInicio = value; }

    //@JsonProperty("fecha_fin")
    public String getFechaFin() { return fechaFin; }
    //@JsonProperty("fecha_fin")
    public void setFechaFin(String value) { this.fechaFin = value; }

    //@JsonProperty("dias_alquiler")
    public String getDiasAlquiler() { return diasAlquiler; }
    //@JsonProperty("dias_alquiler")
    public void setDiasAlquiler(String value) { this.diasAlquiler = value; }

    public String getCliente() { return cliente; }
    //@JsonProperty("dias_alquiler")
    public void setCliente(String value) { this.cliente = value; }

}
