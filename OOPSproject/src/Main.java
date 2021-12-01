package OOPSproject.src;

import java.io.File;
import java.sql.*;
import java.util.*;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/OOP_project"; // Database URL
    static final String USER = "root"; // Database Username

    /********************** CHANGE PASSWORD **********************/
    static final String PASS = "password"; // Database Password

    public static void clr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Operations_implementation op1 = new Operations_implementation();
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS); // connection
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // statement
            String sql; // sql query
            String table_name = "PATIENTS";

            switch (args[0]) { // flags

                case "-h": // help
                {
                    System.out.println(" -h: help");
                    System.out.println(" -c: create table ");
                    System.out.println(" -p: print all the columns");
                    System.out.println(" -i <input_file path> : insert data into the columns from csv");
                    System.out.println("-si <reg_no>: search by reg_no");
                    System.out.println("-sf <name>: search by first name");
                    System.out.println("-ss <severity(weak/mild/strong)>: search by severity");
                    System.out.println("-sr <recovered(true/false)>: search by recovery status");
                    System.out.println("-sv <vaccinated(true/false)>: search by vaccination status");
                    System.out.println(" -q: quit");
                    break;
                }

                // CREATE THE TABLE
                case "-c": {
                    String[] columnName = new String[5];
                    columnName[0] = "reg_no";
                    columnName[1] = "name";
                    columnName[2] = "severity";
                    columnName[3] = "recovered";
                    columnName[4] = "vaccinated";
                    String[] columnType = new String[5];
                    columnType[0] = "INT";
                    columnType[1] = "VARCHAR(255)";
                    columnType[2] = "VARCHAR(255)";
                    columnType[3] = "VARCHAR(255)";
                    columnType[4] = "VARCHAR(255)";

                    sql = "CREATE TABLE " + table_name + "(" + columnName[0] + " " + columnType[0] + " PRIMARY KEY,"
                            + columnName[1] + " " + columnType[1] + ","
                            + columnName[2] + " " + columnType[2] + ","
                            + columnName[3] + " " + columnType[3] + ","
                            + columnName[4] + " " + columnType[4] + ")";
                    stmt.executeUpdate(sql);
                    System.out.println("Table created successfully");
                    break;
                }

                // PRINT THE TABLE
                case "-p": {

                    ResultSet rs = stmt.executeQuery(op1.printTable(table_name));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // INSERT DATA INTO THE TABLE FROM CSV FILE
                case "-i": {
                    List<Table> t = Table
                            .CSVToTable(args[1]); // args[1] is the path of the csv file 
                    ListIterator<Table> i = t.listIterator();
                    Statement st = con.createStatement();
                    while (i.hasNext()) {
                        String[] tokens = i.next().getValues();
                        String query = "INSERT INTO oop_project.patients (reg_no,name,severity,recovered,vaccinated) VALUES ("
                                + tokens[0] + ",'" + tokens[1] + "','" + tokens[2] + "'," + tokens[3] + "," + tokens[4]
                                + ")";
                        st.executeUpdate(query);
                    }
                    System.out.println("Data inserted successfully");
                    st.close();
                    break;
                }

                // SEARCH BY ID
                case "-si": {
                    int id = Integer.parseInt(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchById(table_name, id));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // SEARCH BY FIRST NAME
                case "-sf": {
                    String firstName = args[1];
                    ResultSet rs = stmt.executeQuery(op1.searchByFirstName(table_name, firstName));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // SEARCH BY SEVERITY
                case "-ss": {
                    String severity = args[1];
                    ResultSet rs = stmt.executeQuery(op1.searchBySeverity(table_name, severity));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // SEARCH BY RECOVERY STATUS
                case "-sr": {
                    Boolean recovery = Boolean.parseBoolean(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchByRecovery(table_name, recovery));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // SEARCH BY VACCINATION STATUS
                case "-sv": {
                    Boolean vaccination = Boolean.parseBoolean(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchByVaccination(table_name, vaccination));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // EXIT
                case "-q": {
                    break;
                }
                // end of search part
                default: {
                    System.out.println("Invalid input");
                    break;
                }

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void pagination(ResultSet rs) {
        try {

            rs.last();
            int total_records = rs.getRow();
            int total_pages = total_records / 5;
            if (total_records % 5 != 0)
                total_pages++;
            int page_no = 1;
            rs.first();

            System.out.printf("REG_NO  NAME:%-10s SEVERITY RECOVERED VACCINATED \n","");
            while (page_no <= total_pages) {

                if (page_no >= 1) {
                    int i = 0;
                    while (i < 5 && rs.next()) {
                        System.out.printf("%-7s %-15s %-8s %-9s %-9s\n", rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getBoolean(4), rs.getBoolean(5));
                        i++;
                    }
                    System.out.println("Page " + page_no);
                    String inp = System.console().readLine();

                    if (inp.equals("+")) {
                        clr();
                        page_no++;
                    }

                    else if (inp.equals("-")) {
                        clr();
                        page_no--;
                        rs.absolute(rs.getRow() - 5);
                    }

                    else if (inp.equals("q"))
                        break;
                    else
                        System.out.println("Invalid input");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}