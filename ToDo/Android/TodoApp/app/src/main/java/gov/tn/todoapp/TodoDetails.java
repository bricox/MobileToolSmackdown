package gov.tn.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class TodoDetails extends Activity {

    private Todo mTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);

        int todoId = getIntent().getIntExtra("todoId", -1);
        mTodo = TodoRepository.getInstance().getTodos().get(todoId);

        initText(mTodo);
        initDescription(mTodo);
        initDone(mTodo);
    }

    public void deleteTodo(View view) {
        TodoRepository.getInstance().removeTodo(mTodo);
        finish();
    }

    private void initDone(final Todo todo) {
        CheckBox done = (CheckBox) this.findViewById(R.id.edit_todo_done);
        done.setChecked(todo.getDone());

        done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                todo.setDone(checked);
            }
        });
    }

    private void initText(final Todo todo) {
        EditText title = (EditText) this.findViewById(R.id.edit_todo_title);
        title.setText(todo.getText());
        title.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override
            public void afterTextChanged(Editable editable) {
                todo.setText(editable.toString());
            }
        });
    }

    private void initDescription(final Todo todo) {
        EditText description = (EditText) this.findViewById(R.id.edit_todo_description);
        description.setText(todo.getDescription());
        description.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {  }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {  }
            @Override
            public void afterTextChanged(Editable editable) {
                todo.setDescription(editable.toString());
            }
        });
    }
}
