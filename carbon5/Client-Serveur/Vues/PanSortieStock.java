/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import Client.Controlleurs.ServerConnect;
import Modele.Part;
import Modele.User;
import Serveur.Controlleurs.Serveur;

/**
 *
 * @author Carbon5
 */
public class PanSortieStock extends javax.swing.JPanel {

    /**
     * Creates new form PanSortieStock
     */
    public PanSortieStock() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        for (Part aPart : Part.getAllParts()){
        	model.addElement(aPart.getNamePart());
        }
        
        jComboBox1 = new javax.swing.JComboBox<String>(model);
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("Enregistrer");
        jButton1.addActionListener(new BoutonListener(this));
        jButton2.setText("Annuler");

        jLabel4.setText("Quantite");

        jLabel3.setText("Nom de piece");

        jLabel1.setText("SORTIE DE STOCK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                        )
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 248, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            )))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField3;
    final static Logger logger = Logger.getLogger(Serveur.class);
    // End of variables declaration//GEN-END:variables
    
    /**
     * Class BoutonListener listens button ENREGISTRER
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
        	
        	String namePart = ""+(String) jComboBox1.getSelectedItem()+"";
        	String quantite = jTextField3.getText();
        	
        	ArrayList<String> data = new ArrayList<String>();
        	data.add(namePart);
        	data.add(quantite);
        	data.add(""+User.getAllUsers().get(0).getId());
        	String identifier = "addOutStock";
        	logger.info("Sortie de stock");
        	new ServerConnect(data, identifier, frame);
        }
    }
}
