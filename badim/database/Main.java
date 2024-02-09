package badim.database;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean coconut = true;
        while (coconut) {
            System.out.print("Добро пожаловать!\n");
            System.out.print("1) Войти\n");
            System.out.print("2) Зарегистрироваться\n");

            int choice = scanner.nextInt();
            DBC dbc = new DBC();
            switch (choice) {
                case 1:
                    User user = new User();
                    String asdsda = scanner.nextLine();
                    System.out.print("Логин: ");
                    String login = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();
                    boolean isUserExists = false;
                    try (PreparedStatement ps = dbc.getConnection().prepareStatement("select 1 from data where login = ? and password=?")) {
                        ps.setString(1, login);
                        ps.setString(2, password);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                isUserExists = true;
                                user.setPassword(password);
                                user.setLogin(login);
                            }
                        }
                    } catch (SQLException e) {throw new RuntimeException(e);}
                    if (isUserExists) {
                        System.out.println("ВХОД ВЫПОЛНЕН!");
                        String log = user.getLogin();
                        String pas = user.getPassword();
                        if ((Objects.equals(log, "admin")) & (Objects.equals(pas, "admin"))) {
                            String query = "select * from data";
                            try {
                                Statement statement = dbc.getConnection().createStatement();
                                ResultSet resultSet = statement.executeQuery(query);
                                while (resultSet.next()) {
                                    User users = new User();
                                    users.setId(resultSet.getInt(1));
                                    users.setLogin(resultSet.getString(2));
                                    users.setPassword(resultSet.getString(3));
                                    System.out.println(users);
                                }
                            } catch (SQLException e) {throw new RuntimeException(e);}}
                        coconut = false;}
                    else {System.out.print("Неверные данные либо ваш аккаунт не существует\n");}
                case 2:

            }
        }
    }
}
