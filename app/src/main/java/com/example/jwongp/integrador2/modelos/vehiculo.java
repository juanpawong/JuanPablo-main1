package com.example.jwongp.integrador2.modelos;

import com.example.jwongp.integrador2.utils.Config;

public class vehiculo {
    private String idvehiculos;
    private String marca;
    private String modelo;
    private String img;
    private String descripcion;
    private String precAlquile;
    private String status;

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

    //@JsonProperty("descripcion")
    public String getDescripcion() { return descripcion; }
    //@JsonProperty("descripcion")
    public void setDescripcion(String value) { this.descripcion = value; }

    //@JsonProperty("prec_alquile")
    public String getPrecAlquile() { return precAlquile; }
    //@JsonProperty("prec_alquile")
    public void setPrecAlquile(String value) { this.precAlquile = value; }

    //@JsonProperty("status")
    public String getStatus() { return status; }
    //@JsonProperty("status")
    public void setStatus(String value) { this.status = value; }
}
