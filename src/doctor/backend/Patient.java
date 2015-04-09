package doctor.backend;

import java.sql.Date;
import common.*;

/**
 * This class represents a patient with identificable information, including
 * name, date of birth, phone number, address, and medical number.
 * @author khaister
 *
 */
public class Patient extends User
{
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String medicalNumber;
	
	/**
	 * Creates an "empty" patient.
	 */
	public Patient()
	{
		this("", "", null, "", "", "", "", "", "");
	}

	/**
	 * Creates a Patient object with all identifiable information.
	 * 
	 * @param phone Phone number of the patient.
	 * @param address Address, including number and street name.
	 * @param city City.
	 * @param state State.
	 * @param zipcode Zip code.
	 * @param medicalNumber Medical number.
	 */
	public Patient(String fname, String lname, Date dob, String phone,
			String address, String city, String state, String zipcode,
			String medicalNumber)
	{
		super.setFirstName(fname);
		super.setLastName(lname);
		super.setDOB(dob);
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipcode;
		this.medicalNumber = medicalNumber;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public void setZipCode(String zipcode)
	{
		this.zipCode = zipcode;
	}
	
	public void setMedicalNumber(String medical_number)
	{
		this.medicalNumber = medical_number;
	}
	

	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////

	public String getPhone()
	{
		return this.phone;
	}
	public String getAddress()
	{
		return this.address;
	}
	public String getCity()
	{
		return this.city;
	}
	public String getState()
	{
		return this.state;
	}
	public String getZipCode()
	{
		return this.zipCode;
	}
	public String getMedicalNumber()
	{
		return this.medicalNumber;
	}
	
	/**
	 * Creates the values for INSERT statement in SQL.
	 * @return The string (with parentheses) to place after VALUES in INSERT 
	 *         statements.
	 */
	public String toSQLInsertString()
	{
		return "(\'" + super.getFirstName()      + "\', " +  
				"\'" + super.getLastName()       + "\', " +
				"\'" + super.getDOB().toString() + "\', " +
				"\'" + this.phone          + "\', " +
				"\'" + this.address        + "\', " +
				"\'" + this.city           + "\', " +
				"\'" + this.state          + "\', " +
				"\'" + this.zipCode        + "\', " +
				"\'" + this.medicalNumber  + "\') ";
	}

}