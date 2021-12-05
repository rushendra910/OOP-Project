package OOPSproject.src;

import java.sql.*;

public class printMessages {

    static Connection con = sqlConnect.getCon();
    static Statement stmt = sqlConnect.getStatement();
    
    public static void help(){
        System.out.println(" -h: help");
        //System.out.println(" -c: create table ");
        System.out.println(" -p: print all the columns");
        System.out.println(" -i <input_file path> : insert data into the columns from csv");
        System.out.println(" -u <input_file path> : update data into the columns from csv");
        System.out.println(" -si <reg_no>: search by reg_no");
        System.out.println(" -sf <name>: search by first name");
        System.out.println(" -sal <age>: search for patients with age less than of equal to the given age");
        System.out.println(" -sag <age>: search for patients with age greater than of equal to the given age");
        System.out.println(" -ss <severity(low/mild/severe)>: search by severity");
        System.out.println(" -sr <recovered(true/false)>: search by recovery status");
        System.out.println(" -sv <vaccinated(true/false)>: search by vaccination status");
        System.out.println(" -cr : count of recovered patients");
        System.out.println(" -cv : count of vaccinated patients");
        System.out.println(" -statsev : statistics of severity");
        System.out.println(" -statage : statistics of age");
        System.out.println(" -classage : classification by age");
        System.out.println(" -v : Version");
        System.out.println(" -d <id>: Delete by ID");
        System.out.println(" -dall : Delete all");
        System.out.println(" -q: quit");
    }

    public static void Table(String query) throws SQLException{
        ResultSet rs = stmt.executeQuery(query);
        CovidDataTracker.pagination(rs);
        rs.close();
        con.close();
    }

    public static void ageStatistics() throws SQLException{

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
    }

    public static void extraAgeStatistics() throws SQLException{
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
        System.out.println("\n\nSTATS OF NON-VACCINATED PATIENTS");
        rs = stmt.executeQuery("SELECT AVG(age) FROM PATIENTS WHERE VACCINATED=false;");
        System.out.println("\nCOLUMN \t    VALUE\n");
        rs.next();
        System.out.printf("%-10s  %-5f\n", "MEAN", rs.getFloat(1));
        rs = stmt.executeQuery("SELECT age,COUNT(age) FROM PATIENTS WHERE VACCINATED=false GROUP BY AGE ORDER BY COUNT(AGE) DESC LIMIT 1;");
        rs.next();
        System.out.printf("%-10s  %-5f\n", "MODE", rs.getFloat(1));
        rs = stmt.executeQuery("SELECT stddev(age) FROM PATIENTS WHERE VACCINATED=false;");
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
        System.out.println("\n\nSTATS OF NON-RECOVERED PATIENTS");
        rs = stmt.executeQuery("SELECT AVG(age) FROM PATIENTS WHERE RECOVERED=false;");
        System.out.println("\nCOLUMN \t    VALUE\n");
        rs.next();
        System.out.printf("%-10s  %-5f\n", "MEAN", rs.getFloat(1));
        rs = stmt.executeQuery("SELECT age,COUNT(age) FROM PATIENTS WHERE RECOVERED=false GROUP BY AGE ORDER BY COUNT(AGE) DESC LIMIT 1;");
        rs.next();
        System.out.printf("%-10s  %-5f\n", "MODE", rs.getFloat(1));
        rs = stmt.executeQuery("SELECT stddev(age) FROM PATIENTS WHERE RECOVERED=false;");
        rs.next();
        System.out.printf("%-10s  %-5f\n", "STD DEV", rs.getFloat(1));
    }


}   
