package badim.database.verification;

import badim.database.DBC;
import badim.database.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VerificationReg {
    User user = new User();
    DBC dbc = new DBC();
    public void RegProcess(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Логин: ");
        String Login = scanner.nextLine();
        System.out.println("Пароль: ");
        String Password = scanner.nextLine();
        boolean isUserExist = false;
        try (PreparedStatement ps = dbc.getConnection().prepareStatement("select 1 from users where login = ? and password=?")) {
            ps.setString(1, Login);
            ps.setString(2, Password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExist = true;
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        if (isUserExist) {
            System.out.print("Аккаунт с таким логином уже существует");
        } else {
            String query = "INSERT INTO users (login, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = dbc.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, Login);
                preparedStatement.setString(2, Password);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Аккаунт создан.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
