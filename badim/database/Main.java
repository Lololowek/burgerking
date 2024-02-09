package badim.database;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        DBC dbc = new DBC();

        String query = "select * from data";
        try {
            Statement statement = dbc.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                System.out.print(id);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
