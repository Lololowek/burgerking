package badim.database;

import badim.database.verification.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBC dbc = new DBC();
        boolean coconut = true;
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
            }
        }
    }
}
