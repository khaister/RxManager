package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;

import common.*;

/**
 * A GUI that allows either (1) creating a new patient record (i.e. instantiate 
 * a AddNewPatient, or (2) looking up a returning patient (i.e. instantiate a 
 * ReturningPatientSearch). This window will load if Login instance closes after
 * a successful login.
 */
@SuppressWarnings("unused")
public class PatientConnect extends Window
{
	private Shell shell;

	// FOR TESTING PURPOSES
	public static void main(String[] args)
	{
		PatientConnect patientConnect = new PatientConnect(new Shell(new Display()));
	}
	
	/**
	 * Create the shell.
	 * @param display
	 */
	public PatientConnect(Shell parent) 
	{
		shell = new Shell(parent, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
		parent.setVisible(false);
		
		shell.setBounds(parent.getBounds().x, parent.getBounds().y, 450, 300);
		
		// BUTTON: Create new patient
		Button btnNewPatient = new Button(shell, SWT.CENTER);
		btnNewPatient.setBounds(10, 145, 430, 56);
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
		btnReturningPatient.setBounds(10, 61, 430, 56);
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
		AddNewPatient addNewPatient = new AddNewPatient(parent);
	}
	
	
	/**
	 * Displays ReturningPatientSearch Shell.
	 * @param parent
	 */
	public void showReturningPatientSearch(Shell parent)
	{
		ReturningPatientSearch returningPatientSearch 
			= new ReturningPatientSearch(parent);
	}
}