package com.example.asheransari.todofirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class new_todo_data extends AppCompatActivity {

    private Button mSaveButton;
    private EditText detailText;
    private String date=null ,time=null;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
//    private FirebaseAuth mAuth;
    firebaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo_data);

        Intent i = getIntent();
        final String emial = i.getStringExtra("email");
        Toast.makeText(this, ""+emial, Toast.LENGTH_SHORT).show();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("userID").child(emial);
//        mDatabaseReference = mFirebaseDatabase.getReference("userID").child(emial);
        mSaveButton = (Button)findViewById(R.id.saveToDoBtn);
        detailText = (EditText)findViewById(R.id.edit_todo);

        helper = new firebaseHelper(mDatabaseReference);

//        final DatabaseReference finalMDatabaseReference = mDatabaseReference;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempData = detailText.getText().toString();
                if (TextUtils.isEmpty(tempData))
                {
                    Toast.makeText(new_todo_data.this, "Please Enter The Todo Detail First..", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    toDo_variableClass variableClass = new toDo_variableClass(tempData,dateCurrent(),timeCurrent());
                    mDatabaseReference.push().setValue(variableClass);
//                    mDatabaseReference.child(userId1).push().setValue("temp");
                    if (helper.save(variableClass))
                    {
                        Intent i = new Intent(new_todo_data.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });

    }

    public static String dateCurrent() {
        SimpleDateFormat dfDate_day = new SimpleDateFormat("E, dd/MM/yyyy");
        Calendar cD = Calendar.getInstance();
        return dfDate_day.format(cD.getTime());

    }
    public static String timeCurrent() {
        SimpleDateFormat dfDate_time = new SimpleDateFormat("HH:mm:ss a");
        Calendar cT = Calendar.getInstance();

        return  dfDate_time.format(cT.getTime());
    }
}
