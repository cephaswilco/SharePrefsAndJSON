package com.dvallieres.menusandprefintro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button middleButton = findViewById(R.id.returnButton);
        TextView intentData = findViewById(R.id.intentData);

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.putExtra("special", "This could have been a grocery item");
                setResult(RESULT_OK, intent);
                finish();


            }
        });

        Bundle data = getIntent().getExtras();
        SpecialObject student = (SpecialObject) data.getParcelable("student");
        String whatStringIsThis = (String) data.getString("banana");


        intentData.setText(whatStringIsThis);

    }
}