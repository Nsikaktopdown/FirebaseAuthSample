package com.droidplate.firebaseauthsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class MainActivity extends BaseActivity {

    private EditText emailEdt, passwordEdt;
    private Button signInBtn, signUpBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**
         * View binding
         */
        emailEdt = findViewById(R.id.editTextEmail);
        passwordEdt = findViewById(R.id.editTextPassword);
        signInBtn = findViewById(R.id.btnSignIn);
        signUpBtn = findViewById(R.id.btnSignUp);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressDialog("Signing Up ...");

                String email = emailEdt.getText().toString(); // email
                String password = passwordEdt.getText().toString(); // password

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    emailEdt.setError("please enter email");
                    passwordEdt.setError("Please enter 6 digit + password");
                    hideProgresDialog();
                } else {

                    performSignUp(email, password);
                }

            }
        });


    }


    /**
     * Sign up User using Firebase
     *
     * @param email
     * @param password
     */
    private void performSignUp(String email, String password) {

        getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, " Sign up was not successful", Toast.LENGTH_SHORT).show();
                } else {

                    hideProgresDialog();
                    signUpBtn.setVisibility(View.GONE);
                }


            }
        });

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
