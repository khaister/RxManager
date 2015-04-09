package doctor.backend;

import java.sql.Date;

import common.*;

/**
 * This class represents a typical doctor. It inherits from common.User 
 * @see common.User
 * @author khaister
 *
 */
public class Doctor extends User
{
	public Doctor()
	{
		super();
	}
	
	public Doctor(String fname, String lname, Date dob, String license, 
			String username, String password)
	{
		super(fname, lname, dob, license, username, password);
	}
}