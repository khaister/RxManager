package pharmacist;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import common.*;
import doctor.backend.*;
import pharmacist.backend.*;

/**
 * Displays a list of prescriptions available at a given pharmacy
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class PrescriptionListViewer extends Window
{
	
	private Shell	shell;
	private Table	table; // table contains prescription and related info
	private Text	txtFirstName;
	private Text	txtLastName;
	private Text	txtMedicalNumber;

	ArrayList<Triplet> records; // records for each row in the table

	
	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public PrescriptionListViewer(Shell parent)
	{
		shell = new Shell(parent, SWT.SHELL_TRIM);
		parent.setVisible(false);
		shell.setSize(896, 532);
		shell.setText("Prescriptions");

		// TABLE: AVAILBLE RX
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 74, 876, 395);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnDatePrescribed = new TableColumn(table, SWT.NONE);
		tblclmnDatePrescribed.setWidth(100);
		tblclmnDatePrescribed.setText("Prescribed Date");
		
		TableColumn tblclmnPatient = new TableColumn(table, SWT.NONE);
		tblclmnPatient.setWidth(100);
		tblclmnPatient.setText("Patient");
		
		TableColumn tblclmnPrescriber = new TableColumn(table, SWT.NONE);
		tblclmnPrescriber.setWidth(100);
		tblclmnPrescriber.setText("Prescriber");

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(70);
		tblclmnName.setText("Name");

		TableColumn tblclmnStrength = new TableColumn(table, SWT.NONE);
		tblclmnStrength.setWidth(100);
		tblclmnStrength.setText("Strength");

		TableColumn tblclmnQuantity = new TableColumn(table, SWT.NONE);
		tblclmnQuantity.setWidth(100);
		tblclmnQuantity.setText("Quantity");
		
		TableColumn tblclmnDateFilled = new TableColumn(table, SWT.NONE);
		tblclmnDateFilled.setWidth(100);
		tblclmnDateFilled.setText("Filled Date");
		
		TableColumn tblclmnPatientDeliverypickUp = new TableColumn(table, SWT.NONE);
		tblclmnPatientDeliverypickUp.setWidth(158);
		tblclmnPatientDeliverypickUp.setText("Patient Delivery/Pick up");
		
		this.displayRecords(table);

		// BUTTON: View previous rx
		Button btnView = new Button(shell, SWT.NONE);
		
		btnView.setBounds(811, 475, 75, 25);
		btnView.setText("View");
		// TODO: This button allows user to view previous Rx, one at a time
		btnView.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				TableItem item = table.getSelection()[0];
				showViewPrescription(shell, (Prescription) item.getData());
			}
		});

		// BUTTON: Refresh the list
		Button btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.setBounds(713, 475, 95, 25);
		btnRefresh.setText("Refresh");
		btnRefresh.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				table.clearAll();
				table.setItemCount(0);
				Patient p = new Patient();
				p.setFirstName(txtFirstName.getText());
				getRxs(new Patient());
				for (Triplet tri : records)
				{
					TableItem item = new TableItem(table, SWT.NONE);
					item.setData(tri);
					Doctor d = tri.getDoctor(); 
					Prescription r = tri.getPrescription();
					p = tri.getPatient();
		
					item.setText(new String[]
						{r.getDatePrescribed().toString(),
						 p.getFullName(),
						 d.getFullName(),
						 r.getName(),
						 r.getStrength(), 
						 r.getQuantity(),
						 r.getDateFilled() == null ? "" : r.getDateFilled().toString(),
						 r.getDatePickedUp() == null ? "" : r.getDatePickedUp().toString()
						 });
				}
			}
		});
		
		// BUTTON: Cancel - Goes back to Patient connect?
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(632, 475, 75, 25);
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
		
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setBounds(10, 49, 374, 19);
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setBounds(390, 49, 403, 19);
		
		Label lblFilterByPatients = new Label(shell, SWT.NONE);
		lblFilterByPatients.setBounds(10, 29, 455, 14);
		lblFilterByPatients.setText("Filter by patient's name");
		
		Button btnRefine = new Button(shell, SWT.NONE);
		btnRefine.setBounds(799, 45, 95, 28);
		btnRefine.setText("Refine");
		
		
		

		// Actually open the shell
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (display.readAndDispatch())
				display.sleep();
		}
	}

	
	/**
	 * 
	 */
	public void getRxs(Patient patient)
	{
		String sql = "SELECT * "
				+ "FROM Doctors d "
				+ "INNER JOIN Prescriptions r "
					+ "ON d.dLicense = r.RxDocLicense "
				+ "INNER JOIN Patients p "
					+ "ON r.RxPatientMedNumber = p.pMedicalNumber"
				+ "WHERE p.pMedicalNumber = '" + patient.getMedicalNumber() + "'";
		
		records = null; // clear the records before adding new ones as a result from the query
		
		try
		{
			Statement st = RxManager.dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) // moving the cursor forward throu the rows
			{
				Doctor doctor = new Doctor();
				doctor.setFirstName(rs.getString("dFirstName"));
				doctor.setLastName(rs.getString("dLastName"));
				doctor.setDOB(rs.getDate("dDOB"));
				doctor.setLicense(rs.getString("dLicense"));
				
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
				rx.setDatePickedUp(rs.getDate("RxDatePickedUp"));
				rx.setIsFilled(rs.getBoolean("RxIsFilled"));
				rx.setPharmacyID(rs.getString("RxPharmacyID"));
				rx.setDocLicense(rs.getString("RxDocLicense"));
				rx.setPatientMedNumber(rs.getString("RxPatientMedNumber"));
				
				Patient p = new Patient();
				p.setFirstName(rs.getString("pFirstName"));
				p.setLastName(rs.getString("pLastName"));
				p.setDOB(rs.getDate("pDOB"));
				p.setAddress(rs.getString("pAddress"));
				p.setCity(rs.getString("pCity"));
				p.setState(rs.getString("pState"));
				p.setZipCode(rs.getString("pZipCode"));
				p.setMedicalNumber(rs.getString("pMedicalNumber"));
				
				Triplet aRow = new Triplet(doctor, rx, p);
				records.add(aRow);
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//return foundRxs;
	}
	
	
	public void displayRecords(Table table)
	{
		
	}
	
	/**
	 * 
	 * @param parent
	 * @param rx
	 */
	public void showViewPrescription(Shell parent, Prescription rx)
	{
		PrescriptionViewer rxViewer = new PrescriptionViewer(shell, rx);
	}
	
	
	/**
	 * Holds Doctor, Prescription, and Patient
	 * @author khaister
	 *
	 */
	private class Triplet
	{
		private Doctor doctor;
		private Prescription rx;
		private Patient patient;
		
		private Triplet()
		{
			this(null, null, null);
		}
		
		private Triplet(Doctor doc, Prescription rx, Patient patient)
		{
			this.doctor = doc;
			this.rx = rx;
			this.patient = patient;
		}
		
		public void setDoctor(Doctor doc)
		{
			this.doctor = doc; 
		}
		
		public void setPrescription(Prescription rx)
		{
			this.rx = rx;
		}
		
		public void setPatient(Patient patient)
		{
			this.patient = patient;
		}
		
		public Doctor getDoctor()
		{
			return this.doctor;
		}
		
		public Prescription getPrescription()
		{
			return this.rx;
		}
		
		public Patient getPatient()
		{
			return this.patient;
		}
	}
}