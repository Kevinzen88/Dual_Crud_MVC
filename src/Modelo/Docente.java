/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kevin
 */
public class Docente {
    int id;
    String nom;
    String ape;
    String tel;
    
    public Docente(){
    }
    
    public Docente(int id, String nom, String ape, String tel) {
        this.id = id;
        this.nom = nom;
        this.ape = ape;
        this.tel = nom;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
