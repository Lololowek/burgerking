package badim.database.verification;

import badim.database.*;
import badim.database.Connection;

import java.sql.*;
import java.util.*;

public class VerificationLogin {
    private String Login;
    private String Password;
    public void LoginProcess(Scanner scanner) {
        scanner.nextLine();
        Connection connection = new Connection();
        User user = new User();
        System.out.print("Логин:\n ");
        this.Login = scanner.nextLine();
        System.out.print("Пароль:\n ");
        this.Password = scanner.nextLine();
        boolean isUserExists = false;
        try (PreparedStatement ps = connection.getConnection().prepareStatement("select 1 from users where login = ? and password=?")) {
            ps.setString(1, Login);
            ps.setString(2, Password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExists = true;
                    user.setPassword(Password);
                    user.setLogin(Login);
                    Main.token = rs.getInt(1) ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUserExists) {
            System.out.println("ВХОД ВЫПОЛНЕН!");
            if ((Objects.equals(Login, "admin")) & (Objects.equals(Password, "admin"))) {
                String query1 = "select * from users";
                System.out.println("Вывести всех Users? y/n");
                String ans = scanner.nextLine();
                if(Objects.equals(ans, "y")) {
                    try {
                        Statement statement = connection.getConnection().createStatement();
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            User users = new User();
                            users.setId(resultSet.getInt(1));
                            users.setLogin(resultSet.getString(2));
                            users.setPassword(resultSet.getString(3));

                            System.out.println(users);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Main.coconut = false;
        }
        else {System.out.print("Неверные данные либо ваш аккаунт не существует\n");}
    }
}
