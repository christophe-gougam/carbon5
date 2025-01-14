/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import Client.Controlleurs.ServerConnect;
import Serveur.Controlleurs.Serveur;;



/**
 *
 * @author Carbon5
 */
public class PanAjoutPiece extends javax.swing.JPanel {
    /**
     * Creates new form PanAjoutPiece
     */
    public PanAjoutPiece(){
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("AJOUTER UNE PIECE DETACHEE");
        //jButton1.addActionListener(new BoutonListener(this));
        jLabel3.setText("Nom de piece");

        jLabel4.setText("Prix unitaire");

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new BoutonListener(this));
        jButton2.setText("Annuler");
        jButton2.addActionListener(new Bouton2Listener(this));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(25, 25, 25)
                            .addComponent(jTextField3))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(120, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    final static Logger logger = Logger.getLogger(Serveur.class);
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * Class BoutonListener listens button ADD
     */
    class BoutonListener implements ActionListener{
    	JPanel frame=null;

        /**
         * Class constructor
         * @param f 
         */
    	public BoutonListener  (JPanel f){
    	this.frame=f;
    	}
    	
        /**
         * Method generate component action
         * @param arg0 
         */
        public void actionPerformed(ActionEvent arg0){
        	
        	//String id = jTextField1.getText();
        	String namePart = jTextField1.getText();
        	String purchasePrice = jTextField3.getText();
        	
        	ArrayList<String> data = new ArrayList();
        	//data.add(id);
        	data.add(namePart);
        	data.add(purchasePrice);
        	String identifier = "CreatePart";
        	logger.info("Cr�ation d'une pi�ce d�tach�e");
        	new ServerConnect(data, identifier, frame);
        }
    }
    
    /**
     * Class Bouton2Listener listens button ANNULER
     */
    class Bouton2Listener implements ActionListener{
    	JPanel frame=null;
    	//son constructeur
    	public Bouton2Listener  (JPanel f){
    	this.frame=f;
    	}
    	
        /**
         * Method generate button action
         * @param e 
         * @see dispose()
         */
    	public void actionPerformed(ActionEvent e){
            //code
    		jTextField1.setText("");
    		jTextField2.setText("");
    		jTextField3.setText("");
        }
    }
}
