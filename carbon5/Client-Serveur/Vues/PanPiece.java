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

import Client.Controlleurs.ServerConnect;
import Vues.PanAjoutPiece.BoutonListener;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("LISTE DES PIECES DETACHEES");
        
        try{
        String url = "jdbc:mysql://localhost:3306/carbon5";
        String user = "root";
        String pwd = "";
        Connection connect = DriverManager.getConnection(url, user, pwd);
        String queryString = "SELECT * FROM Part";
        Statement stm = connect.createStatement();
        ResultSet rs = stm.executeQuery(queryString);
        String col[] = {"ID","Stock","Nom de piece","Purchase price"};
        String cont[][] = new String[10][4];
        int i = 0;
        while (rs.next()){
            int id = rs.getInt("Id");
            int stock = rs.getInt("Stock");
            String nom = rs.getString("NamePart");
            int price = rs.getInt("PurchasePrice");
            cont[i][0] = id + "";
            cont[i][1] = stock + "";
            cont[i][2] = nom;
            cont[i][3] = price + "";
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(cont, col);
        jTable1.setModel(model);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                        ))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    

}
