package OOPSproject.src;

import java.sql.*;

public class CovidPatientManagementSystem {

    public static void clr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Operations_implementation op1 = new Operations_implementation();

        try {

            /******************** TO CHANGE THE USERNAME AND PASSWORD sqlConnect ***********************/

            Connection con = sqlConnect.getCon();
            Statement stmt = sqlConnect.getStatement();

            
            String table_name = "PATIENTS";
            String version = "2.3.0";

            switch (args[0]) { // flags

                case "-h": // help
                {
                    printMessages.help();
                    break;
                }

                // PRINT THE TABLE
                case "-p": {
                    
                    String query = op1.printTable(table_name);
                    printMessages.Table(query);
                    break;
                }

                // INSERT DATA INTO THE TABLE FROM CSV FILE
                case "-i": {

                    String path = args[1];
                    crud.insert(path);
                    break;
                }

                case "-u": {

                    String path = args[1];
                    crud.update(path);

                    break;
                }

                // SEARCH BY ID
                case "-si": {
                    int id = Integer.parseInt(args[1]);
                    ResultSet rs = stmt.executeQuery(op1.searchById(table_name, id));
                    // pagination(rs);
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
                        System.out.printf("%-10s %-7s %-5f\n", rs.getString(1), rs.getInt(2), rs.getFloat(3));
                    }
                    rs.close();
                    break;
                }

                // STATISTICS OF AGE
                case "-classage": {
                    
                    printMessages.ageStatistics();
                    break;
                }

                // EXTRA STATISTICS OF AGE
                case "-statage": {
                    printMessages.extraAgeStatistics();
                    break;
                }

                // VERSION
                case "-v": {
                    System.out.println("\nVersion: " + version);
                    break;
                }

                // Delete by Id
                case "-d": {
                    int id = Integer.parseInt(args[1]);

                    crud.delete(id);
                    break;
                }

                // Delete complete data
                case "-dall": {
                    crud.deleteAll();
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
        } 
        
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void pagination(ResultSet rs) {
        try {

            rs.last();
            int total_records = rs.getRow();
            rs.beforeFirst();   

            int total_pages = total_records / 5;
            if (total_records % 5 != 0)
                total_pages++;
            int page_no = 1;

            while (page_no <= total_pages) {

                if (page_no >= 1) {
                    int i = 0;
                    while (i < 5 && rs.next()) {
                        if(i==0)
                            System.out.printf("REG_NO  NAME:%-8s AGE   SEVERITY RECOVERED VACCINATED \n", "");
                        System.out.printf("%-7s %-13s %-5s %-8s %-9s %-9s\n", rs.getInt(1), rs.getString(2),
                                rs.getInt(3), rs.getString(4),
                                rs.getBoolean(5), rs.getBoolean(6));
                        i++;
                    }
                    System.out.println("press \"+\" for next page");
                    System.out.println("press \"-\" for previous page");
                    System.out.println("press \"q\" to exit");
                    System.out.println("Page " + page_no);
                    String inp = System.console().readLine();

                    if (inp.equals("+")) {
                        clr();
                        page_no++;
                    }

                    else if (inp.equals("-")) {
                        if(page_no>1){
                            clr();
                            page_no--;
                            rs.absolute(page_no*5-5);
                        }
                        else{
                            clr();
                            System.out.println("You are on the first page");
                            rs.absolute(rs.getRow()-5);
                        }
                        
                    }
                    else if (inp.equals("q"))
                        break;
                    else {
                        clr();
                        System.out.println("Invalid input");
                        rs.absolute(rs.getRow()-5);
                    }
                }

            }

        } 
        
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
