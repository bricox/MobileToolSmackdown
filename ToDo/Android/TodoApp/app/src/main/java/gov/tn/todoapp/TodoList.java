package gov.tn.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TodoList extends Activity {
    private ListView todoListView;
    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        todoListView = (ListView) findViewById((R.id.todoList));
        todoListAdapter = new TodoListAdapter( TodoRepository.getInstance().getTodos() );
        todoListView.setAdapter(todoListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoListAdapter.notifyDataSetChanged();
    }

    public void goToDetails(View view) {
        int todoId = ((Integer) view.getTag()).intValue();

        Intent goToDetailsIntent = new Intent(getApplicationContext(), TodoDetails.class);
        goToDetailsIntent.putExtra("todoId", todoId);
        startActivity(goToDetailsIntent);
    }

    private class TodoListAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<Todo> mTodos;

        public TodoListAdapter(ArrayList<Todo> todos) {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mTodos = todos;
        }

        @Override
        public int getCount() {
            return mTodos.size();
        }

        @Override
        public String getItem(int position) {
            return mTodos.get(position).getText();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.todorow, null);
                holder = new ViewHolder();
                holder.doneCheckBox = (CheckBox) convertView.findViewById(R.id.doneCheckBox);
                holder.goToDetailsButton = (Button) convertView.findViewById(R.id.goToDetailsButton);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.doneCheckBox.setChecked(mTodos.get(position).getDone());
            holder.doneCheckBox.setText(mTodos.get(position).getText());
            holder.goToDetailsButton.setTag(position);

            return convertView;
        }
    }

    private static class ViewHolder {
        public CheckBox doneCheckBox;
        public Button goToDetailsButton;
    }

}
