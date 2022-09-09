package com.example.quizapp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText mName,mEmail,mPassword,mRetyppePassword;
    Button mRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.register_fragment, container, false);
        mName = (EditText) rootView.findViewById(R.id.email_register);
        mEmail = (EditText) rootView.findViewById(R.id.email_register);
        mPassword = (EditText) rootView.findViewById(R.id.password_register);
        mRetyppePassword = (EditText) rootView.findViewById(R.id.re_password_register);
        mRegister = (Button) rootView.findViewById(R.id.register_button);
        mRegister.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
         saveUserData();
    }

    public void saveUserData(){
        String nameString = mName.getText().toString();
        String emailString = mEmail.getText().toString();
        String passwordString = mPassword.getText().toString();
        String retypePassString = mRetyppePassword.getText().toString();


        if(passwordString.equals(retypePassString)){
            Uri newUri =null;

            if (newUri == null) {

                Toast.makeText(getActivity(), "Resgitered" + newUri, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Have a problem"+newUri, Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getActivity(), "password is not same", Toast.LENGTH_SHORT).show();
        }
    }
}
