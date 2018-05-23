package com.example.yuhaolu.behancedisplay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.model.AppUser;
import com.example.yuhaolu.behancedisplay.utils.DatabaseHelper;
import com.example.yuhaolu.behancedisplay.utils.ImageUtils;
import com.example.yuhaolu.behancedisplay.utils.InputValidation;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_scroll_view) ScrollView scrollView;
    @BindView(R.id.logo) SimpleDraweeView logo;
    @BindView(R.id.input_email_layout) TextInputLayout inputEmailLayout;
    @BindView(R.id.input_email) TextInputEditText inputEmail;
    @BindView(R.id.input_password_layout) TextInputLayout inputPasswordLayout;
    @BindView(R.id.input_password) TextInputEditText inputPassword;
    @BindView(R.id.btn_login) AppCompatButton loginButton;
    @BindView(R.id.link_register) TextView linkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private final AppCompatActivity activity = LoginActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ImageUtils.loadLogo(logo);
        initObjects();
        initListeners();
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
    }

    private void initListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(inputEmailLayout, inputEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextEmail(inputEmailLayout, inputEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(inputPasswordLayout, inputPassword, getString(R.string.error_message_password))) {
            return;
        }


        if (databaseHelper.checkUser(inputEmail.getText().toString().trim(),
                inputPassword.getText().toString().trim())) {
            Intent intent = new Intent(activity, MainActivity.class);
            emptyInputEditText();
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(scrollView, getString(R.string.error_valid_email_password),
                    Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();
        }

    }

    private void emptyInputEditText() {
        inputEmail.setText(null);
        inputPassword.setText(null);
    }
}
