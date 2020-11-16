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

public class Paciente 
{
	DefaultTableModel modelo;
    DatabaseConnection mysql = new DatabaseConnection();
    Connection cn = mysql.getConnection();	

	public DefaultTableModel cargar_datos(String valor)	
	{
		DefaultTableModel ret=null;
        int k = 0;
        String[] titulos = {"Número Ficha", "Nombre Paciente", "Color", "Fecha Nacimiento", "Epecie", "Raza", "Sexo", "Rut Cliente"};
        String[] registro = new String[8];
        String sSQL = "";
        modelo = new DefaultTableModel(null, titulos);

        if (valor.trim().length() > 0)  
        {
            sSQL = "SELECT id, nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente FROM paciente WHERE nombre='" + valor + "'";
        } 
        else 
        {
            sSQL = "SELECT id, nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente FROM paciente";
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
                registro[0] = rs.getString("id");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("color");
                registro[3] = rs.getString("fecha_nacimiento");
                registro[4] = rs.getString("especie");
                registro[5] = rs.getString("raza");
                registro[6] = rs.getString("sexo");
                registro[7] = rs.getString("rut_cliente");
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
 
	public DefaultTableModel GetPaciente(String id) //ModificarPaciente
	{
		DefaultTableModel ret=null;
        int k = 0;
        String sSQL = "";

        String[] registro = new String[7];
        
        sSQL = "SELECT numero_paciente, nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente FROM paciente WHERE nombre='" + id + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) 
            {
                if (rs != null) 
                {
                    k++;
                }
                registro[0] = rs.getString("numero_paciente");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("color");
                registro[3] = rs.getString("fecha_nacimiento");
                registro[4] = rs.getString("especie");
                registro[5] = rs.getString("raza");
                registro[6] = rs.getString("sexo");
                registro[7] = rs.getString("rut_cliente");
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

    public Boolean EliPaciente(String id) 
    {

    	Boolean ret = false;
        String sSQL = "";
        PropertyConfigurator.configure("log4j.properties");
        
        sSQL = "DELETE FROM paciente WHERE nombre='" + id + "'";

        try 
        {
            Statement st = cn.createStatement();
            st.executeUpdate(sSQL);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el Paciente", "Proceso Éxitoso", JOptionPane.PLAIN_MESSAGE);

            ret=true;

        } catch (SQLException ex) 
        {
        }
        return ret;
    }	
	
	public int AgrPaciente(String nombre, String color, String fecha_nacimiento, String especie, String raza, String sexo, String rut_cliente)
	{
		int ret = 0;
        String sSQL = "INSERT INTO paciente (nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente) VALUES(?, ? , ? , ?, ? , ?, ?)";

        try { 
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nombre);
            pst.setString(2, color);
            pst.setString(3, fecha_nacimiento);
            pst.setString(4, especie);
            pst.setString(5, raza);
            pst.setString(6, sexo);
            pst.setString(7, rut_cliente);
            int n = pst.executeUpdate();
            if (n > 0) {
                ret=1;
            }
        } catch (SQLException ex) {
        }
		return ret;
	}	

	public int ModPaciente(String nombre, String color, String fecha_nacimiento, String especie, String raza, String sexo, String rut_cliente, String numero_paciente)
	{
		int ret = 0;
        String sSQL = "UPDATE paciente SET nombre=?, color=?, fecha_nacimiento=?, especie=?, raza=?, sexo=?, rut_cliente=? WHERE numero_paciente='" + numero_paciente + "'";

        try
        {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nombre);
            pst.setString(2, color);
            pst.setString(3, fecha_nacimiento);
            pst.setString(4, especie);
            pst.setString(5, raza);
            pst.setString(6, sexo);
            pst.setString(7, rut_cliente);
            int n = pst.executeUpdate();
            if (n > 0) {
                ret=1;
            }
        } catch (SQLException ex) {
        }	
        return ret;
	}	
	
}
