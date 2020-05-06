package it.unicam.ids.justmeet.rest;
/**
 * Rappresenta la tipologia di un evento
 * @author Andrea
 *
 */
public class PostDescription {

	
	PostCategory Type;
	
	boolean Free;
	
	String Text;
	
	public PostDescription(PostCategory Type, boolean Free, String Text)
	{
		if(Text == null || Text.length() == 0) throw new IllegalArgumentException();
		this.Type = Type;
		this.Free = Free;
		this.Text = Text;
	}
	
	public PostCategory GetType()
	{
		return Type;
	}
	
	public boolean GetFree()
	{
		return Free;
	}
	
	public String GetText()
	{
		return Text;
	}
}
