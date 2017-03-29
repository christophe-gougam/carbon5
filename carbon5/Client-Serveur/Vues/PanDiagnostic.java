/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carbon5
 */
public class PanDiagnostic extends JPanel implements ActionListener{
    private JLabel titre = new JLabel("Diagnostic de vehicule");
    private JLabel label1 = new JLabel("ID vehicule");
    private JLabel label2 = new JLabel("Parking");
    private JLabel label3 = new JLabel("Diagnostic de la panne");
    private JLabel label4 = new JLabel("Degre d'uregence");
    private JLabel label5 = new JLabel("Piece utilise");
    private JLabel label6 = new JLabel("Temps prevu");
    
    private JTextField jt1 = new JTextField();
    private JTextField jt2 = new JTextField();
    private JTextField jt3 = new JTextField();
    
    private JComboBox combo1 = new JComboBox();
    private JComboBox combo2 = new JComboBox();
    private JComboBox combo3 = new JComboBox();
    
    private JButton button1 = new JButton("Valider");
    private JButton button2 = new JButton("Annuler");
    
    /* 
    Class constructor
    */
    public PanDiagnostic(){
        this.add(titre);
        this.add(label1);
        this.add(jt1);
        this.add(label2);
        this.add(combo1);
        this.add(label3);
        this.add(combo2);
        this.add(label4);
        this.add(combo3);
        this.add(label5);
        this.add(jt2);
        this.add(label6);
        this.add(jt3);
        this.add(button1);
        this.add(button2);
        
        //Set dimension 
        combo1.setPreferredSize(new Dimension(100,20));
        combo2.setPreferredSize(new Dimension(100,20));
        combo3.setPreferredSize(new Dimension(100,20));
        jt1.setPreferredSize(new Dimension(100,20));
        jt2.setPreferredSize(new Dimension(100,20));
        jt3.setPreferredSize(new Dimension(100,20));
        
        //Add item to combo box "PARKING"
        combo1.addItem("1");
        combo1.addItem("2");
        combo1.addItem("3");
        
        //Add item to combo box "DIAGNOSTIC PANNE"
        combo2.addItem("Panne moteur");
        combo2.addItem("Panne lampe phare");
        combo2.addItem("Retroviseur casse");
        
        //Add item to combo box "DEGRE URGENCE"
        combo3.addItem("1");
        combo3.addItem("2");
        combo3.addItem("3");
    }
    
    /*
    Method generate components actions
    */
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
