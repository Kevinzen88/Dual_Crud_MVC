/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author kevin
 */
public class Docente_DOC {
    Conexion conectar = new Conexion();
    Connection con;
    
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<Docente> datos = new ArrayList<>();
        String sql = "select * from docentes";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Docente D = new Docente();
                D.setId(rs.getInt(1));
                D.setNom(rs.getString(2));
                D.setApe(rs.getString(3));
                D.setTel(rs.getString(4));
                datos.add(D);
            }
        } catch (SQLException e) {
        }
        return datos;
    }
    
    public int agregar(Docente D) {
        String sql = "insert into docentes(Nombres,Apellidos,Telefono)values(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, D.getNom());
            ps.setString(2, D.getApe());
            ps.setString(3, D.getTel());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 1;
    }
    
    public int actualizar(Docente D) {
        int r = 0;
        String sql = "update docentes set Nombres=?, Apellidos=?, Telefono=? where ID_Docente=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, D.getNom());
            ps.setString(2, D.getApe());
            ps.setString(3, D.getTel());
            ps.setInt(4, D.getId());
            r = ps.executeUpdate();

            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
        }
        return r;
    }
    
    public void eliminar(int id) {
        String sql = "delete from docentes where ID_Docente=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
