package badim.database.functions;

import badim.database.Connection;
import badim.database.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class watched {
    private static boolean manya;
    public void Watch(Scanner scanner) throws SQLException {
        boolean watch = true;
        scanner.nextLine();
        int fid;
        while(watch){
            manya = false;
            Connection connection = new Connection();
            System.out.print("Просмотренно\n");
            System.out.print("1) Посмотреть список\n");
            System.out.print("2) Добавить по id\n");
            System.out.print("3) Добавить по id из wishlist\n");
            System.out.print("4) Вернуться\n");
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
                    System.out.print("id: ");
                    fid = scanner.nextInt();
                    delete(connection, fid);
                    if(manya=true)
                        add(connection, fid);
                    break;
                case 4:
                    watch = false;
                    break;
            }
        }
    }
    private static void print(Connection connection) throws SQLException {
        String query = "SELECT * FROM watched where uid = ?";
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
    private static void add(Connection connection, int fid) throws SQLException {
        String name = null;String author = null; int year = 0;
        String query1 = "SELECT * FROM films WHERE fid = ?";
        try (PreparedStatement cocojumbo = connection.getConnection().prepareStatement(query1)) {
            cocojumbo.setInt(1, fid);
            try (ResultSet resultSet = cocojumbo.executeQuery()) {
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
            e.printStackTrace();
        }
        String query2 = "INSERT INTO watched (fid, name, author, year, uid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.getConnection().prepareStatement(query2)) {
            ps.setInt(1, fid);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setInt(4, year);
            ps.setInt(5, Main.token);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Фильм успешно добавлен.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void delete(Connection connection, int fid) throws SQLException {
        String query = "DELETE FROM wishlist WHERE fid = ? and uid = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, fid);
            preparedStatement.setInt(2, Main.token);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                manya = true;
            } else {
                System.out.println("Фильма с ID " + fid + " нет в вашем wishlist.");
            }
        }
    }
}
