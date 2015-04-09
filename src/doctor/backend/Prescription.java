package doctor.backend;

import java.sql.Date;

public class Prescription
{
	private String name;
	private String strength;
	private String route;
	private String frequency;
	private String quantity;
	private int maxRefill;
	private int refills;
	private String note;
	private Date date;
	private boolean isFilled;
	private String pharmID;
	private String docLicense;
	private String patientMedNumber;
	
	
	/**
	 * Creates an blank prescription.
	 */
	public Prescription()
	{
		this("", "", "", "", "", 0, 0, "", null, false, "", "", "");
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
	 * @param note Doctor or pharmacist's note.
	 * @param date Date the prescription is written.
	 * @param filled True of the Rx has been filled, false otherwise.
	 * @param pharmID Branch ID of the intended pharmacy.
	 * @param docLicense License of the prescribing doctor.
	 * @param patientMedNumber Patient's medical number.
	 */
    public Prescription(String name, String strength, String route,
    		String frequency, String quantity, int maxRefills, int refills,
    		String note, Date date, boolean filled, String pharmID,
    		String docLicense, String patientMedNumber)
    {
    	this.name = name;
    	this.strength = strength;
    	this.route = route;
    	this.frequency = frequency;
    	this.quantity = quantity;
    	this.maxRefill = maxRefills;
    	this.refills = refills;
    	this.note = note;
    	this.date = date;
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
    public void setRxName(String name)
    {
    	this.name = name;
    }
    
    public void setRxStrength(String strength)
    {
    	this.strength = strength;
    }
    
    public void setRxRoute(String route)
    {
    	this.route = route;
    }
    
    public void setRxFrequency(String frequency)
    {
    	this.frequency = frequency;
    }
    
    public void setRxQuantity(String quantity)
    {
    	this.quantity = quantity;
    }
    
    public void setRxMaxRefill(int maxRefill)
    {
    	this.maxRefill = maxRefill;
    }
    
    public void setRefills(int refills)
    {
    	this.refills = refills;
    }
    
    public void setRxNote(String note)
    {
    	this.note = note;
    }
    
    public void setRxDate(Date date)
    {
    	this.date = date;
    }
    
    public void setRxIsFilled(boolean filled)
    {
    	this.isFilled = filled;
    }
    
    public void setRxPharmacyID(String pharmID)
    {
    	this.pharmID = pharmID;
    }
    
    public void setRxDocLicense(String docLicense)
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
    public String getRxName()
    {
    	return this.name;
    }
    
    public String getRxStrength()
    {
    	return this.strength;
    }
    
    public String getRxRoute()
    {
    	return this.route;
    }
    
    public String getRxFrequency()
    {
    	return this.frequency;
    }
    
    public String getRxQuantity()
    {
    	return this.quantity;
    }
    
    public int getRxMaxRefill()
    {
    	return this.maxRefill;
    }
    
    public int getRefills()
    {
    	return this.refills;
    }
    
    public String getRxNote()
    {
    	return this.note;
    }
    
    public Date getRxDate()
    {
    	return this.date;
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
    
}