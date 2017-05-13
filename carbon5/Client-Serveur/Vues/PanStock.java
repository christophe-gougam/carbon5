/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Modele.Part;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        
        String url = "jdbc:mysql://localhost:3306/carbon5";
        String user = "root";
        String pwd = "";
        Connection con1 = DriverManager.getConnection(url, user, pwd);
        String queryString = "SELECT Stock, NamePart FROM Part";
        Statement stm = con1.createStatement();
        ResultSet rs = stm.executeQuery(queryString);
        String col[] = {"Nom de piece","Stock réel"};
        String cont[][] = new String[10][2];
        int i = 0;
        while (rs.next()){
            int stock = rs.getInt("Stock");
            String nom = rs.getString("NamePart");
            cont[i][1] = stock + "";
            cont[i][0] = nom;
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(cont, col);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID piece", "Nom du piece", "Stock actuel", "Derniere entree", "Derniere sortie"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("LE STOCK DES PIECES DETACHEES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
