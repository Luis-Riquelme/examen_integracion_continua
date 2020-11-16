package JUnitTesting;
import org.junit.Assert;
import org.junit.Test;
import ClasesNogocio.Paciente;
public class PacienteTest 
{
	String nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente,numero_paciente;
	@Test
	public void Caso1_Paciente_Agregar() throws ClassNotFoundException 
	{
		nombre = "Terry";color = "Negro"; fecha_nacimiento = "16-11-2019"; especie = "Canino"; 
		raza = "Quiltro"; sexo = "Macho";rut_cliente = "15498781-9";		
		int resultado; int esperado=1;
		Paciente aa = new Paciente();
		resultado = aa.AgrPaciente(nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente);
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void Caso2_Paciente_Modificar() throws ClassNotFoundException 
	{
		nombre = "Terry"; color = "Café Oscuro"; fecha_nacimiento = "16-11-2019"; especie = "Canino"; 
		raza = "Gran Danés"; sexo = "Macho"; rut_cliente = "15498781-9"; numero_paciente = "";
		int resultado;
		int esperado=1;
		Paciente aa = new Paciente();
		resultado = aa.ModPaciente(nombre, color, fecha_nacimiento, especie, raza, sexo, rut_cliente, numero_paciente);
		Assert.assertEquals(esperado, resultado);
	}	
	@Test
	public void Caso3_Paciente_Eliminar() throws ClassNotFoundException 
	{
		numero_paciente = "Terry";
		Boolean resultado;
		Boolean esperado=true;
		
		Paciente aa = new Paciente();
		resultado = aa.EliPaciente(numero_paciente);
		Assert.assertEquals(esperado, resultado);
	}		
}
