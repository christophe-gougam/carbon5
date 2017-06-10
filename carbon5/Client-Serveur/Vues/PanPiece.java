/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import static Vues.PanAjoutPiece.logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Client.Controlleurs.ServerConnect;
import Modele.Part;

/**
 *
 * @author Carbon5
 */
public class PanPiece extends javax.swing.JPanel {
    /**
     * Creates new form PanPiece
     */
    public PanPiece() throws SQLException {
        initComponents();
        this.fillTable();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton = new javax.swing.JButton("Actualiser");
        
        jLabel1.setText("LISTE DES PIECES DETACHEES");     
        
        jButton.addActionListener(new BoutonListener(this));
        jScrollPane1.setViewportView(jTable1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(jButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method listens button "Actualiser"
     */
    class BoutonListener implements ActionListener{
        JPanel frame = null;
        
        public BoutonListener(JPanel f){
            JPanel frame = f;
        }
        @Override
        public void actionPerformed(ActionEvent e) { 	
        	ArrayList<String> data = new ArrayList();
        	String identifier = "SelectAllParts";
        	logger.info("Afficher liste des pieces");
        	new ServerConnect(data, identifier, frame);
            RsTableModel model = new RsTableModel(Part.getAllParts());
            JTable jTableA = new JTable();
            jTableA.setModel(model);
            jScrollPane1.setViewportView(jTableA);
        }
    }
    
    /**
     * Class create model for table 
     */     
    public class RsTableModel extends AbstractTableModel {
        private ArrayList<Part> parts ;
        private String[] columns ; 

        public RsTableModel(ArrayList<Part> listPart){
          super();
          parts = listPart ;
          columns = new String[]{"ID part","Stock","Name part", "Purchase price"};
        }

        // Number of column of your table
        public int getColumnCount() {
          return columns.length ;
        }

        // Number of row of your table
        public int getRowCount() {
          return parts.size();
        }

        // The object to render in a cell
        public Object getValueAt(int row, int col) {
          Part part = parts.get(row);
          switch(col) {
            case 0: return part.getIdPart();
            case 1: return part.getStock();
            case 2: return part.getNamePart();
            case 3: return part.getPurchasePrice();
            default: return null;
          }
        }
        // Optional, the name of your column
        public String getColumnName(int col) {
          return columns[col] ;
        }
    }
    
    /**
     * Method fill data to table
     * @throws SQLException 
     */
    protected void fillTable() throws SQLException{
        try{

            RsTableModel model = new RsTableModel(Part.getAllParts());
            this.jTable1.setModel(model);
        } catch (Exception e){
            e.printStackTrace();
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jButton;
    // End of variables declaration//GEN-END:variables
}
