package it.unicam.ids.justmeet;

class User implements IUser {
	
	int Id;
	
	String Name;
	
	String LastName;
	
	protected User(int Id, String Name, String LastName)
	{
		this.Id = Id;
		this.Name = Name;
		this.LastName = LastName;
	}

	@Override
	public String GetDetails() {
		return String.format("%s %s", Name, LastName);
	}

}
