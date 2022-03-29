package com.dvallieres.menusandprefintro;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> someActivityResultLauncher;

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    String retrieved;

    TextView middleTextView;
    EditText middleEditText;

    SingletonExample singleton;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleton = SingletonExample.getInstance(getApplicationContext());
        singleton.setText("a banana");

        middleTextView = findViewById(R.id.firstString);
        middleEditText = findViewById(R.id.editTextTextPersonName);
        Button middleButton = findViewById(R.id.middleButton);

        // This sets the default values of my preferences if they don't already have a user specified state
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);

        // Gives me access to the preferences object
        prefs = singleton.getPreferences();
        // Gives me the Editing object which allows me to edit my preferences
        editor = prefs.edit();

        boolean syncBool = prefs.getBoolean("sync", true);
        retrieved = prefs.getString("testString", "not set");

        String savedJson = prefs.getString("my_list", null);

        middleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Example of saving a preference not using the preferences view
                editor.putString("testString", middleEditText.getText().toString());
                editor.apply();

                retrieved = prefs.getString("testString", "not set");
                middleTextView.setText(retrieved);
            }

        });

        // Setting up an activity result launcher to take a callback which allows you to pass
        // data from the child to the parent
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();

                            // Grabs the result and stores it into the preferences and sets the textview
                            String strEditText = data.getStringExtra("editTextValue");
                            editor.putString("testString", data.getStringExtra("special"));
                            editor.apply();

                            retrieved = prefs.getString("testString", "not set");
                            middleTextView.setText(retrieved);
                        }
                    }



                });


        middleTextView.setText(retrieved);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.menu_about:

                // Setting up the intent to navigate
                Intent intent = new Intent
                        (
                            getApplicationContext(),
                            AboutActivity.class
                        );

                SpecialObject specialObject = new SpecialObject(5, "apple");

                // Put data in the intent to pass to the other activity
                intent.putExtra("student",  specialObject);

                retrieved = prefs.getString("testString", "not set");
                intent.putExtra("banana", retrieved);

                someActivityResultLauncher.launch(intent);

                break;

            case R.id.menu_settings:
                // CODE
                startActivity(new Intent(
                        getApplicationContext(),
                        SettingsActivity.class
                ));

                break;

            case R.id.menu_list:
                // CODE
                startActivity(new Intent(
                        getApplicationContext(),
                        ListActivity.class
                ));

                break;

        }
        return true;
    }

}