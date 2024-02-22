package badim.database.verification;

import badim.database.*;
import java.sql.*;
import java.util.*;

public class VerificationReg {
    DBC dbc = new DBC();
    public void RegProcess(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Логин:\n ");
        String Login = scanner.nextLine();
        System.out.print("Пароль:\n ");
        String Password = scanner.nextLine();
        boolean isUserExist = false;
        try (PreparedStatement ps = dbc.getConnection().prepareStatement("select 1 from users where login = ?")) {
            ps.setString(1, Login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExist = true;
                }
            }
        } catch (SQLException e) {
            System.out.print("чета не то");
        }
        if (isUserExist) {
            System.out.print("Аккаунт с таким логином уже существует\n");
        } else {
            try (PreparedStatement preparedStatement = dbc.getConnection().prepareStatement("INSERT INTO users (login, password) VALUES (?, ?)")) {
                preparedStatement.setString(1, Login);
                preparedStatement.setString(2, Password);
                System.out.println("Аккаунт создан.");
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.print("чета не то");
            }
            Main.coconut = false;
        }
    }
}
