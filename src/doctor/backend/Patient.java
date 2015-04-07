package doctor.backend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a patient with identificable information, including
 * name, date of birth, phone number, address, and medical number.
 * @author khaister
 *
 */
public class Patient
{
	private String pFirstName;
	private String pLastName;
	private Date pDOB;
	private String pPhone;
	private String pAddress;
	private String pCity;
	private String pState;
	private String pZipCode;
	private String pMedicalNumber;
	
	private final SimpleDateFormat mySQLDateForm = new SimpleDateFormat("yyyy-mm-dd");

	/**
	 * Creates an "empty" patient.
	 */
	public Patient()
	{
		this("", "", new Date(), "", "", "", "", "", "");
	}

	
	/**
	 * Creates a Patient object with all identifiable information.
	 * @param fname First name of the patient.
	 * @param lname Last name of the patient.
	 * @param dob Date of birth of the patient.
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
		this.pFirstName = fname;
		this.pLastName = lname;
		this.pDOB = dob;
		this.pPhone = phone;
		this.pAddress = address;
		this.pCity = city;
		this.pState = state;
		this.pZipCode = zipcode;
		this.pMedicalNumber = medicalNumber;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
	public void setFirstName(String fname)
	{
		this.pFirstName = fname;
		
	}
	
	public void setLastName(String lname)
	{
		this.pLastName = lname;	
	}
	
	public void setDOB(Date dob)
	{
		this.pDOB = dob;
	}
	
	public void setPhone(String phone)
	{
		this.pPhone = phone;
	}
	
	public void setAddress(String address)
	{
		this.pAddress = address;
	}
	
	public void setCity(String city)
	{
		this.pCity = city;
	}
	
	public void setState(String state)
	{
		this.pState = state;
	}
	
	public void setZipCode(String zipcode)
	{
		this.pZipCode = zipcode;
	}
	
	public void setMedicalNumber(String medical_number)
	{
		this.pMedicalNumber = medical_number;
	}
	

	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	public String getFirstName()
	{
		return this.pFirstName;
	}
	
	public String getLastName()
	{
		return this.pLastName;
	}
	public Date getDOB()
	{
		return this.pDOB;
	}
	public String getPhone()
	{
		return this.pPhone;
	}
	public String getAddress()
	{
		return this.pAddress;
	}
	public String getCity()
	{
		return this.pCity;
	}
	public String getState()
	{
		return this.pState;
	}
	public String getZipCode()
	{
		return this.pZipCode;
	}
	public String getMedicalNumber()
	{
		return this.pMedicalNumber;
	}
	
	public String toSQLString()
	{
		return "(\'" + pFirstName                 + "\', " +  
				"\'" + pLastName                  + "\', " +
				"\'" + mySQLDateForm.format(pDOB) + "\', " +
				"\'" + pPhone                     + "\', " +
				"\'" + pAddress                   + "\', " +
				"\'" + pCity                      + "\', " +
				"\'" + pState                     + "\', " +
				"\'" + pZipCode                   + "\', " +
				"\'" + pMedicalNumber             + "\') ";
	}

}