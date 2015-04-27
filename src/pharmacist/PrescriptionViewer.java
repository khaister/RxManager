package pharmacist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import common.*;
import doctor.backend.*;

/**
 * Allows viewing previous prescription.
 * @author Khai Nguyen
 *
 */
@SuppressWarnings("unused")
public class PrescriptionViewer
{
	private Shell shell;
	//private Text txtPatientInfo;
	private Text txtRx;
	private Button btnFill;
	//private Text txtPharm;
	//private Text txtDoctor;
	
	public PrescriptionViewer(Shell parent, Prescription rx)
	{
		shell = new Shell(parent, SWT.CHECK);
		shell.setText("Confirm Prescription");
		shell.setSize(450, 548);
		txtRx = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtRx.setBounds(10, 10, 430, 472);
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
		btnCancel.setBounds(244, 488, 95, 28);
		btnCancel.setText("Close");
		
		btnFill = new Button(shell, SWT.NONE);
		btnFill.setBounds(345, 488, 95, 28);
		btnFill.setText("Fill");
		btnFill.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				markRxFilled(rx);
			}
		});
		
		
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				//shell.getParent().setVisible(true);
				shell.dispose();
			}
		});		
		
		// Actually open the window
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
	}
	
	public void markRxFilled(Prescription rx)
	{
		String sql = "SELECT * FROM Prescriptions "
				+ "WHERE RxDatePrescribed = '" + rx.getDatePrescribed() + "'"
				+ "AND RxName = '" + rx.getName() + "'";
		
		try
		{
			Statement st = RxManager.dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// This should return only 1 row.
			while (rs.next())
			{
				rs.updateBoolean("RxDatePrescribed", true);
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}