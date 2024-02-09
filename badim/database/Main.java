package badim.database;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        DBC dbc = new DBC();

        String query = "INSERT INTO data (login,password) login,password";
        String query1 = "select * from data";
        try {
            Statement statement = dbc.getConnection().createStatement();
            statement.executeQuery(query);
            ResultSet resultSet1 = statement.executeQuery(query1);

            while (resultSet1.next()){
                resultSet1.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
