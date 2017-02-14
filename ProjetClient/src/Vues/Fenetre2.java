package r1Client.Vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author CongThuan
 */
public class Fenetre2 extends JFrame {
    private JPanel container = new JPanel();
    
    private JComboBox combo = new JComboBox();
    private JLabel label = new JLabel("ID personnel");
    
    private JComboBox combo1 = new JComboBox();
    private JLabel label1 = new JLabel("Pièce");
    
    private JFormattedTextField jtf1 = new JFormattedTextField(DateFormat.getDateInstance());
    private JLabel label2 = new JLabel("Date de sortie");
    
    private JFormattedTextField jtf2 = new JFormattedTextField(NumberFormat.getNumberInstance());
    private JLabel label3 = new JLabel("Quantité");
    
    private JButton bouton = new JButton("Enregistrer");
    private JButton bouton2 = new JButton("Annuler");
    
    public Fenetre2(){
        this.setTitle("Sortie de stock");
        this.setSize(300, 160);
        this.setLocationRelativeTo(null);
        
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        combo.setPreferredSize(new Dimension(100, 20));
               
        Font police = new Font("Arial", Font.BOLD, 14);
        jtf1.setFont(police);
        jtf1.setPreferredSize(new Dimension(150, 20));
        jtf1.setForeground(Color.BLUE);
        
        jtf2.setFont(police);
        jtf2.setPreferredSize(new Dimension(150, 20));
        jtf2.setForeground(Color.RED);

        //Ajouter boutons
        bouton.addActionListener(new BoutonListener(this));
        bouton2.addActionListener(new Bouton2Listener(this));
        //bouton2.setEnabled(false);
        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        container.add(south,BorderLayout.SOUTH);
        
        //Ajouter item dans la liste ID personnel
        combo.addItem("P1");
        combo.addItem("P2");
        combo.addItem("P3");
        combo.addItem("P4");
        combo.addItem("P5");
        combo.addItem("P6");
             
        //Ajouter du listener
        combo.addItemListener(new ItemState());
        combo.addActionListener(new ItemAction());
        combo.setPreferredSize(new Dimension(150,20));
        combo.setForeground(Color.red);
        
        //Ajouter item dans la liste des pièces
        combo1.addItem("pneu");
        combo1.addItem("lampe phare");
        combo1.addItem("vitre");
        combo1.addItem("pédales");
        combo1.addItem("chaine");
        combo1.addItem("battery");
             
        //Ajouter du listener
        combo1.addItemListener(new ItemState());
        combo1.addActionListener(new Item1Action());
        combo1.setPreferredSize(new Dimension(150,20));
        combo1.setForeground(Color.blue);
        
        JPanel top = new JPanel();
        GridLayout gl = new GridLayout(4,2);
        gl.setHgap(5);
        gl.setVgap(5);
        top.setLayout(gl);
        //Ajouter ID VEHICULE
        top.add(label);
        top.add(combo);
        //Ajouter TYPE VEHICULE
        top.add(label1);
        top.add(combo1);
        //Ajouter STATUT
        top.add(label2);
        top.add(jtf1);
        //Ajouter NUMERO PARKING
        top.add(label3);
        top.add(jtf2);
        
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }
    
     //Classe interne implémentant l'interface ItemListener
    class ItemState implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            System.out.println("événement déclenché sur : " + e.getItem());
        }               
    }
    
    //ActionListener de TYPE VEHICULE
    class ItemAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("ActionListener: action sur " + combo.getSelectedItem());
        }
    }
    
    //ActionListener de STATUT
    class Item1Action implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("ActionListener: action sur " + combo1.getSelectedItem());
        }
    }
    
    //Classe écoutant bouton AJOUTER
    class BoutonListener implements ActionListener{
    	JFrame frame=null;
    	//son constructeur
    	public BoutonListener  (JFrame f){
    	this.frame=f;
    	}
    	public void actionPerformed(ActionEvent arg0){
            //code
    		frame.dispose();
        }
    }
    
    //Classe écoutant bouton2 ANNULER
    class Bouton2Listener implements ActionListener{
    	JFrame frame=null;
    	//son constructeur
    	public Bouton2Listener  (JFrame f){
    	this.frame=f;
    	}
    	public void actionPerformed(ActionEvent e){
            //code
        	frame.dispose();
        }
    }
}
