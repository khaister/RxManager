package doctor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class Login extends Shell {
	private Text text;
	private Text text_1;
	private Controller controller;
	private Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public Login(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		createContents();
		controller = c;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		this.setSize(450, 300);
		this.setText("Pharm Connect");
		text = new Text(this, SWT.BORDER);
		text.setBounds(128, 99, 176, 21);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setBounds(56, 99, 66, 21);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setBounds(56, 126, 66, 21);
		lblPassword.setText("Password");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(128, 123, 176, 21);
		
		Button btnSignIn = new Button(this, SWT.NONE);
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//If log in info is correct go to next page
				controller.showPatientConnect(t);
			}
		});
		btnSignIn.setBounds(229, 149, 75, 25);
		btnSignIn.setText("Sign In");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
