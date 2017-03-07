/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Client.Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author CongThuan
 */
public class IHM extends JFrame {
    private JPanel pan = new JPanel();
    private JMenuBar menubar = new JMenuBar();
    
    private JPanel pan1 = new JPanel();
    private JPanel pan2 = new JPanel();
    
    /*
    private JPanel panDiagnostic = new JPanel();
    private JPanel panParking = new JPanel();
    private JPanel panOrdreReparation = new JPanel();
    private JPanel panDetailOperation = new JPanel();
    private JPanel panStock = new JPanel();
    private JPanel panEntreeStock = new JPanel();
    private JPanel panSortieStock = new JPanel();
    private JPanel panPieceDetache = new JPanel();
    private JPanel panAjouterPiece = new JPanel();
    private JPanel panModifierPiece = new JPanel();
    private JPanel panSupprimerPiece = new JPanel();
    private JPanel panVehicule = new JPanel();
    private JPanel panAjouterVehicule = new JPanel();
    private JPanel panModifierVehicule = new JPanel();
    private JPanel panSupprimerVehicule = new JPanel();
    private JPanel panStatistique = new JPanel();
    */
    
    private JButton bouton1 = new JButton("Diagnostic") ;
    private JButton bouton2 = new JButton("Parking");
    private JButton bouton3 = new JButton("Ordre de reparation");
    private JButton bouton4 = new JButton("Detail operation");
    private JButton bouton5 = new JButton("Stock");
    private JButton bouton6 = new JButton("Piece detache");
    private JButton bouton7 = new JButton("Vehicule");
    private JButton bouton8 = new JButton("Statistique");    
    
    private JList liste1;
    private String[] l1 = {"Entree","Sortie"};
    
    private JList liste2;
    private String[] l2 = {"Ajouter", "Modifier", "Supprimer"};
    
    private JList liste3;
    private String[] l3 = {"Ajouter", "Modifier", "Supprimer"};
            
    public IHM(){
        this.setTitle("Main menu");
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(pan);
        this.setJMenuBar(menubar);
        
        pan1.setBackground(Color.red);
        pan1.setLayout(new GridLayout(11,1));
        
        //Add component to panel
        pan1.add(bouton1);
        pan1.add(bouton2);
        pan1.add(bouton3);
        pan1.add(bouton4);
        pan1.add(bouton5);
        liste1 = new JList(l1);
        liste1.setBackground(Color.orange);
        pan1.add(liste1);
        pan1.add(bouton6);
        liste2 = new JList(l2);
        liste2.setBackground(Color.orange);
        pan1.add(liste2);
        pan1.add(bouton7);
        liste3 = new JList(l3);
        liste3.setBackground(Color.orange);
        pan1.add(liste3);
        pan1.add(bouton8);
        
        //setBackground buttons
        bouton1.setBackground(Color.red);
        bouton2.setBackground(Color.red);
        bouton3.setBackground(Color.red);
        bouton4.setBackground(Color.red);
        bouton5.setBackground(Color.red);
        bouton6.setBackground(Color.red);
        bouton7.setBackground(Color.red);
        bouton8.setBackground(Color.red);
        
        //Add listener to buttons        
        bouton1.addActionListener(new Ecouteur());
        bouton2.addActionListener(new Ecouteur());
        bouton3.addActionListener(new Ecouteur());
        bouton4.addActionListener(new Ecouteur());
        bouton5.addActionListener(new Ecouteur());
        bouton6.addActionListener(new Ecouteur());
        bouton7.addActionListener(new Ecouteur());    
        bouton8.addActionListener(new Ecouteur());
        
        //Add listener to list
        liste1.addListSelectionListener(new Liste1Listener());
        liste2.addListSelectionListener(new Liste2Listener());
        liste3.addListSelectionListener(new Liste3Listener());
        
        pan2.setBackground(Color.pink);
        
        pan.setLayout(new BorderLayout());
        pan.add(pan1, BorderLayout.WEST);
        pan.add(pan2);
    }
    
    /*
    Class Ecouteur listens the buttons 
    */
    class Ecouteur implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == bouton1){
                //code
            }
            if(e.getSource() == bouton2){
                //code
            }
            if(e.getSource() == bouton3){
                //code
            }
            if(e.getSource() == bouton4){
                //code
            }
            if(e.getSource() == bouton5){
                //code
            }
            if(e.getSource() == bouton6){
                //code
            }
            if(e.getSource() == bouton7){
                //code
            }
            if(e.getSource() == bouton8){
                //code
            }
        }  
    }
    
    /*
    Class listens liste 1
    */
    class Liste1Listener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            //code
            if (!e.getValueIsAdjusting()) {
            Object[] valeur = liste1.getSelectedValues();
            for (int i = 0; i < valeur.length; i++) {
                JLabel label = new JLabel((String) valeur[i]);
                pan2.add(label);
                setVisible(true);
            }
            }
        }
    }
    
    /*
    Class listens liste 2
    */
    class Liste2Listener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            //code
            if (!e.getValueIsAdjusting()) {
            Object[] valeur = liste2.getSelectedValues();
            for (int i = 0; i < valeur.length; i++) {
                JLabel label = new JLabel((String) valeur[i]);
                pan2.add(label);
                setVisible(true);
            }
            }
        }  
    }
    
    /*
    Class listens liste 3
    */
    class Liste3Listener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            //code
            if (!e.getValueIsAdjusting()) {
            Object[] valeur = liste3.getSelectedValues();
            for (int i = 0; i < valeur.length; i++) {
                JLabel label = new JLabel((String) valeur[i]);
                pan2.add(label);
                setVisible(true);
            }
            }
        }
    }
    
    public static void main(String[] args) {
        IHM a = new IHM();
    }
}
