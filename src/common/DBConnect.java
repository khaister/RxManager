package common;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.util.Properties;
//import java.util.ArrayList;

import doctor.backend.*;

/**
 * 
 * @author khaister
 *
 */
public class DBConnect
{
//	/** The name of the MySQL account to use (or empty for anonymous) */
//	private final String userName = "rxmandoc";
//
//	/** The password for the MySQL account (or empty for anonymous) */
//	private final String password = "rxmandocpasswd";
//
//	/** The name of the computer running MySQL */
//	private final String serverName = "localhost";
//
//	/** The port of the MySQL server (default is 3306) */
//	private final int portNumber = 3306;
//
//	/** The name of the database we are testing with (this default is installed with MySQL) */
//	private final String dbName = "rxmanagerdb";
//	
//	/** Connection to the MySQL database */
	private Connection conn;

	
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
}
