package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.util.*;
import java.sql.*;
import java.util.regex.*;

import common.*;
import doctor.backend.*;

// TODO: One field for searching by name (need to have regex)

/**
 * Displays a list of patients found by typing information into the appropriate
 * search box. The selected patient is then viewed in an instance of PatientInfo.
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class ReturningPatientSearch extends Window
{
	private ArrayList<Patient> foundPatients;
	private Patient queryPatient;
	
	Shell shell;
	private Text txtFirstName;
	private Text txtLastName;
	private Text txtMedicalNumber;
	private Table table;
	
	Pattern nameSplitter = Pattern.compile("\\w+\\s+\\w+"); // \w+\s+\w+
	
	// TESTING PURPOSES
	public static void main(String[] args)
	{
		ReturningPatientSearch returnPatientSearch 
			= new ReturningPatientSearch(new Shell(new Display()));
	}
	
	public ReturningPatientSearch(Shell parent) 
	{
		queryPatient = new Patient();
		
		shell = new Shell(parent, SWT.SHELL_TRIM);
		parent.setVisible(false);
		
		shell.setSize(450, 385);
		shell.setText("Returning Patient Search");
				
		// TEXT: Search boxes
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(10, 38, 43, 20);
		lblName.setText("Name");
		
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setToolTipText("First name");
		txtFirstName.setBounds(104, 35, 169, 21);
		txtFirstName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				queryPatient = new Patient();
				queryPatient.setFirstName(txtFirstName.getText());
				foundPatients = getPatients(queryPatient);
				table.clearAll();
				table.setItemCount(0);
				for (Patient patient : foundPatients)
					// TODO: Use Table and a different toString method
				{
					TableItem item = new TableItem(table, SWT.NONE);
					item.setData(patient);
					item.setText(new String[] {patient.getFirstName(), 
							patient.getLastName(), patient.getDOB().toString(), 
							patient.getPhone()});
				}
			}
		});
		
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setToolTipText("Last name");
		txtLastName.setBounds(279, 35, 161, 21);
		txtLastName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				queryPatient = new Patient();
				queryPatient.setLastName(txtLastName.getText());
				foundPatients = getPatients(queryPatient);
				table.clearAll();
				table.setItemCount(0);
				for (Patient patient : foundPatients)
					// TODO: Use Table and a different toString method
				{
					TableItem item = new TableItem(table, SWT.NONE);
					item.setData(patient);
					item.setText(new String[] {patient.getFirstName(), 
							patient.getLastName(), patient.getDOB().toString(), 
							patient.getPhone()});
				}
			}
		});
		
		// Medical number
		Label lblPatientMedNumber = new Label(shell, SWT.NONE);
		lblPatientMedNumber.setBounds(10, 91, 90, 20);
		lblPatientMedNumber.setText("Medical Number");
		txtMedicalNumber = new Text(shell, SWT.BORDER);
		txtMedicalNumber.setBounds(104, 88, 336, 21);
		txtMedicalNumber.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				queryPatient = new Patient();
				queryPatient.setMedicalNumber(txtMedicalNumber.getText());
				foundPatients = getPatients(queryPatient);
				table.clearAll();
				table.setItemCount(0);
				for (Patient patient : foundPatients)
					// TODO: Use Table and a different toString method
				{
					TableItem item = new TableItem(table, SWT.NONE);
					item.setData(patient);
					item.setText(new String[] {patient.getFirstName(), 
							patient.getLastName(), patient.getDOB().toString(), 
							patient.getPhone()});
				}
			}
		});
		
		// BUTTON: View selected patient
		Button btnView = new Button(shell, SWT.NONE);
		btnView.setBounds(350, 323, 90, 30);
		btnView.setText("View");
		btnView.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				// TODO: need to convert from sel to Patient
				TableItem item = table.getSelection()[0];
				Patient patient = (Patient) item.getData();
				showPatientInfo(shell, patient);
			}
		});
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(254, 323, 90, 30);
		btnCancel.setText("Cancel");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 129, 430, 172);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnFirstName = new TableColumn(table, SWT.NONE);
		tblclmnFirstName.setWidth(100);
		tblclmnFirstName.setText("First Name");
		
		TableColumn tblclmnLastName = new TableColumn(table, SWT.NONE);
		tblclmnLastName.setWidth(100);
		tblclmnLastName.setText("Last Name");
		
		TableColumn tblclmnDob = new TableColumn(table, SWT.NONE);
		tblclmnDob.setWidth(100);
		tblclmnDob.setText("DOB");
		
		TableColumn tblclmnPhoneNumber = new TableColumn(table, SWT.NONE);
		tblclmnPhoneNumber.setWidth(100);
		tblclmnPhoneNumber.setText("Phone Number");
		
		// A separator between the search fields
		Label lblHonBarLeft = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblHonBarLeft.setBounds(104, 64, 139, 21);
		Label lblHonBarRight = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblHonBarRight.setBounds(285, 62, 156, 21);
		Label lblOr = new Label(shell, SWT.NONE);
		lblOr.setAlignment(SWT.CENTER);
		lblOr.setBounds(254, 68, 25, 14);
		lblOr.setText("OR");
		
		btnCancel.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				// try to return to PatientConnect
				shell.getParent().setVisible(true);
				shell.close(); 
			}
		});
		
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
	 * Finds all patient records with give patient information.
	 * @param patient Patient whose record is being searched.
	 * @return List of patient records match the given information.
	 */
	public ArrayList<Patient> getPatients(Patient patient)
	{
		ArrayList<Patient> foundPatients = new ArrayList<Patient>();
		
		String sql = "SELECT * FROM Patients WHERE ";
		
		// If there is a first name
		if (patient.getFirstName().length() > 0)
		{
			sql += "pFirstName = '" + patient.getFirstName() + "'";
			
			//If theres a last name
			if (patient.getLastName().length() > 0)
				sql += " AND pLastName = '" + patient.getLastName() + "'";

			//If theres a medical number
			if (patient.getMedicalNumber().length() > 0)
				sql += " AND pMedicalNumber = '" + patient.getMedicalNumber() + "'";
		}

		// If there isn't a first but is a last
		else if (patient.getLastName().length() > 0)
		{
			sql += "pLastName = '" + patient.getLastName() + "'";

			//If theres a medical number
			if(patient.getMedicalNumber().length() > 0)
				sql += " and pMedicalNumber = '" + patient.getMedicalNumber() + "'";
			
		}

		// If theres only a medical number
		else if (patient.getMedicalNumber().length() > 0)
			sql += "pMedicalNumber = '" + patient.getMedicalNumber() + "'";
	
		System.out.println(sql);
		
		// Retrieve a record from the table Patients
		try
		{
			Statement st = RxManager.dbconnect.createStatement();
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
	
	public void showPatientInfo(Shell parent, Patient patient)
	{
		PatientInfo patientInfo = new PatientInfo(parent, patient);
	}
}