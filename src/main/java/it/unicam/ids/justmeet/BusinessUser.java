package it.unicam.ids.justmeet;

class BusinessUser implements IUser {

	String Name;
	
	String VATNumber;
	
	protected BusinessUser(String Name, String VATNumber)
	{
		this.Name = Name;
		this.VATNumber = VATNumber;
	}
	
	
	@Override
	public String GetDetails() {
		return String.format("%s", Name);
	}

}
