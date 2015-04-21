package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import doctor.backend.*;

public class PatientInfo
{
	private Doctor doctor;
	private Patient patient;
	Shell shell;

	/**
	 * Create the shell.
	 * @param display
	 */
	public PatientInfo(Shell parent, Doctor doctor, Patient patient)
	{
		this.doctor = doctor;
		this.patient = patient;
		
		shell = new Shell(parent, SWT.SHELL_TRIM);
		parent.setVisible(false);
		shell.setSize(380, 277);
		shell.setText("Patient Information");
		
		Label lblFirst = new Label(shell, SWT.NONE);
		lblFirst.setBounds(10, 31, 92, 15);
		lblFirst.setText(patient.getFirstName());
		
		Label lblLast = new Label(shell, SWT.NONE);
		lblLast.setBounds(102, 31, 65, 15);
		lblLast.setText(patient.getLastName());
		
		Label lblMedical = new Label(shell, SWT.NONE);
		lblMedical.setBounds(146, 98, 74, 15);
		lblMedical.setText("Medical #:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(226, 98, 55, 15);
		lblNewLabel.setText(patient.getMedicalNumber());
		
		Label lblDOB = new Label(shell, SWT.NONE);
		lblDOB.setBounds(10, 56, 65, 15);
		lblDOB.setText(patient.getDOB().toString());
		
		Label labelPhone = new Label(shell, SWT.NONE);
		labelPhone.setBounds(231, 31, 92, 15);
		labelPhone.setText(patient.getPhone().substring(0, 3) + "-" + patient.getPhone().substring(3,6)
				+"-"+ patient.getPhone().substring(6,10));
		
		Label lblAdd = new Label(shell, SWT.NONE);
		lblAdd.setBounds(10, 77, 157, 15);
		lblAdd.setText(patient.getAddress());
		
		Label lblZip = new Label(shell, SWT.NONE);
		lblZip.setBounds(20, 98, 55, 15);
		lblZip.setText(patient.getZipCode());
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("Previous Medication:");
		label_2.setBounds(10, 134, 344, 20);
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 160, 344, 30);
		
		// BUTTON: Add new Rx
		Button btnAddNewPrescription = new Button(shell, SWT.NONE);
		btnAddNewPrescription.setBounds(215, 204, 139, 25);
		btnAddNewPrescription.setText("Add new Prescription");
		btnAddNewPrescription.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				showAddPrescription(shell, doctor, patient);
			}
		});
		
		Button btnEdit = new Button(shell, SWT.NONE);
		btnEdit.setBounds(121, 204, 75, 25);
		btnEdit.setText("Edit");
		
		// BUTTON: Cancel - Goes back to Patient connect? 
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(10, 204, 75, 25);
		btnCancel.setText("Cancel");
		
		Label lblState = new Label(shell, SWT.NONE);
		lblState.setBounds(257, 77, 24, 15);
		lblState.setText(patient.getState());
		
		Label lblCity = new Label(shell, SWT.NONE);
		lblCity.setBounds(165, 77, 55, 15);
		lblCity.setText(patient.getCity());
		
		// Actually open the shell
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (display.readAndDispatch())
				display.sleep();
		}
	}
	

	public void showAddPrescription(Shell parent, Doctor doctor, Patient patient)
	{
		
	}
}