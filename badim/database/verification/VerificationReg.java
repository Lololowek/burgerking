package badim.database.verification;

import badim.database.*;
import badim.database.Connection;

import java.sql.*;
import java.util.*;

public class VerificationReg {
    public void RegProcess(Scanner scanner) {
        Connection connection = new Connection();
        scanner.nextLine();
        System.out.print("Логин:\n ");
        String Login = scanner.nextLine();
        System.out.print("Пароль:\n ");
        String Password = scanner.nextLine();
        boolean isUserExist = false;
        try (PreparedStatement ps = connection.getConnection().prepareStatement("select 1 from users where login = ?")) {
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
        }
        else {
            try (PreparedStatement reg = connection.getConnection().prepareStatement("INSERT INTO users (login, password) VALUES (?, ?)"))
            {
                System.out.print(Login + " " + Password + " ");
                reg.setString(1, Login);
                reg.setString(2, Password);
                System.out.println("Аккаунт создан.");
            }
            catch (SQLException e) {
                e.getSQLState();
                System.out.print("чета не то");
            }
            Main.coconut = false;
        }
    }
}
