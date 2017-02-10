package r1Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import r1Client.Fenetre.Bouton2Listener;
import r1Client.Fenetre.BoutonListener;
import r1Client.Authentication;
public class Authentication extends JFrame {
	
	private JPanel container = new JPanel();
    private JTextField jtf = new JTextField();
    private JLabel label = new JLabel("Login");
    
    private JTextField jtf2 = new JTextField();
    private JLabel label2 = new JLabel("Mot de passe");
    
    private JButton bouton = new JButton("OK");
    private JButton bouton2 = new JButton("Annuler");
    
    private static Thread t1;
    
    public Authentication(){
    	this.setTitle("Authentification");
        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        //combo.setPreferredSize(new Dimension(100, 20));
               
        Font police = new Font("Arial", Font.BOLD, 14);
        jtf.setFont(police);
        jtf.setPreferredSize(new Dimension(150, 20));
        jtf.setForeground(Color.BLUE);
        
        jtf2.setFont(police);
        jtf2.setPreferredSize(new Dimension(150, 20));
        jtf2.setForeground(Color.BLUE);

        //Ajouter boutons
        bouton.addActionListener(new BoutonListener(this));
        bouton2.addActionListener(new Bouton2Listener(this));
        //bouton2.setEnabled(false);
        JPanel south = new JPanel();
        south.add(bouton);
        south.add(bouton2);
        container.add(south,BorderLayout.SOUTH);
        
        JPanel top = new JPanel();
        GridLayout gl = new GridLayout(3,1);
        gl.setHgap(5);
        gl.setVgap(5);
        top.setLayout(gl);
        //Ajouter ID VEHICULE
        top.add(label);
        top.add(jtf);
        //Ajouter TYPE VEHICULE
        top.add(label2);
        top.add(jtf2);
        
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
        
    }
  //Classe écoutant bouton OK
    class BoutonListener implements ActionListener{
    	JFrame frame=null;
    	//son constructeur
    	public BoutonListener  (JFrame f){
    	this.frame=f;
    	}
        public void actionPerformed(ActionEvent arg0){
        	
        	String login = jtf.getText();
        	String mdp = jtf2.getText();
        	
        	ArrayList<String> data = new ArrayList();
        	data.add(login);
        	data.add(mdp);
        	String identifier = "Authentication";
        	System.out.println("Authentification demandée par le client");
        	t1 = new Thread(new ServerConnect(data, identifier));
        	t1.start();
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
        	//jtf.setText("");
        	//jtf2.setText("");
    		frame.dispose();
        }
    }
}
