/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import static Vues.IHM.logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Client.Controlleurs.ServerConnect;
import Modele.RepairCard;

/**
 *
 * @author Carbon5
 */
public class PanStat extends javax.swing.JPanel {
    /**
     * Creates new form NewJPanel
     */
    public PanStat(int k) throws SQLException{
        if(k == 2){
            initComponents();
            jTabbedPane1.remove(jPanel4);
            this.fillTable1();
            this.fillTable2();
            this.fillTable3();
            this.fillTable4();
            this.revalidate();
        } else {
            initComponents();
            this.fillTable1();
            this.fillTable2();
            this.fillTable3();
            this.fillTable4();
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        jButton1.setText("Actualiser");
        jButton1.addActionListener(new PanStat.BoutonListener());

        jLabel1.setText("LISTE COMPLET DES VEHICULES");

        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Liste vehicule", jPanel1);

        jButton2.setText("Actualiser");
        jButton2.addActionListener(new PanStat.BoutonListener());

        jLabel2.setText("WORKFLOW COMPLET DE VEHICULE");

        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Workflow vehicule", jPanel2);

        jButton3.setText("Actualiser");
        jButton3.addActionListener(new PanStat.BoutonListener());

        jLabel3.setText("CUMUL DE LA JOURNEE");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cumul journée", jPanel3);

        jButton4.setText("Actualiser");
        jButton4.addActionListener(new PanStat.BoutonListener());

        jLabel4.setText("STATISTIQUE MANUTENTIONNAIRES");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manutentionnaire", jPanel4);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private static javax.swing.JButton jButton3;
    private static javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private static javax.swing.JPanel jPanel1;
    private static javax.swing.JPanel jPanel2;
    private static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration 
    
    /**
     * Class model table 1
     */
    public class RsTableModel1 extends AbstractTableModel {
        private ArrayList<RepairCard> repairCard ;
        private String[] columns ; 

        public RsTableModel1(ArrayList<RepairCard> listRepairCard){
          super();
          repairCard = listRepairCard ;
          columns = new String[]{"Num puce","Type véhicule","Degree urgence","Description", "Statut"};
        }

        // Number of column of your table
        public int getColumnCount() {
          return columns.length ;
        }

        // Number of row of your table
        public int getRowCount() {
          return repairCard.size();
        }

        // The object to render in a cell
        public Object getValueAt(int row, int col) {
          RepairCard aRC = repairCard.get(row);
          switch(col) {
            case 0: return aRC.getidcar();
            case 1: return aRC.getCar().getTypeVehicule();
            case 2: return aRC.getDegree().getId();
            case 3: return aRC.getDegree().getDescription();
            case 4: return aRC.getCard().getDescription();
            default: return null;
          }
        }
        // Optional, the name of your column
        public String getColumnName(int col) {
          return columns[col] ;
        }
    }
    
    /**
     * Class model table 2
     */
    public class RsTableModel2 extends AbstractTableModel {
        private ArrayList<RepairCard> repairCard ;
        private String[] columns ; 

        public RsTableModel2(ArrayList<RepairCard> listRepairCard){
          super();
          repairCard = listRepairCard ;
          columns = new String[]{"ID car","Type","Urgency level","Description", "State",
          "Place","Parking","Detail ops","Entry date","Out Date","Nature repair","Time spent",
          "Repair ops","Defect description","Repair time","Criticity","Name part"};
        }

        // Number of column of your table
        public int getColumnCount() {
          return columns.length ;
        }

        // Number of row of your table
        public int getRowCount() {
          return repairCard.size();
        }

        // The object to render in a cell
        public Object getValueAt(int row, int col) {
          RepairCard aRC = repairCard.get(row);
          switch(col) {
            case 0: return aRC.getCar().getNumePuce();
            case 1: return aRC.getCar().getTypeVehicule();
            case 2: return aRC.getDegree().getId();
            case 3: return aRC.getDegree().getDescription();
            case 4: return aRC.getCard().getDescription();
            case 5: return aRC.getPark().getNumPlace();
            case 6: return aRC.getPark().getNumPark();
            case 7: return aRC.getOverAllDetails();
            case 8: return aRC.getEntryDate();
            case 9: return aRC.getOutDate();
            case 10: return aRC.getRepair().getNature();
            case 11: return aRC.getRepair().getTimeSpent();
            case 12: return aRC.getRepair().getDescription();
            case 13: return aRC.getDefect().getDescription();
            case 14: return aRC.getDefect().getduration();
            case 15: return aRC.getDefect().getCriticity();
            case 16: return aRC.getPart().getNamePart();
            default: return null;
          }
        }
        // Optional, the name of your column
        public String getColumnName(int col) {
          return columns[col] ;
        }
    }
    
    /**
     * Class model table 3
     */
    public class RsTableModel3 extends AbstractTableModel {
        private ArrayList<RepairCard> repairCard ;
        private String[] columns ; 

        public RsTableModel3(ArrayList<RepairCard> listRepairCard){
          super();
          repairCard = listRepairCard ;
          columns = new String[]{"Statut", "Nombre de véhicule"};
        }

        // Number of column of your table
        public int getColumnCount() {
          return columns.length ;
        }

        // Number of row of your table
        public int getRowCount() {
          return repairCard.size();
        }

        // The object to render in a cell
        public Object getValueAt(int row, int col) {
          RepairCard aRC = repairCard.get(row);
          switch(col) {
            case 0: return aRC.getStatutCar();
            case 1: return aRC.getNbCar();
            default: return null;
          }
        }
        // Optional, the name of your column
        public String getColumnName(int col) {
          return columns[col] ;
        }
    }
    
    /**
     * Class model table 4
     */
    public class RsTableModel4 extends AbstractTableModel {
        private ArrayList<RepairCard> repairCard ;
        private String[] columns ; 

        public RsTableModel4(ArrayList<RepairCard> listRepairCard){
          super();
          repairCard = listRepairCard ;
          columns = new String[]{"Nom", "Prenom", "Nombre d'opération réalisé"};
        }

        // Number of column of your table
        public int getColumnCount() {
          return columns.length ;
        }

        // Number of row of your table
        public int getRowCount() {
          return repairCard.size();
        }

        // The object to render in a cell
        public Object getValueAt(int row, int col) {
          RepairCard aRC = repairCard.get(row);
          switch(col) {
            case 0: return aRC.getName();
            case 1: return aRC.getLastName();
            case 2: return aRC.getNumRep();
            default: return null;
          }
        }
        // Optional, the name of your column
        public String getColumnName(int col) {
          return columns[col] ;
        }
    }
    
    protected void fillTable1() throws SQLException{
        try{
            PanStat.RsTableModel1 model1 = new PanStat.RsTableModel1(RepairCard.getInfoCars());
            this.jTable1.setModel(model1);
        } catch (Exception e){
            e.printStackTrace();
        }  
    }
    
    protected void fillTable2(){
        try{
            PanStat.RsTableModel2 model2 = new PanStat.RsTableModel2(RepairCard.getInfoCars());
            this.jTable2.setModel(model2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    protected void fillTable3(){
        try{
            PanStat.RsTableModel3 model3 = new PanStat.RsTableModel3(RepairCard.getInfoCars());
            this.jTable3.setModel(model3);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    protected void fillTable4(){
        try{
            PanStat.RsTableModel4 model4 = new PanStat.RsTableModel4(RepairCard.getInfoCars());
            this.jTable4.setModel(model4);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Class listens buttons
     */
    private static class BoutonListener implements ActionListener {

        public BoutonListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                RepairCard.emptyCollection();
                ArrayList<String> data = new ArrayList<String>();
                String identifier = "getInfoCar_query1";
                logger.info("Recuperation des informations des car");
                new ServerConnect(data, identifier, jPanel1);
                jPanel1.revalidate();
            }
            
            if(e.getSource() == jButton2){
                RepairCard.emptyCollection();
                ArrayList<String> data = new ArrayList<String>();
                String identifier = "getWorkflowCar_query2";
                logger.info("Recuperation workflow complet des car");
                new ServerConnect(data, identifier, jPanel2);
                jPanel2.revalidate();
            }
            
            if(e.getSource() == jButton3){
                RepairCard.emptyCollection();
                ArrayList<String> data = new ArrayList<String>();
                String identifier = "getCumulDay_query3";
                logger.info("Cumul de la journée");
                new ServerConnect(data, identifier, jPanel3);
                jPanel3.revalidate();
            }
            
            if(e.getSource() == jButton4){
                RepairCard.emptyCollection();
                ArrayList<String> data = new ArrayList<String>();
                String identifier = "getManutentionnaires_query4";
                logger.info("Recuperation des statistiques manutentionnaires");
                new ServerConnect(data, identifier, jPanel4);
                jPanel4.revalidate();
            }
        }
    }
}
