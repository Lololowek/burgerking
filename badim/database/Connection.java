package badim.database;
import java.sql.*;

public class Connection {
    private final String HOST = "jdbc:mysql://localhost:3306/autorization";
    private final String NAME = "nadim";
    private final String PASSWORD = "root";



    private java.sql.Connection connection;

    public Connection(){
        try{
            connection = DriverManager.getConnection(HOST, NAME, PASSWORD);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
}