package com.example.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import java.sql.SQLException;
import android.database.SQLException;


public class MainActivity extends AppCompatActivity {

    EditText etName ,etCellNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etCellNumber = findViewById(R.id.etCellNumber);
    }

    public void btnSubmit(View v){
        String name = etName.getText().toString().trim();
        String cell = etCellNumber.getText().toString().trim();

        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.createEntry(name, cell);
            db.close();
            Toast.makeText(this, "Successfully saved!!", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etCellNumber.setText("");
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void btnShowData(View v){
        startActivity(new Intent(this, Data.class));
    }

    public void btnEditData(View v){

        try{
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.updateEntry("1", etName.getText().toString().trim(), etCellNumber.getText().toString().trim());
            db.close();
            Toast.makeText(this, "Successfully updated!!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    public void btnDeleteData(View v){

        try{
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.deleteEntry("1");
            db.close();
            Toast.makeText(this, "Successfully deleted!!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

}
