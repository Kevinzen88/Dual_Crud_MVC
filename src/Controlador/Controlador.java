/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Alumno_OPE;
import Vista.Menu_Alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;



/**
 *
 * @author kevin
 */
public final class Controlador implements ActionListener{
    
    Alumno_OPE ope = new Alumno_OPE();
    Alumno alu = new Alumno();
    Menu_Alumnos menUno = new Menu_Alumnos();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    public Controlador(Menu_Alumnos v){
         this.menUno = v;
         this.menUno.btnLimpiar.addActionListener(this);
         this.menUno.btnGuardar.addActionListener(this);
         this.menUno.btnEditar.addActionListener(this);
         this.menUno.btnActualizar.addActionListener(this);
         this.menUno.btnEliminar.addActionListener(this);
         this.menUno.btnBuscar.addActionListener(this);
         listar(menUno.Tabla);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == menUno.btnLimpiar){
            limpiar();
            listar(menUno.Tabla);
        }
        if(e.getSource() == menUno.btnGuardar){
            agregar();
            limpiar();
            listar(menUno.Tabla);
        }
        
        if(e.getSource() == menUno.btnBuscar){
           alu.setNom(menUno.txtBus.getText());
           
           if(ope.buscar(alu)){
               menUno.txtID.setText(String.valueOf(alu.getId()));
               menUno.txtMat.setText(alu.getMat());
               menUno.txtSem.setText(alu.getSem());
               menUno.txtNom.setText(alu.getNom());
            }else{
               JOptionPane.showMessageDialog(menUno,"No existe");
           
           }
        }
        if(e.getSource() == menUno.btnEditar){
            int fila = menUno.Tabla.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(menUno,"Debe seleccionar una fila");
            }else{
                int id = Integer.parseInt((String)menUno.Tabla.getValueAt(fila,0).toString());
                String matricula = (String) menUno.Tabla.getValueAt(fila,1);
                String semestre = (String) menUno.Tabla.getValueAt(fila,2);
                String nombre = (String) menUno.Tabla.getValueAt(fila,3);
            
                menUno.txtID.setText(""+id );
                menUno.txtMat.setText(matricula);
                menUno.txtSem.setText(semestre);
                menUno.txtNom.setText(nombre);
            }   
            
        }
        if(e.getSource()== menUno.btnActualizar){
           int fila = menUno.Tabla.getSelectedRow();
            if(fila == -1){
            JOptionPane.showMessageDialog(menUno,"Debe presionar el botón editar","Error",JOptionPane.WARNING_MESSAGE);
          
            }else{
                actualizar();
                limpiar();
                listar(menUno.Tabla);
            }
        }
        if(e.getSource()== menUno.btnEliminar){
            eliminar();
            limpiar();
            listar(menUno.Tabla);
        }
    }
    
    public void eliminar(){
    int fila = menUno.Tabla.getSelectedRow();   
            if(fila == -1){
             JOptionPane.showMessageDialog(menUno,"Debe seleccionar algo");
            }else{
                int id = Integer.parseInt((String)menUno.Tabla.getValueAt(fila, 0).toString()); 
                
                
                ope.eliminar(id);
                JOptionPane.showMessageDialog(menUno,"Alumno "+id+" eliminado");
            
            }
    }
    
    void limpiar(){
        for(int i = 0; i < menUno.Tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }
    
    public void actualizar(){
        int id = Integer.parseInt(menUno.txtID.getText());
        String matricula = menUno.txtMat.getText();
        String semestre = menUno.txtSem.getText();
        String nombre = menUno.txtNom.getText();
        alu.setId(id);
        alu.setMat(matricula);
        alu.setSem(semestre);
        alu.setNom(nombre);
        int r = ope.actualizar(alu);
        if(r == 1){
            JOptionPane.showMessageDialog(menUno,"Alumno "+nombre+" actualizado");
        }else{
        JOptionPane.showMessageDialog(menUno,"No se actualizó el registro");
        }
    }
    
    public void agregar(){
        String matricula = menUno.txtMat.getText();
        String semestre = menUno.txtSem.getText();
        String nombre = menUno.txtNom.getText();
        alu.setMat(matricula);
        alu.setSem(semestre);
        alu.setNom(nombre);
        int r = ope.agregar(alu);
        
        if (r == 1){
            JOptionPane.showMessageDialog(menUno,"Alumno agregado con exíto");
        }else{
            JOptionPane.showMessageDialog(menUno,"No se agregó el registro");
        }
    }
   
    public void listar(JTable tabla){
        modelo = (DefaultTableModel) tabla.getModel();
        List<Alumno>lista = ope.listar();
        Object[] object = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getMat();
            object[2] = lista.get(i).getSem();
            object[3] = lista.get(i).getNom();
            modelo.addRow(object); 
        }
        
    }

     
    
}
