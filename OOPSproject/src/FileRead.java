/*
NOTE: 
This is just a prototype of the final Table class
Need to create table class with proper columns
*/

package OOPSproject.src;

import java.io.*;
import java.util.*;

class Table{
    //Column Names(We can change these as required)
    private int id;
    private String name;
    private String severity;
    private Boolean recovered;
    private Boolean vaccinated;

    //Table Constructor
    public Table(int id,String name,String severity,Boolean recovered,Boolean vaccinated){
        this.id=id;
        this.name=name;
        this.severity=severity;
        this.recovered=recovered;
        this.vaccinated=vaccinated;
    }

    //toString() utility function
    @Override
    public String toString(){
        String str="Id: "+this.id+"\nName: "+this.name+"\nSeverity: "+this.severity+"\nRecovered: "+this.recovered+"\nVaccinated: "+this.vaccinated;
        return str;
    }

    //Method to convert any CSV file to a Table array
    public List<Table> CSVToTable(String filePath){
        List<Table> t=new ArrayList<>();
        try{
            Scanner sin=new Scanner(new BufferedReader(new FileReader(filePath)));
            while(sin.hasNext()){
                String[] tokens=sin.nextLine().split(",");
                t.add(new Table(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Boolean.parseBoolean(tokens[3]),Boolean.parseBoolean(tokens[4])));
            }
            sin.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        return t;
    }
}