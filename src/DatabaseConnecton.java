import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecton {

    //static variables for the database connection as it's same for the whole class
    private static String host = "jdbc:mysql://localhost:3306/employees";
    private static String uName = "root";
    private static String password = "root";

    //connection to the server
    Connection con;

    {
        try {
            con = DriverManager.getConnection(host, uName, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
