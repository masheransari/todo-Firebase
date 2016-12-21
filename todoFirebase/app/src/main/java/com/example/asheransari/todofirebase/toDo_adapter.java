package com.example.asheransari.todofirebase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asher.ansari on 12/17/2016.
 */

public class toDo_adapter extends ArrayAdapter<toDo_variableClass>{
    public toDo_adapter(Context context, int resources, ArrayList<toDo_variableClass> list)
    {
        super(context,0,list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        if (convertView == null)
        {
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.list_todo,viewGroup,false);
        }

        TextView todoText = (TextView)convertView.findViewById(R.id.todo_data);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView time = (TextView)convertView.findViewById(R.id.time);

        toDo_variableClass variableClass = getItem(position);


        todoText.setText(variableClass.getTodoEdit());
        date.setText(variableClass.getDate());
        time.setText(variableClass.getTime());

        return convertView;
    }


}
