package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.util.ArrayList;
import doctor.backend.*;
import pharmacist.backend.*;

/**
 * 
 * @author khaister
 *
 */
public class DBConnect
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
	
	/** Connection to the MySQL database */
	private Connection conn;
	
	/**
	 * Creates a PatientConnect instance and connect to the database.
	 */
	public DBConnect()
	{
		// Connect to MySQL
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
	}


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
		
		String sql = "SELECT * FROM Patients p "
				+ "WHERE p.pFirstName = " + fname
				+ "AND p.pLastName = " + lname;
		
		// Retrieve a record from the table Patients
		try
		{
			Statement st = conn.createStatement();
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
	
	
	/**
	 * Inserts a patient record into Patients table. It is assumed that there
	 * is no record for this patient exists yet.
	 * @param p Patient to be inserted.
	 * @return 0 if inserted sucessfully, 1 otherwise.
	 */
	public int createPatientRecord(Patient p)
	{
		int errorCode = 0;
		String sql = "INSERT INTO Patients VALUES " + p.toSQLInsertString();
		
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			errorCode = 1;
		}

		return errorCode;
	}
	
	/**
	 * Updates a patient record. Note: all old info will be overwritten with
	 * that from the new Patient object.
	 * 
	 * @param oldRecord Old record of the patient.
	 * @param newRecord New record of the patient.
	 * @return
	 */
	public int updatePatientRecord(Patient oldRecord, Patient newRecord)
	{
		int errorCode = 0;
		
		String sql = "SELECT * FROM Patients p "
				+ "WHERE p.pFirstName = " + oldRecord.getFirstName()
				+ "AND p.pLastName = " + oldRecord.getLastName()
				+ "AND p.pPhone = " + oldRecord.getPhone();
		
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// This should return only 1 row.
			while (rs.next())
			{
				rs.updateString("pFirstName", newRecord.getFirstName());
				rs.updateString("pLastName", newRecord.getLastName());
				rs.updateDate("pDOB", newRecord.getDOB());
				rs.updateString("pAddress", newRecord.getAddress());
				rs.updateString("pCity", newRecord.getCity());
				rs.updateString("pState", newRecord.getState());
				rs.updateString("pZipCode", newRecord.getZipCode());
				rs.updateString("pMedicalNumber", newRecord.getMedicalNumber());
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			errorCode = 1;
		}

		return errorCode;
	}
	
	/**
	 * Creates a new Prescription record (row) in the database.
	 * 
	 * @param rx Prescription to be added to the database.
	 * @return 0 if the INSERT is sucessful, 1 otherwise.
	 */
	public int sendPrescription(Prescription rx)
	{
		int errorCode = 0;
		
		String sql = "INSERT INTO Prescriptions VALUES " + rx.toSQLInsertString();
		
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			errorCode = 1;
		}
		
		return errorCode;
		
	}

}
