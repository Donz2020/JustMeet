package it.unicam.ids.justmeet;

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
