package OOPSproject.src;

interface Operations {
    public String printTable(String tableName);

    public String searchById(String tableName, int id);

    public String searchByFirstName(String tableName, String firstName);

    public String searchBySeverity(String tableName, String severity);

    public String searchByRecovery(String tableName, Boolean recovered);

    public String searchByVaccination(String tableName, Boolean vaccinated);

    public String countRecovered(String tableName);

    public String countVaccinated(String tableName);

    public String deleteById(String tableName, int id);
}

class Operations_implementation implements Operations {
    @Override
    public String printTable(String tableName) {
        String query = "SELECT * FROM " + tableName;
        return query;
    }

    // for search part
    @Override
    public String searchById(String tableName, int id) {
        String query = "SELECT * FROM " + tableName + " WHERE reg_no = " + id;
        return query;
    }

    @Override
    public String searchByFirstName(String tableName, String firstName) {
        String query = "SELECT * FROM " + tableName + " WHERE NAME LIKE '%" + firstName + "%' ORDER BY reg_no ASC";
        return query;
    }

    @Override
    public String searchBySeverity(String tableName, String severity) {
        String query = "SELECT * FROM " + tableName + " WHERE severity =\"" + severity + "\" ORDER BY reg_no ASC";
        return query;
    }

    @Override
    public String searchByRecovery(String tableName, Boolean recovered) {
        String query = "SELECT * FROM " + tableName + " WHERE recovered =" + recovered + " ORDER BY reg_no ASC";
        return query;
    }

    @Override
    public String searchByVaccination(String tableName, Boolean vaccinated) {
        String query = "SELECT * FROM " + tableName + " WHERE vaccinated =" + vaccinated + " ORDER BY reg_no ASC";
        return query;
    }

    @Override
    public String countRecovered(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE recovered = true";
        return query;
    }

    @Override
    public String countVaccinated(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE vaccinated = true";
        return query;
    }


    // for deleting part
    @Override
    public String deleteById(String tableName, int id) {
        String query = "DELETE * FROM " + tableName + "WHERE ID =" + id;
        if (id > 0)
            System.out.println("One User is Successfully Deleted");
        else
            System.out.println("ERROR OCCURED :(");
        return query;
    }
}
