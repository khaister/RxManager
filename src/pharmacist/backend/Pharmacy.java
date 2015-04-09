package pharmacist.backend;

public class Pharmacy
{
	private String phyName;
	private String phyAddress;
	private String phyCity;
	private String phyState;
	private String phyZipCode;
	private String phyBranchID;
	private String phyPhone;
	
	public Pharmacy()
	{
		this("", "", "", "", "", "");
	}
	
	public Pharmacy(String name, String address, String city,
			String state, String zipcode, String phone)
	{
		this.phyName = name;
		this.phyAddress = address;
		this.phyCity = city;
		this.phyState = state;
		this.phyZipCode = zipcode;
		this.phyPhone = phone;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          SETTERS / MUTATORS
	//
	////////////////////////////////////////////////////////////////////////////

	public void setName(String name)
	{
		this.phyName = name;
	}
	
	public void setAddress(String address)
	{
		this.phyAddress = address;
	}
	
	public void setCity(String city)
	{
		this.phyCity = city;
	}
	
	public void setState(String state)
	{
		this.phyState = state;
	}
	
	public void setZipCode(String zipcode)
	{
		this.phyZipCode = zipcode;
	}
	
	public void setPhone(String phone)
	{
		this.phyPhone = phone;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public String getName()
	{
		return this.phyName;
	}
	
	public String getAddress()
	{
		return this.phyAddress;
	}
	
	public String getCity()
	{
		return this.phyCity;
	}
	
	public String getState()
	{
		return this.phyState;
	}
	
	public String getZipCode()
	{
		return this.phyZipCode;
	}
}
