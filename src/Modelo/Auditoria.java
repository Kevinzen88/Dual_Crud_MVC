/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kevin
 */
public class Auditoria {
    int id;
    String usu;
    String fec;
    String acc;
    
    public Auditoria(){
    }
    
    public Auditoria(int id, String usu, String fec, String acc) {
        this.id = id;
        this.usu = usu;
        this.fec = fec;
        this.acc = acc;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }
    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }
    
    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
