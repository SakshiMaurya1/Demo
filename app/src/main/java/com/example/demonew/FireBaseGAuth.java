package com.example.demonew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.annotations.Nullable;

public class FireBaseGAuth extends AppCompatActivity {
    SignInButton signin;
    GoogleSignInClient signinclient;
    private FirebaseAuth auth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_gauth);
        signin = findViewById(R.id.signin);

        auth = FirebaseAuth.getInstance();
        processrequest();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googlelogin();
            }
        });
    }

    private void processrequest() {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        signinclient = GoogleSignIn.getClient(this, options);
    }

    private void googlelogin() {
        Intent signinIn = signinclient.getSignInIntent();
        startActivityForResult(signinIn, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("Asmita==>", "data : " + data);
        Log.e("Asmita==>", "requestCode : " + requestCode);


        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Log.e("Asmita==>", "GoogleSignInAccount: " + task.getResult());

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e("TAG", "account: " + account);
                firebaseAuth(account.getIdToken());
                Log.e("TAG", "serverauth: "+account.getClass());

            } catch (Exception e) {
                Toast.makeText(FireBaseGAuth.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onActivityResult: " + e.getMessage());
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(FireBaseGAuth.this, ContactProfile.class));
                        } else {
                            Toast.makeText(FireBaseGAuth.this, "Problem logging in with Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}