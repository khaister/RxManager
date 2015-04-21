package doctor;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PreviousMedication extends Shell 
{

	private Controller controller;
	private Shell t = this;
	/**
	 * Create the shell.
	 * @param display
	 */
	public PreviousMedication(Display display, Controller c) {
		super(display, SWT.SHELL_TRIM);
		createContents();
		controller = c;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() 
	{
		setSize(450, 300);
		Label lblListOfPrevious = new Label(this, SWT.NONE);
		lblListOfPrevious.setBounds(31, 10, 181, 20);
		lblListOfPrevious.setText("List of Previous Medication:");
		
		Label lblEnterNewMedication = new Label(this, SWT.NONE);
		lblEnterNewMedication.setBounds(31, 136, 150, 20);
		lblEnterNewMedication.setText("Enter New Medication:");
		
		List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setBounds(41, 36, 276, 82);
		
		Text text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(41, 162, 276, 26);
		
		Button btnConfirm_2 = new Button(this, SWT.NONE);
		btnConfirm_2.setBounds(300, 201, 90, 30);
		btnConfirm_2.setText("Confirm");
		btnConfirm_2.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				showPatientConnect(t);
			}
		});

	}

	public void showPatientConnect(Shell parent)
	{
		
	}

}