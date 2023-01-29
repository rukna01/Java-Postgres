import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn =db.connect_to_db("dump01","postgres","rootroot");
        db.delete_table(conn,"employee ");
        db.createTable(conn,"employee");
        db.insert_row(conn,"employee","peter","India");
//          db.read_data(conn,"employee");
//          db.update_name(conn,"employee","raj","peter");
//          db.update_name_byId(conn,"employee",1,"ollie");

//          db.search_by_name(conn,"employee","peter");
//          db.delete_row_by_name(conn,"employee","peter");
        db.read_data(conn,"employee");
    }
}