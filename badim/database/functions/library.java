package badim.database.functions;

import badim.database.Connection;
import badim.database.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class library {
    private static boolean lib;
    public void Lib(Scanner scanner) throws SQLException {
        scanner.nextLine();
        lib = true;
        int fid;
        while(lib){
            Connection connection = new Connection();
            System.out.print("Смотреть\n");
            System.out.print("1) Посмотреть список\n");
            System.out.print("2) Вернуться\n");;
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    print(connection);
                    break;
                case 2:
                    lib = false;
                    break;
            }
        }
    }
    private static void print(Connection connection) throws SQLException {
        String query = "SELECT * FROM films";
        try (Statement statement = connection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while(resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("fid") +
                        ", Название: " + resultSet.getString("name") +
                        ", Автор: " + resultSet.getString("author") +
                        ", Год: " + resultSet.getInt("year"));
            }
        }
    }
}
