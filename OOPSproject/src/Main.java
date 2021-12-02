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
            String version = "2.3.0";

            switch (args[0]) { // flags

                case "-h": // help
                {
                    System.out.println(" -h: help");
                    System.out.println(" -c: create table ");
                    System.out.println(" -p: print all the columns");
                    System.out.println(" -i <input_file path> : insert data into the columns from csv");
                    System.out.println("-si <reg_no>: search by reg_no");
                    System.out.println("-sf <name>: search by first name");
                    System.out.println("-sal <age>: search for patients with age less than of equal to the given age");
                    System.out.println("-sag <age>: search for patients with age greater than of equal to the given age");
                    System.out.println("-ss <severity(low/mild/severe)>: search by severity");
                    System.out.println("-sr <recovered(true/false)>: search by recovery status");
                    System.out.println("-sv <vaccinated(true/false)>: search by vaccination status");
                    System.out.println("-cr : count of recovered patients");
                    System.out.println("-cv : count of vaccinated patients");
                    System.out.println("-statsev : statistics of severity");
                    System.out.println("-statage : statistics of age");
                    System.out.println("-classage : classification by age");
                    System.out.println("-v : Version");
                    System.out.println(" -q: quit");
                    break;
                }

                // CREATE THE TABLE
                case "-c": {
                    String[] columnName = new String[6];
                    columnName[0] = "reg_no";
                    columnName[1] = "name";
                    columnName[2] = "age";
                    columnName[3] = "severity";
                    columnName[4] = "recovered";
                    columnName[5] = "vaccinated";
                    String[] columnType = new String[6];
                    columnType[0] = "INT";
                    columnType[1] = "VARCHAR(255)";
                    columnType[2] = "INT";
                    columnType[3] = "VARCHAR(255)";
                    columnType[4] = "VARCHAR(255)";
                    columnType[5] = "VARCHAR(255)";

                    sql = "CREATE TABLE " + table_name + "(" + columnName[0] + " " + columnType[0] + " PRIMARY KEY,"
                            + columnName[1] + " " + columnType[1] + ","
                            + columnName[2] + " " + columnType[2] + ","
                            + columnName[3] + " " + columnType[3] + ","
                            + columnName[4] + " " + columnType[4] + ","
                            + columnName[5] + " " + columnType[5] + ")";
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
                        String query = "INSERT INTO oop_project.patients (reg_no,name,age,severity,recovered,vaccinated) VALUES ("
                                + tokens[0] + ",'" + tokens[1] + "'," + tokens[2] + ",'" + tokens[3] + "'," + tokens[4]
                                + "," + tokens[5]
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

                // SEARCH BY AGE LESS THAN OR EQUAL TO
                case "-sal": {
                    int age = Integer.parseInt(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchByAgeLessThan(table_name, age));
                    pagination(rs);
                    rs.close();
                    con.close();
                    break;
                }

                // SEARCH BY AGE GREATER THAN OR EQUAL TO
                case "-sag": {
                    int age = Integer.parseInt(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchByAgeGreaterThan(table_name, age));
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

                // COUNT OF RECOVERED PATIENTS
                case "-cr": {
                    ResultSet rs = stmt.executeQuery(op1.countRecovered(table_name));
                    rs.next();
                    System.out.println("Count of recovered patients: " + rs.getString(1));
                    rs.close();
                    break;
                }

                // COUNT OF VACCINATED PATIENTS
                case "-cv": {
                    ResultSet rs = stmt.executeQuery(op1.countVaccinated(table_name));
                    rs.next();
                    System.out.println("Count of vaccinated patients: " + rs.getInt(1));
                    rs.close();
                    break;
                }

                // STATISTICS OF SEVERITY
                case "-statsev": {
                    ResultSet rs = stmt.executeQuery(op1.statisticsOfSeverity(table_name));
                    System.out.println("\nSEVERITY   COUNT   MEAN AGE\n");
                    while (rs.next()) {
                        System.out.printf("%-10s %-7s %-5f\n", rs.getString(1), rs.getInt(2),rs.getFloat(3));
                    }
                    rs.close();
                    break;
                }

                // STATISTICS OF AGE
                case "-classage": {
                    ResultSet rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=20;");
                    System.out.println("\nAGE\tCOUNT\n");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "<=20", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=30&&age>20;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "20-30", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=40&&age>=30;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "30-40", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=50&&age>=40;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "40-50", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=60&&age>=50;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "50-60", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=70&&age>=60;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "60-70", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age<=80&&age>=70;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", "70-80", rs.getInt(1));
                    rs = stmt.executeQuery("SELECT count(age) FROM PATIENTS WHERE age>=80;");
                    rs.next();
                    System.out.printf("%-6s  %-5s\n", ">80", rs.getInt(1));
                    rs.close();
                    break;
                }

                // EXTRA STATISTICS OF AGE
                case "-statage":{
                    System.out.println("OVERALL STATS");
                    ResultSet rs = stmt.executeQuery("SELECT AVG(age) FROM PATIENTS;");
                    System.out.println("\nCOLUMN \t    VALUE\n");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MEAN", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT age,COUNT(age) FROM PATIENTS GROUP BY AGE ORDER BY COUNT(AGE) DESC LIMIT 1;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MODE", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT stddev(age) FROM PATIENTS;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "STD DEV", rs.getFloat(1));

                    System.out.println("\n\nSTATS OF VACCINATED PATIENTS");
                    rs = stmt.executeQuery("SELECT AVG(age) FROM PATIENTS WHERE VACCINATED=true;");
                    System.out.println("\nCOLUMN \t    VALUE\n");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MEAN", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT age,COUNT(age) FROM PATIENTS WHERE VACCINATED=true GROUP BY AGE ORDER BY COUNT(AGE) DESC LIMIT 1;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MODE", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT stddev(age) FROM PATIENTS WHERE VACCINATED=true;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "STD DEV", rs.getFloat(1));
                    
                    System.out.println("\n\nSTATS OF RECOVERED PATIENTS");
                    rs = stmt.executeQuery("SELECT AVG(age) FROM PATIENTS WHERE RECOVERED=true;");
                    System.out.println("\nCOLUMN \t    VALUE\n");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MEAN", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT age,COUNT(age) FROM PATIENTS WHERE RECOVERED=true GROUP BY AGE ORDER BY COUNT(AGE) DESC LIMIT 1;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "MODE", rs.getFloat(1));
                    rs = stmt.executeQuery("SELECT stddev(age) FROM PATIENTS WHERE RECOVERED=true;");
                    rs.next();
                    System.out.printf("%-10s  %-5f\n", "STD DEV", rs.getFloat(1));
                    break;
                }

                // VERSION
                case "-v":
                {
                    System.out.println("\nVersion: " + version);
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
            rs.first();
            rs.previous();

            int total_pages = total_records / 5;
            if (total_records % 5 != 0)
                total_pages++;
            int page_no = 1;

            System.out.printf("REG_NO  NAME:%-8s AGE   SEVERITY RECOVERED VACCINATED \n", "");
            while (page_no <= total_pages) {

                if (page_no >= 1) {
                    int i = 0;
                    while (i < 5 && rs.next()) {
                        System.out.printf("%-7s %-13s %-5s %-8s %-9s %-9s\n", rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getBoolean(5), rs.getBoolean(6));
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