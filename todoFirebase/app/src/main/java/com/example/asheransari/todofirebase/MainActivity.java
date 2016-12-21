package com.example.asheransari.todofirebase;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String emailIntent;
    ChildEventListener mChildEventListener;
    private ListView mListView;
    toDo_adapter mAdapter;
    //firebaseHelper helper;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        emailIntent = auth.getCurrentUser().getEmail();
        emailIntent = emailIntent.replace(".", "_");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        lv = (ListView) findViewById(R.id.listView);

        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.keepSynced(true);

        final ArrayList<toDo_variableClass> list_List = new ArrayList<>();

        mAdapter = new toDo_adapter(this, R.layout.list_todo, list_List);
        lv.setAdapter(mAdapter);
        mAdapter.clear();

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            mDatabaseReference = mFirebaseDatabase.getReference("userID").child(emailIntent);
            onSignInInitialize();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.signout_menu:
//                sigout();
                Toast.makeText(this, "signout Menu Selected", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent i = new Intent(MainActivity.this, login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(MainActivity.this, "user is Sign out", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();

                break;
            case R.id.new_entery:
                Intent iT = new Intent(MainActivity.this, new_todo_data.class);
                iT.putExtra("email", emailIntent);
                startActivity(iT);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void onSignInInitialize() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Log.e("MainActivity", "" + dataSnapshot.getValue());
//                    ArrayList<toDo_variableClass> list_List = new ArrayList<>();
//                    list_List.add(dataSnapshot.getValue(toDo_variableClass.class));
//                    Log.e("MainActivity", "" + list_List);
                        toDo_variableClass variableClass = dataSnapshot.getValue(toDo_variableClass.class);
                        mAdapter.addAll(variableClass);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                public void onCancelled(DatabaseError databaseError) {
                }
            };
//            mmDatabaseReference.addChildEventListener(mChildEventListener);
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }

    }

//    public void sigout(){
////        Toast.makeText(MainActivity.this, "in null body", Toast.LENGTH_SHORT).show();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                Toast.makeText(MainActivity.this, ""+ firebaseUser.getEmail()+" name =", Toast.LENGTH_SHORT).show();
//                if (firebaseUser != null)
//                {
//                    auth.signOut();
//                    Toast.makeText(MainActivity.this, "User is Sign in", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(MainActivity.this, login.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                    Toast.makeText(MainActivity.this, "user is Sign out", Toast.LENGTH_SHORT).show();
//                    startActivity(i);
//                    finish();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "in Else body", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//    }
//
//    private  void onSignInInitialize(String uname)
//    {
//        uname = uname.replace(".","_");
//        attachedDatabaseListner();
//    }
//    private void attachedDatabaseListner()
//    {
//        if (mChildEventListener == null)
//        {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    toDo_variableClass variableClass = dataSnapshot.getValue(toDo_variableClass.class);
//                    mToDo_adapter.add(variableClass);
//                    Log.e("MainActivity",mToDo_adapter.toString());
//                    mListView.setAdapter(mToDo_adapter);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//            mDatabaseReference.addChildEventListener(mChildEventListener);
//        }
//    }
//    private void onSignoutCleanup()
//    {
//        mToDo_adapter.clear();
//        detachDataBaseReadListner();
//    }
//    private void detachDataBaseReadListner()
//    {
//        if (mChildEventListener!= null)
//        {
//            mDatabaseReference.removeEventListener(mChildEventListener);
//            mChildEventListener = null;
//        }
//    }

//    private void retrieveData()
//    {
//        mDatabaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////                list.add(dataSnapshot.getValue(toDo_variableClass.class));
////                toDo_variableClass variableClass = dataSnapshot.getValue(toDo_variableClass.class);
////                mToDo_adapter.add(variableClass);
////                mToDo_adapter.notifyDataSetChanged();
//                getUpdates(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                getUpdates(dataSnapshot);
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
////                list.clear();
////                mToDo_adapter.clear();
//////                list.add(dataSnapshot.getValue(toDo_variableClass.class));
//////                mToDo_adapter.add(list);
//////                toDo_variableClass variableClass = dataSnapshot.getValue(toDo_variableClass.class);
//////                mToDo_adapter.add(variableClass);
////                mToDo_adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//    private void getUpdates(DataSnapshot dataSnapshot)
//    {
//        mToDo_adapter.clear();
//        for (DataSnapshot data : dataSnapshot.getChildren())
//        {
////            variableClass.setTodoEdit(data.getValue(toDo_variableClass.class).getTodoEdit());
////            variableClass.setDate(data.getValue(toDo_variableClass.class).getDate());
////            variableClass.setTime(data.getValue(toDo_variableClass.class).getTime());
//            toDo_variableClass variableClass = new toDo_variableClass(data.getValue(toDo_variableClass.class).getTodoEdit(),data.getValue(toDo_variableClass.class).getDate(),data.getValue(toDo_variableClass.class).getTime());
//            list.add(variableClass);
//        }
//        if (list.size()>0)
//        {
////            ArrayAdapter adapter = new ArrayAdapter(Main`);
//            mToDo_adapter.add();
//            mListView.setAdapter(mToDo_adapter);
//        }
//        else
//        {
//            Toast.makeText(this, "No Data Found..!!", Toast.LENGTH_SHORT).show();
//        }
//    }

    private ArrayList<toDo_variableClass> retireveData(ArrayList<toDo_variableClass> arrayList) {


        return arrayList;
    }
//    private void displayInputDialog() {
//        Dialog d = new Dialog(this);
//        d.setTitle("Save To Firebase");
//        d.setContentView(R.layout.input_dialog);
//        nameEditTxt = (EditText) d.findViewById(R.id.nameEditText);
//        propTxt = (EditText) d.findViewById(R.id.propellantEditText);
//        descTxt = (EditText) d.findViewById(R.id.descEditText);
//        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);
//        //SAVE
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //GET DATA
//                String name = nameEditTxt.getText().toString();
//                String propellant = propTxt.getText().toString();
//                String desc = descTxt.getText().toString();
//                //SET DATA
//                Spacecraft s = new Spacecraft();
//                s.setName(name);
//                s.setPropellant(propellant);
//                s.setDescription(desc);
//                //SIMPLE VALIDATION
//                if (name != null && name.length() > 0) {
//                    //THEN SAVE
//                    if (helper.save(s)) {
//                        //IF SAVED CLEAR EDITXT
//                        nameEditTxt.setText("");
//                        propTxt.setText("");
//                        descTxt.setText("");
//                        adapter = new CustomAdapter(MainActivity.this, helper.retrieve());
//                        lv.setAdapter(adapter);
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        d.show();
//    }

}
