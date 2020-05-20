package test.database;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
public class MAin {

	static final String CONTROLLER = "com.mysql.jdbc.Driver";
	static final String URL_DATABASE  = "jdbc:mysql://localhost/libros";
	public static void main(String[] args) {
		Connection conection = null;//Maneja la conexion
		Statement instruction = null;//Instruccion de consulta
		ResultSet resultDataset = null;//Maneja los resultados
		try {
			Class.forName(CONTROLLER);//Carga la clase CONTROLLER
			conection = DriverManager.getConnection(URL_DATABASE, "root", "root");//Establece la conexión a la base de datos
			instruction = conection.createStatement();//Crea el Statement para la consulta
			resultDataset = instruction.executeQuery("SELECT IDAutor, nombrePila, apellidPaterno FROM autores");//Aquí va la consulta
			//Procesando los resultados de la consulta
			ResultSetMetaData metaData = resultDataset.getMetaData();
			int numberColumns = metaData.getColumnCount();
			System.out.println("Numero de columntas: "+numberColumns);
			System.out.println("Tabla autores de la base de datos:\n");
			for (int i = 1; i <= numberColumns; i++) {
				System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
				System.out.println();
			}
			while ( resultDataset.next() )
			{
				for ( int i = 1; i <= numberColumns; i++ )
					System.out.printf( "%-8s\t", resultDataset.getObject( i ) );
				System.out.println();
			}
		}
		catch(SQLException exception) {
			exception.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally // asegura que conjuntoResultados, instruccion y conexion estén cerrados
		{
			try
			{
				resultDataset.close();
				instruction.close();
				conection.close();
			} 
			catch (Exception excepcion)
			{
				excepcion.printStackTrace();
			} 
		}
	}

}
