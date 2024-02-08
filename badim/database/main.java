package badim.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class main {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "nadim", "root");
            Statement statement = con.createStatement();
            //statement.execute();
            System.out.print(con + "   Connection succesful!");
        }
        catch (Exception e) {
            System.out.print("Connection failed");
        }
    }
}
