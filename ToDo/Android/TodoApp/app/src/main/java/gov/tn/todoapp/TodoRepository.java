package gov.tn.todoapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andycollins on 9/28/14.
 */
public class TodoRepository {
    private static TodoRepository mInstance;
    static {
        mInstance = new TodoRepository();
    }

    public static TodoRepository getInstance() {
        return mInstance;
    }

    private ArrayList<Todo> mTodos;
    private TodoRepository() {
        mTodos = new ArrayList<Todo>();
        mTodos.addAll(Arrays.asList(
                new Todo("I need to do some stuff", "Some description", true),
                new Todo("Another thing"),
                new Todo("This is something that must be done...simply MUST be done.")
        ));
    }

    public ArrayList<Todo> getTodos() {
        return mTodos;
    }
}
