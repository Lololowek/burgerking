package badim.database;
import java.sql.*;

public class DBC {
    private final String HOST = "jdbc:mysql://localhost:3306/autorization";
    private final String NAME = "nadim";
    private final String PASSWORD = "root";

    private Connection connection;

    public DBC(){
        try{
            connection = DriverManager.getConnection(HOST, NAME, PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
