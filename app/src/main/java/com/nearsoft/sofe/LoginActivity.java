package com.nearsoft.sofe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.nearsoft.sofe.map.view.MapsActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    //TextViews
    private TextView mEmailTextView;

    //Buttons
    private Button mLoginButton;
    private Button mRegisterButton;

    //ViewGroups
    private ConstraintLayout mConstraintLayout; //Parent View
    private LinearLayout mUserLinearLayout;

    //EditTexts
    private EditText mEmailEditText;
    private EditText mUserNameEditText;
    private EditText mUserPassword;

    //CardView
    private CardView mCredentialsCardView;
    private CardView mInvalidLoginPopup;

    private FirebaseAuth mFirebaseAuth;


    private ConstraintSet mOriginalConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        setActivityMembers();
        setListeners();
        mOriginalConstraintSet.clone(mConstraintLayout);
    }

    @Override
    public void onBackPressed() {
        if (mCredentialsCardView.getVisibility() == View.VISIBLE) closeCredentialBox();
        else super.onBackPressed();
    }

    private void setActivityMembers() {
        mLoginButton = findViewById(R.id.loginButton);
        mRegisterButton = findViewById(R.id.registerButton);
        mEmailEditText = findViewById(R.id.emailEditText);
        mUserNameEditText = findViewById(R.id.userNameEditText);
        mUserPassword = findViewById(R.id.passwordEditText);
        mCredentialsCardView = findViewById(R.id.credentialsCardView);
        mConstraintLayout = findViewById(R.id.constraintLayout);
        mUserLinearLayout = findViewById(R.id.userLinearLayout);
        mEmailTextView = findViewById(R.id.emailTextView);
        mInvalidLoginPopup = findViewById(R.id.invalidLoginPopup);
    }

    private void setListeners() {
        mLoginButton.setOnClickListener((View v) -> {
            if (mCredentialsCardView.getVisibility() == View.VISIBLE) {
                loginUser();
            }
            showCredentialsBox(v.getId());
        });

        mRegisterButton.setOnClickListener((View v) -> {
            if (mCredentialsCardView.getVisibility() == View.VISIBLE) {
                registerUser();
            }
            showCredentialsBox(v.getId());
        });
    }

    private void loginUser() {
        //FIXME: user needs to login with the email, although the UI uses the username.
        if (!mEmailEditText.getText().toString().isEmpty() && !mUserPassword.getText().toString().isEmpty()){
            mFirebaseAuth.signInWithEmailAndPassword(mEmailEditText.getText().toString(), mUserPassword.getText().toString()).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Intent mapsIntent = new Intent(this, MapsActivity.class);
                    startActivity(mapsIntent);
                } else {
                    showInvalidLoginPopup();
                }
            });

        }
    }

    private void registerUser() {
        //FIXME: What happens if the required fields are empty?
        if(!mEmailEditText.getText().toString().isEmpty() && !mUserPassword.getText().toString().isEmpty()){
            mFirebaseAuth.createUserWithEmailAndPassword(mEmailEditText.getText().toString(), mUserPassword.getText().toString()).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Intent mapsIntent = new Intent(this, MapsActivity.class);
                    startActivity(mapsIntent);
                } else {
                    Log.d("ERROR", Objects.requireNonNull(task.getException()).getMessage());
                    showInvalidSignUpPopup();
                }
            });

        }
    }

    private void showInvalidLoginPopup() {
        //FIXME: the message is showing invalid login also for registration but should be a different message.
        ConstraintSet popupConstraintSet = new ConstraintSet();
        popupConstraintSet.clone(mConstraintLayout);
        popupConstraintSet.clear(R.id.invalidLoginPopup, ConstraintSet.BOTTOM);
        popupConstraintSet.connect(R.id.invalidLoginPopup, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        popupConstraintSet.applyTo(mConstraintLayout);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            popupConstraintSet.clear(R.id.invalidLoginPopup, ConstraintSet.TOP);
            int bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            popupConstraintSet.connect(R.id.invalidLoginPopup, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP, bottomMargin);
            popupConstraintSet.applyTo(mConstraintLayout);
        }, 5000);
    }
    private void showInvalidSignUpPopup() {
        //FIXME: the message is showing invalid login also for registration but should be a different message.
        ConstraintSet popupConstraintSet = new ConstraintSet();
        popupConstraintSet.clone(mConstraintLayout);
        popupConstraintSet.clear(R.id.invalidSignUpPopup, ConstraintSet.BOTTOM);
        popupConstraintSet.connect(R.id.invalidSignUpPopup, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        popupConstraintSet.applyTo(mConstraintLayout);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            popupConstraintSet.clear(R.id.invalidSignUpPopup, ConstraintSet.TOP);
            int bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            popupConstraintSet.connect(R.id.invalidSignUpPopup, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP, bottomMargin);
            popupConstraintSet.applyTo(mConstraintLayout);
        }, 5000);
    }

    private void showCredentialsBox(int upperViewId) {
        if (upperViewId != R.id.loginButton && upperViewId != R.id.registerButton) return;
        //We need the upper view Id to know if we are logging in or registering
        ConstraintSet cardConstraints = new ConstraintSet();
        cardConstraints.clone(mConstraintLayout);
        cardConstraints.clear(R.id.credentialsCardView, ConstraintSet.TOP);
        int cardViewTopMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        cardConstraints.connect(R.id.credentialsCardView, ConstraintSet.TOP, upperViewId, ConstraintSet.BOTTOM, cardViewTopMargin);
        cardConstraints.setVisibility(upperViewId == R.id.loginButton ? R.id.registerButton : R.id.loginButton, View.GONE);
        if (upperViewId == R.id.registerButton) {
            cardConstraints.clear(R.id.registerButton, ConstraintSet.TOP);
            int registerBtnTopMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 357, getResources().getDisplayMetrics());
            cardConstraints.connect(R.id.registerButton, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, registerBtnTopMargin);
            mUserLinearLayout.setVisibility(View.VISIBLE);
            mEmailTextView.setText("Correo");
        } else {
            mUserLinearLayout.setVisibility(View.GONE);
            mEmailTextView.setText("Correo");
        }
        cardConstraints.setVisibility(R.id.credentialsCardView, View.VISIBLE);
        cardConstraints.applyTo(mConstraintLayout);
    }

    private void closeCredentialBox() {
        mOriginalConstraintSet.applyTo(mConstraintLayout);
    }
}
