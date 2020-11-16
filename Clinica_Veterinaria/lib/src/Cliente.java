import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
            sSQL = "SELECT * from cliente";
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
            else 
            {
                JOptionPane.showMessageDialog(null, "No Existen clienten", "Búsqueda Inválida", JOptionPane.PLAIN_MESSAGE);
            }
        } 
        catch (SQLException ex) 
        {
        }		
		
		return ret;
	}
	
	public DefaultTableModel ModCliente(String id)
	{
		DefaultTableModel ret=null;
        int k = 0;
        String sSQL = "";
        // String[] titulos = {"Rut", "Nombre", "Dirección", "Teléfono", "E-Mail"};
        String[] registro = new String[5];
        
        sSQL = "SELECT rut_cliente,nombre, direccion, telefono, mail FROM cliente WHERE rut_cliente='" + id + "'";

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

}
