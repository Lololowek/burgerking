package badim.database.functions;

import badim.database.Connection;
import badim.database.Main;

import java.util.Scanner;
import java.sql.*;

public class wishlist {
    public void Wish(Scanner scanner) throws SQLException {
        boolean wishl = true;
        scanner.nextLine();
        int fid; String name;
        while(wishl){
        Connection connection = new Connection();
        System.out.print("Wishlist\n");
        System.out.print("1) Посмотреть список\n");
        System.out.print("2) Добавить по id\n");
        System.out.print("3) Добавить по названию\n");
        System.out.print("4) Удалить из списка по id\n");
        System.out.print("5) Удалить из списка по названию\n");
        System.out.print("6) Вернуться\n");
        int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    print(connection);
                    break;
                case 2:
                    System.out.print("id: ");
                    fid = scanner.nextInt();
                    add(connection, fid);
                    break;
                case 3:
                    System.out.print("name: \n");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    printname(connection,name, choice);
                    System.out.print("id: ");
                    fid = scanner.nextInt();
                    add(connection, fid);
                    break;
                case 4:
                    System.out.print("id: ");
                    fid = scanner.nextInt();
                    delete(connection,fid);
                    break;
                case 5:
                    System.out.print("name: \n");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    printname(connection,name, choice);
                    fid = scanner.nextInt();
                    delete(connection,fid);
                    break;
                case 6:
                    wishl = false;
            }
        }
    }
    private static void print(Connection connection) throws SQLException {
        String query = "SELECT * FROM wishlist where uid = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, Main.token);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    System.out.println("\nID: " + resultSet.getInt("fid") +
                            ", Название: " + resultSet.getString("name") +
                            ", Автор: " + resultSet.getString("author") +
                            ", Год: " + resultSet.getInt("year"));
                }
            }
        }
    }

    private static void printname(Connection connection, String name, int choice) throws SQLException {
        switch(choice) {
            case 3:
                String query3 = "SELECT * FROM films where name = ?";
                try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query3)) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            System.out.println("\nID: " + resultSet.getInt("fid") +
                                    ", Название: " + resultSet.getString("name") +
                                    ", Автор: " + resultSet.getString("author") +
                                    ", Год: " + resultSet.getInt("year"));
                        }
                    }
                }
            case 5:
                String query5 = "SELECT * FROM wishlist where name = ?";
                try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query5)) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            System.out.println("\nID: " + resultSet.getInt("fid") +
                                    ", Название: " + resultSet.getString("name") +
                                    ", Автор: " + resultSet.getString("author") +
                                    ", Год: " + resultSet.getInt("year"));
                        }
                    }
                }
        }
    }


    private static void add(Connection connection, int fid) throws SQLException {
        String name = null;String author = null; int year = 0;
        String query1 = "SELECT * FROM films WHERE fid = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query1)) {
            preparedStatement.setInt(1, fid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                    author = resultSet.getString("author");
                    year = resultSet.getInt("year");
                }
                else
                    System.out.println("Не найдено фильмов с таким ID");
            }
        }
        catch(SQLException e){
            System.out.println("Ошибка при добавлении книги: " + e.getMessage());
            e.printStackTrace();
        }
        String query2 = "INSERT INTO wishlist (fid, name, author, year, uid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.getConnection().prepareStatement(query2)) {
                ps.setInt(1, fid);
                ps.setString(2, name);
                ps.setString(3, author);
                ps.setInt(4, year);
                ps.setInt(5, Main.token);
                ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Ошибка при добавлении книги: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void delete(Connection connection, int fid) throws SQLException {
        String query = "DELETE FROM wishlist WHERE fid = ? and uid = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, fid);
            preparedStatement.setInt(2, Main.token);
            preparedStatement.executeUpdate();
        }
    }
}
