package gov.tn.todoapp;

public class Todo {
    private String mText;
    private String mDescription;
    private boolean mDone;

    public Todo(String text) { this(text, "", false); }
    public Todo(String text, String description, boolean done) {
        mText = text;
        mDescription = description;
        mDone = done;
    }

    public String getText() { return mText; }
    public void setText(String text) { mText = text; }

    public String getDescription() { return mDescription; }
    public void setDescription(String description) { mDescription = description; }

    public boolean getDone() { return mDone; }
    public void setDone(boolean done) { mDone = done; }
}
