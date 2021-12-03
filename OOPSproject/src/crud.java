package OOPSproject.src;

import java.sql.*;
import java.util.*;

public class crud {

    public static void insert(String path) {

        Connection con = sqlConnect.getCon();
        Statement st = sqlConnect.getStatement();

        List<patientRecord> t = patientRecord.CSVToTable(path); // args[1] is the path of the csv file
        ListIterator<patientRecord> i = t.listIterator();
        try {
            st = con.createStatement();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        while (i.hasNext()) {
            String[] tokens = i.next().getValues();
            String query = "INSERT INTO oop_project.patients (reg_no,name,age,severity,recovered,vaccinated) VALUES ("
                    + tokens[0] + ",'" + tokens[1] + "'," + tokens[2] + ",'" + tokens[3] + "'," + tokens[4]
                    + "," + tokens[5]
                    + ")";
            try {
                st.executeUpdate(query);
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Data inserted successfully");
        try {
            st.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(String path) {

        // Connection con = sqlConnect.getCon();
        Statement s = sqlConnect.getStatement();

        String query;

        ResultSet ans = null;
        List<patientRecord> t = patientRecord.CSVToTable(path);

        ListIterator<patientRecord> i = t.listIterator();

        while (i.hasNext()) {

            String[] tokens = i.next().getValues();

            query = "SELECT * FROM PATIENTS WHERE REG_NO = " + tokens[0];

            try {
                ans = s.executeQuery(query);
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

            try {

                if (ans.next()) {
                    query = "UPDATE PATIENTS SET NAME = \'" + tokens[1] + "\'" + ", AGE = \'" + tokens[2] + "\'"
                            + ", SEVERITY = \'" + tokens[3] + "\', RECOVERED = " + tokens[4] + ", VACCINATED = "
                            + tokens[5] + " WHERE REG_NO = " + tokens[0];
                    s.executeUpdate(query);
                }

                else {
                    query = "INSERT INTO PATIENTS VALUES(" + tokens[0] + ",\'" + tokens[1] + "\'" + ",\'" + tokens[2]
                            + "\'" + ",\'" + tokens[3] + "\'," + tokens[4] + "," + tokens[5] + ")";
                    s.executeUpdate(query);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

        }

        try {
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nDatabase updated successfully!");

    }

    public static void delete(int id) throws SQLException {

        Connection con = sqlConnect.getCon();
        Statement stmt = sqlConnect.getStatement();

        Operations_implementation o = new Operations_implementation();
        ResultSet rs = stmt.executeQuery(o.searchById("patients", id));
        rs.next();

        if (rs.getRow() == 0) {
            System.out.println("\nNo such patient with id " + id);
        }

        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nAre you sure you want to delete this patient? (y/n)");
            String ans = sc.nextLine();
            if (ans.equals("y")) {
                rs.deleteRow();
                System.out.println("\nPatient with id " + id + " deleted");
            } else {
                System.out.println("\nPatient with id " + id + " not deleted");
            }
            sc.close();
        }

        rs.close();
        con.close();

    }

    public static void deleteAll() {

        Connection con = sqlConnect.getCon();
        Statement stmt = sqlConnect.getStatement();

        try {
            stmt.executeUpdate("DELETE FROM PATIENTS");
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nAll patients deleted");
        try {
            stmt.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
