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
		
		// Handle close event
				shell.addListener(SWT.Close, new Listener()
				{
					public void handleEvent(Event e)
					{
						if (shell.getParent() != null)
							shell.getParent().setVisible(true);
						shell.dispose();
					}
				});
//		String rxInfo = "";
//		rxInfo += "Name: " + rx.getName() + ", " + rx.getStrength() + "\n";
//		rxInfo += "Route: " + rx.getRoute() + "\n";
//		rxInfo += "Frequency: " + rx.getFrequency() + "\n";
//		rxInfo += "Quantity: " + rx.getQuantity() + "\n";
//		rxInfo += "Refills: " + rx.getRefills() + "/" + rx.getMaxRefills() + " (Refills/Max)\n";
//		rxInfo += "Precribed: " + rx.getDatePrescribed() + "\n";
//		rxInfo += "Fullfilled: " + rx.getDateFilled() + "\n";
//		rxInfo += "Pharmacy ID: " + rx.getPharmID() + "\n";
//		rxInfo += "Doctor license: " + rx.getDocLicense() + "\n";
//		rxInfo += "Patient medical number: " + rx.getPatientMedNumber();
		txtRx.setText(rx.toString());
		
		// BUTTON Fill - marks Rx as filled
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
		
		// BUTTON: Pick up - marks Rx as picked up 
		Button btnPickUp = new Button(shell, SWT.NONE);
		btnPickUp.setBounds(244, 488, 95, 28);
		btnPickUp.setText("Pick Up");
		btnPickUp.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				markRxPickedUp(rx);
			}
		});
		
		// BUTTON: Cancel - Goes back to AddPrescription
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(142, 488, 95, 28);
		btnCancel.setText("Close");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				if (shell.getParent() != null)
					shell.getParent().setVisible(true);
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
	
	/**
	 * Marks prescription as filled and assign the filled date to be the current
	 * date.
	 * @param rx Prescription to be filled.
	 */
	public void markRxFilled(Prescription rx)
	{
		String sql = "SELECT * FROM Prescriptions "
				+ "WHERE RxDatePrescribed = '" + rx.getDatePrescribed() + "'"
				+ "AND RxName = '" + rx.getName() + "'";
		
		try
		{
			Statement st = RxManager.dbconnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(sql);
			
			// This should return only 1 row.
			while (rs.next())
			{
				rs.updateBoolean("RxIsFilled", true);
				rs.updateDate("RxDateFilled", java.sql.Date.valueOf(java.time.LocalDate.now()));
				rs.updateRow();
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Marks prescription as "picked up" by assigning the pick-up date to be
	 * the current date.
	 * @param rx Prescription to be picked up.
	 */
	public void markRxPickedUp(Prescription rx)
	{
		String sql = "SELECT * FROM Prescriptions "
				+ "WHERE RxDatePrescribed = '" + rx.getDatePrescribed() + "'"
				+ "AND RxName = '" + rx.getName() + "'";
		
		try
		{
			Statement st = RxManager.dbconnect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(sql);
			
			// This should return only 1 row.
			while (rs.next())
			{
				rs.updateDate("RxDatePickedUp", java.sql.Date.valueOf(java.time.LocalDate.now()));
				rs.updateRow();
			}
			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}