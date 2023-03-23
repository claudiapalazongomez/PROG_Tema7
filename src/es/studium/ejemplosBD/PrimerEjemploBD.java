package es.studium.ejemplosBD;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class PrimerEjemploBD implements WindowListener
{
	Frame ventana = new Frame("Ejemplo 1 BD");
	Label lblEmpleado = new Label("idEmpleado");
	TextField txtEmpleado = new TextField(20);
	Label lblNombre = new Label("Nombre");
	TextField txtNombre = new TextField(20);
	Label lblSalario = new Label("Salario");
	TextField txtSalario = new TextField(20);
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";

	Connection connection = null; //para conectarnos a la BD
	Statement statement = null; //para lanzar o ejecutar una sentencia de la BD
	ResultSet rs = null; //para guardar lo que nos devuelve la BD
	
	PrimerEjemploBD(){
		ventana.setSize(200,200);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblEmpleado);
		ventana.add(txtEmpleado);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblSalario);
		ventana.add(txtSalario);
		txtEmpleado.setEnabled(false);
		txtNombre.setEnabled(false);
		txtSalario.setEnabled(false);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver); //accedemos al .jar
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password); //me conecto a la BD
			// Crear una sentencia
			statement = connection.createStatement(); //es como el USE en bd: indicamos que bd vamos a utilizar
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia); //utilizamos este String: "SELECT * FROM empleados"
			//Utilizamos executeUpdate para lo demás, executeQuery es únicamente para el Select

			rs.next(); //mientras haya un elemento en el rs.next() sigue dando vueltas hasta que el cursor llegue al EOR
			txtEmpleado.setText(rs.getString("idEmpleado"));
			txtNombre.setText(rs.getString("nombreEmpleado"));
			txtSalario.setText((rs.getString("salarioEmpleado"))+ "€");
		}

		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}

		finally
		{
			try
			{
				if(connection!=null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error 3-"+e.getMessage());
			}
		}
		
		ventana.setVisible(true);	
	}

	public static void main(String[] args)
	{	
		new PrimerEjemploBD();
	}

	@Override
	public void windowOpened(WindowEvent e)
	{}
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);	
	}
	public void windowClosed(WindowEvent e)
	{}
	public void windowIconified(WindowEvent e)
	{}
	public void windowDeiconified(WindowEvent e)
	{}
	public void windowActivated(WindowEvent e)
	{}
	public void windowDeactivated(WindowEvent e)
	{}

}
