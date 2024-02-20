package badim.database.verification;

import badim.database.DBC;
import badim.database.User;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class VerificationLogin {
    public void LoginProcess(Scanner scanner){
        scanner.nextLine();
        DBC dbc = new DBC();
        User user = new User();
        System.out.print("Логин:\n ");
        String login = scanner.nextLine();
        System.out.print("Пароль:\n ");
        String password = scanner.nextLine();
        boolean isUserExists = false;
        try (PreparedStatement ps = dbc.getConnection().prepareStatement("select 1 from users where login = ? and password=?")) {
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
                String query = "select * from users";
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
        }
        else {System.out.print("Неверные данные либо ваш аккаунт не существует");}
    }
}
