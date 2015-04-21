package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;

import common.*;
import doctor.backend.*;


/**
 * A GUI that allows either (1) creating a new patient record, or (2) looking 
 * up a returning patient. This window will load if Login instance closes after
 * a successful login.
 */
public class PatientConnect extends Window
{
	private Shell shell;
	private Doctor doctor;

	
	/**
	 * Create the shell.
	 * @param display
	 */
	public PatientConnect(Shell parent, Doctor doctor) 
	{
		this.doctor = doctor;
		shell = new Shell(parent, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
		parent.setVisible(false);
		
		shell.setSize(450, 300);
		
		// BUTTON: Create new patient
		Button btnNewPatient = new Button(shell, SWT.CENTER);
		btnNewPatient.setBounds(10, 52, 412, 56);
		btnNewPatient.setText("New Patient");
		btnNewPatient.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e) 
			{
				showAddNewPatient(shell);
			}
		});
		
		// BUTTON: Returning patient search
		Button btnReturningPatient = new Button(shell, SWT.NONE);
		btnReturningPatient.setBounds(10, 136, 412, 56);
		btnReturningPatient.setText("Returning Patient");
		btnReturningPatient.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				showReturningPatientSearch(shell);
			}
		});
		
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}		
	}
	
	
	/**
	 * Displays AddNewPatient Shell.
	 * @param parent
	 */
	public void showAddNewPatient(Shell parent)
	{
		@SuppressWarnings("unused")
		AddNewPatient addNewPatient = new AddNewPatient(parent, doctor);
	}
	
	
	/**
	 * Displays ReturningPatientSearch Shell.
	 * @param parent
	 */
	public void showReturningPatientSearch(Shell parent)
	{
		@SuppressWarnings("unused")
		ReturningPatientSearch returningPatientSearch 
			= new ReturningPatientSearch(parent, doctor);
	}
}