package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class PatientList extends Shell {
	private Controller controller;
	private Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public PatientList(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		createContents();
		controller = c;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(450, 300);
		Label lblListOfPatients = new Label(this, SWT.NONE);
		lblListOfPatients.setBounds(77, 49, 99, 20);
		lblListOfPatients.setText("List of Patients:");
		
		Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setBounds(77, 84, 299, 28);
		
		Button btnNotListed = new Button(this, SWT.NONE);
		btnNotListed.setBounds(248, 136, 128, 53);
		btnNotListed.setText("Not Listed");
		btnNotListed.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				controller.showPatientConnect(t);
			}
		});
		
		Button btnConfirm_1 = new Button(this, SWT.NONE);
		btnConfirm_1.setBounds(77, 136, 128, 53);
		btnConfirm_1.setText("Confirm");
		btnConfirm_1.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				controller.showPreviousMedication(t);
			}
		});


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
