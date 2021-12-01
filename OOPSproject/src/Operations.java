package OOPSproject.src;
interface Operations
{
    public String createTable(String tableName, String[] columnNames, String[] columnTypes);
    public String printTable(String tableName);
}
class Operations_implementation implements Operations
{
    @Override
    public String createTable(String tableName, String[] columnNames, String[] columnTypes) {
        String query = "CREATE TABLE " + tableName + "(";
        for (int i = 0; i < columnNames.length; i++) {
            query += columnNames[i] + " " + columnTypes[i];
            if (i != columnNames.length - 1) {
                query += ",";
            }
        }
        query += ")";
        return query;
    }
    public String printTable(String tableName) {
        String query = "SELECT * FROM " + tableName;
        return query;
    }
    //for search part
    public String searchById(String tableName, int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id = " + id;
        return query;
    }
    public String searchByFirstName(String tableName, String firstName) {
        String query = "SELECT * FROM " + tableName + " WHERE first LIKE '%" + firstName + "%'";
        return query;
    }
    public String searchByAge(String tableName, int age) {
        String query = "SELECT * FROM " + tableName + " WHERE age = " + age;
        return query;
    }
//for deleting part
    public String  deleteById(String tableName, int id) {
        String query = "DELETE * FROM " + tableName + "WHERE ID =" + id;
        if (id > 0)           
                System.out.println("One User is Successfully Deleted");           
            else
                System.out.println("ERROR OCCURED :(");
        return query;
    }
}

