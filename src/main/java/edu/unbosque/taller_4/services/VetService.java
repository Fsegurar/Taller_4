package edu.unbosque.taller_4.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VetService {
    // Objects for handling connection
    private Connection conn;

    public VetService(Connection conn) {
        this.conn = conn;
    }

    public void countVets() {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Counting vets ...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS count FROM Vet" ;
            ResultSet rs = stmt.executeQuery(sql);

            // Pointing to fist row
            rs.next();

            // Printing results
            System.out.println("Total de veterinarias: " + rs.getInt("count") + "\n");

            // Closing resources
            rs.close();
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
