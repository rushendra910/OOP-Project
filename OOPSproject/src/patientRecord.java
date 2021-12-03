package OOPSproject.src;

import java.io.*;
import java.util.*;

public class patientRecord{
    private int id;
    private String name;
    private int age;
    private String severity;
    private boolean recovered;
    private boolean vaccinated;

    //Table Constructor
    public patientRecord(int id,String name,int age,String severity,boolean recovered,boolean vaccinated){
        this.id=id;
        this.name=name;
        this.age=age;
        this.severity=severity;
        this.recovered=recovered;
        this.vaccinated=vaccinated;
    }

    //Utility get functions
    public int getId(){return id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getSeverity(){return severity;}
    public boolean getRecovered(){return recovered;}
    public boolean getVaccinated(){return vaccinated;}

    //toString() utility function
    @Override
    public String toString(){
        String str=getId()+","+getName()+","+getAge()+","+getSeverity()+","+this.getRecovered()+","+this.getVaccinated();
        return str;
    }

    public String[] getValues(){
        String[] tokens=toString().split(",");
        return tokens;
    }

    //Method to convert any CSV file to a Table array
    public static List<patientRecord> CSVToTable(String filePath){
        List<patientRecord> t=new ArrayList<>();
        try{
            Scanner sin=new Scanner(new BufferedReader(new FileReader(filePath)));
            while(sin.hasNext()){
                String[] tokens=sin.nextLine().split(",");
                t.add(new patientRecord(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),tokens[3],Boolean.parseBoolean(tokens[4]),Boolean.parseBoolean(tokens[5])));
            }
            
            sin.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        return t;
    }
}