/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Client.Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Carbon5
 */
public class PanDiagnostic extends JPanel implements ActionListener{
    private JLabel titre = new JLabel("Diagnostic de vehicule");
    private JLabel label1 = new JLabel("ID vehicule");
    private JLabel label2 = new JLabel("Parking");
    private JLabel label3 = new JLabel("Diagnostic de la panne");
    
    
    /* 
    Class constructor
    */
    public PanDiagnostic(){
        
    }
    
    /*
    Method generate components actions
    */
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
