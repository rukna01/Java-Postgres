import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunctions {

    public Connection connect_to_db(String db_name, String user, String pass){
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db_name,user,pass);
            if (con!=null){
                System.out.println("Connected");
            }
        }
        catch(Exception e){
            System.out.print(e);
        }
        return con;
    }
}