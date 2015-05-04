package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import java.sql.*;

import common.*;
import doctor.backend.*;
import pharmacist.backend.*;

/**
 * Preview the prescription before submission to the database.
 * @author khaister
 *
 */
public class PrescriptionConfirm extends Window
{
	private Shell shell;
	private Text txtPatientInfo;
	private Text txtRx;
	private Text txtPharm;
	private Text txtDoctor;
	private Text txtDoctorSignature;
	
	public PrescriptionConfirm(Shell parent, Prescription rx, Patient patient, Pharmacy pharm)
	{
		shell = new Shell(parent, SWT.CHECK);
		shell.setText("Confirm Prescription");
		shell.setSize(450, 548);
		
		// TEXT: Patient info
		Label lblPatient = new Label(shell, SWT.NONE);
		lblPatient.setBounds(10, 10, 60, 14);
		lblPatient.setText("Patient");
		txtPatientInfo = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtPatientInfo.setBounds(10, 34, 430, 101);
		String patientInfo = "";
		patientInfo += patient.getFirstName() + "\t" + patient.getLastName() + "\n";
		patientInfo += patient.getAddress() + "\n";
		patientInfo += patient.getCity() + ", " + patient.getState() + " " + patient.getZipCode() + "\n";
		patientInfo += patient.getPhone() + "\n";
		patientInfo += patient.getMedicalNumber() + "\n";
		txtPatientInfo.setText(patientInfo);
		
		// TEXT: Rx info
		Label lblRx = new Label(shell, SWT.NONE);
		lblRx.setBounds(10, 141, 60, 14);
		lblRx.setText("Rx");
		txtRx = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtRx.setBounds(10, 162, 430, 129);
		String rxInfo = "";
		rxInfo += "Name: " + rx.getName() + ", " + rx.getStrength() + "\n";
		rxInfo += "Route: " + rx.getRoute() + "\n";
		rxInfo += rx.getFrequency() + "\n";
		rxInfo += rx.getQuantity() + "\n";
		rxInfo += "Refills: " + rx.getMaxRefills() + "(done: " + rx.getRefills() + ")\n";
		rxInfo += "Precribed date: " + rx.getDatePrescribed() + "\n";
		rxInfo += "Fill date: " + rx.getDateFilled() + "\n";
		rxInfo += "Pharmacy ID: " + rx.getPharmID() + "\n";
		rxInfo += "Doc license: " + rx.getDocLicense() + "\n";
		rxInfo += rx.getPatientMedNumber();
		txtRx.setText(rxInfo);
		
		// TEXT: Pharmacy info
		Label lblPharmacy = new Label(shell, SWT.NONE);
		lblPharmacy.setBounds(10, 309, 60, 14);
		lblPharmacy.setText("Pharmacy");
		txtPharm = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtPharm.setBounds(10, 329, 197, 56);
		String pharmInfo = "";
		pharmInfo += pharm.getName() + "\n";
		pharmInfo += pharm.getAddress() + "\n";
		pharmInfo += pharm.getCity() + "\n";
		pharmInfo += pharm.getState() + "\n";
		pharmInfo += pharm.getZipCode() + "\n";
		pharmInfo += pharm.getBranchID();
		txtPharm.setText(pharmInfo);
		
		// TEXT: Doctor info
		Label lblPrescriber = new Label(shell, SWT.NONE);
		lblPrescriber.setBounds(222, 309, 60, 14);
		lblPrescriber.setText("Prescriber");
		txtDoctor = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtDoctor.setBounds(221, 329, 219, 56);
		String docInfo = "";
		docInfo += RxManagerDoc.doctor.getFullName() + "\n";
		docInfo += RxManagerDoc.doctor.getLicense();
		txtDoctor.setText(docInfo);
		

		// BUTTON: Send - submit to the database & go back to PatientInfo
		Button btnSend = new Button(shell, SWT.NONE);
		btnSend.setBounds(345, 472, 95, 28);
		btnSend.setText("Send");
		btnSend.setGrayed(true);
		//if (!btnSend.getGrayed())
			btnSend.addSelectionListener(new SelectionAdapter()
			{
				public void widgetSelected(SelectionEvent e)
				{
					System.out.println(rx.toString());
					sendPrescription(rx);
					shell.getParent().getParent().setVisible(true);
					shell.dispose();
					// TODO Update PatientInfo's rx list?
				}
			});
		
		
		// BUTTON: Cancel - Goes back to AddPrescription
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(247, 472, 95, 28);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				shell.getParent().setVisible(true);
				shell.dispose();
			}
		});

		// TEXT: Signaure - Doctor has to enter full name in order to submit Rx
		Label lblSignature = new Label(shell, SWT.NONE);
		lblSignature.setBounds(10, 391, 60, 14);
		lblSignature.setText("Signature");
		txtDoctorSignature = new Text(shell, SWT.BORDER);
		txtDoctorSignature.setBounds(10, 411, 430, 19);
//		txtDoctorSignature.addKeyListener(new KeyAdapter()
//		{
//			public void keyPressed(KeyEvent e)
//			{
//				String docFullName = RxManager.doctor.getFirstName() + " " + RxManager.doctor.getLastName();
//				if (txtDoctorSignature.getText().compareTo(docFullName) == 0)
//					btnSend.setGrayed(false);
//			}
//		});
		
		
		// Actually open the window
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
	/**
	 * Sends prescription to the database.
	 * @param rx Prescription to be sent.
	 */
	public void sendPrescription(Prescription rx)
	{
		String sql = "INSERT INTO Prescriptions VALUES " + rx.toSQLInsertString();
		System.out.println(sql);
		
		try
		{
			Statement st = RxManagerDoc.dbconnect.createStatement();
			st.executeUpdate(sql);
			//rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}