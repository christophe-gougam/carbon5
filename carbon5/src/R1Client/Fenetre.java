/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package R1Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author CongThuan
 */
public class Fenetre extends JFrame implements Runnable{
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
    private JButton bouton2 = new JButton("Annuler");
    
    public void run(){
    	
    }
    
    public Fenetre(){
        this.setTitle("Ajouter un véhicule");
        this.setSize(300, 160);
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
        bouton.addActionListener(new BoutonListener());
        bouton2.addActionListener(new Bouton2Listener());
        //bouton2.setEnabled(false);
        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        container.add(south,BorderLayout.SOUTH);
        
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
        public void actionPerformed(ActionEvent arg0){
            //code
        }
    }
    
    //Classe écoutant bouton2 ANNULER
    class Bouton2Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //code
        }
    }
}
