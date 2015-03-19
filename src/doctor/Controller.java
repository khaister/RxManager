package doctor;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Controller {
	private Display display = Display.getDefault();
	private Login login = new Login(display,this);
	private AddNewPatient anp = new AddNewPatient(display, this);
	private PatientConnect pc = new PatientConnect(display,this);
	private PatientList plist = new PatientList(display,this);
	private PreviousMedication pmed = new PreviousMedication(display,this);
	private ReturningPatientSearch rps = new ReturningPatientSearch(display,this);
	
	
	public Controller(){
		try {
			login.open();
			login.layout();
			while (!login.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showLogin(Shell s){
		login = new Login(display, this);
		s.close();
		login.open();
		login.layout();
		while (!login.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}	
	}
	public void showPatientConnect(Shell s){
		pc = new PatientConnect(display, this);
		s.close();
		pc.open();
		pc.layout();
		while (!pc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}	
	}
	public void showNewPatient(Shell s){
		anp = new AddNewPatient(display, this);
		s.close();
		anp.open();
		anp.layout();
		while (!anp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void showPatientList(Shell s){
		plist = new PatientList(display, this);
		s.close();
		plist.open();
		plist.layout();
		while (!plist.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void showPreviousMedication(Shell s){
		pmed = new PreviousMedication(display,this);
		s.close();
		pmed.open();
		pmed.layout();
		while (!pmed.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void showRPS(Shell s){
		rps = new ReturningPatientSearch(display, this);
		s.close();
		rps.open();
		rps.layout();
		while (!rps.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
