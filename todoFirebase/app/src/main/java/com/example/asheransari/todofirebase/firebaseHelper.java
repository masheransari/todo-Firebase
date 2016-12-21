package com.example.asheransari.todofirebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 12/19/2016.
 */

public class firebaseHelper {
    DatabaseReference db;
    Boolean saved;
    ArrayList<toDo_variableClass> variableClasses = new ArrayList<>();

    public firebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public boolean save(toDo_variableClass variableClass) {
        if (variableClass == null) {
            saved = false;
        } else {
            try {
                db.push().setValue(variableClass);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }


    private void fetchData(DataSnapshot dataSnapshot) {
        variableClasses.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            toDo_variableClass spacecraft=ds.getValue(toDo_variableClass.class);
            variableClasses.add(spacecraft);
        }
//        variableClasses.clear();
//        for (int i=0;i<dataSnapshot.;i++){}
//        toDo_variableClass item = dataSnapshot.getValue(toDo_variableClass.class);
//        variableClasses.add(item);
//
//
//
//        try {
//            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                toDo_variableClass variableClass = ds.getValue(toDo_variableClass.class);
//                variableClasses.add(variableClass);
//            }
//        Log.e("Count ", "" + dataSnapshot.getChildrenCount());
//            variableClasses.clear();
//            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                Log.e(getClass().getName(), "" + dataSnapshot.getValue());
//                toDo_variableClass post = postSnapshot.getValue(toDo_variableClass.class);

//                toDo_variableClass variable = post;
//                variableClasses.add(variable);
//                Log.e("Get Data", post.getDate());
//                Log.e("Get Data", post.getTime());
//                Log.e("Get Data", post.getTodoEdit());
//                Log.w("firebaseHelper", "datas  ==   "+post);

//        } catch (DatabaseException e) {
//            Log.e("firebase", "Exception will be = = " + e);
//        }
//        Log.e("firebaseHelper", String.valueOf(variableClasses));
        Log.e("firebaseHelper", "Size  =  " + String.valueOf(variableClasses.size()));
    }

    public ArrayList<toDo_variableClass> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return variableClasses;
    }
}
