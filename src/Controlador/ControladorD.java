/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.Docente;
import Modelo.Docente_DOC;
import Vista.Menu_Docentes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public final class ControladorD implements ActionListener{
    Icon error;
    
    Docente_DOC doc = new Docente_DOC();
    Docente ma = new Docente();
    Menu_Docentes menDos = new Menu_Docentes();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorD(Menu_Docentes M){
        this.menDos = M;
        this.menDos.btnLimpiarD.addActionListener(this);
        this.menDos.btnGuardarD.addActionListener(this);
        this.menDos.btnEditarD.addActionListener(this);
        this.menDos.btnActualizarD.addActionListener(this);
        this.menDos.btnEliminarD.addActionListener(this);
        listar(menDos.TablaD);
        
        error = new ImageIcon("src/Botones/error.png");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menDos.btnLimpiarD){
            limpiar();
            listar(menDos.TablaD);
        }
        
        if(e.getSource() == menDos.btnGuardarD){
            agregar();
            limpiar();
            listar(menDos.TablaD);
        }
        if(e.getSource()== menDos.btnEliminarD){
            eliminar();
            limpiar();
            listar(menDos.TablaD);
        }
        if(e.getSource()== menDos.btnActualizarD){
            int fila = menDos.TablaD.getSelectedRow();
            if(fila == -1){
            JOptionPane.showMessageDialog(menDos,"Debe presionar el botón editar","Error",JOptionPane.WARNING_MESSAGE,error);
          
            }else{
                actualizar();
                limpiar();
                listar(menDos.TablaD);
            }
            
        }
        
        if(e.getSource() == menDos.btnEditarD){
            int fila = menDos.TablaD.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(menDos,"Debe seleccionar una fila","Error",JOptionPane.WARNING_MESSAGE,error);
            }else{
                int id = Integer.parseInt((String)menDos.TablaD.getValueAt(fila,0).toString());
                String nombre = (String) menDos.TablaD.getValueAt(fila,1);
                String apellido = (String) menDos.TablaD.getValueAt(fila,2);
                String telefono = (String) menDos.TablaD.getValueAt(fila,3);
            
                menDos.txtID.setText(""+id );
                menDos.txtNom.setText(nombre);
                menDos.txtApe.setText(apellido);
                menDos.txtTel.setText(telefono);
            }   
            
        }
        
        if(e.getSource() == menDos.btnLimpiarD){
            
        }
    
    }
    
    
    void limpiar(){
        for(int i = 0; i < menDos.TablaD.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }
    
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel) tabla.getModel();
        List<Docente>lista = doc.listar();
        Object[] object = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNom();
            object[2] = lista.get(i).getApe();
            object[3] = lista.get(i).getTel();
            modelo.addRow(object); 
        }
        
        
    }
    
    public void agregar(){
        String nombre = menDos.txtNom.getText();
        String apellido = menDos.txtApe.getText();
        String telefono = menDos.txtTel.getText();
        ma.setNom(nombre);
        ma.setApe(apellido);
        ma.setTel(telefono);
        int r = doc.agregar(ma);
        
        if (r == -1){
           // JOptionPane.showMessageDialog(menDos,"Docente agregado con exíto");
        }else{
            JOptionPane.showMessageDialog(menDos,"Falta llenar datos","Error",JOptionPane.WARNING_MESSAGE,error);
        }
    }
    
     public void eliminar() {
        int fila = menDos.TablaD.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(menDos, "Debe seleccionar algo","Filas",JOptionPane.WARNING_MESSAGE,error);
        } else {
            int id = Integer.parseInt((String) menDos.TablaD.getValueAt(fila, 0).toString());

            doc.eliminar(id);
            JOptionPane.showMessageDialog(menDos, "Docente " + id + " eliminado");

        }
    }
    
    public void actualizar(){
        int id = Integer.parseInt(menDos.txtID.getText());
        String nombre = menDos.txtNom.getText();
        String apellido = menDos.txtApe.getText();
        String telefono = menDos.txtTel.getText();
        ma.setId(id);
        ma.setNom(nombre);
        ma.setApe(apellido);
        ma.setTel(telefono);
        int r = doc.actualizar(ma);
        if(r == 1){
            JOptionPane.showMessageDialog(menDos,"Docente "+nombre+" actualizado");
        }else{
        JOptionPane.showMessageDialog(menDos,"No hay valores para cambiar","Actualización errónea",JOptionPane.WARNING_MESSAGE,error);
        }
    }
     
     
   
}
