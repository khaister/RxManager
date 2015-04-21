package doctor;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.*;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.StyledText;
import doctor.backend.*;
import java.sql.*;

public class ReturningPatientSearch
{
	private ArrayList<Patient> result;
	private Patient searchPatient = new Patient();
	private static Connection dbconnect;
	private Doctor doctor;
	
	Shell shell;
	private Text txtFirstName;
	private Text txtLastName;
	private Text txtMedicalNumber;
	
	
	/**
	 * 
	 * @param parent
	 * @param conn
	 */
	public ReturningPatientSearch(Shell parent, Doctor doctor) 
	{
		this.doctor = doctor;
		shell = new Shell(parent, SWT.SHELL_TRIM);
		dbconnect = RxManager.dbconnect;
	}
	
	public void open()
	{
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		shell.setSize(450, 385);
		shell.setText("Returning Patient Search");
		
		// Name
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(10, 38, 43, 20);
		lblName.setText("Name");
		txtFirstName = new Text(shell, SWT.BORDER);
		txtFirstName.setToolTipText("First");
		txtFirstName.setBounds(59, 35, 177, 21);
		txtFirstName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
			}
		});

		// ???????????????
		StyledText resultBox = new StyledText(shell, SWT.BORDER);
		resultBox.setBounds(10, 107, 412, 178);
		
		txtLastName = new Text(shell, SWT.BORDER);
		txtLastName.setToolTipText("Last name");
		txtLastName.setTouchEnabled(true);
		txtLastName.setBounds(242, 35, 177, 21);
		txtLastName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				if(txtLastName.getText().length() > 3)
				{
					searchPatient = new Patient();
					result = getPatient(searchPatient);
					for(Patient r: result)
						resultBox.append(r.toSQLInsertString() + "\n");
				}
			}
		});
		
		// Medical number
		Label lblPatientMedNumber = new Label(shell, SWT.NONE);
		lblPatientMedNumber.setBounds(10, 81, 64, 20);
		lblPatientMedNumber.setText("Patient ID");
		txtMedicalNumber = new Text(shell, SWT.BORDER);
		txtMedicalNumber.setBounds(79, 78, 153, 21);
		txtMedicalNumber.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				/*if(txtFirst.getText().length() > 3){
					search = new Patient(txtFirst.getText(), txtLast.getText(), txtID.getText());
					result = controller.getPatient(search);
					for(Patient r: result){
						resultBox.append(r.toString() + "\n");
					}
					
				}*/
			}
		});
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(338, 307, 90, 30);
		btnSearch.setText("Search");
		btnSearch.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				String sel = resultBox.getSelectionText();
				Patient patient = new Patient();
				//controller.setPatient(patient);
				//controller.showPatientInfo(shell);
			}
		});
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(242, 307, 90, 30);
		btnCancel.setText("Cancel");
		btnCancel.addMouseListener(new MouseAdapter()
		{
			public void mouseDown(MouseEvent e)
			{
				shell.close(); // try to return to PatientConnect
			}
		});
	}
	
	
	/*
	 * Returns list of patients from database
	 */
	public ArrayList<Patient> getPatient(Patient t)
	{
		ArrayList<Patient> foundPatients = new ArrayList<Patient>();
		
		String sql = "SELECT * FROM Patients p WHERE ";
		
		//If there is a first name
		if (t.getFirstName().length() > 0)
		{
			sql += "p.pFirstName = '" + t.getFirstName() + "'";
			
			//If theres a last name
			if(t.getLastName().length() > 0)
				sql += " and p.LastName = '" + t.getLastName() + "'";

			//If theres a medical number
			if(t.getMedicalNumber().length() > 0)
				sql += " and p.pMedicalNumber = '" + t.getMedicalNumber() + "'";
		}

		//If there isnt a first but is a last
		else if (t.getLastName().length() > 0)
		{
			sql += "p.pLastName = '" + t.getLastName() + "'";

			//If theres a medical number
			if(t.getMedicalNumber().length() > 0)
				sql += " and p.pMedicalNumber = '" + t.getMedicalNumber() + "'";
			
		}

		//If theres only a medical number
		else if (t.getMedicalNumber().length() > 0)
			sql += "p.pMedicalNumber = '" + t.getMedicalNumber() + "'";
	
		//System.out.println(sql);
		
		// Retrieve a record from the table Patients
		try
		{
			Statement st = dbconnect.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) // moving the cursor forward throu the rows
			{
				Patient p = new Patient();
				p.setFirstName(rs.getString("pFirstName"));
				p.setLastName(rs.getString("pLastName"));
				p.setDOB(rs.getDate("pDOB"));
				p.setPhone(rs.getString("pPhone"));
				p.setAddress(rs.getString("pAddress"));
				p.setCity(rs.getString("pCity"));
				p.setState(rs.getString("pState"));
				p.setZipCode(rs.getString("pZipCode"));
				p.setMedicalNumber(rs.getString("pMedicalNumber"));
				
				foundPatients.add(p);
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return foundPatients;
	}
}