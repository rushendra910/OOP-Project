package OOPSproject.src;

import java.io.*;
import java.util.*;

class Table{
    private int id;
    private String name;
    private String severity;
    private boolean recovered;
    private boolean vaccinated;

    //Table Constructor
    public Table(int id,String name,String severity,boolean recovered,boolean vaccinated){
        this.id=id;
        this.name=name;
        this.severity=severity;
        this.recovered=recovered;
        this.vaccinated=vaccinated;
    }

    //Utility get functions
    public int getId(){return id;}
    public String getName(){return name;}
    public String getSeverity(){return severity;}
    public boolean getRecovered(){return recovered;}
    public boolean getVaccinated(){return vaccinated;}

    //toString() utility function
    @Override
    public String toString(){
        String str=getId()+","+getName()+","+getSeverity()+","+this.getRecovered()+","+this.getVaccinated();
        return str;
    }

    public String[] getValues(){
        String[] tokens=toString().split(",");
        return tokens;
    }

    //Method to convert any CSV file to a Table array
    public static List<Table> CSVToTable(String filePath){
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