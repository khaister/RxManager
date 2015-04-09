package pharmacist.backend;

import java.sql.Date;
import common.*;

/**
 * This class represents a typical pharmacist.
 * 
 * @author khaister
 *
 */
public class Pharmacist extends User
{
	private String pharmBranchID;
	
	/**
	 * Creates an "empty" pharmacist.
	 */
	public Pharmacist()
	{
		this("", "", "", null, "", "", "");
	}
	
	/**
	 * Creates a Pharmacist.
	 * 
	 * @param fname First name.
	 * @param lname Last name.
	 * @param license License.
	 * @param dob Date of birth.
	 * @param branchID Branch ID of the work pharmcy.
	 * @param username Username.
	 * @param password Password.
	 */
	public Pharmacist(String fname, String lname, String license, Date dob, 
			String branchID, String username, String password)
	{
		super.setFirstName(fname);
		super.setLastName(lname);
		super.setLicense(license);
		super.setDOB(dob);
		this.pharmBranchID = branchID;
		super.setUsername(username);
		super.setPassword(password);
	}

	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public void setPharmBranchID(String pharmBranchID)
	{
		this.pharmBranchID = pharmBranchID;
	}

	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public String getPharmBranchID()
	{
		return this.pharmBranchID;
	}

}
