package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uriCall = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(uriCall, null, null, null, null);
        ListView cpListView = findViewById(R.id.cpListview);
        //@SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        //@SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        String[] columnNames = new String[]{CallLog.Calls.NUMBER, CallLog.Calls.DURATION};
        int[] textViews = new int[]{android.R.id.text1, android.R.id.text2};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columnNames, textViews);
        cpListView.setAdapter(cursorAdapter);
    }
}