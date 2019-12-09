package it.unicam.ids.justmeet;

public class PostDescription {

	
	PostCategory Type;
	
	boolean Free;
	
	String Text;
	
	public PostDescription(PostCategory Type, boolean Free, String Text)
	{
		this.Type = Type;
		this.Free = Free;
		this.Text = Text;
	}
}
