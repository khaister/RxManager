package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import common.*;
import doctor.backend.*;

/**
 * Displays personal information of the patient, along with previous medications.
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class PatientInfo extends Window
{
	private Patient	patient;
	private Shell	shell;
	private Table	table;
	private Text	txtFirstName;
	private Text	txtLastName;
	private Text	txtDOB;
	private Text	txtAddress;
	private Text	txtCity;
	private Text	txtState;
	private Text	txtZipCode;
	private Text	txtPhone;
	private Text	txtMedicalNumber;

	// TESTING PURPOSES
	public static void main(String[] args)
	{
		Patient pat = new Patient();
		pat.setFirstName("Jane");
		pat.setLastName("Doe");
		pat.setDOB(new java.sql.Date(10000000));
		pat.setAddress("123 Long Beach Blvd");
		pat.setCity("Long Beach");
		pat.setState("CA");
		pat.setZipCode("90840");
		pat.setPhone("7141234567");
		pat.setMedicalNumber("P111111");
		PatientInfo patInfo = new PatientInfo(new Shell(new Display()), pat);
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public PatientInfo(Shell parent, Patient patient)
	{
		this.patient = patient;

		shell = new Shell(parent, SWT.SHELL_TRIM);
		parent.setVisible(false);
		shell.setSize(896, 532);
		shell.setText("Patient Information");

		// TEXT: First name
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setBounds(10, 10, 210, 15);
		lblFirstName.setText("First name");
		txtFirstName = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtFirstName.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtFirstName.setBounds(10, 25, 210, 21);
		txtFirstName.setText(patient.getFirstName());

		// TEXT: Last name
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setBounds(226, 10, 188, 15);
		lblLastName.setText("Last name");
		txtLastName = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtLastName.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtLastName.setBounds(226, 25, 188, 21);
		txtLastName.setText(patient.getLastName());

		// FIELD: DOB
		// TODO: Need a better Date box (for easy selection)
		Label lblDOB = new Label(shell, SWT.NONE);
		lblDOB.setBounds(10, 55, 210, 21);
		lblDOB.setText("Date of birth");
		txtDOB = new Text(shell, SWT.BORDER);
		txtDOB.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtDOB.setBounds(10, 70, 210, 21);
		txtDOB.setText(patient.getDOB().toString());

		// TEXT: Address (Street name & number)
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBounds(10, 100, 404, 21);
		lblAddress.setText("Address");
		txtAddress = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtAddress.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtAddress.setBounds(10, 115, 404, 21);
		txtAddress.setText(patient.getAddress());

		// TEXT: City
		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(10, 145, 25, 15);
		lblCity.setText("City");
		txtCity = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtCity.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtCity.setBounds(40, 142, 168, 21);
		txtCity.setText(patient.getCity());

		// TEXT: State
		Label lblState = new Label(shell, SWT.NONE);
		lblState.setBounds(222, 145, 32, 21);
		lblState.setText("State");
		txtState = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtState.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtState.setBounds(260, 142, 32, 21);
		txtState.setText(patient.getState());

		// TEXT: Zip code
		Label lblZipCode = new Label(shell, SWT.NONE);
		lblZipCode.setBounds(307, 145, 25, 15);
		lblZipCode.setText("Zip");
		txtZipCode = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtZipCode.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtZipCode.setBounds(334, 142, 80, 21);
		txtZipCode.setText(patient.getZipCode());

		// TEXT: Phone
		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setBounds(225, 55, 189, 21);
		lblPhoneNumber.setText("Phone");
		txtPhone = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtPhone.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtPhone.setBounds(226, 70, 188, 21);
		if (patient.getPhone().length() == 10)
			txtPhone.setText(this.patient.getPhone().substring(0, 3) + "-"
					+ this.patient.getPhone().substring(3, 6) + "-"
					+ this.patient.getPhone().substring(6, 10));
		else
			txtPhone.setText(this.patient.getPhone());

		// TEXT: Medical number
		Label lblMedicalNumber = new Label(shell, SWT.NONE);
		lblMedicalNumber.setBounds(10, 181, 80, 21);
		lblMedicalNumber.setText("Medical ID");
		txtMedicalNumber = new Text(shell, SWT.BORDER);
		txtMedicalNumber.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WIDGET_LIGHT_SHADOW));
		txtMedicalNumber.setBounds(10, 198, 404, 21);
		txtMedicalNumber.setText(patient.getMedicalNumber());

		Label lblListRx = new Label(shell, SWT.NONE);
		lblListRx.setText("Previous Rx");
		lblListRx.setBounds(10, 244, 344, 20);

		// TABLE: PREVIOUS RX
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 270, 876, 199);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(70);
		tblclmnName.setText("Name");

		TableColumn tblclmnStrength = new TableColumn(table, SWT.NONE);
		tblclmnStrength.setWidth(100);
		tblclmnStrength.setText("Strength");

		TableColumn tblclmnQuantity = new TableColumn(table, SWT.NONE);
		tblclmnQuantity.setWidth(100);
		tblclmnQuantity.setText("Quantity");
		
		TableColumn tblclmnPrescriber = new TableColumn(table, SWT.NONE);
		tblclmnPrescriber.setWidth(100);
		tblclmnPrescriber.setText("Prescriber");

		TableColumn tblclmnDatePrescribed = new TableColumn(table, SWT.NONE);
		tblclmnDatePrescribed.setWidth(100);
		tblclmnDatePrescribed.setText("Date Prescribed");
		
		TableColumn tblclmnDateFilled = new TableColumn(table, SWT.NONE);
		tblclmnDateFilled.setWidth(100);
		tblclmnDateFilled.setText("Date Filled");
		
		ArrayList<Prescription> Rxs = this.getRxs(patient);
		for (Prescription rx : Rxs)
		{
			TableItem item = new TableItem(table, SWT.NONE);
			item.setData(rx);
			item.setText(new String[]
				{rx.getName(), rx.getStrength(), rx.getQuantity(),
				 rx.getDocLicense(), rx.getDatePrescribed().toString(), 
				 rx.getDateFilled() == null ? "" : rx.getDateFilled().toString()});
		}

		// BUTTON: Add new Rx
		Button btnAddNewPrescription = new Button(shell, SWT.NONE);
		btnAddNewPrescription.setBounds(747, 475, 139, 25);
		btnAddNewPrescription.setText("Add Rx");
		btnAddNewPrescription.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				showAddPrescription(shell, patient);
			}
		});

		// BUTTON: View previous rx
		Button btnView = new Button(shell, SWT.NONE);
		
		btnView.setBounds(664, 475, 75, 25);
		btnView.setText("View");
		// TODO: This button allows user to view previous Rx, one at a time
		btnView.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				TableItem item = table.getSelection()[0];
				showViewPrescription(shell, patient, (Prescription) item.getData());
			}
		});

		// BUTTON: Cancel - Goes back to Patient connect?
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(580, 475, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				// Try to go back to PatientConnect
				// Parent of this shell is either AddNewPatient or
				// ReturningPatientSearch,
				// each of which has PatientConnect as parent
				shell.getParent().getParent().setVisible(true);
				shell.dispose();
			}
		});

		// Actually open the shell
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (display.readAndDispatch())
				display.sleep();
		}
	}

	public ArrayList<Prescription> getRxs(Patient patient)
	{
		ArrayList<Prescription> foundRxs = new ArrayList<Prescription>();
		String sql = "SELECT * FROM Prescriptions WHERE RxPatientMedNumber = '";
		sql += patient.getMedicalNumber() + "'";
		
		try
		{
			Statement st = RxManager.dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) // moving the cursor forward throu the rows
			{
				Prescription rx = new Prescription();
				rx.setName(rs.getString("RxName"));
				rx.setStrength(rs.getString("RxStrength"));
				rx.setRoute(rs.getString("RxRoute"));
				rx.setFrequency(rs.getString("RxFrequency"));
				rx.setQuantity(rs.getString("RxQuantity"));
				rx.setMaxRefills(rs.getInt("RxMaxRefills"));
				rx.setRefills(rs.getInt("RxRefills"));
				rx.setNotes(rs.getString("RxNote"));
				rx.setDatePrescribed(rs.getDate("RxDatePrescribed"));
				rx.setDateFilled(rs.getDate("RxDateFilled"));
				rx.setIsFilled(rs.getBoolean("RxIsFilled"));
				rx.setPharmacyID(rs.getString("RxPharmacyID"));
				rx.setDocLicense(rs.getString("RxDocLicense"));
				rx.setPatientMedNumber(rs.getString("RxPatientMedNumber"));
				
				foundRxs.add(rx);
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundRxs;
	}
	
	/**
	 * 
	 * @param parent
	 * @param patient
	 */
	public void showAddPrescription(Shell parent, Patient patient)
	{
		AddPrescription addRx = new AddPrescription(parent, patient);
	}
	
	public void showViewPrescription(Shell parent, Patient patient, Prescription rx)
	{
		ViewPrescription viewRx = new ViewPrescription(shell, rx, patient);
	}
}