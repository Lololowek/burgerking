package badim.database.functions;

import badim.database.DBC;

import java.util.Scanner;
import java.sql.*;

public class wishlist {

    public void Wish(Scanner scanner) throws SQLException {
        scanner.nextLine();
        System.out.print("Wishlist\n");
        System.out.print("1) Посмотреть список\n");
        System.out.print("2) Добавить по id\n");
        System.out.print("3) Удалить из списка по id\n");
        System.out.print("4) Добавить по id\n");
        System.out.print("0) Вернуться\n");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                print();
                break;
            case 2:
                new watched().Watched(scanner);
                break;
            case 3:
                new library().Lib(scanner);
                break;
            case 4:
                new watched().Watched(scanner);
                break;
            case 0:
                new library().Lib(scanner);
                break;
        }
    }
    private static void print() throws SQLException {
        DBC dbc = new DBC();
        String query = "SELECT * FROM wishlist";
        try (Statement st = dbc.getConnection().createStatement();
             ResultSet resultSet = st.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("fid") +
                        ", Название: " + resultSet.getString("name") +
                        ", Автор: " + resultSet.getString("author") +
                        ", Год: " + resultSet.getInt("year"));
            }
        }
    }
}
