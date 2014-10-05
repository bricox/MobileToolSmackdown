package gov.tn.todoapp;

import java.util.UUID;

public class Todo {
    private String mId;
    private String mText;
    private String mDescription;
    private boolean mDone;

    public Todo(String text) { this(text, "", false); }
    public Todo(String text, String description, boolean done) {
        mId = UUID.randomUUID().toString();
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo that = (Todo) obj;
            /*
            if (this.mId == null) {
                this.mId = UUID.randomUUID().toString();
            }
            */
            return this.mId.equals(that.mId);
        }
        return false;
    }
}
