package OOPSproject.src;

import java.sql.*;

public class sqlConnect {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/OOP_project"; // Database URL
    static final String USER = "root"; // Database Username

    /********************** CHANGE PASSWORD **********************/
    static final String PASS = "password"; // Database Password

    public static void clr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static Connection getCon() {

        Connection con = null;

        try{
            con = DriverManager.getConnection(DB_URL, USER, PASS); // connection
        } 
        
        catch(SQLException s){
            System.out.println("Failed to connect to DATABASE");
        }

        return con;
    }

    public static Statement getStatement() {

        Statement stmt = null;

        try{
            Connection con = getCon();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // statement
        } 
        
        catch(SQLException s){
            System.out.println(s);
        }

        return stmt;
    }
    
}
