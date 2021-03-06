package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*; 

import java.sql.*;
import java.util.Properties;

import common.*;
import doctor.backend.*;
import static java.lang.System.out;

/**
 * When the application starts, an instance of this class is created first. It
 * provides a login window for user to enter their credential (username and
 * password). Upon successful authentication, the instance calls a 
 * PatientConnect object.
 * 
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class RxManagerDoc extends Window
{
	// SQL Log in
	private static String userName = "lbcs";
	private static String password = "rxmanager";
	private static String serverName = "ec2-52-8-46-242.us-west-1.compute.amazonaws.com";
	private static int portNumber = 3306;
	private static String dbName = "rxmanagerdb";
	public static Connection dbconnect;

	// other fields
	public static Doctor doctor;
		
	// GUI fields
	private Text txtUsername;
	private Text txtPassword;
	
	// TODO: Log out option
	/*--------------------MAIN: STARTING POINT OF THE APPLICATION-------------*/
	/**
	 * @param args Command line arguments.
	 */
	public static void main(String[] args)
	{
		getConnection();
		
		Display display = new Display();
		Shell rxman = new RxManagerDoc().open(display);
		
		while (!rxman.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
	}
	/*------------------------------------------------------------------------*/

	
	/**
	 * Connects to MySQL database
	 */
	public static void getConnection()
	{
		try
		{
			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);

			dbconnect = DriverManager.getConnection("jdbc:mysql://"
					+ serverName + ":" + portNumber + "/" + dbName,
					connectionProps);
		}
		catch (SQLException e)
		{
			out.println(e.getMessage());
		}
	}
	
	/**
	 * Creates a shell and set all the elements.
	 * @param display The display on which the shell is created.
	 * @return The shell.
	 */
	public Shell open(Display display)
	{
		Shell shell = new Shell(display);
		
		shell.setSize(450, 300);
		shell.setText("Login - RxManager");

		// TEXT: Username
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(56, 99, 66, 21);
		lblUsername.setText("Username");
		txtUsername = new Text(shell, SWT.BORDER);
		txtUsername.setBounds(128, 99, 176, 21);

		// TEXT: Password
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(56, 126, 66, 21);
		lblPassword.setText("Password");
		txtPassword = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setBounds(128, 123, 176, 21);
		
		// MESSAGE: Wrong password message
		Label lblWrongPassword = new Label(shell, SWT.WRAP);
		lblWrongPassword.setBounds(129, 149, 175, 55);
		lblWrongPassword.setText("Username/Password combination is incorrect!");
		lblWrongPassword.setAlignment(SWT.CENTER);
		lblWrongPassword.setVisible(false);
		
		// BUTTON: Sign in
		Button btnSignIn = new Button(shell, SWT.NONE);
		btnSignIn.setGrayed(true);
		btnSignIn.setBounds(128, 210, 176, 26);
		btnSignIn.setText("Sign in");
		btnSignIn.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				boolean correctCredential = checkCredential(txtUsername.getText(), txtPassword.getText());
				
				if (correctCredential)
				{
					doctor = getDoctorRecord(txtUsername.getText());
					showPatientConnect(shell);
				}
				
				// else show error msg
				else
				{
					lblWrongPassword.setVisible(true);
					txtPassword.setText("");
					txtPassword.setSelection(0);
				}
			}
		});
		
		// Set tab order
		Control[] tabOrder = new Control[] {txtUsername, txtPassword, btnSignIn};
		shell.setTabList(tabOrder);
		
		shell.open();
		return shell;
	}

	
	/**
	 * Checks whether the credential entered by user is correct or not.
	 *  
	 * @param username
	 * @param password
]	 */
	public boolean checkCredential(String username, String password)
	{
		boolean correctCredential = false;
		String sql = "SELECT dPassword FROM Doctors WHERE dUsername = '" + username + "'";
		
		try
		{
			Statement st = dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.first()) // go to the first row in the result set (should only have one row)
			{
				if (rs.getString("dPassword").compareTo(password) == 0)
					correctCredential = true;
				else
					out.println("Password does not match or username does not exist!");
			}
			else
				out.println("There is no record in the database!");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return correctCredential;
	}

	
	/**
	 * Obtains the doctor's record from the database.
	 * @param username Username of the doctor.
	 * @return The Doctor object that matches the username.
	 */
	public static Doctor getDoctorRecord(String username)
	{
		String sql = "SELECT * FROM Doctors WHERE dUsername = '" + username + "'";
		Doctor foundDoc = null;
		
		try
		{
			Statement st = dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			rs.first();
			foundDoc = new Doctor(rs.getString("dFirstName"), 
					rs.getString("dLastName"), rs.getDate("dDOB"), 
					rs.getString("dLicense"), rs.getString("dUsername"),
					rs.getString("dPassword"));
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundDoc;
	}

	
	/**
	 * Displays the PatientConnect Shell.
	 * @param parent The parent Shell from which the PatientConnect Shell is called.
	 */
	public void showPatientConnect(Shell parent)
	{
		PatientConnect patientConnect = new PatientConnect(parent);
	}
}