package edu.unbosque.taller_4;

import java.sql.*;
import java.util.Scanner;

import edu.unbosque.taller_4.services.PetCaseService;
import edu.unbosque.taller_4.services.UsersService;
import edu.unbosque.taller_4.services.VetService;
import edu.unbosque.taller_4.services.VisitService;

public class Main {

    public static Scanner leer;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/Taller_4";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "Filip1218";

    public static void main(String[] args) {

        leer = new Scanner(System.in);
        // Objects for handling connection
        Connection conn = null;
        int opcion =0;
        do {
            System.out.println("Para Consultar la lista de usuarios registrados con un rol espesifico dijte 1. \nPara Contar la lista de veterinarias registradas en la plataforma por favor dijte 2.\nPara Consultar las visitas que se han registrado para un ID de mascota por favor dijite 3.\nPara Registrar un nuevo caso de robo de una mascota dado su ID por favor dijite 4.\nPara salir dijte 0 ");
            opcion = leer.nextInt();
            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese el rol que desea buscar: official,vet,owner");
                    String role = leer.next();
                    if(role!=null) {
                        try {

                            // Registering the JDBC driver
                            Class.forName(JDBC_DRIVER);

                            // Opening database connection
                            System.out.println("Connecting to database...");
                            conn = DriverManager.getConnection(DB_URL, USER, PASS);

                            UsersService usersService = new UsersService(conn);
                            usersService.listUsersByRole(role);
                            // Closing database connection
                            conn.close();

                        } catch (SQLException se) {
                            se.printStackTrace(); // Handling errors from database
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace(); // Handling errors from JDBC driver
                        } finally {
                            // Cleaning-up environment
                            try {
                                if (conn != null) conn.close();
                            } catch (SQLException se) {
                                se.printStackTrace();
                            }
                        }
                    }
                    break;

                } case 2:{
                    System.out.println("Contando lista de veterinarias en el sistema");
                    try {

                        // Registering the JDBC driver
                        Class.forName(JDBC_DRIVER);

                        // Opening database connection
                        System.out.println("Connecting to database...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);

                        VetService vetService = new VetService(conn);
                        vetService.countVets();

                        // Closing database connection
                        conn.close();

                    } catch(SQLException se) {
                        se.printStackTrace(); // Handling errors from database
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace(); // Handling errors from JDBC driver
                    } finally {
                        // Cleaning-up environment
                        try {
                            if(conn != null) conn.close();
                        } catch(SQLException se) {
                            se.printStackTrace();
                        }
                    }
                    break;

                } case 3:{
                    System.out.println("Por favor ingres el ID de la mascota la cual desea consultar sus visitas");
                    int id= Integer.parseInt(leer.next());
                    try {

                        // Registering the JDBC driver
                        Class.forName(JDBC_DRIVER);

                        // Opening database connection
                        System.out.println("Connecting to database...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);

                        VisitService visitService= new VisitService(conn);
                        visitService.consultingByPet_Id(id);

                        // Closing database connection
                        conn.close();

                    } catch(SQLException se) {
                        se.printStackTrace(); // Handling errors from database
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace(); // Handling errors from JDBC driver
                    } finally {
                        // Cleaning-up environment
                        try {
                            if(conn != null) conn.close();
                        } catch(SQLException se) {
                            se.printStackTrace();
                        }
                    }
                    break;

                } case 4:{
                    System.out.println("Por favor dijite el ID de la mascota sobre la cual desea registrar el robo");
                    int pet_id= Integer.parseInt(leer.next());
                    System.out.println(pet_id);
                    System.out.println("Por favor ingrse el lugar donde sucedio el incidente");
                    String created_at= leer.next();
                    System.out.println(pet_id);
                    System.out.println("Por favor ingrese una breve descripcion de lo ocurrido ");
                    String description= leer.next();
                    System.out.println(pet_id);
                    try {

                        // Registering the JDBC driver
                        Class.forName(JDBC_DRIVER);

                        // Opening database connection
                        System.out.println("Connecting to database...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);

                        PetCaseService petCaseService =new PetCaseService(conn);
                        petCaseService.Insertpetcase(created_at,description,pet_id);

                        // Closing database connection
                        conn.close();

                    } catch(SQLException se) {
                        se.printStackTrace(); // Handling errors from database
                    } catch(ClassNotFoundException e) {
                        e.printStackTrace(); // Handling errors from JDBC driver
                    } finally {
                        // Cleaning-up environment
                        try {
                            if(conn != null) conn.close();
                        } catch(SQLException se) {
                            se.printStackTrace();
                        }
                    }
                    break;
                }
            }
        }while (opcion!=0);

    }
}
