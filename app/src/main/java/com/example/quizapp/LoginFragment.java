package com.example.quizapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final int RC_SIGN_IN = 1000;
    Button mlogin;
   static EditText mUsername,mPassword;;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.login_fragment, container, false);
        mlogin = (Button) rootView.findViewById(R.id.login);
        mUsername = (EditText)  rootView.findViewById(R.id.username_login);
        mPassword = (EditText)  rootView.findViewById(R.id.password_login);
        mlogin.setOnClickListener(this);

       // Configure sign-in to request the user's ID, email address, and basic
       // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
       gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
         // Configure sign-in to request the user's ID, email address, and basic
         // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = rootView.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });


        return rootView;
    }
    @Override
    public void onClick(View view) {
        if(mUsername.getText().toString().equals("admin") &&
                mPassword.getText().toString().equals("admin")) {
            Toast.makeText(getActivity(), "Redirecting", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), QuizHomePage.class);

            startActivity(i);

        }else{
           Toast.makeText(getActivity(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

            }
        }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        getActivity().finish();
        Intent intent = new Intent(getActivity(),QuizHomePage.class);
        startActivity(intent);
        Toast.makeText(getActivity(), "Redirecting", Toast.LENGTH_SHORT).show();
    }

}

