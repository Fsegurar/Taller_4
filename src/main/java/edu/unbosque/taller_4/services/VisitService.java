package edu.unbosque.taller_4.services;

import edu.unbosque.taller_4.dtos.UserApp;
import edu.unbosque.taller_4.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitService {
    private Connection conn;

    public VisitService(Connection conn) {
        this.conn = conn;
    }

    public void consultingByPet_Id(int id) {

        // Objects for handling SQL statement
        Statement stmt = null;

        List<Visit> visits = new ArrayList<Visit>();

        try {

            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM visit WHERE pet_id= "+ id  ;

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                // Extracting row values by column name
                int visit_id = Integer.parseInt(rs.getString("visit_id"));
                String type  = rs.getString("type");
                String description = rs.getString("description");
                String vet_id = rs.getString("vet_id");
                int pet_id = Integer.parseInt(rs.getString("pet_id"));


                // Creating a new UserApp class instance and adding it to the array list
                visits.add(new Visit(visit_id,type, description, vet_id,pet_id));
            }

            // Printing results
            System.out.println("Visit_id | Type | Description | Vet_id | Pet_id");
            for (Visit visit : visits) {
                System.out.print(visit.getVisit_id() + " | ");
                System.out.print(visit.getType() + " | ");
                System.out.print(visit.getDescription() + " | ");
                System.out.println(visit.getVet_id() + " | ");
                System.out.println(visit.getPet_id());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + visits.size() + "\n");

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
