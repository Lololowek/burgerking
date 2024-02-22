package badim.database.functions;

import badim.database.Connection;

import java.util.Scanner;
import java.sql.*;

public class wishlist {
    public static boolean wishl;
    public void Wish(Scanner scanner) throws SQLException {
        scanner.nextLine();
        wishl = true;
        Connection connection = new Connection();
        System.out.print("Wishlist\n");
        System.out.print("1) Посмотреть список\n");
        System.out.print("2) Добавить по id\n");
        System.out.print("3) Удалить из списка по id\n");
        System.out.print("4) Вернуться\n");
        int choice = scanner.nextInt();
        while(wishl){
            switch(choice) {
                case 1:
                    print(connection);
                    break;
                case 2:
                    System.out.print("id: ");
                    int id = scanner.nextInt();
                    findwishlist(connection, id);
                    break;
                case 3:
                    new library().Lib(scanner);
                    break;
                case 4:
                    new watched().Watched(scanner);
                    break;
            }
        }
    }
    private static void print(Connection connection) throws SQLException {

        String query = "SELECT * FROM wishlist";
        try (Statement st = connection.getConnection().createStatement();
             ResultSet resultSet = st.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("fid") +
                        ", Название: " + resultSet.getString("name") +
                        ", Автор: " + resultSet.getString("author") +
                        ", Год: " + resultSet.getInt("year"));
            }
        }
    }
    private static void findwishlist(Connection connection, int fid) throws SQLException {
        String query = "SELECT * FROM wishlist WHERE fid = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, fid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("fid") +
                            ", Название: " + resultSet.getString("name") +
                            ", Автор: " + resultSet.getString("author") +
                            ", Год: " + resultSet.getBoolean("year"));
                } else {
                    System.out.println("Книга с ID " + fid + " не найдена.");
                }
            }
        }
    }
}
