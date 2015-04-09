package common;

import java.sql.Date;

/**
 * This class describes any person involved in the prescription process:
 * doctors, patients, pharmacists. Username and password, however, are available
 * to doctos and pharmacists only.
 * 
 * @author khaister
 *
 */
public class User
{
	private String firstName;
	private String lastName;
	private Date dob;
	private String license;
	private String username;
	private String password;
	
	/**
	 * Creates an "empty" user.
	 */
	public User()
	{
		this("", "", null, "", "", "");
	}
	
	/**
	 * Creates a user.
	 * @param fname First name.
	 * @param lname Last name.
	 * @param dob Date of birth.
	 * @param license License.
	 * @param username Username.
	 * @param password Password.
	 */
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

	/**
	 * Assigns first name to user.
	 * @param fname First name to assign.
	 */
	public void setFirstName(String fname)
	{
		this.firstName = fname;
	}
	
	/**
	 * Assigns last name to user.
	 * @param lname Last name to assign.
	 */
	public void setLastName(String lname)
	{
		this.lastName = lname;
	}
	
	/**
	 * Assigns (medical) license to user.
	 * @param license License to assign.
	 */
	public void setLicense(String license)
	{
		this.license = license;
	}
	
	/**
	 * Assigns date of birth to user.
	 * @param dob Date of birth to assign.
	 */
	public void setDOB(Date dob)
	{
		this.dob = dob;
	}
	
	/**
	 * Assigns username to user.
	 * @param username Username to assign.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Assigns password to user.
	 * @param password Password to assign.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}	
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Obtains user's first name.
	 * @return User's first name.
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/**
	 * Obtains user's last name.
	 * @return User's last name.
	 */
	public String getLastName()
	{
		return this.lastName;
	}
	
	/**
	 * Obtains user's medical license.
	 * @return User's medical license.
	 */
	public String getLicense()
	{
		return this.license;
	}
	
	/**
	 * Obtains user's date of birth.
	 * @return User's date of birth.
	 */
	public Date getDOB()
	{
		return this.dob;
	}
	
	/**
	 * Obtains username.
	 * @return Username.
	 */
	public String getUsername()
	{
		return this.username;
	}
	
	/**
	 * Obtains password.
	 * @return Password.
	 */
	public String getPassword()
	{
		return this.password;
	}
}
