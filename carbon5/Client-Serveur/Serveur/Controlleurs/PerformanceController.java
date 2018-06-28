package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.sql.Connection;

public class PerformanceController implements Runnable {

    private Connection con;
    private String in;
    private PrintWriter out;

    public PerformanceController(Connection con, String in, PrintWriter out) {
        this.con = con;
        this.in = in;
        this.out = out;
    }

    public void run() {

    }

}
