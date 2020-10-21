package com.wintec.degreemap.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wintec.degreemap.Manager_Home;
import com.wintec.degreemap.R;

public class LoginActivity extends AppCompatActivity {

    EditText  mPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        mPassword = findViewById(R.id.edittext_password);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Password Should Be: WinITDMP01
                // But for our convenience, changed it to: 1
                if (mPassword.getText().toString().equals("1")) {
                    jumpTo();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void jumpTo() {
        Intent i = new Intent(this, Manager_Home.class);
        startActivity(i);
    }
}