package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    EditText user_name,user_email;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name = (EditText) findViewById(R.id.user_name_editText);
        user_email = (EditText) findViewById(R.id.user_email_editText);
        saveButton = (Button) findViewById(R.id.save_info);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            user_name.setText(personName);
            user_email.setText(personEmail);

            Log.i("Tag","have data");


            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Editable newName = user_name.getText();
                    Editable newEmail = user_email.getText();

                    user_name.setText(newName);
                    user_email.setText(newEmail);
                }
            });
        }

    }
}