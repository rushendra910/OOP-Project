package OOPSproject.src;

import java.sql.*;
import java.util.*;
import OOPSproject.src.Operations;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/OOP_project"; // Database URL
    static final String USER = "root"; // Database Username

//////////////////////////////////Change PassWord
    static final String PASS = "password"; // Database Password
////////////////////////////////////////////////////////////
    public static void clr(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
    }
    
    public static void main(String[] args) {
        Operations_implementation op1 = new Operations_implementation();
        try {

            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);  // connection
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);  // statement
            String sql; // sql query

            switch (args[0]) { // flags

            case "-h": // help
            {
                System.out.println(" -h: help");
                System.out.println(" -c: create table ");
                System.out.println(" -p: print all the columns");
                System.out.println(" -i: insert data into the columns");
                System.out.println(" -q: quit");
                //for search part
                System.out.println("-si: search by id");
                System.out.println("-sf: search by first name");
                System.out.println("-sa: search by age");
                //end of search part
                break;
            }

            case "-c": // create
            {

                String[] columnName = new String[4];
                String[] columnType = new String[4];
                columnName[0] = "id";
                columnName[1] = "first";
                columnName[2] = "last";
                columnName[3] = "age";
                columnType[0] = "INTEGER";
                columnType[1] = "VARCHAR(255)";
                columnType[2] = "VARCHAR(255)";
                columnType[3] = "INTEGER";

                sql = op1.createTable("REGISTRATION", columnName, columnType);

                stmt.executeUpdate(sql);
                System.out.println("Table created successfully");
                break;
            }

            case "-p": {

                ResultSet rs = stmt.executeQuery(op1.printTable("REGISTRATION"));
                pagination(rs);
                rs.close();
                con.close();

                break;
            }

            case "-i": {
                List<Table> t=Table.CSVToTable(args[1]);
                ListIterator<Table> i=t.listIterator();
                Statement st=con.createStatement();
                while(i.hasNext()){
                    String[] tokens=i.next().getValues();
                    String query="INSERT into PATIENTS values("+tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+tokens[4]+")";
                    st.executeQuery(query);
                }
                System.out.println("Data inserted successfully");
                st.close();
                break;
            }

            case "-q": {
                break;
            }
//search part
            case "-si": {
                int id = Integer.parseInt(args[1]);
                ResultSet rs = stmt.executeQuery(op1.searchById("registration",id));
                pagination(rs);
                rs.close();
                con.close();
                break;
            }

            case "-sf": {
                String firstName = args[1];
                ResultSet rs = stmt.executeQuery(op1.searchByFirstName("registration",firstName));
                pagination(rs);
                rs.close();
                con.close();
                break;
            }

            case "-sa": {
                int age = Integer.parseInt(args[1]);
                ResultSet rs = stmt.executeQuery(op1.searchByAge("registration",age));
                pagination(rs);
                rs.close();
                con.close();
                break;
            }
//end of search part
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

    public static void pagination(ResultSet rs){
        try{

                rs.last();
                int total_records = rs.getRow();
                int total_pages = total_records/5;
                if(total_records%5!=0)
                     total_pages++;
                int page_no = 1;
                rs.first();
            
                while(page_no<=total_pages){
                    
                    if(page_no>=1){
                        System.out.println("Page "+page_no);
                        int i=0;

                        while(i<5 && rs.next()){
                            System.out.println("ID = " + rs.getInt(1) + ", First = " + rs.getString(2) + ", Last = "
                            + rs.getString(3) + ", Age = " + rs.getInt(4));
                            i++;
                        }
                        
                        String inp = System.console().readLine();

                        if(inp.equals("+")){
                            clr();
                            page_no++;
                        }

                        else if(inp.equals("-")){
                            clr(); 
                            page_no--;
                            rs.absolute(rs.getRow()-5);
                        }

                        else if(inp.equals("q"))
                            break;
                        else
                            System.out.println("Invalid input");
                    }

                }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}