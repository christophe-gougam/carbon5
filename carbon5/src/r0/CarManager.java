package r0;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CarManager extends JFrame {

  private JButton getCarButton, insertCarButton, deleteCarButton,
      updateCarButton, nextButton, previousButton, lastButton,
      firstButton, gotoButton, freeQueryButton;

  private JList carNumberList;

  private JTextField marque, modele, immatriculation, kilometrage,
      activeTSText, gotoText, freeQueryText;

  private JTextArea errorText;

  private Connection connection;

  private Statement statement;

  private ResultSet rs;

  public CarManager() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {
      System.err.println("Unable to find and load driver");
      System.exit(1);
    }
  }
  


  private void loadCars() {
    Vector v = new Vector();
    try {
      rs = statement.executeQuery("SELECT * FROM car");

      while (rs.next()) {
        v.addElement(rs.getString("acc_id"));
      }
    } catch (SQLException e) {
      displaySQLErrors(e);
    }
    carNumberList.setListData(v);
  }

  private void buildGUI() {
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    carNumberList = new JList();
    loadCars();
    carNumberList.setVisibleRowCount(2);
    JScrollPane carNumberListScrollPane = new JScrollPane(
        carNumberList);

    gotoText = new JTextField(3);
    freeQueryText = new JTextField(40);

    //Do Get Car Button
    getCarButton = new JButton("Get car");
    getCarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.first();
          while (rs.next()) {
            if (rs.getString("acc_id").equals(
                carNumberList.getSelectedValue()))
              break;
          }
          if (!rs.isAfterLast()) {
            marque.setText(rs.getString("acc_id"));
            modele.setText(rs.getString("username"));
            immatriculation.setText(rs.getString("password"));
            kilometrage.setText(rs.getString("ts"));
          }
        } catch (SQLException selectException) {
          displaySQLErrors(selectException);
        }
      }
    });
       
    //Do Insert car Button
    insertCarButton = new JButton("Insert car");
    insertCarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement
              .executeUpdate("INSERT INTO acc_acc VALUES("
                  + marque.getText() + ", " + "'"
                  + modele.getText() + "', " + "'"
                  + immatriculation.getText() + "', " + "'"
                  + kilometrage.getText() + "', " + "0"
                  + ", " + "now())");
          errorText.append("Inserted " + i + " rows successfully");
          carNumberList.removeAll();
          loadCars();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Delete car Button
    deleteCarButton = new JButton("Delete car");
    deleteCarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement
              .executeUpdate("DELETE FROM car WHERE idCar = "
                  + carNumberList.getSelectedValue());
          errorText.append("Deleted " + i + " rows successfully");
          carNumberList.removeAll();
          loadCars();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Update car Button
    updateCarButton = new JButton("Update car");
    updateCarButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Statement statement = connection.createStatement();
          int i = statement.executeUpdate("UPDATE car "
              + "SET marque='" + marque.getText() + "', "
              + "modele='" + modele.getText() + "', "
              + "immatriculation='" + immatriculation.getText() + "', "
              + "kilometrage='" + kilometrage.getText() + "', "
              + "WHERE acc_id = "
              + carNumberList.getSelectedValue());
          errorText.append("Updated " + i + " rows successfully");
          carNumberList.removeAll();
          loadCars();
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Next Button
    nextButton = new JButton(">");
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (!rs.isLast()) {
            rs.next();
            marque.setText(rs.getString("acc_id"));
            modele.setText(rs.getString("username"));
            immatriculation.setText(rs.getString("password"));
            kilometrage.setText(rs.getString("ts"));
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do Next Button
    previousButton = new JButton("<");
    previousButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (!rs.isFirst()) {
            rs.previous();
            marque.setText(rs.getString("acc_id"));
            modele.setText(rs.getString("username"));
            immatriculation.setText(rs.getString("password"));
            kilometrage.setText(rs.getString("ts"));
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do last Button
    lastButton = new JButton(">|");
    lastButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.last();
          marque.setText(rs.getString("acc_id"));
          modele.setText(rs.getString("username"));
          immatriculation.setText(rs.getString("password"));
          kilometrage.setText(rs.getString("ts"));
          activeTSText.setText(rs.getString("act_ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do first Button
    firstButton = new JButton("|<");
    firstButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.first();
          marque.setText(rs.getString("acc_id"));
          modele.setText(rs.getString("username"));
          immatriculation.setText(rs.getString("password"));
          kilometrage.setText(rs.getString("ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do gotoButton
    gotoButton = new JButton("Goto");
    gotoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          rs.absolute(Integer.parseInt(gotoText.getText()));
          marque.setText(rs.getString("acc_id"));
          modele.setText(rs.getString("username"));
          immatriculation.setText(rs.getString("password"));
          kilometrage.setText(rs.getString("ts"));
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    //Do freeQueryButton
    freeQueryButton = new JButton("Execute Query");
    freeQueryButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (freeQueryText.getText().toUpperCase().indexOf("SELECT") >= 0) {
            rs = statement.executeQuery(freeQueryText.getText());
            if (rs.next()) {
              marque.setText(rs.getString("acc_id"));
              modele.setText(rs.getString("username"));
              immatriculation.setText(rs.getString("password"));
              kilometrage.setText(rs.getString("ts"));
            }
          } else {
            int i = statement
                .executeUpdate(freeQueryText.getText());
            errorText.append("Rows affected = " + i);
            loadCars();
          }
        } catch (SQLException insertException) {
          displaySQLErrors(insertException);
        }
      }
    });

    JPanel first = new JPanel(new GridLayout(5, 1));
    first.add(carNumberListScrollPane);
    first.add(getCarButton);
    first.add(insertCarButton);
    first.add(deleteCarButton);
    first.add(updateCarButton);

    marque = new JTextField(15);
    modele = new JTextField(15);
    immatriculation = new JTextField(15);
    kilometrage = new JTextField(15);
    errorText = new JTextArea(5, 15);
    errorText.setEditable(false);

    JPanel second = new JPanel();
    second.setLayout(new GridLayout(6, 1));
    second.add(marque);
    second.add(modele);
    second.add(immatriculation);
    second.add(kilometrage);

    JPanel third = new JPanel();
    third.add(new JScrollPane(errorText));

    JPanel fourth = new JPanel();
    fourth.add(firstButton);
    fourth.add(previousButton);
    fourth.add(nextButton);
    fourth.add(lastButton);
    fourth.add(gotoText);
    fourth.add(gotoButton);

    JPanel fifth = new JPanel();
    fifth.add(freeQueryText);

    c.add(first);
    c.add(second);
    c.add(third);
    c.add(fourth);
    c.add(fifth);
    c.add(freeQueryButton);
    setSize(500, 500);
    show();
  }

  public void connectToDB() {
    try {
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/carbon5");
      statement = connection.createStatement();

    } catch (SQLException connectException) {
      System.out.println(connectException.getMessage());
      System.out.println(connectException.getSQLState());
      System.out.println(connectException.getErrorCode());
      System.exit(1);
    }
  }

  private void displaySQLErrors(SQLException e) {
    errorText.append("SQLException: " + e.getMessage() + "\n");
    errorText.append("SQLState:     " + e.getSQLState() + "\n");
    errorText.append("VendorError:  " + e.getErrorCode() + "\n");
  }

  private void init() {
    connectToDB();
  }

  public static void main(String[] args) {
    CarManager cars = new CarManager();

    cars.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    cars.init();
    cars.buildGUI();
  }
}