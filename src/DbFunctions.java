import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DbFunctions {
    static HashMap<String,String> mapEmpName= new HashMap<>();
    static HashMap<String,ArrayList> mapEmpInfo=new HashMap<>();

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
    public void createTable(Connection conn,String table_name) {
        Statement statement;  // this is the object
        try {
            String query = "create table " + table_name + "(empid SERIAL,name varchar(200),address varchar(200),primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insert_row(Connection conn,String table_name,String name,String address){
        Statement statement;
        try{
            String query=String.format("insert into %s(name,address) values('%s','%s');",table_name,name,address);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            update_store(conn,table_name,name,address);
            System.out.println("Row Inserted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void read_data(Connection conn ,String table_name){
        Statement statement;
        ResultSet rs =null;
        try{
            String query=String.format("select * from %s",table_name);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+"\t");
                System.out.print(rs.getString("name")+"\t");
                System.out.println(rs.getString("address"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void update_name(Connection conn,String table_name,String old_name,String new_name){
        Statement statement;
        try{
            String query=String.format("update %s set name='%s' where name='%s'",table_name,new_name,old_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void update_name_byId(Connection conn,String table_name,int empid,String new_name){
        Statement statement;
        try{
            String query=String.format("update %s set name='%s' where empid='%s'",table_name,new_name,empid);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void search_by_name(Connection conn,String table_name,String name){
        Statement statement;
        ResultSet rs=null;
        try{
            String query=String.format("select  * from %s where name ='%s'",table_name,name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+"\t");
                System.out.print(rs.getString("name")+"\t");
                System.out.println(rs.getString("address")+"\t");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_row_by_name(Connection conn,String table_name,String name){
        Statement statement;
        try{
            String query=String.format("delete from %s where name ='%s'",table_name,name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void delete_table(Connection conn,String table_name){
        Statement statement;
        try{
            String query=String.format("drop table %s",table_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table deleted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void  update_store(Connection conn,String table_name,String name,String address){
        Statement statement;
        ResultSet rs =null;
        try{
            String query=String.format("select * from %s",table_name);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            ArrayList<String> info = new ArrayList<>();
            info.add(name);
            info.add(address);
            while(rs.next()) {
//                System.out.print(rs.getString("empid")+"\t");
//                System.out.print(rs.getString("name")+"\t");
//                System.out.println(rs.getString("address"));
                mapEmpInfo.put(rs.getString("empid"),info);
                mapEmpName.put(rs.getString("empid"), name);
            }
            System.out.println(mapEmpName);
            System.out.println(mapEmpInfo);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}