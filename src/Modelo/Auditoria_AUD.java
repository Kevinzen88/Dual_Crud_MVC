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
public class Auditoria_AUD {
    Conexion conectar = new Conexion();
    Connection con;
    
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<Auditoria> datos = new ArrayList<>();
        String sql = "select * from auditoria";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Auditoria A = new Auditoria();
                A.setId(rs.getInt(1));
                A.setUsu(rs.getString(2));
                A.setFec(rs.getString(3));
                A.setAcc(rs.getString(4));
                datos.add(A);
            }
        } catch (SQLException e) {
        }
        return datos;
    }
}
