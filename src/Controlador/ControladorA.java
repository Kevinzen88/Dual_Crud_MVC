/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Auditoria;
import Modelo.Auditoria_AUD;
import Vista.Menu_Auditoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public final class ControladorA implements ActionListener{
    Auditoria_AUD aud = new Auditoria_AUD();
    Menu_Auditoria menTres = new Menu_Auditoria();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    public ControladorA(Menu_Auditoria au){
        this.menTres = au;
        listar(menTres.TablaA);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menTres.btnNuevoA){
            
        }
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel) tabla.getModel();
        List<Auditoria>lista = aud.listar();
        Object[] object = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getUsu();
            object[2] = lista.get(i).getFec();
            object[3] = lista.get(i).getAcc();
            modelo.addRow(object); 
        }
        
        
    }

}
