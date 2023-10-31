package com.example.socialmedia.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.MainActivity;
import com.example.socialmedia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {


    private EditText emailEdt, passwordEdt;
    private TextView signUpTv, forgotPasswordTv;
    private Button loginBtn, googleSignUpBtn, facebookSignUpBtn;
    private ProgressBar progressBar;
    private CheckBox rememberCheckBox;

    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();

    }

    private void clickListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                clickLogin();
            }
        });

        googleSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickGoogleLogin();
            }
        });

        facebookSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFacebookLogin();
            }
        });

    }

    private void clickFacebookLogin() {
    }

    private void clickGoogleLogin() {
    }

    private void clickLogin() {
        String email = emailEdt.getText().toString();
        String pass = passwordEdt.getText().toString();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (!user.isEmailVerified()) {
                                Toast.makeText(getContext(), "Please verify your email", Toast.LENGTH_SHORT).show();
                            }

                            sendUserToMainActivity();

                        }
                    }
                });
    }

    private void sendUserToMainActivity() {

        startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));
        getActivity().finish();
    }

    private void init(View view) {
        emailEdt = view.findViewById(R.id.emailLIEdt);
        passwordEdt = view.findViewById(R.id.passwordLIEdt);
        rememberCheckBox = view.findViewById(R.id.checkboxRememberPass);
        signUpTv = view.findViewById(R.id.tvSignUp);
        forgotPasswordTv = view.findViewById(R.id.tvForgotPassword);
        facebookSignUpBtn = view.findViewById(R.id.facebookLoginBtn);
        googleSignUpBtn = view.findViewById(R.id.googleLoginBtn);
        loginBtn = view.findViewById(R.id.loginBtn);

        progressBar = view.findViewById(R.id.progress_login);


        mAuth = FirebaseAuth.getInstance();

    }
}