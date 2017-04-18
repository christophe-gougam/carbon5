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
import Serveur.Controlleurs.Serveur;

/**
 *
 * @author Carbon5
 */
public class PanModifPiece extends javax.swing.JPanel {

    /**
     * Creates new form PanModifPiece
     */
    public PanModifPiece() {
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
        //jTextField1 = new javax.swing.JTextField();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<String> parts = new ArrayList<String>();
        for (Part aPart : Part.getAllParts()){
        	model.addElement(aPart.getNamePart());
        }
        
        jComboBox1 = new javax.swing.JComboBox(model);
        
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("MODIFIER UNE PIECE DETACHEE");

        jLabel3.setText("Nom de piece");

        jLabel4.setText("Prix unitaire");

        jButton1.setText("Valider");
        jButton1.addActionListener(new BoutonListener(this));
        jButton2.setText("Annuler");
        jButton3.setText("Supprimer");
        jButton3.addActionListener(new BoutonListener2(this));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        	 .addComponent(jComboBox1, 0, 248, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                	.addComponent(jButton3))
                .addContainerGap(120, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    //private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jComboBox1;
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
        	
        	String namePart = ""+(String) jComboBox1.getSelectedItem()+"";
        	String purchasePrice = jTextField3.getText();
        	
        	ArrayList<String> data = new ArrayList<String>();
        	data.add(namePart);
        	data.add(purchasePrice);
        	String identifier = "ModificationPart";
        	logger.info("Modification d'une pi�ce d�tach�e");
        	new ServerConnect(data, identifier, frame);
        	
        	
        }
    }
    
    /**
     * Class BoutonListener listens button DELETE
     */
    class BoutonListener2 implements ActionListener{
    	JPanel frame=null;

        /**
         * Class constructor
         * @param f 
         */
    	public BoutonListener2  (JPanel f){
    	this.frame=f;
    	}
    	
        /**
         * Method generate component action
         * @param arg0 
         */
        public void actionPerformed(ActionEvent arg0){
        	
        	String namePart = ""+(String) jComboBox1.getSelectedItem()+"";
        	
        	ArrayList<String> data = new ArrayList<String>();
        	data.add(namePart);
        	String identifier = "DeletePart";
        	logger.info("Modification d'une pi�ce d�tach�e");
        	new ServerConnect(data, identifier, frame);
        }
    }
}
