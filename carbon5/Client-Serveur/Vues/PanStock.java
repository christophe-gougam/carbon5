/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Carbon5
 */
public class PanStock extends javax.swing.JPanel {

    /**
     * Creates new form PanStock
     */
    public PanStock() throws SQLException {
        initComponents();
        this.fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton = new javax.swing.JButton("Actualiser");
//        String url = "jdbc:mysql://localhost:3306/carbon5";
//        String user = "root";
//        String pwd = "";
//        Connection con1 = DriverManager.getConnection(url, user, pwd);
//        String queryString = "SELECT Stock, NamePart FROM Part";
//        Statement stm = con1.createStatement();
//        ResultSet rs = stm.executeQuery(queryString);
//        String col[] = {"Nom de piece","Stock réel"};
//        String cont[][] = new String[40][2];
//        int i = 0;
//        while (rs.next()){
//            int stock = rs.getInt("Stock");
//            String nom = rs.getString("NamePart");
//            cont[i][1] = stock + "";
//            cont[i][0] = nom;
//            i++;
//        }
//        DefaultTableModel model = new DefaultTableModel(cont, col);
//        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);
        jButton.addActionListener(new BoutonListener());
        jLabel1.setText("LE STOCK DES PIECES DETACHEES");

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
//                    .addGroup(layout.createSequentialGroup()
//                        .addComponent(jLabel1)
//                        .addGap(0, 0, Short.MAX_VALUE)))
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addComponent(jLabel1)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
//                .addContainerGap())
//        );
        
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
            String url = "jdbc:mysql://localhost:3306/carbon5";
            String user = "root";
            String pwd = "";
            Connection connect = DriverManager.getConnection(url, user, pwd);
            String queryString = "SELECT Stock, NamePart FROM Part";
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            RsTableModel model = new RsTableModel(rs);
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
    };
    
    /**
     * Method fill data to table
     * @throws SQLException 
     */
    protected void fillTable() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/carbon5";
            String user = "root";
            String pwd = "";
            Connection connect = DriverManager.getConnection(url, user, pwd);
            String queryString = "SELECT Stock, NamePart FROM Part";
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            RsTableModel model = new RsTableModel(rs);
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
