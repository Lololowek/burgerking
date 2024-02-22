package badim.database;
import badim.database.functions.*;
import badim.database.verification.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static boolean coconut;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        coconut = true;
        while (coconut) {
            System.out.print("Добро пожаловать!\n");
            System.out.print("1) Войти\n");
            System.out.print("2) Зарегистрироваться\n ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new VerificationLogin().LoginProcess(scanner);
                    break;
                case 2:
                    new VerificationReg().RegProcess(scanner);
                    break;
            }
        }
        System.out.print("Личный кабинет\n");
        System.out.print("1) На очереди\n");
        System.out.print("2) Просмотренно\n");
        System.out.print("3) Смотреть\n");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                new wishlist().Wish(scanner);
                break;
            case 2:
                new watched().Watched(scanner);
                break;
            case 3:
                new library().Lib(scanner);
                break;
        }
    }
}
