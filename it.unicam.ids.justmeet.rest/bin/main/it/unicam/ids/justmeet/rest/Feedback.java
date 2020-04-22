package it.unicam.ids.justmeet.rest;

/**
 * Questa classe ha bisigno di un evento e di un utente.
 * Rappresenta una valutazione relativa ad un evento
 * @author Andrea
 *
 */
public class Feedback {

	int UserHash;
	
	int PostHash;
	
	FeedbackType Type;
	
	String Text;
	
	public Feedback(int UserHash, int PostHash, FeedbackType Type)
	{
		this.UserHash = UserHash;
		this.PostHash = PostHash;
		this.Type = Type;
	}
	
	public boolean AddAdditionText(String Text)
	{
		if(Text.length() > 0 && Text.length() <= 120){
			this.Text = Text;
			return true;
		} else return false;
	}
}
