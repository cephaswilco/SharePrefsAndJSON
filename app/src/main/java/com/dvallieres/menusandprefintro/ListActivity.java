package com.dvallieres.menusandprefintro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    EditText fruitText;
    EditText quantityText;
    Button removeItem;
    Button addItem;
    Gson gson;
    SingletonExample singleton;

    final String listName = "my_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView itemListView = findViewById(R.id.item_list_view);

        fruitText = findViewById(R.id.fruitName);
        quantityText = findViewById(R.id.fruitQuantity);
        removeItem = findViewById(R.id.remove_item_button);
        addItem = findViewById(R.id.add_item_button);

        singleton = SingletonExample.getInstance(getApplicationContext());

        prefs = singleton.getPreferences();
        editor = prefs.edit();

        // JSON RELATED
        gson = new Gson();

        TextView arrayAsString = findViewById(R.id.array_as_string);
        ArrayList<SpecialObject> specialObjects = new ArrayList<SpecialObject>();

        // JSON RELATED
        String jsonString = gson.toJson(specialObjects);
        String savedJson = prefs.getString("my_list", null);

        //arrayAsString.setText(savedJson);
        ArrayList<SpecialObject> arrayList = new ArrayList<SpecialObject>();

        // JSON RELATED
        Type listOfMyClassObject = new TypeToken<ArrayList<SpecialObject>>() {}.getType();
        arrayList = gson.fromJson(savedJson, listOfMyClassObject);

        // Create an ArrayAdapter from List
        SpecialObjectAdapter arrayAdapter = new SpecialObjectAdapter
                (this, arrayList);

        // DataBind ListView with items from ArrayAdapter
        itemListView.setAdapter(arrayAdapter);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<SpecialObject> data = arrayAdapter.AddItem(new SpecialObject(Integer.parseInt(quantityText.getText().toString()), fruitText.getText().toString()));
                    SaveDataToPreferences(data);
                }
                catch(Exception e){

                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SpecialObject> data = arrayAdapter.RemoveLastItem();
                SaveDataToPreferences(data);
            }
        });



        fruitText.setText(singleton.getText());



    }



    void SaveDataToPreferences(ArrayList<SpecialObject> data){
        // JSON RELATED
        String jsonString = gson.toJson(data);

        // JSON RELATED
        editor.putString(listName, jsonString);
        editor.apply();
    }

}





/*
[ {"firstName": "bob","lastName": "parker",
    "phoneNumber": "5552522",
    },
    {
    "firstName": "bob",
    "lastName": "parker",
    "phoneNumber": "5552522",
    },
    {
    "firstName": "bob",
    "lastName": "parker",
    "phoneNumber": "5552522",
    }
]
*/




/*

class PhoneNumber {

    String firstName;
    String lastName;
    int phoneNumber;



    public String FullName(){
        return firstName + " " + lastName;
    }

}



*/
/*

{
    "firstName": "david",
    "lastName": "vallieres",
    "phoneNumber": "5555252",
}


*//*

*/
/*


{
    "firstName": "bob",
    "lastName": "parker",
    "phoneNumber": "5552522",
}


*//*



*/


















