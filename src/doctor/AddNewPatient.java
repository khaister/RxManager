package doctor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddNewPatient extends Shell {
	private Controller controller;
	Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public AddNewPatient(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		createContents();
		controller = c;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(459, 622);
		Label lblPleaseEnterThe = new Label(this, SWT.NONE);
		lblPleaseEnterThe.setBounds(10, 10, 175, 20);
		lblPleaseEnterThe.setText("Please enter the following:");
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setBounds(20, 39, 43, 20);
		lblName.setText("Name:");
		
		Label lblDateOfBirth = new Label(this, SWT.NONE);
		lblDateOfBirth.setBounds(20, 123, 88, 20);
		lblDateOfBirth.setText("Date of Birth:");
		
		Label lblAddress = new Label(this, SWT.NONE);
		lblAddress.setBounds(20, 207, 56, 20);
		lblAddress.setText("Address:");
		
		Label lblPhoneNumber = new Label(this, SWT.NONE);
		lblPhoneNumber.setBounds(20, 265, 102, 20);
		lblPhoneNumber.setText("Phone Number:");
		
		Label lblMedication = new Label(this, SWT.NONE);
		lblMedication.setBounds(20, 333, 78, 20);
		lblMedication.setText("Medication:");
		
		Text text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(30, 233, 340, 26);
		
		Text text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(30, 291, 190, 26);
		
		Text text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(175, 349, 220, 26);
		
		Button btnEnter = new Button(this, SWT.NONE);
		btnEnter.setBounds(355, 535, 76, 30);
		btnEnter.setText("Enter");
		
		Label lblFirst = new Label(this, SWT.NONE);
		lblFirst.setBounds(30, 65, 32, 20);
		lblFirst.setText("First");
		
		Label lblLast = new Label(this, SWT.NONE);
		lblLast.setBounds(184, 65, 32, 20);
		lblLast.setText("Last");
		
		Label lblMi = new Label(this, SWT.NONE);
		lblMi.setBounds(346, 65, 23, 20);
		lblMi.setText("M.I.");
		
		Text text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(30, 91, 135, 26);
		
		Text text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(184, 91, 135, 26);
		
		Text text_6 = new Text(this, SWT.BORDER);
		text_6.setBounds(346, 91, 23, 26);
		
		Label lblMonth = new Label(this, SWT.NONE);
		lblMonth.setBounds(30, 149, 43, 20);
		lblMonth.setText("Month");
		
		Label lblDay = new Label(this, SWT.NONE);
		lblDay.setBounds(90, 149, 32, 20);
		lblDay.setText("Day");
		
		Label lblYear = new Label(this, SWT.NONE);
		lblYear.setBounds(133, 149, 32, 20);
		lblYear.setText("Year");
		
		Text text_7 = new Text(this, SWT.BORDER);
		text_7.setBounds(30, 175, 43, 26);
		
		Text text_8 = new Text(this, SWT.BORDER);
		text_8.setBounds(82, 175, 40, 26);
		
		Text text_9 = new Text(this, SWT.BORDER);
		text_9.setBounds(132, 175, 56, 26);
		
		Label lblName_1 = new Label(this, SWT.NONE);
		lblName_1.setBounds(123, 352, 46, 20);
		lblName_1.setText("Name:");
		
		Text text_10 = new Text(this, SWT.BORDER);
		text_10.setBounds(175, 381, 78, 26);
		
		Text text_11 = new Text(this, SWT.BORDER);
		text_11.setBounds(175, 413, 78, 26);
		
		Text text_12 = new Text(this, SWT.BORDER);
		text_12.setBounds(175, 445, 78, 26);
		
		Text text_13 = new Text(this, SWT.BORDER);
		text_13.setBounds(82, 477, 267, 88);
		
		Label lblDosage = new Label(this, SWT.NONE);
		lblDosage.setBounds(109, 384, 56, 20);
		lblDosage.setText("Dosage:");
		
		Label lblRouteOfAdministration = new Label(this, SWT.NONE);
		lblRouteOfAdministration.setBounds(3, 416, 162, 20);
		lblRouteOfAdministration.setText("Route of Administration:");
		
		Label lblNumOfRefills = new Label(this, SWT.NONE);
		lblNumOfRefills.setBounds(68, 448, 97, 20);
		lblNumOfRefills.setText("Num of Refills:");
		
		Label lblNotes = new Label(this, SWT.NONE);
		lblNotes.setBounds(33, 480, 43, 20);
		lblNotes.setText("Notes:");
		btnEnter.addMouseListener(new MouseAdapter(){
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
