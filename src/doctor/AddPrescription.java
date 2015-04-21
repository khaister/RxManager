package doctor;


import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.sql.Date;
import java.util.*;
import java.sql.*;
import pharmacist.backend.*;

import common.*;
import doctor.backend.*;

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
	private String docLicense;
	private String patientMedNumber;
	
	private Prescription rx;
	private Connection dbconnect;

	
	/**
	 * Create the shell.
	 * @param display
	 */
	public AddPrescription(Shell parent, Connection dbconnect) 
	{
		shell = new Shell(parent, SWT.SHELL_TRIM);
		shell.setSize(424, 316);
		
		////////////////////////////////////////////////////////////////////////
		//
		//                   Text fields & corresponding labels
		//
		////////////////////////////////////////////////////////////////////////
		
		// Drug name
		Label lblDrugName = new Label(shell, SWT.NONE);
		lblDrugName.setText("Drug:");
		lblDrugName.setBounds(21, 31, 64, 20);
		name = new Text(shell, SWT.BORDER);
		name.setBounds(91, 28, 290, 21);

		// Strength
		Label lblStrength = new Label(shell, SWT.NONE);
		lblStrength.setText("Strength:");
		lblStrength.setBounds(21, 60, 43, 20);
		strength = new Text(shell, SWT.BORDER);
		strength.setBounds(83, 57, 36, 21);
		
		// Route
		Label lblRoute = new Label(shell, SWT.NONE);
		lblRoute.setText("Route of administration:");
		lblRoute.setBounds(21, 117, 133, 20);
		route = new Text(shell, SWT.BORDER);
		route.setBounds(160, 114, 218, 21);
		
		// Frequency
		Label lblFrequency = new Label(shell, SWT.NONE);
		lblFrequency.setBounds(140, 60, 64, 15);
		lblFrequency.setText("Frequency:");
		frequency = new Text(shell, SWT.BORDER);
		frequency.setBounds(210, 57, 36, 21);
		
		// Quantity
		Label lblQuantity = new Label(shell, SWT.NONE);
		lblQuantity.setBounds(280, 91, 55, 15);
		lblQuantity.setText("Quantity:");
		quantity = new Text(shell, SWT.BORDER);
		quantity.setBounds(341, 85, 36, 21);

		// Max number of refills
		Label lblMaxRefills = new Label(shell, SWT.NONE);
		lblMaxRefills.setBounds(147, 91, 64, 15);
		lblMaxRefills.setText("Max Refills:");
		maxRefills = new Text(shell, SWT.BORDER);
		maxRefills.setBounds(220, 85, 36, 21);

		// Doctor's notes
		Label lblNotes = new Label(shell, SWT.NONE);
		lblNotes.setText("Notes:");
		lblNotes.setBounds(21, 180, 43, 20);
		notes = new Text(shell, SWT.BORDER);
		notes.setBounds(70, 180, 309, 57);
		
		// Window's title
		Label lblAddNewMedication = new Label(shell, SWT.NONE);
		lblAddNewMedication.setText("Add New Medication");
		lblAddNewMedication.setBounds(21, 10, 129, 15);

		// BUTTONS
		Button btnConfirm = new Button(shell, SWT.NONE);
		btnConfirm.setBounds(304, 243, 75, 25);
		btnConfirm.setText("Confirm");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(210, 243, 75, 25);
		btnCancel.setText("Cancel");
		
		// Pharmacies selection
		Label lblPharm = new Label(shell, SWT.NONE);
		lblPharm.setBounds(21, 144, 55, 15);
		lblPharm.setText("Pharmacy:");
		
		List lstPharms = new List(shell, SWT.BORDER);
		
		// Adds pharmacy info to list
		lstPharms.setBounds(91, 143, 288, 31);
		ArrayList<Pharmacy> pharmList = getPharmacies();
		
		for (Pharmacy p: pharmList)
			lstPharms.add(p.toString() + "\n");
		
		// CANCEL BUTTON
		btnCancel.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				shell.close(); // try to return to PatientConnect
			}
		});
		
		// CONFIRM BUTTON
		btnConfirm.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				//sets selected pharmacy
				Pharmacy pharm = new Pharmacy();
				pharmID = pharm.getBranchID();
				
				//Creates prescription from chars in text widgets
				System.out.println(maxRefills.getText());
				
				rx = new Prescription();
				rx.setName(name.getText());
				rx.setStrength(strength.getText());
				rx.setFrequency(frequency.getText());
				rx.setQuantity(quantity.getText());
						
				rx.setMaxRefills(Integer.parseInt(maxRefills.getText()));
				rx.setRefills(0);
				rx.setNotes(notes.getText());
				rx.setDatePrescribed(java.sql.Date.valueOf(java.time.LocalDate.now()));
				rx.setPharmacyID(pharmID);
				rx.setDocLicense("doctor license");
				rx.setPatientMedNumber("patient med number");
				
				//System.out.println(rx.toSQLInsertString());
				// sendPrescription(rx);
				// showPatientConnect(shell);
			}
		});
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
			Statement st = dbconnect.createStatement();
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

}