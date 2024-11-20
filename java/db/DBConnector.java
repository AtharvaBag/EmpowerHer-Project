package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector 
{
    static Connection con = null;
    static Statement st = null;
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpowerHer?useUnicode=true&characterEncoding=UTF-8","root","root");
            System.out.println("Database Connected");
            
            st=con.createStatement();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }
    public static Statement getStatement()
    {
        return st;
    }
    public static Connection getConnection()
    {
        return con;
    }
}
