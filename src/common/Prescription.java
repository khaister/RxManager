package common;

import java.sql.Date;

/**
 * This class represents a typical prescription.
 * @author khaister
 *
 */
public class Prescription
{
	private String name;
	private String strength;
	private String route;
	private String frequency;
	private String quantity;
	private int maxRefills;
	private int refills;
	private String notes;
	private Date datePrescribed;
	private Date dateFilled;
	private Date datePickedUp;
	private boolean isFilled;
	private String pharmID;
	private String docLicense;
	private String patientMedNumber;
	
	
	/**
	 * Creates an blank prescription.
	 */
	public Prescription()
	{
		this("", "", "", "", "", 0, 0, "", null, null, null, false, "", "", "");
	}
	
	/**
	 * Creates a precription.
	 * 
	 * @param name Name of the drug.
	 * @param strength Drug's strength.
	 * @param route Route of administration.
	 * @param frequency How often the drug should be taken.
	 * @param quantity Quantity of the drug (pills, bottles, etc.)
	 * @param maxRefills Maximum number of refills.
	 * @param refills Number of refills done.
	 * @param notes Doctor or pharmacist's note.
	 * @param datePrescribed Date the prescription is written.
	 * @param dateFilled Date the prescription is filled.
	 * @param datePickedUp Date the prescription is picked up by patient.
	 * @param filled True of the Rx has been filled, false otherwise.
	 * @param pharmID Branch ID of the intended pharmacy.
	 * @param docLicense License of the prescribing doctor.
	 * @param patientMedNumber Patient's medical number.
	 */
    public Prescription(String name, String strength, String route,
    		String frequency, String quantity, int maxRefills, int refills,
    		String notes, Date datePrescribed, Date dateFilled, Date datePickedUp,
    		boolean filled, String pharmID, String docLicense, String patientMedNumber)
    {
    	this.name = name;
    	this.strength = strength;
    	this.route = route;
    	this.frequency = frequency;
    	this.quantity = quantity;
    	this.maxRefills = maxRefills;
    	this.refills = refills;
    	this.notes = notes;
    	this.datePrescribed = datePrescribed;
    	this.dateFilled = dateFilled;
    	this.datePickedUp = datePickedUp;
    	this.isFilled = filled;
    	this.pharmID = pharmID;
    	this.docLicense = docLicense;
    	this.patientMedNumber = patientMedNumber;
    }
    
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public void setStrength(String strength)
    {
    	this.strength = strength;
    }
    
    public void setRoute(String route)
    {
    	this.route = route;
    }
    
    public void setFrequency(String frequency)
    {
    	this.frequency = frequency;
    }
    
    public void setQuantity(String quantity)
    {
    	this.quantity = quantity;
    }
    
    public void setMaxRefills(int maxRefills)
    {
    	this.maxRefills = maxRefills;
    }
    
    public void setRefills(int refills)
    {
    	this.refills = refills;
    }
    
    public void setNotes(String notes)
    {
    	this.notes = notes;
    }
    
    public void setDatePrescribed(Date datePrescribed)
    {
    	this.datePrescribed = datePrescribed;
    }
    
    public void setDateFilled(Date dateFilled)
    {
    	this.dateFilled = dateFilled;
    }
    
    public void setDatePickedUp(Date datePickedUp)
    {
    	this.datePickedUp = datePickedUp;
    }
    
    public void setIsFilled(boolean filled)
    {
    	this.isFilled = filled;
    }
    
    public void setPharmacyID(String pharmID)
    {
    	this.pharmID = pharmID;
    }
    
    public void setDocLicense(String docLicense)
    {
    	this.docLicense = docLicense;
    }
    
    public void setPatientMedNumber(String patientMedNumber)
    {
    	this.patientMedNumber = patientMedNumber;
    }
    
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
    public String getName()
    {
    	return this.name;
    }
    
    public String getStrength()
    {
    	return this.strength;
    }
    
    public String getRoute()
    {
    	return this.route;
    }
    
    public String getFrequency()
    {
    	return this.frequency;
    }
    
    public String getQuantity()
    {
    	return this.quantity;
    }
    
    public int getMaxRefills()
    {
    	return this.maxRefills;
    }
    
    public int getRefills()
    {
    	return this.refills;
    }
    
    public String getNote()
    {
    	return this.notes;
    }
    
    public Date getDatePrescribed()
    {
    	return this.datePrescribed;
    }
    
    public Date getDateFilled()
    {
    	return this.dateFilled;
    }
    
    public Date getDatePickedUp()
    {
    	return this.datePickedUp;
    }
    
    public boolean IsFilled()
    {
    	return this.isFilled;
    }
    
    public String getPharmID()
    {
    	return this.pharmID;
    }
    
    public String getDocLicense()
    {
    	return this.docLicense;
    }
    
    public String getPatientMedNumber()
    {
    	return this.patientMedNumber;
    }
    
    public String toSQLInsertString()
	{
    	String datePrescribedString = "";
    	if (this.datePrescribed != null) 
    		datePrescribedString = "'" + this.datePrescribed.toString() + "', ";
    	else
    		datePrescribedString = "null, ";
    	
    	String dateFilledString = "";
    	if (this.dateFilled != null) 
    		dateFilledString = "'" + this.dateFilled.toString() + "', ";
    	else
    		dateFilledString = "null, ";
    	
    	String datePickedUpString = "";
    	if (this.datePickedUp != null)
    		datePickedUpString = "'" + this.datePickedUp.toString() + "', ";
    	else
    		datePickedUpString = "null, ";
    	
    	int boolBit = 0;
    	if (this.isFilled == true) boolBit = 1;
    		
		return "('" + this.name                      + "', " +  
				"'" + this.strength                  + "', " +
				"'" + this.route                     + "', " +
				"'" + this.frequency                 + "', " +
				"'" + this.quantity                  + "', " +
				"" + this.maxRefills                + ", " +
				"" + this.refills                   + ", " +
				     datePrescribedString +
				     dateFilledString   +
				     datePickedUpString +
				"'" + this.notes                     + "', " +
				"" + boolBit                  + ", " +
				"'" + this.pharmID                   + "', " +
				"'" + this.docLicense                + "', " +
				"'" + this.patientMedNumber          + "') ";
	}
    
    public String toString()
    {
    	String rxInfo = "";
    	rxInfo += "Name: " + getName() + ", " + getStrength() + "\n";
		rxInfo += "Route: " + getRoute() + "\n";
		rxInfo += getFrequency() + "\n";
		rxInfo += getQuantity() + "\n";
		rxInfo += "Refills: " + getMaxRefills() + "(done: " + getRefills() + ")\n";
		rxInfo += "Precribed date: " + (getDatePrescribed()==null ? "" : getDatePrescribed().toString()) + "\n";
		rxInfo += "Fill date: " + (getDateFilled()==null ? "" : getDateFilled().toString()) + "\n";
		rxInfo += "Pick-up date: " + (getDatePickedUp()==null ? "" : getDatePickedUp().toString()) + "\n";
		rxInfo += "Pharmacy ID: " + getPharmID() + "\n";
		rxInfo += "Doc license: " + getDocLicense() + "\n";
		rxInfo += "Patient med #: " + getPatientMedNumber();
		return rxInfo;
    }
}