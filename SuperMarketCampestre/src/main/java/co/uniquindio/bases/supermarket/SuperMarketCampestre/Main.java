package co.uniquindio.bases.supermarket.SuperMarketCampestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	static final String CONTROLLER = "com.mysql.jdbc.Driver";
	static final String URL_DATABASE  = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";

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
			String sql = "DROP DATABASE prueba_bases;";
//			instruction.executeUpdate("CREATE TABLE autores (idAutor varchar(50) NOT NULL, nombre varchar(10), apellido varchar(10), primary key (idAutor));");//Aquí va la consulta
			instruction.executeUpdate(sql);
			System.out.println("Base de datos eliminada");
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
			e.printStackTrace();
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(instruction!=null)
		            instruction.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conection!=null)
		            conection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		

	}

}
