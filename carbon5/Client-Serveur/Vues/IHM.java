/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import Client.Controlleurs.Connection;
import Client.Controlleurs.ServerConnect;
import Modele.Parking;
import Modele.Part;
import Modele.TypeCar;
import Serveur.Controlleurs.Serveur;

/**
 *
 * @author Carbon5
 */
public class IHM extends JFrame {
	int compteur1=0;
	int compteur2=0;
	int compteur3=0;
	int compteur4=0;
	int compteur5=0;
	int compteur6=0;
	int compteur7=0;
	private JPanel pan = new JPanel();
    final static Logger logger = Logger.getLogger(Serveur.class);
    private JPanel pan1 = new JPanel();
    private JPanel pan2 = new JPanel();
    
//    private JButton bouton1 = new JButton("Diagnostic") ;
    private JButton bouton2 = new JButton("Parking");
    private JButton bouton3 = new JButton("Ordre de reparation");
    private JButton bouton4 = new JButton("Detail operation");
    private JButton bouton5 = new JButton("Stock");
    private JButton bouton6 = new JButton("Piece detache");
    private JButton bouton7 = new JButton("Vehicule");
    private JButton bouton8 = new JButton("Statistique");
    private JButton bouton9 = new JButton("Performances");
    private JButton bouton10 = new JButton("Attribuer Bonus");
    
    private JList<?> liste1;
    private String[] l1 = {"Entree","Sortie"};
    
    private JList<?> liste2;
    private String[] l2 = {"Ajouter", "Modifier"};
    
    private JList<?> liste3;
    private String[] l3 = {"Ajouter", "Modifier"};
    
    CardLayout cl = new CardLayout();
    String[] listContent = {"C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13","C14","C15","C16"};
    int indice = 0;
    
    public IHM() throws SQLException{
        this.setTitle("Main menu");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(pan);
        
        pan1.setBackground(Color.GRAY);
        pan1.setLayout(new GridLayout(10,1));
        
        //Add component to panel
//        pan1.add(bouton1);
        pan1.add(bouton2);
        pan1.add(bouton3);
        pan1.add(bouton4);
        pan1.add(bouton5);
        
        liste1 = new JList(l1);
        liste1.setBackground(Color.orange);
        liste1.setVisible(false);
        
        pan1.add(liste1);
        pan1.add(bouton6);
        
        liste2 = new JList(l2);
        liste2.setBackground(Color.orange);
        liste2.setVisible(false);
        
        pan1.add(liste2);
        pan1.add(bouton7);
        
        liste3 = new JList(l3);
        liste3.setBackground(Color.orange);
        liste3.setVisible(false);
        
        pan1.add(liste3);
        pan1.add(bouton8);
        pan1.add(bouton9);
        pan1.add(bouton10);
        
        //setBackground buttons
//        bouton1.setBackground(Color.red);
        bouton2.setBackground(Color.red);
        bouton3.setBackground(Color.red);
        bouton4.setBackground(Color.red);
        bouton5.setBackground(Color.red);
        bouton6.setBackground(Color.red);
        bouton7.setBackground(Color.red);
        bouton8.setBackground(Color.red);
        bouton9.setBackground(Color.red);
        bouton10.setBackground(Color.red);
        
        //Add listener to buttons        
//        bouton1.addActionListener(new Ecouteur());
        bouton2.addActionListener(new Ecouteur());
        bouton3.addActionListener(new Ecouteur());
        bouton4.addActionListener(new Ecouteur());
        bouton5.addActionListener(new Ecouteur());
        bouton6.addActionListener(new Ecouteur());
        bouton7.addActionListener(new Ecouteur());    
        bouton8.addActionListener(new Ecouteur());
        bouton9.addActionListener(new Ecouteur());
        bouton10.addActionListener(new Ecouteur());


        //Add listener to list
        liste1.addListSelectionListener(new Liste1Listener());
        liste2.addListSelectionListener(new Liste2Listener());
        liste3.addListSelectionListener(new Liste3Listener());
        
        pan2.setLayout(cl);
        
        pan.setLayout(new BorderLayout());
        pan.add(pan1, BorderLayout.WEST);
        pan.add(pan2);
        
        pan2.add(new PanDiagnostic(), listContent[0]);
        pan2.add(new PanParking(), listContent[1]);
        
        //pan2.add(new PanelDetailOperation(), listContent[3]);
        pan2.add(new PanStock(), listContent[4]);
        
        
        pan2.add(new PanPiece(), listContent[7]);
        

        pan2.add(new PanVehicule(), listContent[10]);
        pan2.add(new PanAjoutVehicule(), listContent[11]);
        pan2.add(new PanModifVehicule(), listContent[12]);
        pan2.add(Connection.panStat, listContent[13]);
        pan2.add(new PanPerformances(), listContent[14]);
        pan2.add(new PanChoixBonus(), listContent[15]);

    }
    
