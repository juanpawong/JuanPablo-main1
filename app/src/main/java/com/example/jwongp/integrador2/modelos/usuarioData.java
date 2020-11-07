package com.example.jwongp.integrador2.modelos;

public class usuarioData {
    private String idUsuario;
    private String fechaDef;
    private String userDni;
    private String userApe;
    private String userPass;
    private String userStatus;
    private String userTipo;
    private String userNom;
    private String userMail;

    //JsonProperty("id_usuario")
    public String getIDUsuario() { return idUsuario; }
    //JsonProperty("id_usuario")
    public void setIDUsuario(String value) { this.idUsuario = value; }

    //JsonProperty("fecha_def")
    public String getFechaDef() { return fechaDef; }
    //JsonProperty("fecha_def")
    public void setFechaDef(String value) { this.fechaDef = value; }

    //JsonProperty("user_dni")
    public String getUserDni() { return userDni; }
    //JsonProperty("user_dni")
    public void setUserDni(String value) { this.userDni = value; }

    //JsonProperty("user_ape")
    public String getUserApe() { return userApe; }
    //JsonProperty("user_ape")
    public void setUserApe(String value) { this.userApe = value; }

    //JsonProperty("user_pass")
    public String getUserPass() { return userPass; }
    //JsonProperty("user_pass")
    public void setUserPass(String value) { this.userPass = value; }

    //JsonProperty("user_status")
    public String getUserStatus() { return userStatus; }
    //JsonProperty("user_status")
    public void setUserStatus(String value) { this.userStatus = value; }

    //JsonProperty("user_tipo")
    public String getUserTipo() { return userTipo; }
    //JsonProperty("user_tipo")
    public void setUserTipo(String value) { this.userTipo = value; }

    //JsonProperty("user_nom")
    public String getUserNom() { return userNom; }
    //JsonProperty("user_nom")
    public void setUserNom(String value) { this.userNom = value; }

    //JsonProperty("user_mail")
    public String getUserMail() { return userMail; }
    //JsonProperty("user_mail")
    public void setUserMail(String value) { this.userMail = value; }
}
