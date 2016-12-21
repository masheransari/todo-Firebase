package com.example.asheransari.todofirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.todofirebase.R;
import com.example.asheransari.todofirebase.toDo_variableClass;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 12/19/2016.
 */

public class Custom_adapter extends BaseAdapter {
    Context c;
    ArrayList<toDo_variableClass> variableClassesArrayList = new ArrayList<>();

    public Custom_adapter(Context c, ArrayList<toDo_variableClass> variableClassesArrayList) {
        this.c = c;
        this.variableClassesArrayList = variableClassesArrayList;
    }


    @Override
    public int getCount() {
        return variableClassesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return variableClassesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.list_todo,viewGroup,false);
        }

        final toDo_variableClass variableClass = (toDo_variableClass) this.getItem(i);

        TextView todoText = (TextView)view.findViewById(R.id.todo_data);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView time = (TextView)view.findViewById(R.id.time);

        todoText.setText(variableClass.getTodoEdit());
        date.setText(variableClass.getDate());
        time.setText(variableClass.getTime());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, ""+variableClass.getTodoEdit()+" and "+variableClass.getDate()+" and "+variableClass.getTime(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