    /*
    Class Ecouteur listens the buttons 
    */
    class Ecouteur implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == bouton2){
            	if(compteur2<1){
                	Parking.emptyCollection();
                    ArrayList<String> data = new ArrayList<String>();
                    String identifier = "SelectAllParkings";
                    logger.info("Recuperation des parking");
                    new ServerConnect(data, identifier, pan2);
                    compteur2++;
                }
                cl.show(pan2, listContent[1]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton3){
  
            	pan2.add(new PanOrdreReparation(), listContent[2]);
            	
                cl.show(pan2, listContent[2]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton4){
                //code
            	ArrayList<String> data = new ArrayList<String>();
            	String identifier = "SelectFirstFromWaitList";
            	//String identifier2 = "SelectAllParts";
            	logger.info("Recuperation des pieces");
            	new ServerConnect(data, identifier, pan2);
            	//new ServerConnect(data, identifier2, pan2);
            	pan2.add(new PanelDetailOperation(), listContent[3]);
                cl.show(pan2, listContent[3]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton5){
                //implement select of all nameParts to fill list
            	
            	if(compteur5<1){
            		Part.emptyCollection();
                	ArrayList<String> data = new ArrayList<String>();
                	String identifier = "SelectAllParts";
                	logger.info("R�cup�ration des pi�ces");
                	new ServerConnect(data, identifier, pan2);
                    compteur5++;
                }
            	//}
            	pan2.add(new PanEntreeStock(), listContent[5]);
            	pan2.add(new PanSortieStock(), listContent[6]);
                cl.show(pan2, listContent[4]);
                liste1.setVisible(true);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton6){
            	//implement select of all nameParts to fill list
            	if(compteur6<1){
            		Part.emptyCollection();
                	ArrayList<String> data = new ArrayList<String>();
                	String identifier = "SelectAllParts";
                	logger.info("R�cup�ration des pi�ces");
                	new ServerConnect(data, identifier, pan2);
                    compteur6++;
                }
                pan2.add(new PanAjoutPiece(), listContent[8]);
                pan2.add(new PanModifPiece(), listContent[9]);
                cl.show(pan2, listContent[7]);
                liste1.setVisible(false);
                liste2.setVisible(true);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton7){
            	if(compteur7<1){
            		TypeCar.emptyCollection();
	            	ArrayList<String> data = new ArrayList<String>();
	            	String identifier = "LoadAllComboBox";
            		logger.info("Chargement des listes deroulantes");
            		new ServerConnect(data, identifier, pan2);
            		compteur7++;
            	}
            	pan2.add(new PanAjoutVehicule(), listContent[11]);
                pan2.add(new PanModifVehicule(), listContent[12]);
            	cl.show(pan2, listContent[10]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(true);
                pan.revalidate();
            }
            if(e.getSource() == bouton8){
                //code
                cl.show(pan2, listContent[13]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton9){

                pan2.add(new PanPerformances(), listContent[14]);

                cl.show(pan2, listContent[14]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
            }
            if(e.getSource() == bouton10){

                pan2.add(new PanChoixBonus(), listContent[15]);

                cl.show(pan2, listContent[15]);
                liste1.setVisible(false);
                liste2.setVisible(false);
                liste3.setVisible(false);
                pan.revalidate();
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
                switch ((String) valeur[i]){
                    case "Entree":
                        cl.show(pan2, listContent[5]);
                        pan.revalidate();
                        break;
                    case "Sortie":
                        cl.show(pan2, listContent[6]);
                        pan.revalidate();
                        break;
                }
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
                switch((String) valeur[i]){
                    case "Ajouter":
                        cl.show(pan2, listContent[8]);
                        pan.revalidate();
                        break;
                    case "Modifier":
                        cl.show(pan2, listContent[9]);
                        pan.revalidate();
                        break;
                }
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
                switch((String) valeur[i]){
                    case "Ajouter":
                        cl.show(pan2, listContent[11]);
                        pan.revalidate();
                        break;
                    case "Modifier":
                        cl.show(pan2, listContent[12]);
                        pan.revalidate();
                        break;
                }
            }
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        IHM a = new IHM();
        a.revalidate();
    }
}
