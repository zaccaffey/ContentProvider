package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.contentprovider.database.DbAccessObj;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPassword;
    TextView conTextView, tvContact;
    DbAccessObj entryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName); // instantiation
        etPassword = findViewById(R.id.etPassword);
        conTextView = findViewById(R.id.tvContact);
        tvContact = findViewById(R.id.tvContact);

        // define a DAO object for accessing the database
        entryDao = new DbAccessObj(this);

        // open the database
        entryDao.openDb();

        /* THIS IS CODE FOR CONTENT PROVIDERS
        Uri uriCall = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(uriCall, null, null, null, null);
        //ListView cpListView = findViewById(R.id.cpListview);
        //@SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        //@SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        String[] columnNames = new String[]{CallLog.Calls.NUMBER, CallLog.Calls.DURATION};
        int[] textViews = new int[]{android.R.id.text1, android.R.id.text2};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columnNames, textViews);
        //cpListView.setAdapter(cursorAdapter);*/
    }

    public void dbHandler(View view) {
        switch(view.getId()) {
            case R.id.btnCommit:
                saveDb();
                break;
            case R.id.btnRetreive:
                loadDb();
                break;

        }
    }

    private void saveDb() {
        String title = etName.getText().toString();
        String subtitle = etPassword.getText().toString();
        entryDao.createRow(title, subtitle);
    }

    private void loadDb() {
        tvContact.setText(entryDao.readRow());
    }
}