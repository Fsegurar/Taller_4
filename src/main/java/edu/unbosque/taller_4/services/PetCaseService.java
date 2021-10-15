package edu.unbosque.taller_4.services;

import edu.unbosque.taller_4.dtos.PetCase;
import edu.unbosque.taller_4.dtos.Visit;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetCaseService {

    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void Insertpetcase(String created_at,String description, int pet_id) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Counting vets ...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO petcase(created_at,type,description,pet_id) VALUES ('" + created_at + "'," + "'Robo'," + "'" + description + "'," + "'" + pet_id + "')";

            stmt.executeQuery(sql);

            stmt.close();
        }catch (PSQLException e){
            System.out.println("Caso guardado satisfactoriamente");
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
