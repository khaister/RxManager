packge doctor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



public class PatientConnect extends Shell {
	private Controller controller;
	Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public PatientConnect(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		controller = c;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(450, 300);
		Button btnNewPatient = new Button(this, SWT.NONE);
		btnNewPatient.setBounds(10, 52, 412, 56);
		btnNewPatient.setText("New Patient");
		btnNewPatient.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				controller.showNewPatient(t);
			}
		});
		
		Button btnReturningPatient = new Button(this, SWT.NONE);
		btnReturningPatient.setBounds(10, 136, 412, 56);
		btnReturningPatient.setText("Returning Patient");
		btnReturningPatient.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				controller.showRPS(t);
			}
		});


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
