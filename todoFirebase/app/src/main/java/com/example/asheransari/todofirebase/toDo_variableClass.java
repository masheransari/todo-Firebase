package com.example.asheransari.todofirebase;

/**
 * Created by asher.ansari on 12/17/2016.
 */

public class toDo_variableClass {
    private String todoEdit;
    private String date;
    private String time;

    public toDo_variableClass() {
    }

    public toDo_variableClass(String todoEdit, String date, String time) {
        this.todoEdit = todoEdit;
        this.date = date;
        this.time = time;
    }


    public void setTodoEdit(String todoEdit) {
        this.todoEdit = todoEdit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTodoEdit() {
        return todoEdit;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
