package doctor.backend;

import java.sql.Date;

public class Doctor
{
	private String dFirstName;
	private String dLastName;
	private String dLicense;
	private Date dDOB;
	private String dUsername;
	private String dPassword;
	
	public Doctor()
	{
		this("", "", "", null, "", "");
	}
	
	public Doctor(String fname, String lname, String license, 
			Date dob, String username, String password)
	{
		this.dFirstName = fname;
		this.dLastName = lname;
		this.dLicense = license;
		this.dDOB = dob;
		this.dUsername = username;
		this.dPassword = password;
	}

	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////

	public void setFirstName(String fname)
	{
		this.dFirstName = fname;
	}
	
	public void setLastName(String lname)
	{
		this.dLastName = lname;
	}
	
	public void setLicense(String license)
	{
		this.dLicense = license;
	}
	
	public void setDOB(Date dob)
	{
		this.dDOB = dob;
	}
	
	public void setUsername(String username)
	{
		this.dUsername = username;
	}
	
	public void setPassword(String password)
	{
		this.dPassword = password;
	}	
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public String getFirstName()
	{
		return this.dFirstName;
	}
	
	public String getLastName()
	{
		return this.dLastName;
	}
	
	public String getLicense()
	{
		return this.dLicense;
	}
	
	public Date getDOB()
	{
		return this.dDOB;
	}
	
	public String getUsername()
	{
		return this.dUsername;
	}
	
	public String getPassword()
	{
		return this.dPassword;
	}	

}