package it.unicam.ids.justmeet.rest;
/**
 * Ogni istanza è un utente commerciale
 * che generalmente pubblica eventi a scopo di lucro.
 * @author Andrea
 *
 */
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


	@Override
	public String GetUniqueID() {
		return VATNumber;
	}

}
