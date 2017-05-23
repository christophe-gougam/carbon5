/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import javax.swing.*;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import Modele.Car;
import Serveur.Controlleurs.Serveur;
/**
 *
 * @author Carbon5
 */
public class PanVehicule extends javax.swing.JPanel {
	ResultSet car=null;

    final static Logger logger = Logger.getLogger(Serveur.class);
    /**
     * Creates new form PanVehicule
     */
    public PanVehicule() throws SQLException {
        initComponents();
        this.fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton = new javax.swing.JButton("Actualiser"); 
            
        jLabel1.setText("LISTE DES VEHICULES");
        jButton.addActionListener(new BoutonListener());
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
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            	for(ResultSet i: Car.getAllCar()){
                	car=i;
                }
            	RsTableModel model = new RsTableModel(car);
	            JTable jTableA = new JTable();
	            jTableA.setModel(model);
	            jScrollPane1.setViewportView(jTableA);
	            model.setData();
	            } catch (Exception eve){
	            eve.printStackTrace();
	            }
        }
    }
    
    /**
     * Class create model for table 
     */
    class RsTableModel extends AbstractTableModel {
        private Vector colHeaders;
        private Vector tbData;
        
        /**
         * Class constructor
         * @param rsData
         * @throws SQLException 
         */
        public RsTableModel(ResultSet rsData) throws SQLException {
            ResultSetMetaData rsMeta = rsData.getMetaData();
            int count = rsMeta.getColumnCount();

            tbData = new Vector();
            colHeaders = new Vector(count);

            for(int i = 1; i <= count; i++){
                colHeaders.addElement(rsMeta.getColumnName(i));
            }

            while (rsData.next()){
                Vector dataRow = new Vector(count);
                for(int i = 1; i <= count; i++){
                    dataRow.addElement(rsData.getObject(i));
                }
                tbData.addElement(dataRow);
            }
        }
        @Override
        public String getColumnName(int column) {
            return  (String) (colHeaders.elementAt(column));
        }
        
        @Override
        public int getRowCount() { return tbData.size(); }

        @Override
        public int getColumnCount() { return colHeaders.size(); }

        @Override
        public Object getValueAt(int row, int column) {
            Vector rowData = (Vector)(tbData.elementAt(row));
            return rowData.elementAt(column);
        }
        
        public void setData(){
        super.fireTableDataChanged();
        }
    }
    
    /**
     * Method fill data to table
     * @throws SQLException 
     */
    protected void fillTable() throws SQLException{
        try{
        	
            for(ResultSet i: Car.getAllCar()){
            	car=i;
            	
            }
            RsTableModel model = new RsTableModel(car);
            this.jTable1.setModel(model);
            model.setData();
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
