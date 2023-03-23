package es.studium.ejemplosBD;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SegundoEjemploBD implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Ejemplo 2 BD");
	Label lblEmpleado = new Label("idEmpleado");
	TextField txtEmpleado = new TextField(20);
	Label lblNombre = new Label("Nombre");
	TextField txtNombre = new TextField(20);
	Label lblSalario = new Label("Salario");
	TextField txtSalario = new TextField(20);
	Button btnAnterior = new Button("Anterior");
	Button btnSiguiente = new Button("Siguiente");

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";

	Connection connection = null; //para conectarnos a la BD
	Statement statement = null; //para lanzar o ejecutar una sentencia de la BD
	ResultSet rs = null; //para guardar lo que nos devuelve la BD

	SegundoEjemploBD(){
		ventana.setSize(300,200);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblEmpleado);
		ventana.add(txtEmpleado);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblSalario);
		ventana.add(txtSalario);
		btnAnterior.addActionListener(this);
		ventana.add(btnAnterior);
		btnSiguiente.addActionListener(this);
		ventana.add(btnSiguiente);
		txtEmpleado.setEnabled(false);
		txtNombre.setEnabled(false);
		txtSalario.setEnabled(false);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);

		try
		{

			Class.forName(driver); 
			connection = DriverManager.getConnection(url, login, password); 
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
			rs = statement.executeQuery(sentencia); 
			rs.next(); 
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

		ventana.setVisible(true);	
	}

	public static void main(String[] args)
	{
		new SegundoEjemploBD();
	}

	@Override
	public void windowOpened(WindowEvent e)
	{}
	public void windowClosing(WindowEvent ee)
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnSiguiente)) {

			try {
				if(!rs.isLast()) {
				rs.next();
				txtEmpleado.setText(rs.getString("idEmpleado"));
				txtNombre.setText(rs.getString("nombreEmpleado"));
				txtSalario.setText((rs.getString("salarioEmpleado"))+ "€");
				}
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
		}

		else if(e.getSource().equals(btnAnterior)) {
			
			try {
				if(!rs.isFirst()) {
				rs.previous();
				txtEmpleado.setText(rs.getString("idEmpleado"));
				txtNombre.setText(rs.getString("nombreEmpleado"));
				txtSalario.setText((rs.getString("salarioEmpleado"))+ "€");
				}
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
		}

	}



}
