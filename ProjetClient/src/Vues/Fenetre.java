/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package r1Client.Vues;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import r1Client.Controlleurs.ServerConnect;

/**
 *
 * @author CongThuan
 */
public class Fenetre extends JFrame{
    private JPanel container = new JPanel();
    
    private JTextField jtf = new JTextField();
    private JLabel label = new JLabel("ID véhicule");
    
    private JComboBox combo = new JComboBox();
    private JLabel label1 = new JLabel("Type véhicule");
    
    private JComboBox combo1 = new JComboBox();
    private JLabel label2 = new JLabel("Statut");
    
    private JTextField jtf1 = new JTextField();
    private JLabel label3 = new JLabel("Numéro parking");
    
    private JButton bouton = new JButton("Ajouter");
    private JButton bouton2 = new JButton("Fermer");
    private JButton bouton3 = new JButton("Log in");
    private JButton bouton4 = new JButton("EntrerDeStock");
    private JButton bouton5 = new JButton("SortieDeStock");
    public Fenetre(){
        this.setTitle("Ajouter un véhicule");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        combo.setPreferredSize(new Dimension(100, 20));
               
        Font police = new Font("Arial", Font.BOLD, 14);
        jtf.setFont(police);
        jtf.setPreferredSize(new Dimension(150, 20));
        jtf.setForeground(Color.BLUE);

        //Ajouter boutons
        bouton.addActionListener(new BoutonListener(this));
        bouton2.addActionListener(new Bouton2Listener(this));
        bouton3.addActionListener(new Bouton3Listener());
        bouton4.addActionListener(new Bouton4Listener());
        bouton5.addActionListener(new Bouton5Listener());
        //bouton2.setEnabled(false);
        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        south.add(bouton3);
        container.add(south,BorderLayout.SOUTH);
        JPanel center = new JPanel();
        center.add(bouton4);
        center.add(bouton5);
        container.add(center,BorderLayout.CENTER);
        //Ajouter item dans la liste véhicule
        combo.addItem("Vélo");
        combo.addItem("Voiture");
             
        //Ajouter du listener
        combo.addItemListener(new ItemState());
        combo.addActionListener(new ItemAction());
        combo.setPreferredSize(new Dimension(150,20));
        combo.setForeground(Color.red);
        
        //Ajouter item dans la liste véhicule
        combo1.addItem("En service");
        combo1.addItem("En attendant de réparer");
        combo1.addItem("En cours de réparer");
        combo1.addItem("Disponible");
             
        //Ajouter du listener
        combo1.addItemListener(new ItemState());
        combo1.addActionListener(new Item1Action());
        combo1.setPreferredSize(new Dimension(150,20));
        combo1.setForeground(Color.blue);
        
        JPanel top = new JPanel();
        GridLayout gl = new GridLayout(4,2);
        gl.setHgap(5);
        gl.setVgap(5);
        top.setLayout(gl);
        //Ajouter ID VEHICULE
        top.add(label);
        top.add(jtf);
        //Ajouter TYPE VEHICULE
        top.add(label1);
        top.add(combo);
        //Ajouter STATUT
        top.add(label2);
        top.add(combo1);
        //Ajouter NUMERO PARKING
        top.add(label3);
        top.add(jtf1);
        
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }
    
    //Classe interne implémentant l'interface ItemListener
    class ItemState implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            System.out.println("événement déclenché sur : " + e.getItem());
        }               
    }
    
    //ActionListener de TYPE VEHICULE
    class ItemAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("ActionListener: action sur " + combo.getSelectedItem());
        }
    }
    
    //ActionListener de STATUT
    class Item1Action implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("ActionListener: action sur " + combo1.getSelectedItem());
        }
    }
    
    //Classe écoutant bouton AJOUTER
    class BoutonListener implements ActionListener{
    	JFrame frame=null;
    	//son constructeur
    	public BoutonListener  (JFrame f){
    	this.frame=f;
    	}
    	public void actionPerformed(ActionEvent arg0){
            //code
        	String IDVehicule = jtf.getText();
        	String NumParking = jtf1.getText();
        	String TypeVehicule = combo.getSelectedItem().toString();
        	String statut =combo1.getSelectedItem().toString();
        	System.out.println(IDVehicule+""+TypeVehicule+""+statut+""+""+NumParking);
        	ArrayList<String> data = new ArrayList();
        	data.add(IDVehicule);
        	data.add(TypeVehicule);
        	data.add(statut);
        	data.add(NumParking);
        	String identifier = "AjoutVehicule";
        	System.out.println("Authentification demandée par le client");
        	new ServerConnect(data, identifier, frame);
        }
    }
    
    //Classe écoutant bouton2 Fermer
    class Bouton2Listener implements ActionListener{
    	JFrame frame=null;
    	//son constructeur
    	public Bouton2Listener  (JFrame f){
    	this.frame=f;
    	}
    	public void actionPerformed(ActionEvent e){
            //code
    		frame.dispose();
        }
    }
 
    //Classe écoutant bouton2 LOG IN
    class Bouton3Listener implements ActionListener{
    		public void actionPerformed(ActionEvent arg0){
        	
    			Authentication auth = new Authentication();
        }
    }
  //Classe écoutant bouton4 EntrerEnStock
    class Bouton4Listener implements ActionListener{
    		public void actionPerformed(ActionEvent arg0){
        	
    			  Fenetre1 EntrerEnStock = new Fenetre1();
        }
    }
  //Classe écoutant bouton5 SortieDeStock
    class Bouton5Listener implements ActionListener{
    		public void actionPerformed(ActionEvent arg0){
        	
    			Fenetre2 SortieDeStock = new Fenetre2();
        }
    }
}
