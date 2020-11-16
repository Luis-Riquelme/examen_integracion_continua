package BaseDatos;

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DatabaseConnection
{
	   
	static Logger logger = Logger.getLogger(DatabaseConnection.class);

    private static Connection conn; 
    
    public Connection getConnection() 
    {
        if (conn == null)
         performConnection();
         
        return conn;
    }
 
    public void delConnection()
    {
		try
		{
			conn.close();
		}
		catch(SQLException e)
		{
		}
		conn = null;
	}
     
    public static void performConnection() 
    {
        PropertyConfigurator.configure("log4j.properties");
        try 
        { 
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_veterinaria?useSSL=false", "root", "");
            logger.info("Se ha establecido conexión con la base de datos.");
        } 
        catch (Exception e) 
        {
            logger.error("Error al abrir la conexión: " + e);
        }
    }
}