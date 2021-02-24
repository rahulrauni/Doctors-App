package com.example.doctest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText docnameText, docprofText, descriptionText;
    Button uploadButton,pendingAppointButton,confirmAppointButton;
    DatabaseReference mref;
    RadioGroup availabilitygroupButton;
    RadioButton categorybutton,avaialbleButton, notAvailableButton;

    private static final int MY_REQUEST_CODE = 1781;
    List<AuthUI.IdpConfig> providers;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (user == null) {
            signIn();
        }
        else{
            Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
        }

        docnameText = findViewById(R.id.docnameText);
        docprofText = findViewById(R.id.docprofText);
        descriptionText = findViewById(R.id.descriptionText);
        uploadButton = findViewById(R.id.uploadButton);
        availabilitygroupButton = findViewById(R.id.availabilitygroupButton);
        pendingAppointButton = findViewById(R.id.pendingAppointButton);
        confirmAppointButton = findViewById(R.id.confirmAppointButton);
        // radiogroup
//        int selectedId=availabilitygroupButton.getCheckedRadioButtonId();
//        categorybutton = (RadioButton)findViewById(selectedId);
//        String availability = categorybutton.getText().toString();


        mref = FirebaseDatabase.getInstance().getReference("Doctor");
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //radiogroup
                int selectedId= availabilitygroupButton.getCheckedRadioButtonId();
                categorybutton = findViewById(selectedId);
                String availability = String.valueOf(categorybutton.getText());

                String docName = docnameText.getText().toString();
                String docProf = docprofText.getText().toString();
                String descriptiondoc = descriptionText.getText().toString();
                //String key = mref.push().getKey();
                DatabaseReference docdetail = mref.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                docdetail.child("docName").setValue(docName);
                docdetail.child("docProf").setValue(docProf);
                docdetail.child("descriptionDoc").setValue(descriptiondoc);
                docdetail.child("dockey").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                docdetail.child("availability").setValue(availability);
                startActivity(new Intent(MainActivity.this, docDetails.class));
            }
        });
        pendingAppointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AppointmentActivity.class));

            }
        });
    }

    private void signIn() {
        //Init providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        showSignInOptions();
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .setTheme(R.style.MyTheme)
                        .build(),MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) ;
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                //Get user
                user = FirebaseAuth.getInstance().getCurrentUser();
                //Show user email on Toast
                Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
