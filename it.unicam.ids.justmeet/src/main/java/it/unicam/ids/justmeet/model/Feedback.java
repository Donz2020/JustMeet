package it.unicam.ids.justmeet.model;

/**
 * Questa classe ha bisigno di un evento e di un utente.
 * Rappresenta una valutazione relativa ad un evento
 *
 * @author Andrea
 */
public class Feedback {

    int UserHash;

    int PostHash;

    FeedbackType Type;

    String Text;

    public Feedback(IPhysicalUser PhysicalUser, Post ReferredPost, FeedbackType Type) {
        if (PhysicalUser == null || ReferredPost == null) throw new NullPointerException();
        UserHash = PhysicalUser.hashCode();
        PostHash = ReferredPost.hashCode();
        this.Type = Type;
    }

    public boolean AddAdditionalText(String Text) {
        if (Text != null && Text.length() > 0 && Text.length() <= 120) {
            this.Text = Text;
            return true;
        } else return false;
    }

    public int GetUserHash() {
        return UserHash;
    }

    public int GetPostHash() {
        return PostHash;
    }

    public FeedbackType GetType() {
        return Type;
    }

    public String GetText() {
        return Text;
    }


}
