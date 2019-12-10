package it.unicam.ids.justmeet;
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
