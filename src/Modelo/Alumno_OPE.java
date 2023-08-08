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
public class Alumno_OPE  {

    Conexion conectar = new Conexion();
    Connection con;
    
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Alumno> datos = new ArrayList<>();
        String sql = "select * from alumnos";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumno A = new Alumno();
                A.setId(rs.getInt(1));
                A.setMat(rs.getString(2));
                A.setSem(rs.getString(3));
                A.setNom(rs.getString(4));
                datos.add(A);
            }
        } catch (SQLException e) {
        }
        return datos;
    }

    public int agregar(Alumno A) {
        String sql = "insert into alumnos(Matricula,Semestre,Nombre)values(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, A.getMat());
            ps.setString(2, A.getSem());
            ps.setString(3, A.getNom());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 1;
    }

    public int actualizar(Alumno A) {
        int r = 0;
        String sql = "update alumnos set Matricula=?, Semestre=?, Nombre=? where ID_Alumno=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, A.getMat());
            ps.setString(2, A.getSem());
            ps.setString(3, A.getNom());
            ps.setInt(4, A.getId());
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
    
    public boolean buscar(Alumno A) {
        ps = null;
        con = conectar.getConnection();
        
        String sql = "select from alumnos where Nombre=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, A.getNom());
            rs = ps.executeQuery();
            
            if(rs.next()){
                A.setId(Integer.parseInt(rs.getString("ID_Alumno")));
                A.setMat(rs.getString("Matricula"));
                A.setSem(rs.getString("Semestre"));
                A.setNom(rs.getString("Nombre"));
                return true;
            }
            
            return false;
        }catch(SQLException e){
            System.err.print(e);
            return false;
        } finally{
         
        }
        
        
        
    }

    public void eliminar(int id) {
        String sql = "delete from alumnos where ID_Alumno=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public int existe(String alumno){
        ps = null;
        con = conectar.getConnection();
        rs = null;
        
        String sql = "SELECT count(ID_Alumno) From alumnos WHERE  Nombre = ?";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, alumno);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            
            return 1;
            
        }catch(SQLException e){
        }
        return 1;
       
    }
}
