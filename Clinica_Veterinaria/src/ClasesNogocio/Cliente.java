package ClasesNogocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.PropertyConfigurator;

import BaseDatos.DatabaseConnection;

public class Cliente 
{
	DefaultTableModel modelo;
    DatabaseConnection mysql = new DatabaseConnection();
    Connection cn = mysql.getConnection();
	
	public DefaultTableModel cargar_datos(String valor)	
	{
		DefaultTableModel ret=null;
        int k = 0;
        String[] titulos = {"Rut", "Nombre", "Dirección", "Teléfono", "E-Mail"};
        String[] registro = new String[5];
        String sSQL = "";
        modelo = new DefaultTableModel(null, titulos);

        if (valor.trim().length() > 0) 
        {
            sSQL = "SELECT rut_cliente,nombre, direccion, telefono, mail FROM cliente WHERE rut_cliente='" + valor + "'";
        } 
        else 
        {
            sSQL = "SELECT rut_cliente,nombre, direccion, telefono, mail FROM cliente";
        }
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) 
            {
                if (rs != null) 
                {
                    k++;
                }
                registro[0] = rs.getString("rut_cliente");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("direccion");
                registro[3] = rs.getString("telefono");
                registro[4] = rs.getString("mail");
                modelo.addRow(registro);
            }
            if (k > 0) 
            {
            	ret=modelo;
            	
            } 
        } 
        catch (SQLException ex) 
        {
        }		
		
		return ret;
	}

	public DefaultTableModel GetCliente(String id) {
		DefaultTableModel ret=null;
        int k = 0;
        String sSQL = "";

        String[] registro = new String[5];
        
        sSQL = "SELECT rut_cliente, nombre, direccion, telefono, mail FROM cliente WHERE rut_cliente='" + id + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) 
            {
                if (rs != null) 
                {
                    k++;
                }
                registro[0] = rs.getString("rut_cliente");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("direccion");
                registro[3] = rs.getString("telefono");
                registro[4] = rs.getString("mail");
                modelo.addRow(registro);
            }
            if (k > 0) 
            {
            	ret=modelo;
            } 

        } catch (SQLException ex) {
        }
        return ret;
	}
	
    public Boolean EliCliente(String id) 
    {

    	Boolean ret = false;
        String sSQL = "";
        PropertyConfigurator.configure("log4j.properties");
        
        sSQL = "DELETE FROM cliente WHERE rut_cliente='" + id + "'";

        try 
        {
            Statement st = cn.createStatement();
            st.executeUpdate(sSQL);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente", "Proceso Éxitoso", JOptionPane.PLAIN_MESSAGE);

            ret=true;

        } catch (SQLException ex) 
        {
        }
        return ret;
    }	
	
	public int AgrCliente(String rut_cliente, String nombre, String direccion, String telefono, String mail)
	{
		int ret = 0;
        String sSQL = "INSERT INTO cliente(rut_cliente, nombre, direccion, telefono, mail) VALUES(?, ? , ? , ? ,?)";

        try { 
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, rut_cliente);
            pst.setString(2, nombre);
            pst.setString(3, direccion);
            pst.setString(4, telefono);
            pst.setString(5, mail);
            int n = pst.executeUpdate();
            if (n > 0) {
                ret=1;
            }
        } catch (SQLException ex) {
        }
		return ret;
	}	

	public int ModCliente(String rut_cliente, String nombre, String direccion, String telefono, String mail)
	{
		int ret = 0;
        String sSQL = "UPDATE cliente SET rut_cliente=?, nombre=?, direccion=?, telefono=?, mail=? WHERE rut_cliente='" + rut_cliente + "'";

        try
        {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, rut_cliente);
            pst.setString(2, nombre);
            pst.setString(3, direccion);
            pst.setString(4, telefono);
            pst.setString(5, mail);
            int n = pst.executeUpdate();
            if (n > 0) {
                ret=1;
            }
        } 
        catch (SQLException ex) 
        {
        }	
        return ret;
	}	
		
}
