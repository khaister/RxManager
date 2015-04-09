package common;

import java.sql.Date;

public class User
{
	private String firstName;
	private String lastName;
	private Date dob;
	private String license;
	private String username;
	private String password;
	
	public User()
	{
		this("", "", null, "", "", "");
	}
	
	public User(String fname, String lname, Date dob, String license, String username, String password)
	{
		this.firstName = fname;
		this.lastName = lname;
		this.dob = dob;
		this.license = license;
		this.username = username;
		this.password = password;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////

	public void setFirstName(String fname)
	{
		this.firstName = fname;
	}
	
	public void setLastName(String lname)
	{
		this.lastName = lname;
	}
	
	public void setLicense(String license)
	{
		this.license = license;
	}
	
	public void setDOB(Date dob)
	{
		this.dob = dob;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}	
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public String getLicense()
	{
		return this.license;
	}
	
	public Date getDOB()
	{
		return this.dob;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
}
