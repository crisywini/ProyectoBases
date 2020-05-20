package test.database;
import java.sql.*;
public class Main2 {
	static final String CONTROLLER = "com.mysql.jdbc.Driver";
	static final String URL_DATABASE  = "jdbc:mysql://localhost:3306/libros?autoReconnect=true&useSSL=false";
	public static void main(String[] args) {
		Connection conection = null;//Maneja la conexion
		Statement instruction = null;//Instruccion de consulta
		ResultSet resultDataset = null;//Maneja los resultados
		
		try {
			Class.forName(CONTROLLER).newInstance();//Carga la clase CONTROLLER
			conection = DriverManager.getConnection(URL_DATABASE, "root", "root");//Establece la conexión a la base de datos
			System.out.println("Conexión: "+conection);
			instruction = conection.createStatement();//Crea el Statement para la consulta
			System.out.println("Instrucción: "+instruction);
			instruction.executeUpdate("CREATE TABLE autores (idAutor varchar(50) NOT NULL, nombre varchar(10), apellido varchar(10), primary key (idAutor));");//Aquí va la consulta
			System.out.println("Tabla Autores creada");
		}
		catch(SQLException exception) {
			System.out.println("Error en la conexión");
			exception.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("error controlador");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
