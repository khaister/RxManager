package doctor.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author khaister
 *
 */
public class PatientConnect
{
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "rxmandoc";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "rxmandocpasswd";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "rxmanagerdb";


	/**
	 * Gets a new database connection.
	 * 
	 * @return An object represents the database connection.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException 
	{
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	
	/**
	 * Looks up patients by both first name and last name.
	 * @param fname First name.
	 * @param lname Last name.
	 * @return An ArrayList of patients with the given first and last name.
	 */
	public ArrayList<Patient> getPatient(String fname, String lname) 
	{
		ArrayList<Patient> foundPatients = new ArrayList<Patient>();

		// Connect to MySQL
		Connection conn = null;
		try 
		{
			conn = this.getConnection();
			//System.out.println("Connected to database");
		} 
		catch (SQLException e) 
		{
			System.out.println("ERROR: Could not connect to the database!");
			e.printStackTrace();
			//return;
		}

		// Retrieve a record from the table Patients
		try
		{
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM Patients p "
					+ "WHERE p.pFirstName = " + fname
					+ "AND p.pLastName = " + lname;
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) // moving the cursor forward throu the rows
			{
				Patient p = new Patient();
				p.setFirstName(rs.getString("pFirstName"));
				p.setLastName(rs.getString("pLastName"));
				p.setDOB(rs.getDate("pDOB"));
				p.setPhone(rs.getString("pPhone"));
				p.setAddress(rs.getString("pAddress"));
				p.setCity(rs.getString("pCity"));
				p.setState(rs.getString("pState"));
				p.setZipCode(rs.getString("pZipCode"));
				p.setMedicalNumber(rs.getString("pMedicalNumber"));
				
				foundPatients.add(p);
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundPatients;
	}

}
