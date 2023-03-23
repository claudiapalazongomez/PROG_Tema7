package es.studium.ejemplosBD;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TercerEjemploBD implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Ejemplo 3 BD");
	Label lblEmpleado = new Label("idEmpleado");
	Label lblNombre = new Label("Nombre");
	Label lblSalario = new Label("Salario");
	TextArea txtArea = new TextArea();
	Button btnVolver = new Button("Volver");
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";

	Connection connection = null; 
	Statement statement = null; 
	ResultSet rs = null; 
	
	TercerEjemploBD(){
		ventana.setSize(500,600);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblEmpleado);
		ventana.add(lblNombre);
		ventana.add(lblSalario);
		ventana.add(txtArea);
		ventana.add(btnVolver);
		btnVolver.addActionListener(this);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		
		try
		{

			Class.forName(driver); 
			connection = DriverManager.getConnection(url, login, password); 
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
			rs = statement.executeQuery(sentencia); 
			while(rs.next()) {
			txtArea.setText(txtArea.getText()+"\n"+rs.getString("idEmpleado")+" / "+rs.getString("nombreEmpleado")+" / "+(rs.getString("salarioEmpleado"))+ "€");
			//txtArea.setText(txtArea.getText()+"\n"+rs.getString("nombreEmpleado"));
			//txtArea.setText(txtArea.getText()+"\n"+(rs.getString("salarioEmpleado"))+ "€");
			}
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
		new TercerEjemploBD();

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
		if(e.getSource().equals(btnVolver)) {
			System.exit(0);
		}
	}

}
