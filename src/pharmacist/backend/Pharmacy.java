package pharmacist.backend;

/**
 * This class represents a typical pharmacy pharmacists work at.
 * @author khaister
 *
 */
public class Pharmacy
{
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String branchID;
	private String phone;
	
	/**
	 * Creates an "empty" pharmacy.
	 */
	public Pharmacy()
	{
		this("", "", "", "", "", "");
	}
	
	/**
	 * Creates an actual pharmacy.
	 * @param name Name of the pharmacy.
	 * @param address Address.
	 * @param city City.
	 * @param state State.
	 * @param zipcode Zip code.
	 * @param phone Phone number.
	 */
	public Pharmacy(String name, String address, String city,
			String state, String zipcode, String phone)
	{
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipcode;
		this.phone = phone;
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
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//
	//                          GETTERS / ACCESSORS
	//
	////////////////////////////////////////////////////////////////////////////
	
	public String getName()
	{
		return this.name;
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
}
