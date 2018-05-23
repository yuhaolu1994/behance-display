package com.example.yuhaolu.behancedisplay;

import android.graphics.Color;
import android.net.Uri;
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
import com.example.yuhaolu.behancedisplay.utils.InputValidation;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_scroll_view) ScrollView scrollView;
    @BindView(R.id.app_user_picture) SimpleDraweeView appUserPicture;
    @BindView(R.id.input_name_layout) TextInputLayout inputNameLayout;
    @BindView(R.id.input_name) TextInputEditText inputName;
    @BindView(R.id.input_email_layout) TextInputLayout inputEmailLayout;
    @BindView(R.id.input_email) TextInputEditText inputEmail;
    @BindView(R.id.input_password_layout) TextInputLayout inputPasswordLayout;
    @BindView(R.id.input_password) TextInputEditText inputPassword;
    @BindView(R.id.confirm_password_layout) TextInputLayout confirmPasswordLayout;
    @BindView(R.id.confirm_password) TextInputEditText confirmPassword;
    @BindView(R.id.btn_register) AppCompatButton registerButton;
    @BindView(R.id.link_login) TextView linkLogin;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private AppUser user;
    private Snackbar snackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        appUserPicture.setImageURI(Uri.parse("res:/" + R.drawable.user_logo));
        initObjects();
        initListeners();
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        databaseHelper = new DatabaseHelper(this);
        user = new AppUser();
    }

    private void initListeners() {

        appUserPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(inputNameLayout, inputName, getString(R.string.error_message_name))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(inputEmailLayout, inputEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextEmail(inputEmailLayout, inputEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(inputPasswordLayout, inputPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (!inputValidation.isInputEditTextMatches(inputPassword, confirmPassword, confirmPasswordLayout, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(inputEmail.getText().toString().trim())) {
            user.setName(inputName.getText().toString().trim());
            user.setEmail(inputEmail.getText().toString().trim());
            user.setPassword(inputPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            snackbar = Snackbar.make(scrollView, getString(R.string.success_message),
                    Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();
            emptyInputEditText();
            finish();
        } else {
            snackbar = Snackbar.make(scrollView, getString(R.string.error_email_exists),
                    Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();
        }

    }

    private void emptyInputEditText() {
        inputName.setText(null);
        inputEmail.setText(null);
        inputPassword.setText(null);
        confirmPassword.setText(null);
    }
}
