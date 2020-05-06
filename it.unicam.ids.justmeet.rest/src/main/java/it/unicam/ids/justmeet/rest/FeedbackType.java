package it.unicam.ids.justmeet.rest;
/**
 * Indica il tipo di feedback per un evento
 * @author Andrea
 *
 */
public enum FeedbackType {

	REALLYBAD(0),
	BAD(1),
	DISCRETE(2),
	GOOD(3),
	REALLYGOOD(4);

	private final int Value;

	private FeedbackType (int Value){
		this.Value = Value;
	}

	public int GetValue(){
		return  Value;
	}

}

