package OOPSproject.src;

public interface Operations {
    
    String printTable(String tableName); 
    
    String searchById(String tableName, int id); 
    
    String searchByFirstName(String tableName, String firstName); 
    
    String searchByAgeLessThan(String tableName, int age); 
    
    String searchByAgeGreaterThan(String tableName, int age); 
    
    String searchBySeverity(String tableName, String severity); 
    
    String searchByRecovery(String tableName, Boolean recovered); 
    
    String searchByVaccination(String tableName, Boolean vaccinated); 
    
    String countRecovered(String tableName); 
    
    String countVaccinated(String tableName); 
    
    String statisticsOfSeverity(String tableName); 
    
    String deleteById(String tableName, int id);
}
