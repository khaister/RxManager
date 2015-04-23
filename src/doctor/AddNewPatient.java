package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.sql.*;

import common.*;
import doctor.backend.*;

// TODO: Add confirmation for buttons
// TODO: Add checkpoint that the patient being added is not in the database yet

/**
 * This class represents a window that allows user to enter information for a 
 * new patient whose record is not in the database yet. After the information is
 * entered, user is presented with PatientInfo, which shows the newly-saved 
 * patient record.
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class AddNewPatient extends Window
{
	private Shell shell;
	private Text txtFirstName;
	private Text txtLastName;
	private DateTime dtDOB;
	private Text txtPhone;
	private Text txtAddress;
	private Text txtCity;
	private Text txtState;
	private Text txtZipCode;
	private Text txtMedicalNumber;
	
	// FOR TESTING PURPOSES
	public static void main(String[] args)
	{
		AddNewPatient addNewPatient = new AddNewPatient(new Shell(new Display()));
	}
	
	
	/**
	 * Create the shell.
	 * @param display
	 */
	public AddNewPatient(Shell parent) 
	{
		shell = new Shell(parent, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
		parent.setVisible(false);
		
		shell.setText("Add New Patient");
		shell.setBounds(parent.getBounds().x, parent.getBounds().y, 428, 302);
		
		// TEXT: First name
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setBounds(10, 10, 210, 15);
		lblFirstName.setText("First name");
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setBounds(10, 25, 210, 21);

		// TEXT: Last name
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setBounds(226, 10, 188, 15);
		lblLastName.setText("Last name");
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setBounds(226, 25, 188, 21);
				
		// FIELD: DOB
		// TODO: Need a better Date box (for easy selection)
		Label lblDOB = new Label(shell, SWT.NONE);
		lblDOB.setBounds(10, 55, 210, 21);
		lblDOB.setText("Date of birth");
		dtDOB = new DateTime(shell, SWT.BORDER);
		dtDOB.setBounds(10, 70, 210, 25);
		
		// TEXT: Address (Street name & number)
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBounds(10, 100, 404, 21);
		lblAddress.setText("Address");
		txtAddress = new Text(shell, SWT.BORDER);
		txtAddress.setBounds(10, 115, 404, 21);
		
		// TEXT: City
		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(10, 145, 25, 15);
		lblCity.setText("City");
		txtCity = new Text(shell, SWT.BORDER);
		txtCity.setBounds(10, 166, 191, 21);
		
		// TEXT: State
		Label lblState = new Label(shell, SWT.NONE);
		lblState.setBounds(207, 145, 32, 15);
		lblState.setText("State");
		txtState = new Text(shell, SWT.BORDER);
		txtState.setBounds(207, 166, 47, 21);
		
		// TEXT: Zip code
		Label lblZipCode = new Label(shell, SWT.NONE);
		lblZipCode.setBounds(260, 145, 25, 15);
		lblZipCode.setText("Zip");
		txtZipCode = new Text(shell, SWT.BORDER);
		txtZipCode.setBounds(260, 166, 154, 21);

		// TEXT: Phone
		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setBounds(225, 55, 189, 21);
		lblPhoneNumber.setText("Phone");
		txtPhone = new Text(shell, SWT.BORDER);
		txtPhone.setBounds(226, 70, 188, 21);

		// TEXT: Medical number
		Label lblMedicalNumber = new Label(shell, SWT.NONE);
		lblMedicalNumber.setBounds(10, 206, 80, 15);
		lblMedicalNumber.setText("Medical ID");
		txtMedicalNumber = new Text(shell, SWT.BORDER);
		txtMedicalNumber.setBounds(10, 220, 404, 21);

				
		////////////////////////////////////////////////////////////////////////
		//
		//                             BUTTONS
		//
		////////////////////////////////////////////////////////////////////////
		
		// BUTTON: CANCEL - Click to return to PatientConnect Shell
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(260, 247, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			// trying to go back to previous windows
			public void widgetSelected(SelectionEvent e)
			{
				shell.getParent().setVisible(true);
				shell.dispose(); 
			}
		});
		
		// BUTTON: SAVE - Click to save Patient to the database and show ??
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(339, 247, 75, 25);
		btnSave.setText("Save");
		btnSave.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				// Format dob in sql format
				String date = dtDOB.getYear() + "-";
				if (dtDOB.getMonth() < 10) date += "0";
				date += dtDOB.getMonth() + "-";
				if (dtDOB.getDay() < 10) date += "0";
				date += dtDOB.getDay();
				
				Patient patient 
					= new Patient(txtFirstName.getText(), txtLastName.getText(), 
							java.sql.Date.valueOf(date), txtPhone.getText(),
							txtAddress.getText(), txtCity.getText(), 
							txtState.getText(), txtZipCode.getText(),
							txtMedicalNumber.getText());
				
				sendPatient(patient);
				showPatientInfo(shell, patient);
			}
		});

		// Tab order
		Control[] tabOrder = new Control[]
			{txtFirstName, txtLastName, dtDOB, txtPhone, txtAddress,
			 txtCity, txtState, txtZipCode, txtMedicalNumber, btnCancel, 
			 btnSave};
		shell.setTabList(tabOrder);
		
		// Actually open the shell
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	
	/**
	 * Inserts new patient record into the database.
	 * @param patient Patient to be inserted into the database.
	 */
	public void sendPatient(Patient patient)
	{
		String sql = "INSERT INTO Patients VALUES " + patient.toSQLInsertString();

		try
		{
			Statement st = RxManager.dbconnect.createStatement();
			st.executeUpdate(sql);	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * Opens PatientInfo to view information of the patient, including Rx.
	 * 
	 * @param parent The parent shell.
	 * @param patient The patient whose information to be viewed.
	 */
	public void showPatientInfo(Shell parent, Patient patient)
	{
		PatientInfo patientInfo = new PatientInfo(parent, patient);
	}
}