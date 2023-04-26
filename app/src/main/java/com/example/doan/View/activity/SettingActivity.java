package com.example.doan.View.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.doan.R;

public class SettingActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword ;
    private Button btn ;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etName = findViewById(R.id.nameEt_SettingsPage);
        etPassword = findViewById(R.id.EmailEt_SettingsPage);

        btn = findViewById(R.id.saveSetting_SettingsBtn);

        back = findViewById(R.id.backIv_ProfileFrag);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}