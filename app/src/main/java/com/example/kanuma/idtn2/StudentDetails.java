package com.example.kanuma.idtn2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class StudentDetails extends AppCompatActivity {

    private Vibrator vib;
    Animation animShake;
    private EditText firstName, lastName, collegeName,emailName;
    private TextInputLayout firstNameLayout, lastNameLayout, collegeNameLayout,emailLayout;
    private Button btnProceed;
    private ImageView profileImage;
    private  FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        //TextInputLayout
        firstNameLayout =findViewById(R.id.firstname_layout);
        lastNameLayout=findViewById(R.id.lastname_layout);
        collegeNameLayout=findViewById(R.id.college_name_layout);
        emailLayout=findViewById(R.id.email_layout);

        //EditText
        firstName =findViewById(R.id.input_first_name);
        lastName=findViewById(R.id.input_last_name);
        collegeName=findViewById(R.id.college_name);
        emailName=findViewById(R.id.email_input);

        //Button
        btnProceed=findViewById(R.id.btnProceed);

        //ImageView
        profileImage=findViewById(R.id.profileImage);


        //Firebase
        FirebaseAuthentication();


        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }


    public void FirebaseAuthentication()
    {
        auth= FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                if(user !=null)
                {

                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();

                    Log.i("Acc","Name"+name);
                    Log.i("Acc","Email"+email);
                    Log.i("Acc","Photo url"+photoUrl);


                    firstName.setText(name);
                    emailName.setText(email);

                    if(photoUrl!=null)
                    {
                        Picasso.with(StudentDetails.this).load(photoUrl).into(profileImage);
                    }


                }else
                {
                    Log.i("LogOut","User is unavailable");
                }
            }
        });


    }



    private void submitForm()
    {

        if (!checkName(firstName,firstNameLayout)) {
            firstName.setAnimation(animShake);
            firstName.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (!checkName(lastName,lastNameLayout)) {
            lastName.setAnimation(animShake);
            lastName.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }

        if (!checkName(collegeName,collegeNameLayout)) {
            collegeName.setAnimation(animShake);
            collegeNameLayout.startAnimation(animShake);
            vib.vibrate(120);
            return;
        }


        Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();

    }

    private boolean checkName(EditText tv,TextInputLayout til) {
        if (tv.getText().toString().trim().isEmpty()) {

            showError(til);
            return false;
        }
        til.setErrorEnabled(false);
        return true;
    }


    private void showError(TextInputLayout field)
    {
        field.setErrorEnabled(true);
        field.setError(getString(R.string.err_msg_name));
    }





}
