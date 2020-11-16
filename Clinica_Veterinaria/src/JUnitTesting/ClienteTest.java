package JUnitTesting;
import org.junit.Assert;
import org.junit.Test;
import ClasesNogocio.Cliente;
public class ClienteTest 
{
	String nombre, rut_cliente, direccion, telefono, mail;
	@Test
	public void Caso1_Cliente_Agregar() throws ClassNotFoundException 
	{
		nombre = "Luis Riquelme"; rut_cliente = "15498781-9"; direccion = "Los Castaños 2020";
		telefono = "97765432"; mail = "luis.miguel.riquelme.yanez@gmail.com";
		int resultado;
		int esperado=1;
		Cliente aa = new Cliente();
		resultado = aa.AgrCliente(rut_cliente, nombre, direccion, telefono, mail);
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void Caso2_Cliente_Modificar() throws ClassNotFoundException 
	{
		nombre = "Luis Miguel Riquelme Yañez"; rut_cliente = "15498781-9"; direccion = "Las Encinas 1427";
		telefono = "97765432"; mail = "luis.miguel.riquelme.yanez@gmail.com";
		int resultado;
		int esperado=1;
		Cliente aa = new Cliente();
		resultado = aa.ModCliente(rut_cliente, nombre, direccion, telefono, mail);
		Assert.assertEquals(esperado, resultado);
	}	

	@Test
	public void Caso3_Cliente_Eliminar() throws ClassNotFoundException 
	{
		rut_cliente = "15498781-9";
		Boolean resultado;
		Boolean esperado=true;
		
		Cliente aa = new Cliente();
		resultado = aa.EliCliente(rut_cliente);
		Assert.assertEquals(esperado, resultado);
	}		
	
}
