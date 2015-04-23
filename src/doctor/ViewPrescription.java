package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

//import pharmacist.backend.Pharmacy;
import doctor.backend.*;

/**
 * Allows viewing previous prescription.
 * @author Khai Nguyen
 *
 */
public class ViewPrescription
{
	private Shell shell;
	private Text txtPatientInfo;
	private Text txtRx;
	//private Text txtPharm;
	//private Text txtDoctor;
	
	public ViewPrescription(Shell parent, Prescription rx, Patient patient)
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
		patientInfo += patient.getFirstName() + " " + patient.getLastName() + "\n";
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
		txtRx.setBounds(10, 162, 430, 304);
		String rxInfo = "";
		rxInfo += "Name: " + rx.getName() + ", " + rx.getStrength() + "\n";
		rxInfo += "Route: " + rx.getRoute() + "\n";
		rxInfo += "Frequency: " + rx.getFrequency() + "\n";
		rxInfo += "Quantity: " + rx.getQuantity() + "\n";
		rxInfo += "Refills: " + rx.getRefills() + "/" + rx.getMaxRefills() + " (Refills/Max)\n";
		rxInfo += "Precribed: " + rx.getDatePrescribed() + "\n";
		rxInfo += "Fullfilled: " + rx.getDateFilled() + "\n";
		rxInfo += "Pharmacy ID: " + rx.getPharmID() + "\n";
		rxInfo += "Doctor license: " + rx.getDocLicense() + "\n";
		rxInfo += "Patient medical number: " + rx.getPatientMedNumber();
		txtRx.setText(rxInfo);
		
		
		// BUTTON: Cancel - Goes back to AddPrescription
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(345, 472, 95, 28);
		btnCancel.setText("Close");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				//shell.getParent().setVisible(true);
				shell.dispose();
			}
		});
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
}