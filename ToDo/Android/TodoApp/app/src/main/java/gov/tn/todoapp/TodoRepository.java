package gov.tn.todoapp;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by andycollins on 9/28/14.
 */
public class TodoRepository {
    private final static String TODO_FILE = "gov.tn.todoapp.todos";
    private static TodoRepository instance;
    private static Context fileAccessor;

    public static void initFileAccessorContext(Context fileAccessorContext) {
        fileAccessor = fileAccessorContext;
    }

    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository(fileAccessor);
        }
        return instance;
    }



    private ArrayList<Todo> mTodos;
    private TodoRepository(Context fileAccessor) {
        BufferedReader todosInput = null;
        try {
            String jsonLine;
            StringBuilder json = new StringBuilder();

            todosInput =  new BufferedReader(  new InputStreamReader( fileAccessor.openFileInput(TODO_FILE)) );
            while ((jsonLine = todosInput.readLine()) != null) {
                json.append(jsonLine);
            }

            Gson gson = new Gson();
            mTodos = gson.fromJson(json.toString(), (new TypeToken<ArrayList<Todo>>() {}).getType());

        } catch (Exception e) {
            mTodos = new ArrayList<Todo>();
            mTodos.addAll(Arrays.asList(
                    new Todo("I need to do some stuff", "Some description", true),
                    new Todo("Another thing"),
                    new Todo("This is something that must be done...simply MUST be done.")
            ));
        } finally {
            try {
                if (todosInput != null)
                    todosInput.close();;
            } catch (Exception e)  {}
        }
    }

    public void save() {
        FileOutputStream todoOutput = null;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(mTodos);

            todoOutput = fileAccessor.openFileOutput(TODO_FILE, Context.MODE_PRIVATE);
            todoOutput.write(json.getBytes());

        } catch (Exception e) {  }
        finally {
            try {
                if (todoOutput != null)
                    todoOutput.close();
            } catch (Exception e) {  }
        }
    }

    public void removeTodo(Todo todoToRemove) {
        mTodos.remove(todoToRemove);
    }

    public ArrayList<Todo> getTodos() { return mTodos; }
}
