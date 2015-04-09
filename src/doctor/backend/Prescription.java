package doctor.backend;

import java.sql.Date;

public class Prescription
{
	private String RxName;
	private String RxStrength;
	private String RxRoute;
	private String RxFrequency;
	private String RxQuantity;
	private int RxMaxRefill;
	private int RxRefills;
	private String RxNote;
	private Date RxDate;
	private boolean RxIsFilled;
	private String RxPharmacyID;
	private String RxDocLicense;
	private String RxPatientMedNumber;
	
	
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
	 * @param pharmacyID Branch ID of the intended pharmacy.
	 * @param docLicense License of the prescribing doctor.
	 * @param patientMedNumber Patient's medical number.
	 */
    public Prescription(String name, String strength, String route,
    		String frequency, String quantity, int maxRefills, int refills,
    		String note, Date date, boolean filled, String pharmacyID,
    		String docLicense, String patientMedNumber)
    {
    	this.RxName = name;
    	this.RxStrength = strength;
    	this.RxRoute = route;
    	this.RxFrequency = frequency;
    	this.RxQuantity = quantity;
    	this.RxMaxRefill = maxRefills;
    	this.RxRefills = refills;
    	this.RxNote = note;
    	this.RxDate = date;
    	this.RxIsFilled = filled;
    	this.RxPharmacyID = pharmacyID;
    	this.RxDocLicense = docLicense;
    	this.RxPatientMedNumber = patientMedNumber;
    }
    
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
    public void setRxName(String name)
    {
    	this.RxName = name;
    }
    
    public void setRxStrength(String strength)
    {
    	this.RxStrength = strength;
    }
    
    public void setRxRoute(String route)
    {
    	this.RxRoute = route;
    }
    
    public void setRxFrequency(String frequency)
    {
    	this.RxFrequency = frequency;
    }
    
    public void setRxQuantity(String quantity)
    {
    	this.RxQuantity = quantity;
    }
    
    public void setRxMaxRefill(int maxRefill)
    {
    	this.RxMaxRefill = maxRefill;
    }
    
    public void setRefills(int refills)
    {
    	this.RxRefills = refills;
    }
    
    public void setRxNote(String note)
    {
    	this.RxNote = note;
    }
    
    public void setRxDate(Date date)
    {
    	this.RxDate = date;
    }
    
    public void setRxIsFilled(boolean filled)
    {
    	this.RxIsFilled = filled;
    }
    
    public void setRxPharmacyID(String pharmacyID)
    {
    	this.RxPharmacyID = pharmacyID;
    }
    
    public void setRxDocLicense(String docLicense)
    {
    	this.RxDocLicense = docLicense;
    }
    
    public void setPatientMedNumber(String patientMedNumber)
    {
    	this.RxPatientMedNumber = patientMedNumber;
    }
    
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////
    public String getRxName()
    {
    	return this.RxName;
    }
    
    public String getRxStrength()
    {
    	return this.RxStrength;
    }
    
    public String getRxRoute()
    {
    	return this.RxRoute;
    }
    
    public String getRxFrequency()
    {
    	return this.RxFrequency;
    }
    
    public String getRxQuantity()
    {
    	return this.RxQuantity;
    }
    
    public int getRxMaxRefill()
    {
    	return this.RxMaxRefill;
    }
    
    public int getRefills()
    {
    	return this.RxRefills;
    }
    
    public String getRxNote()
    {
    	return this.RxNote;
    }
    
    public Date getRxDate()
    {
    	return this.RxDate;
    }
    
    public boolean IsFilled()
    {
    	return this.RxIsFilled;
    }
    
    public String getRxPharmacyID()
    {
    	return this.RxPharmacyID;
    }
    
    public String getRxDocLicense()
    {
    	return this.RxDocLicense;
    }
    
    public String getPatientMedNumber()
    {
    	return this.RxPatientMedNumber;
    }
    
}