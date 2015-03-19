import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.util.Date;


public class MySQLDriverTester
{

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "khaister";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "cecs343";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "rxmanagerdb";
	
	
	/**
	 * Get a new database connection
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
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Retrieve a record from the table PATIENTS
		try
		{
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM PATIENTS";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next())
			{
				String fname = rs.getString("p_fname");
				String lname = rs.getString("p_lname");
				Date dob = rs.getDate("p_dob");
				String id = rs.getString("p_id");
				
				System.out.println("First name: " + fname);
				System.out.println("Last name:" + lname);
				System.out.println("DOB: " + dob.toString());
				System.out.println("ID: " + id);
			}
			
				
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) 
	{
		MySQLDriverTester app = new MySQLDriverTester();
		app.run();
	}
}