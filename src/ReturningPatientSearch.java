import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ReturningPatientSearch extends Shell {
	private Controller controller;
	Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public ReturningPatientSearch(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		createContents();
		controller = c;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(450, 357);
		Label lblPleaseEnter = new Label(this, SWT.NONE);
		lblPleaseEnter.setBounds(10, 10, 262, 20);
		lblPleaseEnter.setText("Please enter the following to search:");
		
		Label lblName2 = new Label(this, SWT.NONE);
		lblName2.setBounds(20, 94, 43, 20);
		lblName2.setText("Name:");
		
		Label lblFirst = new Label(this, SWT.NONE);
		lblFirst.setBounds(30, 120, 32, 20);
		lblFirst.setText("First");
		
		Label lblLast = new Label(this, SWT.NONE);
		lblLast.setBounds(185, 120, 32, 20);
		lblLast.setText("Last");
		
		Label lblDateOfBirth2 = new Label(this, SWT.NONE);
		lblDateOfBirth2.setBounds(20, 190, 88, 20);
		lblDateOfBirth2.setText("Date of Birth:");
		
		Label lblPatientIdNo = new Label(this, SWT.NONE);
		lblPatientIdNo.setBounds(20, 36, 92, 20);
		lblPatientIdNo.setText("Patient ID No.");
		
		Text text = new Text(this, SWT.BORDER);
		text.setBounds(44, 62, 102, 26);
		
		Text text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(30, 146, 135, 26);
		
		Text text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(185, 146, 135, 26);
		
		DateTime dateTime2 = new DateTime(this, SWT.BORDER);
		dateTime2.setBounds(44, 216, 102, 28);
		
		Button btnAdvancedSearch = new Button(this, SWT.NONE);
		btnAdvancedSearch.setBounds(10, 270, 126, 30);
		btnAdvancedSearch.setText("Advanced Search");
		
		Button btnConfirm = new Button(this, SWT.NONE);
		btnConfirm.setBounds(218, 270, 90, 30);
		btnConfirm.setText("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				controller.showPatientList(t);
			}
		});
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(332, 270, 90, 30);
		btnCancel.setText("Cancel");
		btnCancel.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				controller.showPatientConnect(t);;
			}
		});


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
