package it.unicam.ids.justmeet.rest;

/**
 * Ogni istanza è un utente fisico
 * che verrà differenziato in base al ruolo
 * @author Andrea
 *
 */
class User implements IPhysicalUser {
	
	String Email; //aggiornare uml
	
	String Name;
	
	String LastName;
	
	UserRole Role;
	
	protected User(String Email, String Name, String LastName)
	{
		this.Email = Email;
		this.Name = Name;
		this.LastName = LastName;
	}

	@Override
	public String GetDetails() {
		return String.format("%s %s", Name, LastName);
	}

	@Override
	public String GetUniqueID() {
		return Email;
	}

	@Override
	public UserRole GetRole() {
		return Role;
	}

}
