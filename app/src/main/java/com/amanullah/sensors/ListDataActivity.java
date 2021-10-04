package com.amanullah.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mdatabaseHelper;

    private ListView mlistView;
    ArrayList<User> userArrayList;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        mlistView = findViewById(R.id.listView);
        mdatabaseHelper = new DatabaseHelper(this);
        userArrayList = new ArrayList<>();

        Cursor data = mdatabaseHelper.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(getApplicationContext(),"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                user = new User(data.getString(1), data.getString(2),
                        data.getString(3), data.getString(4));
                userArrayList.add(i,user);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+
                        data.getString(3)+" "+data.getString(4));
                System.out.println(userArrayList.get(i).getLightSensor());
                i++;
            }
            FourColumnListAdapter adapter =  new FourColumnListAdapter(this,R.layout.list_adapter_view, userArrayList);
            mlistView = (ListView) findViewById(R.id.listView);
            mlistView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}