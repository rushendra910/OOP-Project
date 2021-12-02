package OOPSproject.src;

public interface Operations {
    public String printTable(String tableName);

    public String searchById(String tableName, int id);

    public String searchByFirstName(String tableName, String firstName);

    public String searchByAgeLessThan(String tableName, int age);

    public String searchByAgeGreaterThan(String tableName, int age);

    public String searchBySeverity(String tableName, String severity);

    public String searchByRecovery(String tableName, Boolean recovered);

    public String searchByVaccination(String tableName, Boolean vaccinated);

    public String countRecovered(String tableName);

    public String countVaccinated(String tableName);

    public String statisticsOfSeverity(String tableName);

    public String deleteById(String tableName, int id);
}
