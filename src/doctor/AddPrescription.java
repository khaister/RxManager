package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.List;

import java.util.*;
import java.sql.*;

import common.*;
import doctor.backend.*;
import pharmacist.backend.*;

/**
 * This class allows the user to enter a new prescription. However, it does not
 * submit the rx but instead intantiates PrescriptionConfirm so that user can
 * preview the rx before actually submit it.
 * 
 * @author Khai Nguyen
 */
@SuppressWarnings("unused")
public class AddPrescription extends Window 
{
	private Shell shell;

	private Text name;
	private Text strength;
	private Text route;
	private Text frequency;
	private Text quantity;
	private Text maxRefills;
	private Text notes;
	private String pharmID;
	
	private Prescription rx;
	private Patient patient;
	private Pharmacy pharmacy;

	
	/**
	 * Create the shell.
	 * @param display
	 */
	public AddPrescription(Shell parent, Patient patient) 
	{
		shell = new Shell(parent, SWT.SHELL_TRIM);
		shell.setBounds(parent.getBounds().x+parent.getBounds().width,
				parent.getBounds().y, 424, 338);

		// Window's title
		shell.setText("Add New Medication");
		
		////////////////////////////////////////////////////////////////////////
		//
		//                   Text fields & corresponding labels
		//
		////////////////////////////////////////////////////////////////////////
		
		// Drug name
		Label lblDrugName = new Label(shell, SWT.NONE);
		lblDrugName.setText("Drug");
		lblDrugName.setBounds(21, 31, 64, 20);
		name = new Text(shell, SWT.BORDER);
		name.setBounds(107, 28, 307, 21);

		// Strength
		Label lblStrength = new Label(shell, SWT.NONE);
		lblStrength.setText("Strength");
		lblStrength.setBounds(21, 60, 64, 20);
		strength = new Text(shell, SWT.BORDER);
		strength.setBounds(107, 55, 94, 21);
		
		// Route
		Label lblRoute = new Label(shell, SWT.WRAP);
		lblRoute.setText("Route of administration");
		lblRoute.setBounds(21, 120, 80, 31);
		route = new Text(shell, SWT.BORDER | SWT.MULTI);
		route.setBounds(107, 117, 307, 34);
		
		// Frequency
		Label lblFrequency = new Label(shell, SWT.NONE);
		lblFrequency.setBounds(210, 60, 64, 15);
		lblFrequency.setText("Frequency");
		frequency = new Text(shell, SWT.BORDER);
		frequency.setBounds(280, 55, 134, 21);
		
		// Quantity
		Label lblQuantity = new Label(shell, SWT.NONE);
		lblQuantity.setBounds(210, 88, 55, 15);
		lblQuantity.setText("Quantity");
		quantity = new Text(shell, SWT.BORDER);
		quantity.setBounds(280, 82, 134, 21);

		// Max number of refills
		Label lblMaxRefills = new Label(shell, SWT.NONE);
		lblMaxRefills.setBounds(21, 86, 64, 15);
		lblMaxRefills.setText("Max Refills");
		maxRefills = new Text(shell, SWT.BORDER);
		maxRefills.setBounds(107, 82, 94, 21);

		// Doctor's notes
		Label lblNotes = new Label(shell, SWT.NONE);
		lblNotes.setText("Notes");
		lblNotes.setBounds(21, 208, 43, 20);
		notes = new Text(shell, SWT.BORDER);
		notes.setBounds(107, 205, 307, 57);
		
		// Pharmacies selection
		Label lblPharm = new Label(shell, SWT.NONE);
		lblPharm.setBounds(21, 168, 55, 15);
		lblPharm.setText("Pharmacy");
		List lstPharms = new List(shell, SWT.BORDER);
		
		// Adds pharmacy info to list
		lstPharms.setBounds(107, 168, 307, 31);
		ArrayList<Pharmacy> pharmList = getPharmacies();
		
		for (Pharmacy pharm: pharmList)
		{
			lstPharms.add(pharm.getName());
			lstPharms.setData(pharm.getName(), pharm);
		}
		
		// BUTTONS
		Button btnPreview = new Button(shell, SWT.NONE);
		btnPreview.setBounds(339, 285, 75, 25);
		btnPreview.setText("Preview");
		btnPreview.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				Pharmacy pharm = (Pharmacy) lstPharms.getData(lstPharms.getSelection()[0]);
				
				rx = new Prescription();
				rx.setName(name.getText());
				rx.setStrength(strength.getText());
				rx.setFrequency(frequency.getText());
				rx.setQuantity(quantity.getText());
				rx.setRoute(route.getText());		
				rx.setMaxRefills(Integer.parseInt(maxRefills.getText()));
				rx.setRefills(0);
				rx.setNotes(notes.getText());
				rx.setDatePrescribed(java.sql.Date.valueOf(java.time.LocalDate.now()));
				rx.setDateFilled(null);
				rx.setIsFilled(false);
				rx.setPharmacyID(pharm.getBranchID());
				rx.setDocLicense(RxManager.doctor.getLicense());
				rx.setPatientMedNumber(patient.getMedicalNumber());
				
				
				showPrescriptionConfirm(rx, patient, pharm);
			}
		});
		
		// BUTTON: Cancel - goes back to PatientInfo
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(258, 285, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				shell.getParent().setVisible(true);
				shell.dispose();
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
	 * Gets pharmacies from the database.
	 * 
	 * @return
	 */
	public ArrayList<Pharmacy> getPharmacies()
	{
		ArrayList<Pharmacy> foundPharmacies = new ArrayList<Pharmacy>();
		String sql = "SELECT * FROM Pharmacies";
		try
		{
			Statement st = RxManager.dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) // moving the cursor forward throu the rows
			{
				Pharmacy p = new Pharmacy();
				p.setName(rs.getString("phyName"));
				p.setAddress(rs.getString("phyAddress"));
				p.setCity(rs.getString("phyCity"));
				p.setState(rs.getString("phyState"));
				p.setZipCode(rs.getString("phyZipCode"));
				p.setBranchID(rs.getString("phyBranchID"));
				p.setPhone(rs.getString("phyPhone"));
				
				foundPharmacies.add(p);
			}
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundPharmacies;
	}
	
	/**
	 * 
	 * @param rx
	 * @param patient
	 * @param pharm
	 */
	public void showPrescriptionConfirm(Prescription rx, Patient patient, Pharmacy pharm)
	{
		PrescriptionConfirm rxConfirm = new PrescriptionConfirm(shell, rx, patient, pharm);
	}

}